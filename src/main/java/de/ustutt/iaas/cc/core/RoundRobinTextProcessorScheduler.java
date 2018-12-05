package de.ustutt.iaas.cc.core;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Scheduler with round robin routing.
 * 
 * @author davidkopp
 *
 */
public class RoundRobinTextProcessorScheduler extends TextProcessorScheduler {

    private int currentIndex;

    public RoundRobinTextProcessorScheduler(List<String> textProcessorResources, Client client) {
        super(textProcessorResources, client);

        currentIndex = -1;
    }

    @Override
    public String getName() {
        return "Round Robin";
    }

    @Override
    protected WebTarget schedule() {
        WebTarget target = null;

        int nextIndex;
        if (currentIndex < 0) {
            // Initializing
            nextIndex = 0;
        } else {
            // Next target
            nextIndex = currentIndex + 1;
            if (nextIndex > targets.size() - 1) {
                // Begin again with first target
                nextIndex = 0;
            }
        }

        target = targets.get(nextIndex);
        currentIndex = nextIndex;
        return target;
    }

}