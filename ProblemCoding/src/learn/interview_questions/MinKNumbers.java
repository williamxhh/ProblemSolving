package learn.interview_questions;

import java.util.Random;

/**
 * 
 * @author Xiaowei GAO
 * @date 2014年9月11日
 * @description 返回数组中最小的k个数
 * @ClassName MinKNumbers
 *
 */
public class MinKNumbers {
	static Random r = new Random();
	
	// O(n) 算法
	public int[] getMinKNumbers(int[] nums, int k) {
		if (nums == null || k <= 0) {
			return null;
		}
		
		if (nums.length < k) {
			return nums;
		}
		int left = 0;
		int right = nums.length - 1;
		int index = partition(nums, left, right);
		
		while(index != k - 1) {
			if(index > k - 1) {
				right = index - 1;
				index = partition(nums, left, right);
			} else {
				left = index + 1;
				index = partition(nums, left, right);
			}
		}
		
		int[] result = new int[k];
		for(int i = 0; i < k; i++) {
			result[i] = nums[i];
		}
		
		return result;
	}
	
	// O(nlogk)算法，适合处理海量数据
	public int[] getMinKNumbersHeapSort(int[] nums, int k) {
		if (nums == null || k <= 0) {
			return null;
		}
		
		if (nums.length < k) {
			return nums;
		}
		
		int[] result = new int[k];
		for(int i = 0; i < k; i++) {
			result[i] = nums[i];
		}
		buildMaxHeap(result);
		
		for(int i = k; i < nums.length; i++) {
			if(result[0] > nums[i]){
				result[0] = nums[i];
				buildMaxHeap(result);
			}
		}
		
		return result;
	}
	
	private int partition(int[] nums, int left, int right) {
		
		int index = left + r.nextInt(right - left + 1);
		swap(nums,index,right);
		
		int small = left - 1;
		for(index = left; index < right; index++) {
			if(nums[index] < nums[right]) {
				++small;
				if(small != index) {
					swap(nums,small,index);
				}
			}
		}
		
		++small;
		swap(nums,small,right);
		
		return small;
	}
	
	private void swap(int[] nums, int i1, int i2) {
		int temp = nums[i1];
		nums[i1] = nums[i2];
		nums[i2] = temp;
	}
	
	private void adjustdown(int[] a, int left, int right) {
		int temp = a[left];
		int child_index = left * 2 + 1;
		while(child_index <= right) {
			if(child_index < right && a[child_index] < a[child_index + 1]) {
				++child_index;
			}
			if(temp >= a[child_index]) {
				break;
			}
			a[(child_index - 1) / 2] = a[child_index];
			child_index = 2 * child_index + 1;
		}
		a[(child_index - 1)/2] = temp;
	}
	
	private void buildMaxHeap(int[] a) {
		int n = a.length;
		for(int i = n - 2; i > -1; i--) {
			adjustdown(a, i, n - 1);
		}
	}
	
	
	public static void main(String[] args) {
		int k = 5;
		MinKNumbers instance = new MinKNumbers();
		int[] nums = {32,1,6,2,7,43,2157,89,5,8};
		for(int n : instance.getMinKNumbers(nums, k)) {
			System.out.println(n);
		}
		System.out.println("##########");
		int[] nums1 = {32,1,6,2,7,43,2157,89,5,8};
		for(int n : instance.getMinKNumbersHeapSort(nums1, k)) {
			System.out.println(n);
		}
	}
}
