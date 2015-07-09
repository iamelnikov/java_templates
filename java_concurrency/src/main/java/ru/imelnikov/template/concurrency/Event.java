package ru.imelnikov.template.concurrency;

import java.io.Serializable;

public interface Event extends Serializable, Comparable<Integer>{
	public String getId();
	public long getCreationTime();
	public String getName();
	public int getPriority();
}
