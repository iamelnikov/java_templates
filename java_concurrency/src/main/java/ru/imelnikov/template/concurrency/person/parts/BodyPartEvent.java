package ru.imelnikov.template.concurrency.person.parts;

import ru.imelnikov.template.concurrency.Event;

public abstract class BodyPartEvent implements Event, Comparable<Integer> {

	private static final long serialVersionUID = -5064820825529917573L;

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
