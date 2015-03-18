package HomeWorks;

public class DeepCopyOfArray {

	public static void main(String[] args) {
		
		int[][] a = {
						{1,2,3,4},
						{5,6,7,8},
						{9,10,11,12},
		};
		
		int[][] b = {
				{1,2,3,4,5},
				{6,7},
				{8},
				{9,10,11}
		};
		
		int[][] New1, New2;

		
	New1 = deepCopy(a);
	New2 = deepCopy(b);
	print(a);
	print(b);
	
	System.out.println("Modify array A:");
	a[0][0] = 10;
	a[1][3] = 0;
	a[2][1] = 50;
	
	print(a);
	
	System.out.println("Array \"New1\" was changed?");
	print(New1);
	
	
		

	}

	private static int[][] deepCopy(int[][] a) {
		
	int[][] NewArray = new int[a.length][];
	
	for (int i = 0; i < a.length; i++) {
		NewArray[i] = new int[a[i].length];
	}
	
	for (int i = 0; i < a.length; i++) {
		System.arraycopy(a[i], 0, NewArray[i], 0, a[i].length);
	}
	
		return NewArray;
	}
	
	
	
	private static void print(int[][] a)
	{
		for (int[] i : a) {
			for (int j : i) {
				System.out.printf("%2d ", j);
			}
			System.out.println();
		}
		
		System.out.println();
	}

}
