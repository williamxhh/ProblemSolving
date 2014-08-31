package learn.interview_questions;

public class ReverseLinkedList {
	public static ListNode reverseList(ListNode head) {
	    if(head == null) {
	        return head;
	    }
	    
	    ListNode pre = null;
	    ListNode node = head;
	    ListNode next = null;
	    
	    while (node != null) {
	        next = node.next;
	        node.next = pre;
	        pre = node;
	        node = next;
	    }
	    
	    return pre;
	}
	
	public static void printList(ListNode head) {
		if (head == null) {
			System.out.println("Null head");
		} else {
			while (head != null) {
				System.out.print(head.val + " ");
				head = head.next;
			}
		}
	}
	
	public static void main(String[] args) {
		test1();
		System.out.println();
		test2();
		System.out.println();
		test3();
	}
	
	public static void test1(){
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		ListNode l5 = new ListNode(5);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		
		printList(l1);
		System.out.println();
		printList(reverseList(l1));
	}
	
	public static void test2(){
		ListNode l1 = new ListNode(1);
		
		printList(l1);
		System.out.println();
		printList(reverseList(l1));
	}
	
	public static void test3() {
		printList(null);
		System.out.println();
		printList(reverseList(null));
	}
}
