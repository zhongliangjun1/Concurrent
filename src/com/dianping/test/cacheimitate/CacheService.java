package com.dianping.test.cacheimitate;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类说明
 * @author liangjun.zhong
 * @version 创建时间：Jan 24, 2013 1:26:41 AM
 */
public class CacheService {
  
	//private static DelayQueue<CacheItem> items = new DelayQueue<CacheItem>();
	
	public void addCache(DelayQueue<CacheItem> items, CacheItem cacheItem){
		//Inserts the specified element into this queue, waiting if necessary for space to become available.
		items.put(cacheItem);
		cacheItem.recordAddTime();
		//do 将记录存入缓存服务器，在此略 items类似于是对缓存服务器中记录的映射
	}
	
	
	
	
	public static void main(String[] args) {
		DelayQueue<CacheItem> items = new DelayQueue<CacheItem>();
		CacheService cacheService = new CacheService();
		ExecutorService exec = Executors.newCachedThreadPool(); 
		
		Random r = new Random();
		for(int i=1; i<40; i++){
			String key = "key"+i;
			Object value = "value"+i;
			long survivalPeriod = 3000 + r.nextInt(9000);
			CacheItem cacheItem = new CacheItem(key, value, survivalPeriod); //添加缓存
			cacheService.addCache(items, cacheItem);
		}
		
		
		exec.execute(new Cleaner(items));

	}

}
