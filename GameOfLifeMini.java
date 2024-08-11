class GameOfLifeMini {
	public static final int ROWS = 20;
	public static final int COLS = 40;
	public static final int TIMEOUT = 150;
	public static boolean WRAP = false;

	public static void main(String[] args) throws Exception {
		int[][] current = new int[ROWS][COLS];
		int[][] next = new int[ROWS][COLS];

		// initGliders(current);

		initGliderGun(current);

		System.out.print("\033[2J"); // clear screen first time.
		while (true) {
			display(current);
			Thread.sleep(TIMEOUT);
			computeNextState(current, next);
			int[][] temp = current;
			current = next;
			next = temp;

		}
	}

	public static void display(int[][] board) {
		StringBuilder frame = new StringBuilder();
		frame.append("\033[H"); // Move cursor to top-left (ANSI escape code)
		for (int[] r : board) {
			for (int i : r) {
				frame.append(i == 0 ? ". " : "# ");
			}
			frame.append("\n");
		}
		System.out.print(frame);
		System.out.flush(); // clears the buffer? idk exactly.
	}

	public static int getAliveNbourCount(int i, int j, int[][] board) {

		int count = 0;
		for (int di = -1; di <= 1; di++) {
			for (int dj = -1; dj <= 1; dj++) {
				if (!(di == 0 && dj == 0)) {
					if (WRAP) {
						int ni = (i + di + ROWS) % ROWS;
						int nj = (j + dj + COLS) % COLS;
						count += board[ni][nj];
					} else {
						int ni = i + di;
						int nj = j + dj;
						if (ni >= 0 && nj >= 0 && ni < ROWS && nj < COLS) {
							count += board[ni][nj];
						}
					}
				}
			}
		}
		return count;
	}

	public static void computeNextState(int[][] current, int[][] next) {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				int aliveNbours = getAliveNbourCount(i, j, current);
				if (aliveNbours == 3 || (aliveNbours == 2 && current[i][j] == 1)) {
					next[i][j] = 1;
				} else {
					next[i][j] = 0;
				}
			}
		}
	}

	public static void initGliders(int[][] current) {

		// init gliders
		current[0][1] = 1;
		current[1][2] = 1;
		current[2][0] = 1;
		current[2][1] = 1;
		current[2][2] = 1;

		current[0][1 + 9] = 1;
		current[1][2 + 9] = 1;
		current[2][0 + 9] = 1;
		current[2][1 + 9] = 1;
		current[2][2 + 9] = 1;
	}

	public static void initGliderGun(int[][] current) {
		// got the co-ords from: https://github.com/jdomingu/GameOfLifeLisp
		current[5][1] = 1;
		current[5][2] = 1;
		current[6][1] = 1;
		current[6][2] = 1;
		current[5][11] = 1;
		current[6][11] = 1;
		current[7][11] = 1;
		current[4][12] = 1;
		current[3][13] = 1;
		current[3][14] = 1;
		current[8][12] = 1;
		current[9][13] = 1;
		current[9][14] = 1;
		current[6][15] = 1;
		current[4][16] = 1;
		current[5][17] = 1;
		current[6][17] = 1;
		current[7][17] = 1;
		current[6][18] = 1;
		current[8][16] = 1;
		current[3][21] = 1;
		current[4][21] = 1;
		current[5][21] = 1;
		current[3][22] = 1;
		current[4][22] = 1;
		current[5][22] = 1;
		current[2][23] = 1;
		current[6][23] = 1;
		current[1][25] = 1;
		current[2][25] = 1;
		current[6][25] = 1;
		current[7][25] = 1;
		current[3][35] = 1;
		current[4][35] = 1;
		current[3][36] = 1;
		current[4][36] = 1;
	}
}