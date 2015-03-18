package HomeWorks;

import java.util.Scanner;

public class WishNumber_recursive {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in); 
		System.out.print("Wish a number 1 <= N <= 100: ");
		
		int wnum = in.nextInt();
		while(wnum < 1 || wnum > 100){
			System.out.print("Wrong number! Try again: ");
			wnum = in.nextInt();
		}
		
		search(wnum, 1, 100, 0);
		
		
		System.out.print("\n\n\n \"Search\" testing cycle\n\n");
		for (int i = 1; i <= 10 ; i++) {
			search(i, 1,100,0);
			System.out.println("------------------------");
		}

	}

	private static int search(int wnum, int st, int fin, int count) {
		
		int t = ((fin + st) / 2);//, count = 0;
		
		if (count == 0)
			System.out.printf("Init. Position: %d - %d, tmp = %d, Target = %d\n", st, fin, t, wnum);
		
		System.out.printf("Step %d: %d - %d, tmp = %d, Target = %d\n", ++count, st, fin, t, wnum);
		
		if (st == fin) return count;
		if (count > 7) return count;
		
		if (wnum > t)
			count = search(wnum, t + 1, fin, count);
		else
			count = search(wnum, st, t, count);
		
		
		
		
		return count;
	}

}
