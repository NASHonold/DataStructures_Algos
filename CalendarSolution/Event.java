public class Event {
	private Date date;
	private int start;
	private int end;
	private String description;

	public Event(Date date, int start, int end, String description) {
		if (start > end || start > 23 || start < 0 || end > 23 || end < 0) {
			throw new IllegalArgumentException();
		}

		this.date = date;
		this.start = start;
		this.end = end;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public String toString() {
		return String.format("%s %d -- %d: %s", date, start, end, description);
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Event)) {
			return false;
		}
		if (this == obj) {
			return true;
		}

		Event otherEvent = (Event) obj;
		return this.date.equals(otherEvent.date) && this.start == otherEvent.start && this.end == otherEvent.end
				&& this.description.contentEquals(otherEvent.description);
	}
}
