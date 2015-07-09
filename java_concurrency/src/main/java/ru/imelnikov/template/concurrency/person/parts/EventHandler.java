package ru.imelnikov.template.concurrency.person.parts;

import ru.imelnikov.template.concurrency.Event;

public interface EventHandler<T extends Event, V> extends Runnable{
	
}
