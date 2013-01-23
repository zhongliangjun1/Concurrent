package com.dianping.test.compare;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

class LockTest extends TestTemplate{     
    ReentrantLock lock=new ReentrantLock();     
    public LockTest(String _id,int _round,int _threadNum,CyclicBarrier _cb){     
        super( _id, _round, _threadNum, _cb);     
    }     
    
    
    @Override    
    long getValue() {     
        try{     
            lock.lock();     
            return super.countValue;     
        }finally{     
            lock.unlock();     
        }     
    }     
    @Override    
    void sumValue() {     
        try{     
            lock.lock();     
            //super.countValue+=preInit[index++%round];            
			//Thread.sleep(1);
//            for(int i=1;i<100;i++){
//            	i++;
//            }
			
        }
        catch (Exception e) {
        	
        }
        finally{     
            lock.unlock();     
        }     
    }     
}     
