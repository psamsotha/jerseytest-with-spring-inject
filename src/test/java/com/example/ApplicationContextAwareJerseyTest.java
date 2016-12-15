package com.example;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class ApplicationContextAwareJerseyTest extends JerseyTest {

    protected ApplicationContext applicationContext;


    /* This causes the test to fail. So not sure how else we can reset the context. */
    // @Before
    // public void setUp() throws Exception {
    //     ((ConfigurableApplicationContext)this.applicationContext).refresh();
    //    super.setUp();
    // }

    @Override
    protected final ResourceConfig configure() {
        final ResourceConfig config = new ResourceConfig();
        configureApplication(config);

        this.applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        config.property("contextConfig", this.applicationContext);
        final AutowireCapableBeanFactory bf = this.applicationContext.getAutowireCapableBeanFactory();
        bf.autowireBean(this);
        return config;
    }

    public final ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    protected void configureApplication(ResourceConfig resourceConfig) {};
}
