package ru.imelnikov.template.concurrency.person.parts;

import ru.imelnikov.template.concurrency.Person;

public abstract class Volumed implements BodyPart{
	
	private static final long serialVersionUID = 3906317321339891685L;

	protected Person person;
	protected double maxVolume;
	protected double health;
	protected double loaded;
	
	public Volumed(Person person, double maxVolume, double health) {
		this.person = person;
		this.maxVolume = maxVolume;
		this.health = health;
	}
	
	public void load(double eatVolume){
		if (loaded + eatVolume > maxVolume)
			throw new IllegalStateException("Could not Eat More!!!");
			
		this.loaded+=eatVolume;
	}

	@Override
	public double getHealth(){
		return health;
	}
	
	public double getMaxVolume() {
		return maxVolume;
	}
	
	public double getLoaded(){
		return loaded;
	}
}
