package com.oneupsecurity.xxedetector;
import java.lang.instrument.Instrumentation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Main app begin");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        
    }
}
