package com.cjm.eventb.facade;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cjm.eventb.stream.EventOut;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TriggerFacade {

	private MessageChannel eventChannel;

	public TriggerFacade(EventOut eventOut) {
		eventChannel = eventOut.event();
	}

	@PostMapping("/event/{event}/trigger")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void greeting(@PathVariable final String event, @RequestHeader("xtid") final String headerXtid,
			@RequestBody final String message) {

		try {

			val xtid = StringUtils.isBlank(headerXtid) ? UUID.randomUUID().toString() : headerXtid;
			MDC.put("xtid", xtid);

			log.info("Posting event '{}' with message '{}'", event, message);

			val eventMessage = MessageBuilder.withPayload(message)
					//
					.setHeader("event", event)
					//
					.setHeader("xtid", xtid).build();

			eventChannel.send(eventMessage);

		} finally {
			MDC.clear();
		}

	}

}
