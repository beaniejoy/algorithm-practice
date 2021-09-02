# Quick Sort



```java
public class QuickSort {
  public static void quickSort(int[] arr) {
      
    sort(arr, 0, arr.length-1);
  }
  
  public static void sort(int[] arr, int start, int end) {
    if(start >= end) return;
    
    int pivotIdx = splitAndSwap(arr, start, end);
    
    sort(arr, start, pivotIdx - 1);
    sort(arr, pivotIdx, end);
  }
  
  public static int splitAndSwap(int[] arr, int start, int end) {
    
    int pivot = arr[(start + end) / 2];
    
    while(start <= end) {
      while(pivot > arr[start]) start++; 
      while(pivot < arr[end]) end--;
      
      if(start <= end) {
        swap(arr, start, end);
        start++;
        end--;
      }
    }
    
    return start;
  }

  //...

}

```
- pivot 기준으로 left, right 배열 객체를 생성해 나눠 저장하는 방식은 성능상 비효율적이다. (재귀호출때마다 배열객체 생성)
- in-place 정렬이 이런면에서 효율적 (해당 배열에 바로 적용)
- [[알고리즘] 퀵 정렬 - Quick Sort (Python, Java)](https://www.daleseo.com/sort-quick/)