package com.apr7.sponge.schedule.task;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseSpongeTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseSpongeTask.class);

	private ReentrantLock reentrantLock;

	protected boolean checkSwitch() {
		return true;
	}

	protected boolean concurrent() {
		return false;
	}

	public void task() {
		if (checkSwitch()) {
			if (concurrent()) {
				if (reentrantLock == null) {
					synchronized (this) {
						if (reentrantLock == null) {
							reentrantLock = new ReentrantLock();
						}
					}
				}
				if (reentrantLock.tryLock()) {
					try {
						this.work();
					} finally {
						reentrantLock.unlock();
					}
				}
			} else {
				this.work();
			}
		}
	}

	public void work() {
		LOGGER.info("task doAction start: {}", this.getClass());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		doAction();
		stopWatch.stop();
		LOGGER.info("task doAction finish: {}: {}ms", this.getClass(), stopWatch.getTime());
	}

	public abstract void doAction();
}
