package ru.imelnikov.template.concurrency.person.parts;

import ru.imelnikov.template.concurrency.Event;

public interface PersonBodyInnerEvent extends Event {
	public static final int DEFAULT_PRIORITY = 5;
	
}
