package com.example.G4_DailyReport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class G4DailyReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(G4DailyReportApplication.class, args);
	}

}
