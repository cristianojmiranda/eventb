package com.cjm.eventb.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EventExchange {

	@Output("eventChannel")
	MessageChannel event();

}
