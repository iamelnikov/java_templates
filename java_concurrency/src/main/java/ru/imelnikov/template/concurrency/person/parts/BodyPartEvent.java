package ru.imelnikov.template.concurrency.person.parts;

import ru.imelnikov.template.concurrency.PersonActivityEvent;

public abstract class BodyPartEvent<T extends PersonActivityEvent> implements Comparable<Integer> {

	protected final static int DEFAULT_PRIORITY = 5;
	
	private int priority;
	
	public BodyPartEvent() {
		this.priority = DEFAULT_PRIORITY;
	}
	
	public BodyPartEvent(int priority) {
		this.priority = priority;
	}
	
	@Override
	public int compareTo(Integer o) {
		if (o!=null)
			return Integer.compare(priority, o);
		else 
			return 1;
	}
}
