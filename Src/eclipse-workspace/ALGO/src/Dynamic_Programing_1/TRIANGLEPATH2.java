package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TRIANGLEPATH2 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	// 각각 캐시, 삼각형, 높이를 뜻한다.
	private static int[][] cache;
	private static int[][] triangle;
	private static int N;

	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			N = Integer.parseInt(sc.readLine());
			cache = new int[100][100];
			
			triangle = new int[N][N];
			
			for(int y=0; y<N; y++) {
				st = new StringTokenizer(sc.readLine());
				for(int x=0; x<N; x++) {
					triangle[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append(solve(0,0)+"\n");
		}
		System.out.println(sb);
	}
	private static int solve(int y, int x) {
		// ex) 6 0 0 0 0에서 0은 빈 공간을 의미하기 때문에
		// 바로 return 0;
		if(triangle[y][x] == 0) return 0;
				
		// 맨 아래층에 도달 시, 최종 sum값 리턴
		if(y==N-1) return triangle[y][x];
		
		// 캐시 히트라면, 캐시 값 리턴
		if(cache[y][x] != 0) {
			System.out.println("cache hit! "+y+":"+x);
			System.out.println(cache[y][x]);
			return cache[y][x];
		}
		
		// 부분 합 리턴하기
		return cache[y][x] = Math.max(solve(y+1, x), solve(y+1, x+1)) + triangle[y][x];
	}
}