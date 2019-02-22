package Basic;

// practice: output a triangle matrix

public class GenerateMatrix {
	public void generateMatrix(int n) {
		for (int i = 0; i < n/2; i++) {
			for (int j = i; j < n/2; j++) {
				System.out.print(0);
			}
			System.out.println();
		}
		for (int k = n/2; k < n; k++) {
			for (int z = n/2; z < k; z++) {
				System.out.print(0);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		GenerateMatrix test = new GenerateMatrix();
		test.generateMatrix(10);
	}
}
