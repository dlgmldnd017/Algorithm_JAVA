package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class NUMB3RS_2 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	// 각각 마을의 수, 지난 일수, 교도소가 있는 마을 변수
	private static int N, D, P;
	
	// 넘겨주기 위한 인수값
	private static ArrayList<Integer> arr;
	
	// 마을 i와 j가 연결되어 있는지 확인하는 행렬 변수
	private static int[][] A;
	
	// 마을 i와 연결된 마을의 수 변수
	private static int[] degree;
	
	// 각각 계산할 마을의 수와 T개의 정수로 확률을 계산할 마을의 번호 변수들
	private static int T;
	private static int[] Q;
	
	private static double[][] cache;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			cache = new double[51][101];
			
			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			// 교도소 위치를 넣어주고 인자값에게 넘겨준다.
			P = Integer.parseInt(st.nextToken());
			arr = new ArrayList<>();
			arr.add(P);
			
			// y와 x가 서로 연결되었는 지 확인하는 과정
			// 0이면 연결되지 않았고 1이면 연결되어 있다.
			A = new int[N][N];
			degree = new int[N];
			for(int y=0; y<N; y++) {
				st = new StringTokenizer(sc.readLine());
				for(int x=0; x<N; x++) {
					A[y][x] = Integer.parseInt(st.nextToken());
					
					// 만약 마을과 연결되었다면 degree[y]++
					if(A[y][x]==1) {
						degree[y]++;
					}
				}
			}
			
			T = Integer.parseInt(sc.readLine());
			Q = new int[T];
			
			st = new StringTokenizer(sc.readLine());
			for(int y=0; y<T; y++) {
				Q[y] = Integer.parseInt(st.nextToken());
			}
			
			for(int y=0; y<T; y++) {
				initCache();
				sb.append(solve(P, 0, y)+ "  ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static double solve(int here, int days, int i) {
		// 기저 사례(1): 지난 일수가 지났다면
		if(days==D) return (here==Q[i]?1.0:0.0);
		
		if(cache[here][days] > -0.5) return cache[here][days];
		
		double ret = 0.0;
		for(int there=0; there<N; there++) {
			if(A[here][there]==1) {
				ret += solve(there, days+1, i)/degree[here];	
			}
		}
		return cache[here][days] = ret;
	}
	
	// 캐시를 -1.0으로 초기화
	private static void initCache() {
		for(int y=0; y<N; y++) {
			for(int x=0; x<101; x++) {
				cache[y][x] = -1.0;
			}
		}
	}
}