package com.sparta.AlphaTeam.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Timer {
	private static final Logger LOG = LogManager.getLogger(Timer.class);
	double start;

	public void start(){
		LOG.info("Starting timer.");
		start = System.nanoTime();
	}
	public double stop(){
		LOG.info("Stopping timer.");
		return System.nanoTime() - start;
	}
}
