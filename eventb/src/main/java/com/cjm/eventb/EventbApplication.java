package com.cjm.eventb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

import com.cjm.eventb.stream.EventExchange;

@SpringBootApplication
@EnableBinding(value = { Sink.class, EventExchange.class })
public class EventbApplication {

	// https://stackabuse.com/spring-cloud-stream-with-rabbitmq-message-driven-microservices

	public static void main(String[] args) {
		SpringApplication.run(EventbApplication.class, args);
	}

}
