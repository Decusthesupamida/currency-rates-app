package com.krautsou.currencyexchange.initializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public class PostgresInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final Logger log = LoggerFactory.getLogger(PostgresInitializer.class);
    @Container
    private final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:11.1");

    public PostgresInitializer() {
    }

    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        this.postgres.start();
        TestPropertyValues testPropertyValues = TestPropertyValues.of(new String[]{"spring.datasource.url=" + this.postgres.getJdbcUrl(), "spring.datasource.username=" + this.postgres.getUsername(), "spring.datasource.password=" + this.postgres.getPassword()});
        log.info("postgres jdbc url: {} username: {} password: {}", new Object[]{this.postgres.getJdbcUrl(), this.postgres.getUsername(), this.postgres.getPassword()});
        testPropertyValues.applyTo(configurableApplicationContext);
    }
}
