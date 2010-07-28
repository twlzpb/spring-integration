/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.samples.ws;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.context.BeanFactoryChannelResolver;
import org.springframework.integration.core.ChannelResolver;
import org.springframework.integration.core.MessageBuilder;
import org.springframework.integration.core.MessageChannel;

/**
 * Demonstrates a web service invocation through a Web Service outbound Gateway.
 * A header-enricher provides the Soap Action prior to invocation. See the
 * 'temperatureConversion.xml' configuration file for more detail.
 * 
 * @author Marius Bogoevici
 */
public class WebServiceDemo {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("temperatureConversion.xml", WebServiceDemo.class);
		ChannelResolver channelResolver = new BeanFactoryChannelResolver(context);

		// Compose the XML message according to the server's schema
		String requestXml =
				"<FahrenheitToCelsius xmlns=\"http://tempuri.org/\">" +
				"    <Fahrenheit>90.0</Fahrenheit>" +
				"</FahrenheitToCelsius>";

		// Create the Message object
		Message<String> message = MessageBuilder.withPayload(requestXml).build();

		// Send the Message to the handler's input channel
		MessageChannel channel = channelResolver.resolveChannelName("fahrenheitChannel");
		channel.send(message);
	}

}
