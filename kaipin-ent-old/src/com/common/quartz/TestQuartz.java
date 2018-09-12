package com.common.quartz;

public class TestQuartz {
	public void show() throws InterruptedException{
		for (int i = 1; i < 6; i++) {
			Thread.sleep(1000);
			System.out.println(i);
		}
	}

}
