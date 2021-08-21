# Dynamic Programming && Divide and Conquer

## 동적계획법
- 작은 부분 문제들부터 해결한 결과값을 저장해두고 있다가 더 큰 크기의 문제들을 해결하는데 활용하면서 전체 문제를 해결해나가는 기법
- Memorization 이용
  - 작은 문제들에 대한 결과값을 저장하고 더 큰 크기의 문제가 작은 문제에 대한 결과값을 요구할 때 또 계산하지 않고 저장된 값을 이용  
  (일종의 캐싱기법)
  - 피보나치 수열을 배열에다가 저장해놓고 푸는 방식의 예

```java
public int fibo(int index) {
  if(index < 0) 
    return -1;
  if(index == 0 || index == 1) 
    return 1;
  
  int[] arr = new int[index + 1];
  arr[0] = 1;
  arr[1] = 1;

  for(int i = 2; i <= index; i++) {
    arr[i] = arr[i-1] + arr[i-2];
  }

  return arr[index];
}

```

## 분할정복
- 하향식 접근방법
- 재귀적인 방법으로 호출한다는 점에서 오버헤드 발생
- 이진탐색, merge sort, quick sort

```java
public int fibo(int index) {
  if(index < 0) 
    return 0;
  if(index == 0 || index == 1) 
    return 1;

  return fibo(index - 1) + fibo(index - 2);
}
```