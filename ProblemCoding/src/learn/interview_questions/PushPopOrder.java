package learn.interview_questions;

import java.util.Stack;

/**
 * 
 * @author Xiaowei GAO
 * @date 2014年9月9日
 * @description 判断pop序列是不是push序列的出栈序列
 * @ClassName PushPopOrder
 *
 */
public class PushPopOrder {
	public static void main(String[] args) {
		int[] push = {1,2,3,4,5};
//		int[] pop = {4,3,5,1,2};
		int[] pop = {4,5,3,2,1};
		System.out.println(isPopOrder(push, pop));
	}
	
	public static boolean isPopOrder(int[] push, int[] pop) {
		if(push.length != pop.length) {
			return false;
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		
		int nextPushIndex = 0;
		int nextPopIndex = 0;
		
		while(nextPopIndex < pop.length) {
			while (stack.empty() || stack.peek() != pop[nextPopIndex]) {
				if(nextPushIndex >= push.length) {
					break;
				}
				stack.push(push[nextPushIndex++]);
			}
			
			if(stack.peek() != pop[nextPopIndex]) {
				break;
			}
			
			stack.pop();
			nextPopIndex++;
		}
		
		return nextPopIndex == pop.length;
	}
}
