package HomeWorks;

import java.util.Arrays;
import java.util.Random;

public class LSD_sortingV3 {
	
	private static final int MAX = 1000000;

	public static void main(String[] args) {
		
//		int[] data = generate();
////		print(data);
//		
//		long start = System.currentTimeMillis();
////		Arrays.sort(data);
//		lsdsort(data);
//		long stop = System.currentTimeMillis();
//		
//		System.out.println("Elapsed = " + (stop - start));
//		print(data);
		
		tests(500);

	}

	private static void tests(int count) {
		
		int sum = 0;
		
		System.out.println("Sorting of " + MAX + " 32-bits random integers");
		for (int i = 0; i < count; i++) {
			int[] data = generate();			
			long start = System.currentTimeMillis();
//			Arrays.sort(data);
			lsdsort(data);
			long stop = System.currentTimeMillis();
			
			System.out.println("Round " + (i + 1) + ", time = " + (stop - start));
			sum += (stop - start);
		}
		
		System.out.println(count + " rounds, average time = " + (sum / count));
	}

	private static void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
		
	}

	private static void lsdsort(int[] a) {
	
		int R = 256;	//использование полного байта, вместо полубайта в прошлой версии метода,
						//даёт почти двухкратный прирост: ~28 мс вместо ~54
		int N = a.length;
		int[] aux = new int[N];
		
		/*
		 Как показали опыты, сканировать с R > 256, т.е. больше байта, не приводит к уменьшению времени сортировки,
		 но приводит к распуханию R, т.е. потребность в доп. памяти. Т.о. сканирование по байту - золотая середина
		 производительности и потребности в памяти.
		 */
		for(int d = 0xFF, dwn = 0, step = 0; step < 4; d <<= 8, dwn += 8, step++){
			
			int[] count = new int[R + 1];
			
			for(int i = 0; i < N; i++){
				count[((a[i] & d) >> dwn) + 1]++;
			}
			
			for(int r = 0; r < R; r++){		
				count[r + 1] += count[r];
			}
			
			for(int i = 0; i < N; i++){
				aux[count[(a[i] & d) >> dwn]++] = a[i];
			}
			
			for(int i = 0; i < N; i++)
				a[i] = aux[i];
			
		}
		
	}

	private static int[] generate() {
		
		int[] data = new int[MAX];
		
		Random random = new Random();
		
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextInt(Integer.MAX_VALUE);
		}
		
		return data;
	}

}
