package HomeWorks;

import java.util.Arrays;
import java.util.Random;

public class Radix_sort_v3 {

	private static final int MAX = 1000000;

	public static void main(String[] args) {
		
		tests(500);
		
		
	}

	private static void tests(int count) {
		
		int sum = 0;
		int progress_bar = count / 30;
		
		System.out.println("Sorting of " + MAX + " 32-bits random integers");
		System.out.println("Working...");
		for (int i = 0; i < count; i++) {
			int[] data = generate();			
			long start = System.currentTimeMillis();
//			Arrays.sort(data);
			radixSort(data);
			long stop = System.currentTimeMillis();
			
//			System.out.println("Round " + (i + 1) + ", time = " + (stop - start));
			System.out.print((i % progress_bar == 0 ? "=" : ""));
			sum += (stop - start);
		}
		
		System.out.println("\n" + count + " rounds, average time of round = " + (sum / count) + " ms");
	}

	private static void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
		
	}

	private static void radixSort(int[] a) { //незначительно быстрее LSD_sortingV2 (единицы мс), видимо за счёт немного более
										     //короткого R. Но 99% сути не поменялось
	
		int R = 256;			
		int N = a.length;
		int[] aux = new int[N];
		
		for(int d = 0xFF, dwn = 0, step = 0; step < 4; d <<= 8, dwn += 8, step++){
			
			int[] count = new int[R];	//в отличие от LSDsort на единицу меньше 
			
			for(int i = 0; i < N; i++){
				count[((a[i] & d) >> dwn)]++; 
			}									  
			
			for(int r = 0; r < R - 1; r++){		
				count[r + 1] += count[r];
			}
			
			for(int i = N - 1; i >= 0; i--){ //разница с LSD_sortingV3: 
											 //1) обход с конца в начало
											 //2) значение в count не инкрементируется, а декрементируется
											 //3) СНАЧАЛА декремент, потом aux <- a 
				aux[--count[(a[i] & d) >> dwn]] = a[i];
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
