package practice;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] firstLine = sc.nextLine().split(" ");
		int n = Integer.parseInt(firstLine[0]);
		int m = Integer.parseInt(firstLine[1]);

		int[][] board = new int[m][2];
		for (int i = 0; i < m; i++) {
			String[] line = sc.nextLine().split(" ");
			board[i][0] = Integer.parseInt(line[0]);
			board[i][1] = Integer.parseInt(line[1]);
		}

		Main main = new Main();
		main.solution(n, m, board);
	}

	int INF = 10000005;

	public void solution(int N, int M, int[][] friendshipNetwork) {
		int[][] board = new int[N][N]; //board 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				board[i][j] = INF;
			}
		}
		for (int i = 0; i < M; i++) {
			int x = friendshipNetwork[i][0] - 1;
			int y = friendshipNetwork[i][1] - 1;
			board[x][y] = 1;
			board[y][x] = 1;
		}

		floyd(N, board);

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < N; j++) {
				sum += board[i][j];
			}
			if (min > sum) {
				pq.clear();
				pq.offer(i + 1);
				min = sum;
			} else if (min == sum) {
				pq.offer(i + 1);
			}
		}
		System.out.println(pq.poll());
	}

	private void floyd(int n, int[][] board) {
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (board[i][k] + board[k][j] < board[i][j]) board[i][j] = board[i][k] + board[k][j];
				}
			}
		}
	}
}