package learn.interview_questions;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Xiaowei GAO
 * @date 2014年8月31日
 * @description 
 * 如果是二叉搜索树，找到第一个值介于两个值之间的节点，就是最近公共祖先
 * 如果是多叉树
 *     如果有指向父节点的指针的话，可以转化为这两个节点到根节点的两条链表，然后求两个链表的第一个公共节点（未实现）
 *     如果没有指向父节点的指针，先用先序遍历的方式找到根节点到两个节点的路径，然后找到两条链表的最后一个公共节点
 * @ClassName MultiChildrenTreeNode
 *
 */
class MultiChildrenTreeNode {
	int val;
	ArrayList<MultiChildrenTreeNode> children;
	
	public MultiChildrenTreeNode(int val) {
		this.val = val;
		children = new ArrayList<MultiChildrenTreeNode>();
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.val);
	}
}

class BinarySearchTreeNode {
	int val;
	BinarySearchTreeNode left;
	BinarySearchTreeNode right;
	
	public BinarySearchTreeNode(int val) {
		this.val = val;
		left = null;
		right = null;
	}
	
	public BinarySearchTreeNode(int val, BinarySearchTreeNode left, BinarySearchTreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return String.valueOf(this.val);
	}
	
	
}

public class LastCommonParent {
	public static void testBST(){
		/**
		 *             5
		 *           /   \
		 *          3     8
		 *         / \   / \
		 *        2   4 7   9 
		 */
		BinarySearchTreeNode node2 = new BinarySearchTreeNode(2);
		BinarySearchTreeNode node4 = new BinarySearchTreeNode(4);
		
		BinarySearchTreeNode node7 = new BinarySearchTreeNode(7);
		BinarySearchTreeNode node9 = new BinarySearchTreeNode(9);
		
		BinarySearchTreeNode node8 = new BinarySearchTreeNode(8,node7,node9);
		BinarySearchTreeNode node3 = new BinarySearchTreeNode(3,node2,node4);
		
		BinarySearchTreeNode node5 = new BinarySearchTreeNode(5,node3,node8);
		
		System.out.println(lcp_bst(node5,2,4));
		System.out.println(lcp_bst(node5,2,7));
	}
	
	public static BinarySearchTreeNode lcp_bst(BinarySearchTreeNode root,int val1, int val2) {
		if(root == null) {
			return null;
		}
		
		if(val1 > val2) {
			int temp = val1;
			val1 = val2;
			val2 = temp;
		}
		
		if (root.val > val2) {
			return lcp_bst(root.left, val1, val2);
		} else if (root.val < val1) {
			return lcp_bst(root.right, val1, val2);
		} else {
			return root;
		}
	}
	
	public static void test() {
		/**
		 *                5
		 *        /      / \     \
		 *       7      2   4     6
		 *     / | \   / \      /
		 *    12 9  8 15 21    11
		 */
		
		MultiChildrenTreeNode node12 = new MultiChildrenTreeNode(12);
		MultiChildrenTreeNode node9 = new MultiChildrenTreeNode(9);
		MultiChildrenTreeNode node8 = new MultiChildrenTreeNode(8);
		MultiChildrenTreeNode node15 = new MultiChildrenTreeNode(15);
		MultiChildrenTreeNode node21 = new MultiChildrenTreeNode(21);
		MultiChildrenTreeNode node11 = new MultiChildrenTreeNode(11);
		
		MultiChildrenTreeNode node7 = new MultiChildrenTreeNode(7);
		MultiChildrenTreeNode node2 = new MultiChildrenTreeNode(2);
		MultiChildrenTreeNode node4 = new MultiChildrenTreeNode(4);
		MultiChildrenTreeNode node6 = new MultiChildrenTreeNode(6);
		
		node7.children.add(node12);
		node7.children.add(node9);
		node7.children.add(node8);
		
		node2.children.add(node15);
		node2.children.add(node21);
		
		node6.children.add(node11);
		
		MultiChildrenTreeNode node5 = new MultiChildrenTreeNode(5);
		node5.children.add(node7);
		node5.children.add(node2);
		node5.children.add(node4);
		node5.children.add(node6);
		
		System.out.println(lcp_multree(node5, 12, 8));
		System.out.println(lcp_multree(node5, 12, 15));
	}
	
	public static MultiChildrenTreeNode lcp_multree(MultiChildrenTreeNode root, int val1, int val2) {
		if(root == null) {
			return null;
		}
		
		List<MultiChildrenTreeNode> path1 = new ArrayList<MultiChildrenTreeNode>();
		List<MultiChildrenTreeNode> path2 = new ArrayList<MultiChildrenTreeNode>();
		
		if(findNodePath(root, val1, path1) && findNodePath(root, val2, path2)) {
			return getLastCommonNode(path1, path2);
		}
		
		return null;
		
	}
	
	static MultiChildrenTreeNode getLastCommonNode(List<MultiChildrenTreeNode> path1, List<MultiChildrenTreeNode> path2) {
		MultiChildrenTreeNode last = null;
		
		for (int i = 0; i < path1.size() && i < path2.size(); i++) {
			if(path1.get(i).val == path2.get(i).val) {
				last = path1.get(i);
			}
		}
		
		return last;
	}
	
	static boolean findNodePath(MultiChildrenTreeNode root, int val, List<MultiChildrenTreeNode> path) {
		if (root == null) {
			return false;
		}
		if (root.val == val) {
			return true;
		}
		
		boolean found = false;
		path.add(root);
		
		for(MultiChildrenTreeNode child : root.children) {
			found = findNodePath(child, val, path);
			if(found) {
				break;
			}
		}
		
		if (!found) {
			path.remove(path.size() - 1);
		}
		
		return found;
	}
	
	public static void main(String[] args) {
		testBST();
		test();
	}
}
