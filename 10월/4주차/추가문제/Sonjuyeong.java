import java.io.*;
import java.util.*;

public class Sonjuyeong {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());

		int[][][] dp = new int[t + 1][3][w + 1]; // 초, 위치, 이동 횟수
		for (int i = 1; i <= t; i++) {
			// 자두가 떨어지는 위치 n
			int n = Integer.parseInt(br.readLine());
			int o = n == 1 ? 2 : 1;
			for (int j = 0; j <= (i < w ? i : w); j++) {
				// 움직인 적 없을 때 바로 이전 횟수를 더해줌.
				// 위치가 2일 때는 적어도 한 번 이상은 움직인 게 분명하니까, 그냥 이전거를 바로 넣고, 아닌 경우에는 +1까지 해주기
				if (j == 0) {
					dp[i][n][j] = dp[i - 1][n][j] + n % 2;
				} else { // 그 외
					// 값을 가질 수 없는 경우는 0 처리.
					// ex. 위치가 1인데 이동횟수가 홀수인 경우 혹은 위치가 2인데 이동횟수가 짝수인 경우.
					if (j % 2 == n) {
						dp[i][n][j] = 0;
					} else {
						// 이동하지 않았을 때와 이동했을 때의 값을 비교하여 max값 할당
						dp[i][n][j] = Math.max(dp[i - 1][n][j] + 1, dp[i - 1][o][j - 1] + 1);
					}
				}
				// 자두가 떨어지지 않는 위치는 단순히 이전 값 넣어주기.
				dp[i][o][j] = dp[i - 1][o][j];
			}
		}

		int answer = 0;

		// t초까지 받았을 때 가장 많이 받은 자두 개수 찾기.
		for (int i = 0; i <= w; i++) {
			answer = Math.max(answer, Math.max(dp[t][1][i], dp[t][2][i]));
		}
		System.out.println(answer);
	}
}
