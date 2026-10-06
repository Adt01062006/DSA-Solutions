class Solution {
    public ListNode insertAtKthPosition(ListNode head, int X, int K) {
        ListNode newnode=new ListNode(X);
        if(K==1){
            newnode.next=head;
            return newnode;
        }
        ListNode temp=head;
        for(int i=1;i<K-1 && temp!=null;i++){
            temp=temp.next;
        }
        if(temp!=null){
            newnode.next=temp.next;
            temp.next=newnode;
        }
        return head;
    }
}