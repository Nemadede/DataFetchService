package org.datafetch.service;

import org.datafetch.service.puller.PullService;
import org.datafetch.service.utils.Connection;
import org.datafetch.service.utils.ReadPropertyFile;
import org.datafetch.service.utils.Schedule;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.quartz.SchedulerException;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/api/";
    public static Schedule scheduler;
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in org.datafetch.service package
        final ResourceConfig rc = new ResourceConfig().packages("org.datafetch.service");

        try {
            new Connection().setUp();
            new ReadPropertyFile().setProp();
            scheduler = Schedule.getInstance();
            scheduler.getMyScheduler().start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, SchedulerException {
        final HttpServer server = startServer();
//        new Queries().testQuery(1);
//        new PullService().runPullService(1);
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();

        scheduler.getMyScheduler().shutdown();
        server.stop();

    }
}

