package HomeWorks;

import java.util.Scanner;

public class WishNumber {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in); 
		System.out.print("Wish a number 1 <= N <= 100: ");
		
		int wnum = in.nextInt();
		while(wnum < 1 || wnum > 100){
			System.out.print("Wrong number! Try again: ");
			wnum = in.nextInt();
		}
		
		search(wnum);
		
		
		System.out.print("\n\n\n \"Search\" testing cycle\n\n");
		for (int i = 1; i <= 100; i++) {
			if (search(i) > 7){
				System.err.println("Method don't work correctly!");
				break;
			}
			System.out.println("---------------------");
		}
		
		in.close();
	}
	
	
	private static int search(int wnum){
		int s = 1, f = 100, tmp = f / 2, count = 1;
		
		//System.out.printf("Step %d: %d - %d, tmp = %d, Target = %d\n",++count,s,f,tmp,wnum);
		while(count < 10){
			
			if (wnum > tmp){
				s = tmp + 1;
				tmp = (s+f)/2;
				System.out.printf("Step %d: %d - %d, tmp = %d, Target = %d\n",count,s,f,tmp,wnum);
			}
			else{
				f = tmp;
				tmp = (s + f)/2;
				System.out.printf("Step %d: %d - %d, tmp = %d, Target = %d\n",count,s,f,tmp,wnum);
			}
			
			if (s == f){
				System.out.println("Your number is " + tmp + "! OOOOOOOO-HO-HO-HO-HO!");
				break;
			}
			
			++count;
		}
		
		return count;
	}
	
	
}
	
//	private static void search(int wnum){
//		
//		Scanner in = new Scanner(System.in);
//		
//		int as = 1, af = 100, tmp = af / 2, i = 1;
//		String answer;
//		
//		System.out.print("Step " + i + ". Number >= " + tmp + " ? ");
//		
//		while(true){
//			answer = new String(in.next());
//			
//			if (answer.equals("y")){
//				as = tmp;
//				tmp = (as + af) / 2;
//				System.out.printf("s = %d, f = %d, tmp = %d\n", as,af,tmp);
//			}
//			else{
//				af = tmp;
//				tmp = (as + af) / 2;
//				System.out.printf("s = %d, f = %d, tmp = %d\n", as,af,tmp);
//			}
//			
//			//if ((af - as) == 1){
//			if (tmp + 1 == af && tmp - 1 == as){
//				System.out.println("Your number is " + tmp + "! OOOOOOOOO-HO-HO-HO!!!");
//				break;
//			}
//			
//			System.out.print("Step " + ++i + " Number >= " + tmp + "? ");
//			}
//			
//		}
//}

