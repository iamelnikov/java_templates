package ru.imelnikov.template.concurrency.person.parts;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import ru.imelnikov.template.concurrency.Person;
import ru.imelnikov.template.concurrency.person.parts.Stomach.METABOLISM;

public class PersonDigestiveSystem implements BodyPart {
	
	private static final long serialVersionUID = 7574768510659531701L;

	private Person person;
	
	private Set<BodyPart> systemPartSet;
	
	private Set<BodyPartEvent> eventSet;
	
	private ExecutorService pool;
	
	public PersonDigestiveSystem(Person person, double[] stomachParamArray, METABOLISM metabolismSpeed, double[] rectumParamArray, double[] bladderParamArray){
		this.person = person;
		this.systemPartSet = new LinkedHashSet<BodyPart>();
		this.eventSet = new LinkedHashSet<BodyPartEvent>();
		Stomach stomach = new Stomach(person, stomachParamArray[0], stomachParamArray[1], metabolismSpeed);
		Rectum rectum = new Rectum(person, rectumParamArray[0], rectumParamArray[1]);
		Bladder bladder = new Bladder(person, bladderParamArray[0], bladderParamArray[1]);
		this.addSystemPart(stomach);
		this.addSystemPart(rectum);
		this.addSystemPart(bladder);
	}
	
	private void initWork(){
		this.pool = Executors.newFixedThreadPool(this.systemPartSet.size());
		pool.submit(getStomach());
		pool.submit(getRectum());
		pool.submit(getBladder());
	}
	
	public void addSystemPart(BodyPart bodyPart){
		this.systemPartSet.add(bodyPart);
	}

	@Override
	public double getHealth() {
		assert this.systemPartSet != null; 
		OptionalDouble avg = this.systemPartSet.parallelStream().mapToDouble(BodyPart::getHealth).average();
		return avg.getAsDouble();
	}
	
	public Stomach getStomach(){
		List<BodyPart> list = this.systemPartSet.parallelStream().filter(it -> Stomach.class.isAssignableFrom(it.getClass())).collect(Collectors.toList());
		assert list.size()==1;
		BodyPart stomach = list.get(0);
		return (Stomach)stomach;
	}
	
	public Rectum getRectum(){
		List<BodyPart> list = this.systemPartSet.parallelStream().filter(it -> Rectum.class.isAssignableFrom(it.getClass())).collect(Collectors.toList());
		assert list.size()==1;
		BodyPart rectum = list.get(0);
		return (Rectum)rectum;
	}
	
	public Bladder getBladder(){
		List<BodyPart> list = this.systemPartSet.parallelStream().filter(it -> Bladder.class.isAssignableFrom(it.getClass())).collect(Collectors.toList());
		assert list.size()==1;
		BodyPart bladder = list.get(0);
		return (Bladder)bladder;
	}
	
	public void acceptEat(double eating){
		Stomach stomach = getStomach();
		assert stomach!=null;
		stomach.load(eating);
	}
	
	public void addBodyEvent(BodyPartEvent event) {
		assert this.eventSet != null;
		this.eventSet.add(event);
	}

	@Override
	public void run() {
		initWork();
		while (true) {
			
		}
	}
}
