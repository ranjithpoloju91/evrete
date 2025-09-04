package com.example.promotion.controller;
import org.evrete.KnowledgeService;
import org.evrete.api.StatelessSession;
import org.evrete.dsl.annotation.Rule;
import org.evrete.dsl.annotation.RuleSet;
import org.evrete.dsl.annotation.Fact;
import org.evrete.dsl.annotation.Where;

import java.io.IOException;

public class FeverDetectionExample {

    public static void main(String[] args) {
        KnowledgeService service = new KnowledgeService();
        try {
            StatelessSession session = null;
            try {
                session = service.newKnowledge()
                        .importRules("JAVA-CLASS", FeverRuleset.class)
                        .newStatelessSession();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Insert facts (person objects) into the session
            session.insert(new Person("Alice", 98.6));
            session.insert(new Person("Bob", 101.2));
            session.insert(new Person("Charlie", 99.5));

            // Fire the rules
            session.fire();

        } finally {
            service.shutdown(); // Important to shut down the service
        }
    }

    // Define the ruleset
    @RuleSet
    public static class FeverRuleset {

        @Rule("Check for Fever")
        @Where("$p.temperature >= 100.0")
        public void checkForFever(@Fact("$p") Person person) {
            System.out.println(person.getName() + " has a fever! Temperature: " + person.getTemperature());
        }

        @Rule("No Fever")
        @Where("$p.temperature < 100.0")
        public void noFever(@Fact("$p") Person person) {
            System.out.println(person.getName() + " does NOT have a fever. Temperature: " + person.getTemperature());
        }
    }

    // Simple fact class
    public static class Person {
        private String name;
        private double temperature;

        public Person(String name, double temperature) {
            this.name = name;
            this.temperature = temperature;
        }

        public String getName() {
            return name;
        }

        public double getTemperature() {
            return temperature;
        }
    }
}