package de.ustutt.iaas.cc.core;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Scheduler with round robin routing.
 * 
 * @author davidkopp
 *
 */
public class RoundRobinTextProcessorScheduler extends TextProcessorScheduler {

    private final AtomicInteger counter = new AtomicInteger(0);

    public RoundRobinTextProcessorScheduler(List<String> textProcessorResources, Client client) {
        super(textProcessorResources, client);
    }

    @Override
    public String getName() {
        return "Round Robin";
    }

    @Override
    protected WebTarget schedule() {
        int index = counter.incrementAndGet() % targets.size();

        return targets.get(index);
    }

}