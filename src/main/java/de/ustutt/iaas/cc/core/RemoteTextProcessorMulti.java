package de.ustutt.iaas.cc.core;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A text processor that sends the text to one of a set of remote REST API for
 * processing (and balances the load between them round-robin).
 * 
 * @author hauptfn
 *
 */
public class RemoteTextProcessorMulti implements ITextProcessor {

	private final static Logger logger = LoggerFactory.getLogger(RemoteTextProcessorMulti.class);

	private final TextProcessorScheduler scheduler;

	public RemoteTextProcessorMulti(TextProcessorScheduler scheduler) {
		super();
		this.scheduler = scheduler;
	}

	@Override
	public String process(String text) {
		String processedText = "[processed by RemoteTextProcessorMulti] - " + text;

		WebTarget target = scheduler.schedule();
		logger.info("Use `TextProcessor` instance with URI '" + target.getUri() + "'");
		String processed = target.request(MediaType.TEXT_PLAIN).post(Entity.entity(processedText, MediaType.TEXT_PLAIN),
				String.class);

		return processed;
	}
}
