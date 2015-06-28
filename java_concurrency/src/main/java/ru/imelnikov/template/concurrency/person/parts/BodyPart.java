package ru.imelnikov.template.concurrency.person.parts;

import java.io.Serializable;

public interface BodyPart extends Serializable, Runnable {
	public double getHealth();
}
