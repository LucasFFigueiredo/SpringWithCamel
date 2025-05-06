package com.camelwspring.microservice.camelmicroservicea.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FirstTimerRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception{
        //queue from timer
        //transformation - body
        //database from log

//Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]] first test
//Exchange[ExchangePattern: InOnly, BodyType: String, Body: Scoppia Che La Vittoria e Nostra] second test
//Exchange[ExchangePattern: InOnly, BodyType: String, Body: Time now is 2025-05-06T15:29:10.161619600] third test


        from("timer:first-timer")
        .transform().constant("Scoppia Che La Vittoria e Nostra")
        .to("log:first-timer");
    }

}
