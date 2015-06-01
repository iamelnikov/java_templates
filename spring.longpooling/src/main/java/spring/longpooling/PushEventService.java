package spring.longpooling;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
@Scope("singleton")
public class PushEventService {

	private static Long eventInterval = 15000L;
	
	
	private final LinkedBlockingQueue<DeferredResult<Set<PushEvent>>> subscribers;
	Set<PushEvent> eventSet;
	private Thread generator;

	public PushEventService() {
		this.eventSet = Collections
				.synchronizedSet(new LinkedHashSet<PushEvent>());
		this.generator = new Thread(new EventGeneratorRunnable(this.eventSet, this.subscribers));
		this.subscribers = new LinkedBlockingQueue<DeferredResult<Set<PushEvent>>>();
	}
	
	public void subscribe(DeferredResult<Set<PushEvent>> peSet){
		this.subscribers.add(peSet);
	}

	public synchronized void start() {
		if (!this.generator.isAlive() || this.generator.isInterrupted())
			this.generator = new Thread(new EventGeneratorRunnable(this.eventSet, this.subscribers));
		
		this.generator.start();
	}
	
	public synchronized void stop(){
		this.generator.interrupt();
	}

	private static class EventGeneratorRunnable implements Runnable {
		
		private Set<PushEvent> eventSet;
		private LinkedBlockingQueue<DeferredResult<Set<PushEvent>>> subscribers;

		public EventGeneratorRunnable(Set<PushEvent> eventSet, LinkedBlockingQueue<DeferredResult<Set<PushEvent>>> subscribers) {
			this.eventSet = eventSet;
			this.subscribers = subscribers;
		}
		
		public void run() {
			while (true) {
				try {
					Thread.sleep(eventInterval);
					PushEvent pe = new PushEvent();
					this.eventSet.add(pe);
					if (this.subscribers!=null && !this.subscribers.isEmpty()){
						while(!this.subscribers.isEmpty()){
							DeferredResult<Set<PushEvent>> dr = this.subscribers.take();
							dr.setResult(this.eventSet);
						}
					}
				} catch (InterruptedException ie){
					ie.printStackTrace();
				}
			}
		}
	}
}
