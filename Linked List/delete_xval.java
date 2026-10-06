class Solution {
    public ListNode deleteNodeWithValueX(ListNode head, int X) {
        if(head==null){
            return null;
        }
        if(head.data==X){
            return head.next;
        }
        ListNode temp=head;
        while(temp.next!=null){
            if(temp.next.data==X){
                temp.next=temp.next.next;
                break;
            }
            temp=temp.next;
        }
        return head;
    }
}