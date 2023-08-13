/*
Student: Nash Honold
Date Completed: 09/14/21
*/

public class Islands {
	public static void main(String args[]) {
		int map[][] = new int[5][5];

		int maxValue = 100;
		int dropChance = 25;
		randomIslands(map, maxValue, dropChance);
		printIslands(map);
		System.out.println("There is an island with a value of " + maxValueIsland(map));
	}



	public static int maxValueIsland(int map[][]) {
		int maxValue = 0;
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if (map[r][c] != 0) {
					int value = getIslandValue(map, r, c);
					if (value > maxValue) {
						maxValue = value;
					}
				}
			}
		}
		return maxValue;
	}


	private static int getIslandValue(int map[][], int row, int col) {
		// need to check that row or col is not out of bounds. If it is then we will
		// return.
		if (row >= map.length || col >= map[0].length || row < 0 || col < 0) {
			return 0;
		}

		// will check that the given value of matrix is less than 1 and will return 0
		if (map[row][col] < 1) {
			return 0;
		}

		int sum = map[row][col];// This will store the value that has been passed to the function

		map[row][col] = 0; // this marks the matrix so this value will not be populated later

		sum += getIslandValue(map, row + 1, col)
				+ getIslandValue(map, row - 1, col)
				+ getIslandValue(map, row, col + 1)
				+ getIslandValue(map, row, col - 1);
		// this is the sum of all returned values from the recursive function of
		// adjacent positions in the matrix

		return sum;
	}

	/******************************************************************/

	public static void randomIslands(int map[][], int maxPossibleValue, int chance) {
		if (maxPossibleValue <= 0) {
			throw new IllegalArgumentException("The max possible value must be a positive integer.");
		}
		if (chance > 100 || chance < 0) {
			throw new IllegalArgumentException("The chance of money drop must be between 0 <= p <= 100");
		}
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				int possible = (int) (Math.random() * 100) + 1;
				if (possible <= chance) {
					map[r][c] = (int) (Math.random() * maxPossibleValue) + 1;
				}
			}
		}
	}

	public static void printIslands(int island[][]) {
		int maxDigits = getMaxDigits(island);
		for (int r = 0; r < island.length; r++) {
			for (int c = 0; c < island[r].length; c++) {
				int value = island[r][c];
				String s = "%" + maxDigits + "d";
				if (value != 0) {
					System.out.print(" |");
					System.out.printf(s, value);
					System.out.print("| ");
				} else {
					System.out.print("  ");
					System.out.printf("%" + maxDigits + "s", "-");
					System.out.print("  ");
				}
			}
			System.out.println(" ");
		}
	}

	private static int getMaxDigits(int[][] arr) {
		int maxDigitSize = 0;
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[r].length; c++) {
				int value = arr[r][c];
				int digits = 0;
				while (value != 0) {
					digits += 1;
					value /= 10;
				}
				if (digits > maxDigitSize) {
					maxDigitSize = digits;
				}
			}
		}
		return maxDigitSize;
	}

}
