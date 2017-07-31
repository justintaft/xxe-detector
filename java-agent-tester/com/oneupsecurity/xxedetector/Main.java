package com.oneupsecurity.xxedetector;
import java.lang.instrument.Instrumentation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.lang.*;
import java.util.*;



class AnotherClass {
   public static void insecureTest() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
    }

   public static void secureTest() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        DocumentBuilder db = dbf.newDocumentBuilder();
    }

}

class Main {
    
    public static void main(String[] args) throws Exception {
        System.out.println("Main app begin2");

        AnotherClass.insecureTest();
        AnotherClass.secureTest();
    }
    
    public void  myTest() {
        System.out.println("what");
    }
}
