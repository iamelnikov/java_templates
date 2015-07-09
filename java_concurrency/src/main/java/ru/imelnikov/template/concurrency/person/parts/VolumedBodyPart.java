package ru.imelnikov.template.concurrency.person.parts;

public abstract class VolumedBodyPart extends AbstractBodyPart{
	
	private static final long serialVersionUID = 3906317321339891685L;

	protected double maxVolume;
	protected double loaded;
	
	public VolumedBodyPart(NervousSystem nervousSystem, double maxVolume, double health) {
		super(nervousSystem, health);
		this.maxVolume = maxVolume;
	}
	
	public abstract void load(double addedVolume);
	
	public double getMaxVolume() {
		return maxVolume;
	}
	
	public double getLoaded(){
		return loaded;
	}
}
