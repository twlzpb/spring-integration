/* Copyright 2002-2008 the original author or authors.
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

package org.springframework.integration.samples.errorhandling;

import org.springframework.integration.Message;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.MessagingException;
import org.springframework.integration.message.ErrorMessage;

/**
 * @author Iwein Fuld
 */
@MessageEndpoint
public class ErrorUnwrapper {

	@Transformer
	public Message<?> transform(ErrorMessage errorMessage) {
		return ((MessagingException) errorMessage.getPayload()).getFailedMessage();
	}

}
