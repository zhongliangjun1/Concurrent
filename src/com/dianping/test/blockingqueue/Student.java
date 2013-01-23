package com.dianping.test.blockingqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 类说明
 * @author liangjun.zhong
 * @version 创建时间：Jan 23, 2013 8:43:37 PM
 */
public class Student implements Runnable,Delayed{
	
	private String name;  
    private long submitTime;//交卷时间  
    private long workTime;//考试时间  
    public Student() {  
        // TODO Auto-generated constructor stub  
    }  
    public Student(String name, long submitTime) {  
        super();  
        this.name = name;  
        workTime = submitTime;  //workTime预设某该学生的考试所需时间，类似与某缓存的失效时间
        //都转为转为ns  
        //计算从考试开始+该学生答卷所需时间=其交卷的具体时间点
        this.submitTime = TimeUnit.NANOSECONDS.convert(submitTime, TimeUnit.MILLISECONDS) + System.nanoTime();  
    }  
  
    @Override  
    public void run() {  
        System.out.println(name + " 交卷,用时" + workTime/100 + "分钟");  
    }  
  
    @Override  
    public long getDelay(TimeUnit unit) {  
    	long distance = unit.convert(submitTime - System.nanoTime(), unit.NANOSECONDS);
    	System.out.println(name+": getDelay 检测一下是不是该学生可以完成的时间点:"+distance); 
    	System.out.println("submitTime - System.nanoTime()"+(submitTime - System.nanoTime()));
        return distance;//unit.convert(submitTime - System.nanoTime(), unit.NANOSECONDS);  
    }  
  
    //Compares this object with the specified object for order. 
    //Returns a negative integer, zero, or a positive integer 
    //as this object is less than, equal to, or greater than the specified object.
    @Override  
    public int compareTo(Delayed o) {  
        Student that = (Student) o;  
        return submitTime > that.submitTime?1:(submitTime < that.submitTime ? -1 : 0);  
    }  
    
    
    public static class EndExam extends Student{  
        private ExecutorService exec;  
        public EndExam(int submitTime,ExecutorService exec) {  
            super(null,submitTime);  
            this.exec = exec;  
        }  
        @Override  
        public void run() {  
            exec.shutdownNow();  
        }  
    }  
    
    

}
