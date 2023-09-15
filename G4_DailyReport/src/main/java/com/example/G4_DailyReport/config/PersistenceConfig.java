package com.example.G4_DailyReport.config;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
public class PersistenceConfig {
}
