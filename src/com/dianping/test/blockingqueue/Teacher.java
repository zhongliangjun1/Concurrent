package com.dianping.test.blockingqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;

/**
 * 类说明
 * @author liangjun.zhong
 * @version 创建时间：Jan 23, 2013 8:59:57 PM
 */
public class Teacher implements Runnable{
	
	private DelayQueue<Student> students;  
//	  private ExecutorService exec;  
      
//    public Teacher(DelayQueue<Student> students,ExecutorService exec) {  
//        super();  
//        this.students = students;  
//        this.exec = exec;  
//    } 
    
    public Teacher(DelayQueue<Student> students) {  
        super();  
        this.students = students;    
    } 
  
  
    @Override  
    public void run() {  
        try {  
            System.out.println("考试开始……");  
            while (!Thread.interrupted()) {  
                students.take().run();  
            }  
            System.out.println("考试结束……");  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
  
    }  

}
