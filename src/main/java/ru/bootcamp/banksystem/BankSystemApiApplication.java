package ru.bootcamp.banksystem;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class BankSystemApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankSystemApiApplication.class, args);
    }

    // Для подключения к H2 через Intellij Idea
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");

    }
}
