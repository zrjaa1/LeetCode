package Basic;

// test for keyword - static
public class test {
	public int[] generateArray(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			if (i == 20) continue; 
			arr[i] = i; 
		}
		return arr;
	}
	
	public static void main(String[] args) throws Exception {
		
	}
}
