/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.utils;

import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Alvin
 */
public class MyLogger implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        InputStream in = getClass().getResourceAsStream("/log4j.properties");
        if(in!=null)
        {
            System.out.println("Si se hallo c:");
            PropertyConfigurator.configure(in);
            System.out.println("Logger con propiedades personalizadas ---> ");
        }
        else
        {
            System.out.println("NBo se hallo :c");
            BasicConfigurator.configure();
            System.out.println("Logger con propiedadeS predeterminadas ---> ");
        }
        System.out.println("PUPUSAS");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
        
}
