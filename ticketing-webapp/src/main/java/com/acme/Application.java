package com.acme;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.scxml.env.SimpleErrorHandler;
import org.apache.commons.scxml.io.SCXMLParser;
import org.apache.commons.scxml.model.ModelException;
import org.apache.commons.scxml.model.SCXML;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SCXML getStateMachine() throws IOException, SAXException, ModelException {
        URL url = Application.class.getClassLoader().getResource("issues.xml");
        ErrorHandler errHandler = new SimpleErrorHandler();
        return SCXMLParser.parse(url, errHandler);
    }

}
