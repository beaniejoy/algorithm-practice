package _concept.sorting;

public class SortingDemo {

  // bubble sort
  private static void bubbleSort(int[] data) {
    for (int i = 0; i < data.length - 1; i++) {
      for (int j = 0; j < data.length - i - 1; j++) {
        if (data[j] > data[j + 1]) {
          swap(data, j, j + 1);
        }
      }
    }
  }

  // insertion sort
  private static void insertionSort(int[] data) {
    for (int i = 1; i < data.length; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (data[j] > data[j+1]) {
          swap(data, j, j+1);
        }
      }
    }
  }

  // selection sort
  private static void selectionSort(int[] data) {
    for (int i = 0; i < data.length - 1; i++) {
      int indexLowest = i;
      for (int j = i + 1; j < data.length; j++) {
        if (data[j] < data[indexLowest]) {
          indexLowest = j;
        }
      }

      swap(data, i, indexLowest);
    }
  }

  // ##### merge sort start ######
  private static void mergeSplit(int[] data) {
    int len = data.length;
    if (len < 2) {
      return;
    }

    int mid = len / 2;
    int[] left = new int[mid];
    int[] right = new int[len - mid];

    for (int i = 0; i < mid; i++) {
      left[i] = data[i];
    }

    for (int i = mid; i < len; i++) {
      right[i - mid] = data[i];
    }

    mergeSplit(left);
    mergeSplit(right);

    merge(data, left, right);
  }

  private static void merge(int[] total, int[] left, int[] right) {
    int idxLeft = 0, idxRight = 0, idxTotal = 0;

    int leftLen = left.length;
    int rightLen = right.length;

    while (idxLeft < leftLen && idxRight < rightLen) {
      if (left[idxLeft] <= right[idxRight]) {
        total[idxTotal++] = left[idxLeft++];
      } else {
        total[idxTotal++] = right[idxRight++];
      }
    }

    // left 나머지
    while(idxLeft < leftLen) {
      total[idxTotal++] = left[idxLeft++];
    }

    // right 나머지
    while(idxRight < rightLen) {
      total[idxTotal++] = right[idxRight++];
    }
  }
  // ##### merge sort end ######

  // ##### quick sort start(이부분이 어렵다) ######
  private static void quickSort(int[] data, int start, int end) {
    if(start >= end) return;
    
    int pivotIdx = splitAndSwap(data, start, end);
    
    quickSort(data, start, pivotIdx - 1);
    quickSort(data, pivotIdx, end);
  }
  
  private static int splitAndSwap(int[] data, int start, int end) {
    
    int pivot = data[(start + end) / 2];
    
    while(start <= end) {
      while(pivot > data[start]) start++; 
      while(pivot < data[end]) end--;
      
      if(start <= end) {
        swap(data, start, end);
        start++;
        end--;
      }
    }
    
    return start;
  }
  // ##### quick sort end ######

  private static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

  public static void main(String[] args) {
    int[] data = { 14, 12, 49, 7, 3, 4, 8, 20, 9, 17, 11, 5, 27, 31, 20, 19 };

    // bubbleSort(data);
    // insertionSort(data);
    // selectionSort(data);
    // mergeSplit(data);
    quickSort(data, 0, data.length - 1);

    for (int i : data) {
      System.out.print(i + ", ");
    }
  }
}
