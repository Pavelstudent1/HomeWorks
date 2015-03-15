package HW1;

import java.util.Arrays;
import java.util.Random;

public class LSD_sorting {
	
	private static final int MAX = 1000000;

	public static void main(String[] args) {
		
		int[] data = generate();
//		print(data);
		
		long start = System.currentTimeMillis();
//		Arrays.sort(data);
		lsdsort(data);
		long stop = System.currentTimeMillis();
		
		System.out.println("Elapsed = " + (stop - start));
//		print(data);
		

	}

	private static void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
		
	}

	private static void lsdsort(int[] a) {
	
		int R = 20;
		int N = a.length;
		int[] aux = new int[N];
		
		for(int d = 0xF; d <= 0xF0000; d <<= 4){
			
			R = d + 1;
			int[] count = new int[R + 1];
			
			for(int i = 0; i < N; i++){
				count[(a[i] & d) + 1]++;
			}
			
			for(int r = 0; r < R; r++){
				count[r + 1] += count[r];
			}
			
			for(int i = 0; i < N; i++){
				aux[count[a[i] & d]++] = a[i];
			}
			
			for(int i = 0; i < N; i++)
				a[i] = aux[i];
			
		}
		
		
	}

	private static int[] generate() {
		
		int[] data = new int[MAX];
		
		Random random = new Random();
		
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextInt(MAX); //0 - 999999
		}
		
		return data;
	}

}
