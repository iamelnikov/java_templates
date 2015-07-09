package ru.imelnikov.template.concurrency.person.parts;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import org.apache.log4j.Logger;

import ru.imelnikov.template.concurrency.Event;

public class NervousSystem implements BodyPart {

	private static final long serialVersionUID = -1894469284873911300L;
	private static Logger LOG = Logger.getLogger(NervousSystem.class);

	private ExecutorService pool;
	private PriorityBlockingQueue<PersonBodyInnerEvent> innerEventQueue;
	private PriorityBlockingQueue<PersonBodyOuterEvent> outerEventQueue;

	public NervousSystem() {
		this.innerEventQueue = new PriorityBlockingQueue<PersonBodyInnerEvent>(
				50);
		this.outerEventQueue = new PriorityBlockingQueue<PersonBodyOuterEvent>(
				15);
		this.pool = Executors.newCachedThreadPool();
		pool.submit(new InnerEventHandler(innerEventQueue));
		pool.submit(new OuterEventHandler(outerEventQueue, innerEventQueue));
	}

	void sendInnerEvent(PersonBodyInnerEvent event) {
		this.innerEventQueue.offer(event);
	}

	public void sendOuterEvent(PersonBodyOuterEvent event) {
		this.outerEventQueue.offer(event);
	}

	private void sendActionToBodyPart(Event event) {
		// TODO
		assert false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public double getHealth() {
		// TODO
		assert false;
		return 0;
	}

	private static class InnerEventHandler implements Runnable {

		private PriorityBlockingQueue<PersonBodyInnerEvent> queue;

		InnerEventHandler(PriorityBlockingQueue<PersonBodyInnerEvent> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			assert queue != null;
			while (true) {
				try {
					PersonBodyInnerEvent event = queue.take();
				} catch (InterruptedException e) {
					LOG.error("Could not process event");
					e.printStackTrace();
				}
			}
		}
	}

	private static class OuterEventHandler implements Runnable {

		private PriorityBlockingQueue<PersonBodyOuterEvent> queue;
		private PriorityBlockingQueue<PersonBodyInnerEvent> inQueue;
		private Map<PersonBodyOuterEvent, PersonBodyInnerEvent> eventMap;
		
		OuterEventHandler(PriorityBlockingQueue<PersonBodyOuterEvent> queue, PriorityBlockingQueue<PersonBodyInnerEvent> inQueue) {
			this.queue = queue;
			this.inQueue = inQueue;
		}

		@Override
		public void run() {
			assert queue != null;
			while (true) {
				try {
					PersonBodyOuterEvent event = queue.take();
					PersonBodyInnerEvent inEvent = eventMap.get(event);
					assert inEvent!=null;
					inQueue.offer(inEvent);
				} catch (InterruptedException e) {
					LOG.error("Could not process event");
					e.printStackTrace();
				}
			}
		}
	}
}
