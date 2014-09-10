package learn.interview_questions;

/**
 * 
 * @author Xiaowei GAO
 * @date 2014年9月10日
 * @description 将二叉搜索树转换为双向链表
 * @ClassName BinarySearchTree2DoubleLinkedList
 *
 */
class Node {
	int value;
	Node left;
	Node right;
	public Node(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
}

public class BinarySearchTree2DoubleLinkedList {
	Node lastNodeInList = null;
	
	public Node convert(Node root) {
		if (root == null) {
			return null;
		}
		
		convert(root.left);
		
		root.left = lastNodeInList;
		if(lastNodeInList != null) {
			lastNodeInList.right = root;
		}
		
		lastNodeInList = root;
		
		convert(root.right);
		
		Node head = lastNodeInList;
		while(head.left != null) {
			head = head.left;
		}
		
		return head;
	}
	
	public static void main(String[] args) {
		BinarySearchTree2DoubleLinkedList instance = new BinarySearchTree2DoubleLinkedList();
		/**
		 *         10
		 *        /  \
		 *       6   14
		 *     /  \  / \
		 *    4   8 12  16
		 */
		Node n10 = new Node(10);
		
		Node n6 = new Node(6);
		Node n4 = new Node(4);
		Node n8 = new Node(8);
		
		Node n14 = new Node(14);
		Node n12 = new Node(12);
		Node n16 = new Node(16);
		
		n10.left = n6;
		n10.right = n14;
		n6.left = n4;
		n6.right = n8;
		n14.left = n12;
		n14.right = n16;
		
		Node head = instance.convert(n10);
		
		while(head != null) {
			System.out.println(head.value);
			head = head.right;
		}
	}
}
