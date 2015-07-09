package ru.imelnikov.template.concurrency.person.parts;

import java.util.UUID;

public abstract class AbstractPersonBodyPartInnerEvent implements
		PersonBodyInnerEvent {

	private static final long serialVersionUID = 14926437248310299L;
	protected String id;
	protected long creationTime;
	protected String name;
	protected int priority;

	public AbstractPersonBodyPartInnerEvent(String name, int priority) {
		this.id = UUID.randomUUID().toString();
		this.creationTime = System.currentTimeMillis();
		this.name = name;
		this.priority = priority;
	}

	public AbstractPersonBodyPartInnerEvent(String name) {
		this.id = UUID.randomUUID().toString();
		this.creationTime = System.currentTimeMillis();
		this.name = name;
		this.priority = PersonBodyInnerEvent.DEFAULT_PRIORITY;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public long getCreationTime() {
		return creationTime;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getPriority(){
		return this.priority;
	}

	@Override
	public int compareTo(Integer o) {
		if (o != null)
			return Integer.compare(priority, o);
		else
			return 1;
	}
}
