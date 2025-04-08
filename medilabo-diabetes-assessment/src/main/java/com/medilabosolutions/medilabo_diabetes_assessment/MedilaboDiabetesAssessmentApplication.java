package com.medilabosolutions.medilabo_diabetes_assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = "com.medilabosolutions.medilabo_diabetes_assessment.client")
public class MedilaboDiabetesAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedilaboDiabetesAssessmentApplication.class, args);
	}

}
