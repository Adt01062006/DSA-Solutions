class Solution {
    public ListNode insertAtHead(ListNode head, int X) {
        ListNode newNode= new ListNode(X);
        newNode.next=head;
        return newNode;
    }
}