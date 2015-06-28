package ru.imelnikov.template.concurrency.person.parts;

import ru.imelnikov.template.concurrency.Person;

public class Bladder extends Volumed{

	private static final long serialVersionUID = 401826315672803080L;

	public Bladder(Person person, double volume, double health) {
		super(person, volume, health);
	}

	@Override
	public void run() {
		
	}
}
