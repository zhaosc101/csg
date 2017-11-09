package com.zsc.csg.test;

public class TestThread {
	
	public static void main(String[] args) {
		Runnan1 rn = new Runnan1();
		Thread r = new Thread(rn);
		r.start();
		for (int i = 0; i < 10; i++) {
			System.out.println("main"+i);
		}
	}
}
class Runnan1 implements Runnable {

	@Override
	public void run() {
		
		for (int i = 0; i < 10; i++) {
			System.out.println("line"+i);
		}
	}
	
}
class Thead1 extends Thread{
	
}