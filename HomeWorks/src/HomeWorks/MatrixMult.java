package HomeWorks;

public class MatrixMult {

	public static void main(String[] args) { //исключение порой выскакивает, проверить!
		
		int[][] a = {
				{1,2,3},
				{4,5,6}
		};
		
		int[][] b = {
				{1,2},
				{3,4},
				{5,6}
		};
		
		int[][] c;
		
		int[][] d = {
				{1,2},
				{3,4}
		};
		
		int[][] e = {
				{5,6},
				{7,8}
		};
		
		c = MatrixMult(a,b);
		print(c);
		c = MatrixMult(d, e);
		print(c);
		
		int[][] f = CreateRandomMatrix();
		int[][] g = CreateRandomMatrix();
		
		print(f);
		print(g);
		
		int[][] h = MatrixMult(f, g);
		print(h);

	}

	private static void print(int[][] c) {
		
		if (c == null){
			System.out.println("NullPointer matrix!");
			return;
		}
		
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[i].length; j++) {
				System.out.printf("%2d ", c[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static int[][] MatrixMult(int[][] a, int[][] b) {
		
		if (a == null || b == null){
			System.out.println("No all or one of matrix!");
			return null;
		}
		
		
		if (a[0].length != b.length){
			System.out.println("Matrix cant't be multy!");
			return null;
		}
		
		
		int[][] NEW = new int[a.length][b[0].length];
		
		int n = 0;
		
		for (int m = 0; m < a.length; m++) {
			
			while(n < b[0].length){
				
				for (int q = 0; q < a.length; q++) {
					
				NEW[m][n] += (a[m][q] * b[q][n]);
					
				}
				++n;
			}
			n = 0;
		}
		
		System.out.println("Matrix multiplied");
		return NEW;	
	}
	
	private static int[][] CreateRandomMatrix()
	{
		int m = 0, n = 0;
		do{
			m = (int) (Math.random() * 10 - 5);
			n = (int) (Math.random() * 10 - 5);
		}while(m < 2 || n < 2);
		
		int[][] NEW = new int[m][n];
		
		for (int i = 0; i < NEW.length; i++) {
			for (int j = 0; j < NEW[i].length; j++) {
				NEW[i][j] = (int)(Math.random() * 100);
			}
		}
		
		return NEW;
	}

}