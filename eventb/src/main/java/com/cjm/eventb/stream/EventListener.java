package com.cjm.eventb.stream;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class EventListener {

	@StreamListener(target = "eventChannel")
	public void handle(String message) {

		System.out.println("Received: " + message);

	}

}
