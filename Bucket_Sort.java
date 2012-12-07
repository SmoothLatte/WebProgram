package sort;

/*
 * 桶排序
 * 假设输入数据均匀分布在0到99之间
 */
public class Bucket_Sort {
	int[][] bucket;
	
	public Bucket_Sort() {
		bucket = new int[10][];
	}
	
	public int[] sort(int[] a) {
		int[] count = new int[10];
		for (int i = 0; i < a.length; i++)
			count[a[i] / 10]++;
		int[] index = new int[10];
		for (int i = 0; i < 10; i++) {
			bucket[i] = new int[count[i]];
			index[i] = -1;
		}
		for (int i = 0; i < a.length; i++) {
			int key = a[i] / 10;
			index[key]++;
			bucket[key][index[key]] = a[i];
		}
		Insertion_Sort insertionSort = new Insertion_Sort();
		for (int i = 0; i < 10; i++)
			if (count[i] > 0)
				bucket[i] = insertionSort.sort(bucket[i]);
		int[] b = new int[a.length];
		int c = -1;
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < count[i]; j++) {
				c++;
				b[c] = bucket[i][j];
			}
		return b;
	}
}
