public class Date implements Comparable<Date> {

	private int day;
	private int month;
	private int year;

	public Date(int year, int month, int day) {
		if (year < 2023 || year > 2080) {
			throw new IllegalArgumentException();
		}
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException();
		}
		if (day < 1 || day > 31) {
			throw new IllegalArgumentException();
		}

		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public String toString() {
		return String.format("%d/%d/%d", month, day, year);
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Date)) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		Date otherDate = (Date) obj;
		return this.day == otherDate.day && this.month == otherDate.month && this.year == otherDate.year;
	}

	@Override
	public int compareTo(Date other) {
		if (this.year < other.year) {
			return -1;
		}
		if (this.year > other.year) {
			return 1;
		}
		if (this.month < other.month) {
			return -1;
		}
		if (this.month > other.month) {
			return 1;
		}
		if (this.day < other.day) {
			return -1;
		}
		if (this.day > other.day) {
			return 1;
		}
		return 0; // the dates are the same
	}
}
