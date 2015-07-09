package ru.imelnikov.template.concurrency.person.parts;

public abstract class AbstractBodyPart implements BodyPart{

	private static final long serialVersionUID = -8856475041482126299L;
	
	protected NervousSystem nervousSystem;
	protected double health;

	public AbstractBodyPart(NervousSystem nervousSystem, double health) {
		super();
		this.nervousSystem = nervousSystem;
		this.health = health;
	}
	
	@Override
	public double getHealth(){
		return health;
	}
}
