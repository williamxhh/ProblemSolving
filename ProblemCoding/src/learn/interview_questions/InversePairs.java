package learn.interview_questions;

import java.util.Arrays;

/**
 * 
 * @author Xiaowei GAO
 * @date 2014年9月12日
 * @description 统计数组里面的逆序数
 * @ClassName InversePairs
 *
 */
public class InversePairs {
	public int inverse_count(int[] nums) {
		if(nums == null || nums.length == 0) {
			return 0;
		}
		
		return sort_and_count(nums, 0, nums.length - 1);
	}
	
	private int sort_and_count(int[] nums, int begin, int end) {
		if(nums == null || end <= begin) {
			return 0;
		}
		
		int middle = begin + (end - begin) / 2;
		
		int left = sort_and_count(nums, begin, middle);
		int right = sort_and_count(nums, middle + 1, end);
		
		int merge_count = merge(nums,begin,middle+1,end);
		
		return left + right + merge_count;
	}
	
	private int merge(int[] nums, int begin ,int begin2, int end) {
		int count = 0;
		int[] left = Arrays.copyOfRange(nums,begin, begin2);
		int[] right = Arrays.copyOfRange(nums, begin2, end + 1);
		
		int index = end;
		int index_left = begin2 - 1 - begin ;
		int index_right = end - begin2;
		
		while(index_left >= 0 && index_right >= 0) {
			if(left[index_left] <= right[index_right]) {
				nums[index--] = right[index_right--];
			} else {
				count += index_right + 1;
				nums[index--] = left[index_left--];
			}
		}
		
		while(index_left >= 0) {
			nums[index--] = left[index_left--];
		}
		
		while(index_right >= 0) {
			nums[index--] = right[index_right--];
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		InversePairs instance = new InversePairs();
		int[] nums = {1,2,5,4,3};
		System.out.println(instance.inverse_count(nums));
	}
}
