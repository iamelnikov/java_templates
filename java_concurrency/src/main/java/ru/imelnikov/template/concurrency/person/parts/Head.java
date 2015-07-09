package ru.imelnikov.template.concurrency.person.parts;

import ru.imelnikov.template.concurrency.person.Person;

public class Head implements BodyPart {

	private static final long serialVersionUID = -3968285381779568070L;
	private Person person;
	
	public Head(Person person){
		this.person = person;
	}
	
	public void setPerson(Person person){
		this.person = person;
	}
	
	public Person getPerson(){
		return this.person;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
