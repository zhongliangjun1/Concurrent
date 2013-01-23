package com.dianping.test.cacheimitate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


/**
 * 缓存项
 * @author liangjun.zhong
 * @version 创建时间：Jan 23, 2013 9:40:54 PM
 */
public class CacheItem implements Delayed{
	
	private String key;
	private Object value;
	
	private long survivalPeriod; //存活时间段
	private long diePoint; //失效时间点
		
	private String addTime; //加入时刻

	
	public CacheItem(String key, Object value, long survivalPeriod){
		this.key = key;
		this.value = value;
		this.survivalPeriod = survivalPeriod;
		//转为ns
		this.diePoint = TimeUnit.NANOSECONDS.convert(survivalPeriod, TimeUnit.MILLISECONDS) + System.nanoTime();		
	}

	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		CacheItem other = (CacheItem) o;  
        return this.diePoint > other.diePoint?1:(this.diePoint < other.diePoint ? -1 : 0); 
	}

	@Override
	public long getDelay(TimeUnit unit) {
		//Compares this object with the specified object for order. 
	    //Returns a negative integer, zero, or a positive integer 
	    //as this object is less than, equal to, or greater than the specified object.
		//System.out.println("diePoint - System.nanoTime()"+(diePoint - System.nanoTime()));
		long distance = unit.convert(diePoint - System.nanoTime(), unit.NANOSECONDS);
    	System.out.println(this.key+": getDelay 检测计算距离缓存失效时间点的distance："+distance);  	
        return distance;
	}
	
	/**
	 * 回收缓存记录
	 */
	public void destory(){
		System.out.println(this.key + "存活" + this.survivalPeriod/100 + "分钟");//,加入时刻:"+this.addTime+" 失效时刻:"+getTimeNow()); 
	}
		
	/**
	 * 记录缓存被存入的时刻
	 */
	public void recordAddTime() {
		this.addTime = getTimeNow();
	}

	private String getTimeNow(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String strTime = format.format(date);
		return strTime;
	}

}
