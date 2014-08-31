package learn.interview_questions;

public class LongestCommonSubsequence {
	public static void main(String[] args) {
		String a = "abcbdab";
		String b = "bdcaba";
		
		int[][] lcs_len = new int[a.length() + 1][b.length() + 1];
		int[][] mark = new int[a.length() + 1][b.length() + 1];
		
		lcs(a,b,lcs_len,mark);
		
		System.out.print("  ");
		for(int j = 0; j < b.length(); j++) {
			System.out.print(b.charAt(j) + " ");
		}
		System.out.println();
		for(int i = 1; i <= a.length(); i++) {
			System.out.print(a.charAt(i - 1) + " ");
			for(int j = 1; j <= b.length(); j++) {
				System.out.print(lcs_len[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		print_lcs(a,a.length(),b,b.length(),mark);
		
	}
	
	public static void lcs(String a, String b, int[][] lcs_len, int[][] mark) {
		int lena = a.length();
		int lenb = b.length();
		
		for (int i = 0; i <= lena; i++) {
			lcs_len[i][0] = 0;
		}
		
		for (int j = 0; j <= lenb; j++) {
			lcs_len[0][j] = 0;
		}
		
		for (int i = 1; i <= lena; i++) {
			for (int j = 1; j <= lenb; j++) {
				if (a.charAt(i-1) == b.charAt(j-1)) {
					lcs_len[i][j] = lcs_len[i - 1][j - 1] + 1;
					mark[i][j] = 0;
				} else {
					if (lcs_len[i][j - 1] > lcs_len[i - 1][j]) {
						lcs_len[i][j] = lcs_len[i][j - 1];
						mark[i][j] = -1;
					} else {
						lcs_len[i][j] = lcs_len[i - 1][j];
						mark[i][j] = 1;
					}
				}
			}
		}
	}
	
	public static void print_lcs(String a, int lena, String b, int lenb, int[][] mark) {
		
		if (lena == 0 || lenb == 0) {
			return;
		}
		
		if (mark[lena][lenb] == 0) {
			print_lcs(a, lena - 1, b, lenb - 1, mark);
			System.out.print(a.charAt(lena - 1) + " ");
		} else if (mark[lena][lenb] == 1) {
			print_lcs(a,lena - 1,b,lenb,mark);
		} else {
			print_lcs(a,lena,b,lenb - 1,mark);
		}
	}
}
