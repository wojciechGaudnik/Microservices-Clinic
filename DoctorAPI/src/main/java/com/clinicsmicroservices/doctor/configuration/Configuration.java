package com.clinicsmicroservices.doctor.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("doctor-service")
public class Configuration {
	private int bq666;
}
