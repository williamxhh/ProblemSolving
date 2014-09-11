package learn.interview_questions;

public class UglyNumber {
	private int getKthUglyNumber(int k) {
		if(k <= 0) {
			return 0;
		}
		
		int[] ugly_numbers = new int[k];
		ugly_numbers[0] = 1;
		
		int next_index = 1;
		
		int next_2 = 0;
		int next_3 = 0;
		int next_5 = 0;
		
		while(next_index < k){
			ugly_numbers[next_index] = min(ugly_numbers,next_2,next_3,next_5);
			
			while(ugly_numbers[next_2] * 2 <= ugly_numbers[next_index]){
				++next_2;
			}
			
			while(ugly_numbers[next_3] * 3 <= ugly_numbers[next_index]) {
				++next_3;
			}
			
			while(ugly_numbers[next_5] * 5 <= ugly_numbers[next_index]) {
				++next_5;
			}
			
			++next_index;
		}
		
		return ugly_numbers[k - 1];
		
	}
	
	private int min(int[] nums,int number1, int number2, int number3) {
		int num1 = nums[number1] * 2;
		int num2 = nums[number2] * 3;
		int num3 = nums[number3] * 5;
		
		int min_number = num1 < num2 ? num1 : num2;
		min_number = min_number < num3 ? min_number : num3;
		return min_number;
	}
	
	public static void main(String[] args) {
		UglyNumber instance = new UglyNumber();
		System.out.println(instance.getKthUglyNumber(15));
	}
}
