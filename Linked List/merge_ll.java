class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode temp1=list1;
        ListNode temp2=list2;
        ListNode dummy=new ListNode(-1);
        ListNode tail=dummy;
        while(temp1!=null && temp2!=null){
            if(temp1.val<=temp2.val){
                tail.next=temp1;
                temp1=temp1.next;
            }else{
                tail.next=temp2;
                temp2=temp2.next;
            }
            tail=tail.next;
        }
        if(temp1!=null){
            tail.next=temp1;
        }else{
            tail.next=temp2;
        }
        return dummy.next;
    }
}