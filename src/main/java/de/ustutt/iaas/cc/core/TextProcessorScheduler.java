package de.ustutt.iaas.cc.core;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Interface for the text processor scheduler e.g. Random or Round Robin.
 * 
 * @author davidkopp
 *
 */
public abstract class TextProcessorScheduler {

    protected final List<WebTarget> targets;

    public TextProcessorScheduler(List<String> textProcessorResources, Client client) {
        this.targets = new ArrayList<WebTarget>();
        textProcessorResources.forEach(tpr -> this.targets.add(client.target(tpr)));
    }

    public abstract String getName();
    protected abstract WebTarget schedule();
}
