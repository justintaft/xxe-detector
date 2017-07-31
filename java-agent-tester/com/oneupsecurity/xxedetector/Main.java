package com.oneupsecurity.xxedetector;
import java.lang.instrument.Instrumentation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.lang.*;
import java.util.*;

import java.util.logging.Logger;
import java.util.logging.Level;



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
    
    public static final Logger LOGGER = Logger.getLogger( Main.class.getName() );

    public static void main(String[] args) throws Exception {
        LOGGER.log(Level.INFO,"Creating secure document builder...");
        AnotherClass.secureTest();

        LOGGER.log(Level.INFO,"Creating insecure document builder...");
        AnotherClass.insecureTest();
    }
    
    public void  myTest() {
        System.out.println("what");
    }
}
