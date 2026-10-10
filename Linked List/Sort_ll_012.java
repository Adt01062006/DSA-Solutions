class Solution {
    public ListNode sortList(ListNode head) {
        ListNode zero=new ListNode(-1);
        ListNode one=new ListNode(-1);
        ListNode two=new ListNode(-1);
        ListNode z=zero,o=one,t=two;
        ListNode temp=head;
        while(temp!=null){
            if(temp.data==0){
                z.next=temp;
                z=z.next;
            }else if(temp.data==1){
                o.next=temp;
                o=o.next;
            }else{
                t.next=temp;
                t=t.next;
            }
            temp=temp.next;
        }
        z.next=one.next;
        o.next=two.next;
        t.next=null;
        if(one.next!=null){
            z.next=one.next;
        }else{
            z.next=two.next;
        }
        return zero.next;
    }
}