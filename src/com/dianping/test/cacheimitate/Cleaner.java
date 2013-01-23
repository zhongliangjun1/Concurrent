package com.dianping.test.cacheimitate;

import java.util.concurrent.DelayQueue;

/**
 * 缓存回收者
 * @author liangjun.zhong
 * @version 创建时间：Jan 24, 2013 1:19:13 AM
 */
public class Cleaner implements Runnable{
	
	private DelayQueue<CacheItem> items;
	
	public Cleaner(DelayQueue<CacheItem> items){
		super();
		this.items = items;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.interrupted()) {
			if(items!=null){
				try {
					//take() Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
					items.take().destory();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	
	
	
	

}
