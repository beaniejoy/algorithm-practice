# Brute Force

- 모든 경우의 수를 탐색하면서 조건에 맞는 경우를 골라내는 방법
- 종종 효과적일 때가 있다.

## Permutation (순열)

```java
public int solution(String numbers) {
  //...

  // 원본리스트 생성
  int[] numbList = new int[numbers.length()];
  for (int i = 0; i < numbers.length(); i++) {
      numbList[i] = Integer.parseInt(String.valueOf(numbers.charAt(i)));
  }

  // 원본 리스트 정렬(오름차순)

  // 원하는 데이터를 담을 저장소
  Set<Integer> primeList = new HashSet<>();

  // 본격 순열 알고리즘 적용(recursive)
  // nP1, nP2, ... , nPn 까지 개수별로 모든 경우를 조회
  // 1부터 for loop 시작
  for (int i = 1; i <= numbList.length; i++) {
      perm(numbList, 0, i, primeList);
  }

  System.out.println(primeList);

  //...

}

public void perm(int[] arr, int depth, int k, Set primeList) {
  if (depth == k) { 
    // 한번 depth 가 k로 도달하면 사이클이 돌았음. 출력함.
    returnNumber(arr, k, primeList);
    return;
  } else {
    // depth에 따라 순서를 바꾸면서 숫자 조합을 구성
    for (int i = depth; i < arr.length; i++) {
        swap(arr, i, depth); // depth를 기준으로 바꾸고
        perm(arr, depth + 1, k, primeList); // permutation depth+1로 돌리고 나서
        swap(arr, i, depth); // 다시 원래대로 원위치
    }
  }
}

private void returnNumber(int[] arr, int k, Set primeList) {
  // depth에 도달했을 때(하나의 조합을 완성했을 때) 문제 조건에 맞는 작업 진행
}

public void swap(int[] arr, int i, int j) {
  int temp = arr[i];
  arr[i] = arr[j];
  arr[j] = temp;
}
```

## Combination (조합)

`AB`, `BA`를 같게 보는 경우

```java
public int solution(String numbers) {
  //...

  // 원본리스트 생성
  int[] numbList = new int[numbers.length()];
  for (int i = 0; i < numbers.length(); i++) {
      numbList[i] = Integer.parseInt(String.valueOf(numbers.charAt(i)));
  }

  // 원본 리스트 정렬(오름차순)

  // 원하는 데이터를 담을 저장소
  Set<Integer> primeList = new HashSet<>();

  // 본격 조합 알고리즘 적용(recursive)
  // nC1, nC2, ... , nPn 까지 개수별로 모든 경우를 조회
  // 1부터 for loop 시작
  for (int i = 1; i <= numbList.length; i++) {
      combination(numbList, 0, 0, i);
  }

  System.out.println(primeList);

  //...
}

private void combination(int[] arr, int depth, int index, int k) {
  if (depth == k) {
    // 한번 depth 가 k로 도달하면 사이클이 돌았음.
    return;
  }

  for (int i = index; i < menuList.size(); i++) {
    combination(arr, depth + 1, i + 1, k);
  }
}
```

결국 위의 Combination 방법, Permutation 방법은 brute-force 방식에 근거한다.