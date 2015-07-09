package ru.imelnikov.template.concurrency.person.parts;

public class Stomach extends VolumedBodyPart {

	private static final long serialVersionUID = -9162736444712784490L;
	private static final int FULL_THRESHOLD = 80;
	private METABOLISM metabolismSpeed;

	public Stomach(NervousSystem nervousSystem, double volume, double health,
			METABOLISM metabolismSpeed) {
		super(nervousSystem, volume, health);
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
			if (this.loaded > 0) {
				double digestFoodVolume = metabolismSpeed.ratio * 0.1;
				this.loaded -= digestFoodVolume;
				double[] digestFoodArray = digestFood(digestFoodVolume);
				this.nervousSystem.sendInnerEvent(new EnegryEvent(
						digestFoodArray[0]));
				this.nervousSystem.sendInnerEvent(new ToRectumEvent(
						digestFoodArray[1]));
			}

			if ((this.loaded / this.maxVolume) * 100 < 10 && this.loaded > 0)
				this.nervousSystem.sendInnerEvent(new LightHungryEvent());
			else if ((this.loaded == 0))
				this.nervousSystem.sendInnerEvent(new StrongHungryEvent());

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private double[] digestFood(double volume) {
		double[] returnValue = new double[2];
		returnValue[0] = volume * 0.8;
		returnValue[0] = volume * 0.2;
		return returnValue;
	}
	
	@Override
	public void load(double addedVolume) {
		if (loaded + addedVolume > maxVolume) {
			this.loaded = maxVolume;
			this.nervousSystem.sendInnerEvent(new SatietyEvent());
		} else {
			this.loaded += addedVolume;
			if (this.loaded > FULL_THRESHOLD)
				this.nervousSystem.sendInnerEvent(new IsFullEvent());
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

	static class EnegryEvent extends AbstractPersonBodyPartInnerEvent {

		private static final long serialVersionUID = -3047201274662727951L;
		private static final String EVENT_NAME = "ENERGY";
		private static final int PRIORITY = 2;
		private double volume;

		public EnegryEvent(double volume) {
			super(EVENT_NAME, PRIORITY);
			this.volume = volume;
		}

		public double getVolume() {
			return volume;
		}
	}

	static class ToRectumEvent extends AbstractPersonBodyPartInnerEvent {

		private static final long serialVersionUID = -5635738687297296833L;
		private static final String EVENT_NAME = "FROM_STOMACH_TO_RECTUM";
		private static final int PRIORITY = 2;
		private double volume;

		ToRectumEvent(double volume) {
			super(EVENT_NAME, PRIORITY);
			this.volume = volume;
		}
		
		public double getVolume(){
			return volume;
		}
	}
	
	static class ToBladderEvent extends AbstractPersonBodyPartInnerEvent {

		private static final long serialVersionUID = -288760781035419717L;
		private static final String EVENT_NAME = "FROM_STOMACH_TO_BLADDER";
		private static final int PRIORITY = 2;
		private double volume;

		ToBladderEvent(double volume) {
			super(EVENT_NAME, PRIORITY);
			this.volume = volume;
		}
		
		public double getVolume(){
			return volume;
		}
	}

	static class LightHungryEvent extends AbstractPersonBodyPartInnerEvent {

		private static final long serialVersionUID = 525938498930968183L;
		private static final String EVENT_NAME = "LIGHT_HUNGRY";
		private static final int PRIORITY = 4;

		public LightHungryEvent() {
			super(EVENT_NAME, PRIORITY);
		}
	}

	static class StrongHungryEvent extends AbstractPersonBodyPartInnerEvent {
		private static final long serialVersionUID = 5943857918727596374L;
		private static final String EVENT_NAME = "LIGHT_HUNGRY";
		private static final int PRIORITY = 8;

		public StrongHungryEvent() {
			super(EVENT_NAME, PRIORITY);
		}
	}

	static class IsFullEvent extends AbstractPersonBodyPartInnerEvent {
		private static final String EVENT_NAME = "IS_FULL";
		private static final int PRIORITY = 7;
		private static final long serialVersionUID = -241433332910103585L;

		public IsFullEvent() {
			super(EVENT_NAME, PRIORITY);
		}
	}

	static class SatietyEvent extends AbstractPersonBodyPartInnerEvent {
		private static final long serialVersionUID = -1075610992423976648L;
		private static final String EVENT_NAME = "SATIETY";
		private static final int PRIORITY = 10;

		public SatietyEvent() {
			super(EVENT_NAME, PRIORITY);
		}
	}

}
