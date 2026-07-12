import os
import boto3
import pymysql

# AWS clients
s3 = boto3.client("s3")

# Environment variables (set these in Lambda later)
DB_HOST = os.environ["DB_HOST"]
DB_USER = os.environ["DB_USER"]
DB_PASSWORD = os.environ["DB_PASSWORD"]
DB_NAME = "backupsystem"
BUCKET_NAME = os.environ["BUCKET_NAME"]


def lambda_handler(event, context):
    connection = pymysql.connect(
        host=DB_HOST,
        user=DB_USER,
        password=DB_PASSWORD,
        database=DB_NAME,
        cursorclass=pymysql.cursors.DictCursor
    )

    try:
        with connection.cursor() as cursor:

            # Read backup preferences
            cursor.execute(
                "SELECT source_prefix, schedule_minutes FROM backup_preferences LIMIT 1"
            )
            pref = cursor.fetchone()

            source_prefix = pref["source_prefix"]
            backup_prefix = "backups/"

            # Create backup job
            cursor.execute(
                """
                INSERT INTO backup_jobs(trigger_type,status)
                VALUES(%s,%s)
                """,
                ("manual", "running"),
            )

            job_id = connection.insert_id()
            connection.commit()

            # List S3 files
            response = s3.list_objects_v2(
                Bucket=BUCKET_NAME,
                Prefix=source_prefix
            )

            if "Contents" in response:
                for obj in response["Contents"]:

                    key = obj["Key"]

                    if key.endswith("/"):
                        continue

                    filename = key.split("/")[-1]

                    backup_key = backup_prefix + filename

                    # Copy file
                    s3.copy_object(
                        Bucket=BUCKET_NAME,
                        CopySource={
                            "Bucket": BUCKET_NAME,
                            "Key": key,
                        },
                        Key=backup_key,
                    )

                    # Store record
                    cursor.execute(
                        """
                        INSERT INTO backup_files
                        (job_id,file_name,s3_key,file_size_bytes)
                        VALUES(%s,%s,%s,%s)
                        """,
                        (
                            job_id,
                            filename,
                            backup_key,
                            obj["Size"],
                        ),
                    )

            cursor.execute(
                """
                UPDATE backup_jobs
                SET status='success',
                    completed_at=NOW()
                WHERE id=%s
                """,
                (job_id,),
            )

            connection.commit()

        return {
            "statusCode": 200,
            "body": "Backup completed successfully."
        }

    except Exception as e:
        connection.rollback()
        return {
            "statusCode": 500,
            "body": str(e)
        }

    finally:
        connection.close()