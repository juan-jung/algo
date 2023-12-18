# [Silver II] N과 M (9) - 15663 

[문제 링크](https://www.acmicpc.net/problem/15663) 

### 성능 요약

메모리: 48668 KB, 시간: 600 ms

### 분류

백트래킹

### 제출 일자

2023년 12월 18일 19:38:24

### 문제 설명

<p>N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.</p>

<ul>
	<li>N개의 자연수 중에서 M개를 고른 수열</li>
</ul>

### 입력 

 <p>첫째 줄에 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)</p>

<p>둘째 줄에 N개의 수가 주어진다. 입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.</p>

### 출력 

 <p>한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.</p>

<p>수열은 사전 순으로 증가하는 순서로 출력해야 한다.</p>


### 새로운 풀이

Map을 사용하지 않고, int형 변수에 이전 값을 저장해 같으면 continue
-> 메모리나 시간 차이가 거의 없긴 하다...

```
static void perm(int cnt, int[] arr, boolean[] v, int[] selected, int M, int N) {
	if(cnt == M) {
		for(int i=0;i<M;i++) System.out.print(selected[i]+" ");
		return;
	}
	
	int prev = -1;
	
	for(int i=0;i<N;i++) {
		if(v[i] || prev==arr[i]) continue;
		v[i] = true;
		prev = arr[i];
		selected[cnt] = arr[i];
		perm(cnt+1,arr,v,selected,M,N);
		v[i] = false;
	}
}
```
