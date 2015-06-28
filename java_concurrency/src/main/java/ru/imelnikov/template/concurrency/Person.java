package ru.imelnikov.template.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import ru.imelnikov.template.concurrency.person.parts.BodyPartEvent;

public abstract class Person implements Runnable {

	protected PersonalData personalData;
	protected PriorityBlockingQueue<BodyPartEvent> bodyPartEventSet;
	protected PriorityBlockingQueue<PersonActivityEvent> personActivityEventSet;

	protected ExecutorService personActivitiesPool;

	public Person() {
		this.personalData = new PersonalData();
		this.personActivitiesPool = Executors.newCachedThreadPool();
		personActivitiesPool.execute(new BackgroundEventHandler());
		personActivitiesPool.execute(new PersonActivityEventHandler());
	}

	public void sendEvent(BodyPartEvent event) {
		assert this.bodyPartEventSet != null;
		this.bodyPartEventSet.add(event);
	}

	public void run() {

	}

	protected abstract void doDayActivity();

	protected abstract void sleeping();

	protected abstract void wakeUp();

	protected abstract void fallAsleep();

	protected abstract void morningBath();

	protected abstract void eating(EATING type);

	protected abstract void toilet(TOILET type);

	protected abstract void eveningBath();

	protected static enum EATING {
		FIRST_BREAKFAST, SECOND_BREAKFAST, FIRST_LUNCH, SECOND_LUNCH, FIRST_DINNER, SECOND_DINNER, SNACK
	}

	protected static enum TOILET {
		SMALL, BIG
	}

	private class BackgroundEventHandler implements Runnable {
		@Override
		public void run() {
			assert Person.this.bodyPartEventSet!=null;
			if (Person.this.bodyPartEventSet!=null && !Person.this.bodyPartEventSet.isEmpty())
				for (BodyPartEvent event: bodyPartEventSet){
					
				}
		}
	}

	private class PersonActivityEventHandler implements Runnable {
		@Override
		public void run() {
			assert Person.this.personActivityEventSet!=null;
			
		}
	}

}
