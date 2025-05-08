package com.camelwspring.microservice.camelmicroservicea.routes.a;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
public class FirstTimerRoute extends RouteBuilder{

    @Autowired
    private SimpleLoggingProcessingComponent loggingComponent;

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Override
    public void configure() throws Exception{
        //queue from timer
        //transformation - body
        //database from log

//Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]] first test
//Exchange[ExchangePattern: InOnly, BodyType: String, Body: Scoppia Che La Vittoria e Nostra] second test
//Exchange[ExchangePattern: InOnly, BodyType: String, Body: Time now is 2025-05-06T15:29:10.161619600] third test
        // codigo acima .transform().constant("Time now is " + LocalDateTime.now()

        from("timer:first-timer")
        .log("${body}")//recebe null

        .transform().constant("Scoppia Che La Vittoria e Nostra")
        .log("${body}")//recebe Scoppia Che La Vittoria e Nostra

        .bean(getCurrentTimeBean)
        .log("${body}")// recebe currentTimeBean

        .bean(loggingComponent)
        .log("$body{}")//recebe loggingComponent

                .process(new SimpleLoggingProcessor())

        .to("log:first-timer");
    }
}

@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time now is " + LocalDateTime.now();
    }
}

@Component
class SimpleLoggingProcessingComponent{

    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);

    public void process(String message){

        logger.info("SimpleLoggingProcessingComponent {}", message);

    }

}

class SimpleLoggingProcessor implements Processor{

    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);

    @Override
    public void process(Exchange exchange) throws Exception{

        logger.info("SimpleLoggingProcessor {}", exchange);

    }

}