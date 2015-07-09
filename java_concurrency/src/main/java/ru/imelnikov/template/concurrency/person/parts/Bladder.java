package ru.imelnikov.template.concurrency.person.parts;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import ru.imelnikov.template.concurrency.person.parts.Stomach.ToBladderEvent;


public class Bladder extends VolumedBodyPart{

	private static final long serialVersionUID = 401826315672803080L;

	
	public Bladder(NervousSystem nervousSystem, double volume, double health) {
		super(nervousSystem, volume, health);
	}

	@Override
	public void run() {
		while (true){
			if (this.loaded > 0) {
				
			}
		}
	}
	
	@Override
	public void load(double addedVolume) {
		this.loaded +=addedVolume;
	}
	
	private static class FromStomachToBladderHandler implements EventHandler<ToBladderEvent, Void> {
		@Override
		public void processEvent(ToBladderEvent event) {
			
		}

		@Override
		public void run() {
			
		}
	}



	
}
