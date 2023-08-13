public class EightQueens {
	private static final char QUEEN = 'Q';
	private static final char WHITE_SPACE = '-';
	private static final char DARK_SPACE = '#';

	private static void pause(long ms) {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
			System.exit(1);
		}
	}

	public static char[][] newBoard(int boardsize) {
		char board[][] = new char[boardsize][boardsize];

		for (int rank = 0; rank < boardsize; rank++) {
			for (int file = 0; file < boardsize; file++) {
				if ((rank + file) % 2 == 0) {
					board[rank][file] = WHITE_SPACE;
				} else {
					board[rank][file] = DARK_SPACE;
				}
			}
		}

		return board;
	}

	public static void printBoard(char board[][]) {
		int hyphens = 2 * board.length + 3;
		for (int i = 0; i < hyphens; i++) {
			System.out.print('-');
		}
		System.out.println();
		for (char rank[] : board) {
			System.out.print('|');
			for (char pos : rank) {
				System.out.printf("%2s", pos);
			}
			System.out.println(" |");
		}
		for (int i = 0; i < hyphens; i++) {
			System.out.print('-');
		}
		System.out.println();
	}

	public static boolean isAttacking(char board[][], int rank, int file) {
		return false;
	}

	public static void placeQueen(char board[][], int rank, int file) {
		board[rank][file] = QUEEN;
	}

	public static void removeQueen(char board[][], int rank, int file) {
		board[rank][file] = (rank + file) % 2 == 0 ? WHITE_SPACE : DARK_SPACE;
	}

	public static void solve(char board[][]) {
		solve(board, 0);
	}

	private static void solve(char board[][], int file) {
		// Solve the eight queens puzzle
	}

	public static void main(String args[]) {
		int boardsize = 8;
		char board[][] = newBoard(boardsize);
		solve(board);
	}
}
