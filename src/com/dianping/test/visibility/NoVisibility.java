package com.dianping.test.visibility;

public class NoVisibility {
	
	private static boolean ready;
	private static int number;
	
	private static class ReaderThread extends Thread {
	   public int id;
	   public void run() {
	      while(!ready) {
	         System.out.println("thread"+id+": "+System.currentTimeMillis());
	         Thread.yield();
	      }
	      System.out.println("thread"+id+": "+number);
	   }
	}
	
	public static void main(String[] args) {
		for(int i=0;i < 100;i++){
			ReaderThread thread = new ReaderThread();
			thread.id = i;
			thread.start();
		}
		  number = 42;
		  ready = true;
		}

}
