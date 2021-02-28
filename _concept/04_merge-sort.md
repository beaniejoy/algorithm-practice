# Merge Sort

```java
public class MergeSort {
  
  public static void mergeSort(int[] total, int len) {
    // length가 1이 되었을 때 split 종료
    if(len < 2) {
      return;
    }

    // element 개수를 가지고 쪼갠다. (index 자리기준 X)
		// left, right 배열을 따로 만든다.
    int mid = len / 2;
    int[] left = new int[mid];
    int[] right = new int[n - mid];

    // left 부분 쪼개기
    for(int i = 0; i < mid; i++) {
      left[i] = total[i];
    }

    // right 부분 쪼개기
    for(int i = mid; i < n; i++) {
      right[i - mid] = total[i]
    }

    // left and right spliting
    mergeSort(left, mid);
    mergeSort(right, n - mid);

    // after spliting complete,
    // merge left and right list
    merge(total, left, right, mid, n - mid);
  }

  // parameter: left, right > index 자리넘버가 아닌 list의 요소 개수
  public static void merge(
    int[] total, int[] left, int[] right, int leftLen, int rightLen
  ) {
    
    int i = 0, j = 0, k = 0;
    
    while(i < leftLen && j < rightLen) {
      if(left[i] <= right[j]) {
        total[k++] = left[i++];
      } else {
        total[k++] = right[j++];
      }
    }

    // left list에서 남은 요소가 있으면 그대로 total에 입력
    while(i < leftLen) {
      total[k++] = left[i++];
    }

    // right list에서 남은 요소 있을 때
    while(j < rightLen) {
      total[k++] = right[j++];
    }
  }

  public static void main(String args[]) {
    int[] test = { 5, 2, 7, 10, 3, 1, 6};

    mergeSort(test, test.length);
    for(int element : test) {
      System.out.print(element);
    }
  }
}
```- 알고리즘 분석은 쉽지 않음
- 몇 단계 깊이까지 만들어지는지를 depth 라고 하고 $i$로 놓자. 맨 위 단계는 0으로 놓자.
    - 다음 그림에서 $n/2^2$ 는 2단계 깊이라고 해보자.
    - 각 단계에 있는 하나의 노드 안의 리스트 길이는 $n/2^2$ 가 된다.
    - 각 단계에는 $2^i$ 개의 노드가 있다.
- 따라서, 각 단계는 항상 $2^i * { n }/{ 2^i } = O(n)$
- 단계는 항상 $log_2 n$ 개 만큼 만들어짐, 시간 복잡도는 결국 $O(log n)$, 2는 역시 상수이므로 삭제
- 따라서, 단계별 시간 복잡도 $O(n) * O(log n) = O(n log n)$

![https://www.fun-coding.org/00_Images/mergesortcomplexity.png](https://www.fun-coding.org/00_Images/mergesortcomplexity.png)

```java
public class MergeSort {
  
  public static void mergeSort(int[] total, int len) {
    // length가 1이 되었을 때 split 종료
    if(len < 2) {
      return;
    }

    // element 개수를 가지고 쪼갠다. (index 자리기준 X)
		// left, right 배열을 따로 만든다.
    int mid = len / 2;
    int[] left = new int[mid];
    int[] right = new int[n - mid];

    // left 부분 쪼개기
    for(int i = 0; i < mid; i++) {
      left[i] = total[i];
    }

    // right 부분 쪼개기
    for(int i = mid; i < n; i++) {
      right[i - mid] = total[i]
    }

    // left and right spliting
    mergeSort(left, mid);
    mergeSort(right, n - mid);

    // after spliting complete,
    // merge left and right list
    merge(total, left, right, mid, n - mid);
  }

  // parameter: left, right > index 자리넘버가 아닌 list의 요소 개수
  public static void merge(
    int[] total, int[] left, int[] right, int leftLen, int rightLen
  ) {
    
    int i = 0, j = 0, k = 0;
    
    while(i < leftLen && j < rightLen) {
      if(left[i] <= right[j]) {
        total[k++] = left[i++];
      } else {
        total[k++] = right[j++];
      }
    }

    // left list에서 남은 요소가 있으면 그대로 total에 입력
    while(i < leftLen) {
      total[k++] = left[i++];
    }

    // right list에서 남은 요소 있을 때
    while(j < rightLen) {
      total[k++] = right[j++];
    }
  }

  public static void main(String args[]) {
    int[] test = { 5, 2, 7, 10, 3, 1, 6};

    mergeSort(test, test.length);
    for(int element : test) {
      System.out.print(element);
    }
  }
}
```
- 몇 단계 깊이까지 만들어지는지를 depth 라고 하고 $i$로 놓자. 맨 위 단계는 0으로 놓자.
    - 다음 그림에서 $n/2^2$ 는 2단계 깊이라고 해보자.
    - 각 단계에 있는 하나의 노드 안의 리스트 길이는 $n/2^2$ 가 된다.
    - 각 단계에는 $2^i$ 개의 노드가 있다.
- 따라서, 각 단계는 항상 $2^i * { n }/{ 2^i } = O(n)$
- 단계는 항상 $log_2 n$ 개 만큼 만들어짐, 시간 복잡도는 결국 $O(log n)$, 2는 역시 상수이므로 삭제
- 따라서, 단계별 시간 복잡도 $O(n) * O(log n) = O(n log n)$

![https://www.fun-coding.org/00_Images/mergesortcomplexity.png](https://www.fun-coding.org/00_Images/mergesortcomplexity.png)
