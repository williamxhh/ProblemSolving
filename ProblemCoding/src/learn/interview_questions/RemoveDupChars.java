package learn.interview_questions;

import java.util.Arrays;

/**
 * 
 * @author Xiaowei GAO
 * @date 2014年9月12日
 * @description TODO
 * @ClassName RemoveDupChars
 *
 */
public class RemoveDupChars {
	/**
	 * 连续的冗余字符只保留一个
	 * @param input
	 * @return
	 */
	public String removeDupCharsToOne(String input) {
		if (input == null || input.length() < 2) {
			return input;
		}
		
		char[] s = input.toCharArray();
		
		int dup_count = 0;
		for(int i = 1; i < s.length; i++) {
			if(s[i] == s[i-1]) {
				++dup_count;
			} else {
				s[i - dup_count] = s[i];
			}
		}
		
		return new String(Arrays.copyOfRange(s, 0, s.length - dup_count));
	}
	
	/**
	 * 把连续的冗余字符全部去掉
	 * @param input
	 * @return
	 */
	public String removeDupChars(String input) {
		if(input == null || input.length() < 2) {
			return input;
		}
		char[] s = input.toCharArray();
		int index = 0;
		for(int i = 0; i < s.length; i++) {
			if(i == 0){
				if (s[i] != s[i+1]) {
					s[index++] = s[i];
				}
			} else if (i == s.length - 1) {
				if (s[i] != s[i-1]) {
					s[index++] = s[i];
				}
			} else if (s[i] != s[i-1] && s[i] != s[i+1]) {
				s[index++] = s[i];
			}
		}
		
		return new String(Arrays.copyOfRange(s, 0, index));
	}
	
	public static void main(String[] args) {
		RemoveDupChars instance = new RemoveDupChars();
		System.out.println(instance.removeDupCharsToOne("aaabdedeee"));
		System.out.println(instance.removeDupChars("aaabdedeee"));
	}
}
