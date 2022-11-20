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
- insertion sort는 bubble sort와 유사해 보임
  - 방향만 반대
  - insertion은 데이터 하나(tmp)를 집어놓고 그 앞에 자리 데이터들을 하나씩 비교
  - tmp보다 작은 데이터가 나오면 그 뒤에 insert한다고 해서 삽입 정렬
  - 그런데 사실 tmp보다 큰 데이터가 나오면 자리를 바꿔줘야 하기에 bubble

- 반복문이 두 개 $O(n^2)$
  - 최악의 경우, ${ n * (n - 1)}/{ 2 }$
  - 완전 정렬이 되어 있는 상태라면 최선은 $O(n)$