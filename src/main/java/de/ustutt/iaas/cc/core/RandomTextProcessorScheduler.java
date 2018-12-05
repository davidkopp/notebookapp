package de.ustutt.iaas.cc.core;

import java.util.List;
import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Scheduler with random routing.
 * 
 * @author davidkopp
 *
 */
public class RandomTextProcessorScheduler extends TextProcessorScheduler {

    public RandomTextProcessorScheduler(List<String> textProcessorResources, Client client) {
        super(textProcessorResources, client);
    }

    @Override
    public String getName() {
        return "Random";
    }

    @Override
    protected WebTarget schedule() {
        WebTarget next = null;

        Random r = new Random();
        int index = r.nextInt(targets.size());
        next = targets.get(index);

        return next;
    }

}