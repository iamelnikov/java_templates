package ru.imelnikov.template.concurrency;

public abstract class Place {
	
	protected double[] coordinates;
	

	public Place(double x, double y) {
		super();
		this.coordinates = new double[2];
		this.coordinates[0] = x;
		this.coordinates[1] = y;
	}
	
	public void setX(double x) {
		this.coordinates[0] = x;
	}
	
	public void setY(double y){
		this.coordinates[1] = y;
	}
	
	public double getX(){
		return this.coordinates[0];
	}
	
	public double getY(){
		return this.coordinates[1];
	}
	
	public void setCoordinates(double x, double y){
		this.setX(x);
		this.setY(y);
	}

}
