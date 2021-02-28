# Insertion Sort

```java
public class InsertionSort {

	static void insertionSort(int[] data) {

		for (int i = 1; i < data.length; i++) {
			for (int j = i; j > 0; j--) {
				if (data[j - 1] > data[j])
					swap(data, j - 1, j);
			}
		}
	}

	static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	public static void main(String[] args) {

		int[] data = { 14, 12, 7, 3, 4, 8, 20, 9, 17 };

		insertionSort(data);

		for (int i = 0; i < data.length; i++)
			System.out.println(data[i]);
	}
}
```

반복문이 두 개 $O(n^2)$
- 최악의 경우, ${ n * (n - 1)}/{ 2 }$
- 완전 정렬이 되어 있는 상태라면 최선은 $O(n)$