package ru.imelnikov.template.concurrency.person.parts;

import ru.imelnikov.template.concurrency.person.Person;

public class Rectum extends VolumedBodyPart{

	private static final long serialVersionUID = 2962675378685019711L;

	public Rectum(Person person, double volume, double health) {
		super(person, volume, health);
	}

	@Override
	public void run() {
		
	}
}
