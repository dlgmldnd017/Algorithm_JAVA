package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TRIANGLEPATH1 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	// 각각 캐시, 삼각형, 높이를 뜻한다.
	private static int[][][] cache;
	private static int[][] triangle;
	private static int N;

	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			N = Integer.parseInt(sc.readLine());
			
			// 캐시 3차원 배열의 크기는 sum값을
			// 저장하기 때문에 크기가 아주 큰 것으로 초기화한다.
			cache = new int[100][100][999*100+1];
			
			triangle = new int[N][N];
			
			for(int y=0; y<N; y++) {
				st = new StringTokenizer(sc.readLine());
				for(int x=0; x<N; x++) {
					triangle[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append(solve(0,0,0)+"\n");
		}
		System.out.println(sb);
	}
	private static int solve(int y, int x, int sum) {
		// ex) 6 0 0 0 0에서 0은 빈 공간을 의미하기 때문에
		// 바로 return 0;
		if(triangle[y][x] == 0) return 0;
		
		// 맨 아래층에 도달 시, 최종 sum값 리턴
		if(y==N-1) return sum+triangle[y][x];
		
		// 캐시 히트라면, 캐시 값 리턴
		if(cache[y][x][sum] != 0) {
			System.out.println("cache hit! "+y+":"+x+":"+sum);
			System.out.println(cache[y][x][sum]);
			return cache[y][x][sum];
		}
		
		// 아래층 또는 아래층 오른쪽 도착하면
		// sum 값 구하기
        sum += triangle[y][x];	
		
        // 재귀 호출을 통해
        // 완전 탐색 시작
		int ret = cache[y][x][sum] = Math.max(solve(y+1, x, sum), solve(y+1, x+1, sum));
		
		return ret;
	}
}