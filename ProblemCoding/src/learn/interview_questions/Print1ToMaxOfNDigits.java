package learn.interview_questions;

public class Print1ToMaxOfNDigits {
	  public static void main(String[] args) {
		    print1ToMaxOfNDigits(5);
		  }
		  
		  public static void print1ToMaxOfNDigits (int n) {
		    char[] sb = new char[n];
		    
		    for(char c = '0'; c <= '9'; c++) {
		      sb[0] = c;
		      printNumberRecursively(sb, n, 0);
		    }
		  }
		  
		  public static void printNumberRecursively(char[] sb, int n, int index) {
		    if(index == n - 1) {
		      printNumber(sb);
		      return;
		    }
		    
		    for(char c = '0'; c <= '9'; c++) {
		      sb[index + 1] = c;
		      printNumberRecursively(sb, n, index + 1);
		    }
		    
		  }
		  
		  public static void printNumber(char[] sb) {
		    for (int i = 0; i < sb.length; i++) {
		      if(sb[i] != '0') {
		    	String s = new String(sb);
		        System.out.println(s.substring(i));
		        break;
		      }
		    }
		  }
		}
