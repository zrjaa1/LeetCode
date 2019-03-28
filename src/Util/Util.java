package Util;
import java.util.*;

public class Util {
	public static void swapIntArray(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
