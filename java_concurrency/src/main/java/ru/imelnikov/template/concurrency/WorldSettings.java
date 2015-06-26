package ru.imelnikov.template.concurrency;

public class WorldSettings {
	
	public static final WorldSettings INSTANCE = new WorldSettings();
	
	private Boolean isManInTheWorldBecomeCrazy;

	private WorldSettings(){
		this.isManInTheWorldBecomeCrazy = false;
	}
	
	public Boolean isManInTheWorldBecomeCrazy(){
		return this.isManInTheWorldBecomeCrazy;
	}
	
	public void setManInTheWorldBecomeCrazy(boolean isManInTheWorldBecomeCrazy){
		this.isManInTheWorldBecomeCrazy = isManInTheWorldBecomeCrazy;
	}
}
