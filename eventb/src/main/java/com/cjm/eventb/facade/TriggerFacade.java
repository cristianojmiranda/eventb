package com.cjm.eventb.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cjm.eventb.stream.EventExchange;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TriggerFacade {

	@Autowired
	private EventExchange eventBinding;

	@PostMapping("/event/{event}/trigger")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void greeting(@PathVariable final String event, @RequestBody final String message) {

		log.info("Triggered event {} -> message: {}", event, message);
		eventBinding.event().send(MessageBuilder.withPayload(message).build());

	}

}
