package com.dianping.test.visibility;

public class NoVisibility1 {
	
	private static boolean ready;
	private static int number;
	
	private static class ReaderThread extends Thread {	
		public void run() {
			while(!ready) {
				System.out.println("wait...");
			    Thread.yield();
			}
			System.out.println(number);
		}
	}
	
	public static void main(String[] args) {
		new ReaderThread().start();
		number = 42;
		ready = true;
	}

}
