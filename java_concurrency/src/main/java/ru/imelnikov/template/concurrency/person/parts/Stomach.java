package ru.imelnikov.template.concurrency.person.parts;

import ru.imelnikov.template.concurrency.Person;

public class Stomach extends Volumed {

	private static final long serialVersionUID = -9162736444712784490L;

	private METABOLISM metabolismSpeed;

	public Stomach(Person person, double volume, double health,
			METABOLISM metabolismSpeed) {
		super(person, volume, health);
		this.metabolismSpeed = metabolismSpeed;
	}

	public METABOLISM getMetabolismSpeed() {
		return metabolismSpeed;
	}

	public void setMetabolismSpeed(METABOLISM metabolismSpeed) {
		this.metabolismSpeed = metabolismSpeed;
	}

	@Override
	public void run() {
		while (true) {
			if (this.loaded > 0)
				this.loaded -= metabolismSpeed.ratio * 0.1;

			if ((this.loaded / this.maxVolume) * 100 > 10)
				this.person.sendEvent(new LightHungryEvent());

			if ((this.loaded == 0))
				this.person.sendEvent(new StrongHungryEvent());

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static class LightHungryEvent extends BodyPartEvent {
		public LightHungryEvent() {
			super(3);
		}
	}

	private static class StrongHungryEvent extends BodyPartEvent {
		public StrongHungryEvent() {
			super(7);
		}
	}

	static enum METABOLISM {
		SLOW(1), MEDIUM(2), FAST(3);
		private double ratio;

		METABOLISM(double ratio) {
			this.ratio = ratio;
		}

		public double getRatio() {
			return this.ratio;
		}
	}

}
