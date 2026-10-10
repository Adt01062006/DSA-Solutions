class Solution {
    public ListNode deleteMiddle(ListNode head) {
        if(head==null || head.next==null){
            return null;
        }
        ListNode temp=head;
        int cnt=0;
        while(temp!=null){
            cnt++;
            temp=temp.next;
        } 
        int mid=cnt/2;
        temp=head;
        for(int i=0;i<mid-1;i++){
            temp=temp.next;
        }
        temp.next=temp.next.next;
        return head;
    }
}
class Solution{
    public ListNode deleteMiddle(ListNode head){
        if(head==null || head.next==null){
            return null;
        }
        ListNode prev=null;
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null){
            prev=slow;
            slow=slow.next;
            fast=fast.next.next;
        }
        prev.next=slow.next;
        return head;
    }
}