class Solution {
    public ListNode deleteKthNode(ListNode head, int k) {
        if(head==null){
            return null;
        }
        if(k==1){
            return head.next;
        }
        ListNode temp=head;
        for(int i=1;i<k-1;i++){
            temp=temp.next;
        }
        temp.next=temp.next.next;
        return head;
    }
}
