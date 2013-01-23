package com.dianping.test.compare;

import java.util.concurrent.CyclicBarrier;

class AtomicTest extends TestTemplate{ 
	
    public AtomicTest(String _id,int _round,int _threadNum,CyclicBarrier _cb){     
        super( _id, _round, _threadNum, _cb);     
    }  
    
    @Override          
    long  getValue() {     
        return super.countValueAtmoic.get();     
    }     
    @Override    
    void  sumValue() {     
        super.countValueAtmoic.addAndGet(super.preInit[indexAtomic.get()%round]);     
    }     
}     