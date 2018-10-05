package edu.mayo.micro.fda.impl.application;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.server.EmbeddedServer;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class);
//        ApplicationContext.run(EmbeddedServer.class);
    }
}