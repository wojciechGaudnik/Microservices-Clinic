package com.clinicsmicroservices.doctor.configuration;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Slf4j
//@Setter
//@Getter
//@AllArgsConstructor(access = AccessLevel.PUBLIC)
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Component
@ConfigurationProperties("doctor-service")
public class Configuration {
	private int bq666;


	public int getBq666() {
		return bq666;
	}

	public void setBq666(int bq666) {
		log.error(String.valueOf(bq666) + " <------------------------");
		this.bq666 = bq666;
	}
}
