package com.cjm.eventb.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface EventIn {

	@Input
	SubscribableChannel event();

}
