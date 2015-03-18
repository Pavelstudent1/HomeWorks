package HomeWorks;

import java.util.Arrays;
import java.util.Random;

public class LSD_sortingV2 {
	
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
		
		tests(100);

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

	private static void lsdsort(int[] a) { //действительно быстрее ~ в 2 раза (~54 ms против ~114 ms)
	
		int R = 16;			//предельное значение бит в полубайте
		int N = a.length;
		int[] aux = new int[N];
		
		/*
			d служит маской для выборки и с каждым циклом перемещается сдвигом на 4-ре позиции влево
			dwn служит компенсатором сдвига маски, переводя текущий байт в самый младший. Также помогает
			восстановить реальное положение данного байта на определённом цикле прохода по числу.
		*/
		for(int d = 0xF, dwn = 0, step = 0; step < 8; d <<= 4, dwn += 4, step++){ //step идёт до 8, т.к 32 бита = 4 байта = 8 полубайт
			
			int[] count = new int[R + 1]; //т.к. используется dwn, нет необходимости в R, растущем
							  			  //при каждом цикле в 16 раз.
			for(int i = 0; i < N; i++){
				count[((a[i] & d) >> dwn) + 1]++; //приводим текущий байт к самому младшему и 
			}									  //записываем в массив R в нужную позицию + 1
			
			for(int r = 0; r < R; r++){		//нарастающее суммирование
				count[r + 1] += count[r];
			}
			
			for(int i = 0; i < N; i++){
				aux[count[(a[i] & d) >> dwn]++] = a[i]; //зная разницу dwn, всегда можно восстановить истинное
			}											//положение байта
			
			for(int i = 0; i < N; i++)
				a[i] = aux[i];
			
		}
		
	}

	private static int[] generate() {
		
		int[] data = new int[MAX];
		
		Random random = new Random();
		
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextInt(Integer.MAX_VALUE); //0 - 999999
		}
		
		return data;
	}

}
