public class Calendar {
	private static final int MAXEVENTS = 4;
	private Event events[];
	private int numEvents;

	public Calendar() {
		events = new Event[MAXEVENTS];
	}

	public boolean addEvent(Event e) {
		if (numEvents == MAXEVENTS) {
			return false;
		}
		for (int i = 0; i < MAXEVENTS; i++) {
			if (events[i] == null) {
				events[i] = e;
				numEvents += 1;
				return true;
			}
		}
		return false;
	}

	public int findEvent(Event e) {
		if (numEvents == 0) {
			return -1;
		}
		for (int i = 0; i < events.length; i++) {
			if (events[i] != null && events[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}

	public boolean removeEvent(Event e) {
		if (numEvents == 0) {
			return false;
		}
		int idx = findEvent(e);
		if (idx == -1) {
			return false;
		}

		events[idx] = null;
		numEvents -= 1;
		return true;
	}

	public void dump() {
		for (Event e : events) {
			if (e != null) {
				System.out.println(e);
			}
		}
	}
}
