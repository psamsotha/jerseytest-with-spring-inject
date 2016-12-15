package com.example;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class SimpleJerseyTest extends ApplicationContextAwareJerseyTest {


    @Path("test")
    public static class SimpleResource {
        @Autowired
        private MessageService service;

        @GET
        public String getMessage() {
            return this.service.getMessage();
        }
    }

    @Override
    public void configureApplication(ResourceConfig config) {
        config.register(SimpleResource.class);
        config.register(new LoggingFilter(Logger.getAnonymousLogger(), true));
    }

    @Autowired
    private MessageService messageService;

    @Test
    public void doitOnce() {
        messageService.setMessage("BOOYAH");
        final Response response = target("test").request().get();
        assertEquals("BOOYAH", response.readEntity(String.class));
    }

    @Test
    public void doitTwice() {
        messageService.setMessage("YAHOO");
        final Response response = target("test").request().get();
        assertEquals("YAHOO", response.readEntity(String.class));
    }
}
