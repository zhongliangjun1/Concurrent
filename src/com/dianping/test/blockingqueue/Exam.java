package com.dianping.test.blockingqueue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类说明
 * @author liangjun.zhong
 * @version 创建时间：Jan 23, 2013 8:57:31 PM
 */
public class Exam {
	
	static final int STUDENT_SIZE = 45; 
	
    public static void main(String[] args) {  
    	
        Random r = new Random();  
        
        DelayQueue<Student> students = new DelayQueue<Student>();  
        ExecutorService exec = Executors.newCachedThreadPool();  
        
        //开始发卷
        for(int i = 0; i < STUDENT_SIZE; i++){  
            students.put(new Student("学生" + ( i + 1), 3000 + r.nextInt(9000)));  
        }  
        
        students.put(new Student.EndExam(12000,exec));//1200为考试结束时间  
        
        //exec.execute(new Teacher(students, exec));
        exec.execute(new Teacher(students));  //老师是单个线程去学生的delayqueue中查询是否有人已经做好了
          
    }  

}
