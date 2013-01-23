package com.dianping.test.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SerTest {


	public static void main(String[] args) {
		boolean a = false;//false;
		if(a){
			try
	        {
	            Person ted = new Person("Ted", "Neward");
	            Person charl = new Person("Charlotte",
	                "Neward");
	            
//	            Person ted = new Person("Ted", "Neward", 39);
//	            Person charl = new Person("Charlotte",
//	                "Neward", 38);

	            ted.setSpouse(charl); charl.setSpouse(ted);
	            
	            Dog d = new Dog("jack",2);
	            FileOutputStream fo = new FileOutputStream("D:/dog.ser");
	            ObjectOutputStream oo = new ObjectOutputStream(fo);
	            oo.writeObject(d);
	            oo.close();
	            

	            FileOutputStream fos = new FileOutputStream("D:/tempdata.ser");
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(ted);
	            oos.close();
	        }
	        catch (Exception ex)
	        {
	            System.out.println("Exception thrown during test: " + ex.toString());
	        }
		}
		
        
        try
        {
        	/**
            FileInputStream fis = new FileInputStream("D:/tempdata.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Person ted = (Person) ois.readObject();
            ois.close();
            System.out.println(ted.getFirstName());
            System.out.println(ted.getSpouse().getFirstName());
            */
            //assertEquals(ted.getFirstName(), "Ted");
            //assertEquals(ted.getSpouse().getFirstName(), "Charlotte");

            // Clean up the file
            //new File("tempdata.ser").delete();
        	
        	FileInputStream fis = new FileInputStream("D:/dog.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Dog ted = (Dog) ois.readObject();
            ois.close();
            //System.out.println(ted.getName());
            //System.out.println(ted.getAge());
            
        }
        catch (Exception ex)
        {
        	System.out.println("Exception thrown during test: " + ex.toString());
        }
        
       
	    

	}

}
