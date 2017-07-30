package com.oneupsecurity.xxedetector;
import java.lang.instrument.Instrumentation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.lang.*;
import java.util.*;



class AnotherClass {
   public static void test() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
    }
}

class Main {
    
    public static void main(String[] args) throws Exception {
        System.out.println("Main app begin2");

        /*
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        System.out.println("DBF Class: " + dbf.getClass());
        DocumentBuilder db = dbf.newDocumentBuilder();
        */

        AnotherClass.test();
        
    }
    
    public void  myTest() {
        System.out.println("what");
    }
}
