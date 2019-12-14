package com.clinicsmicroservices.doctor.configuration;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Component
@ConfigurationProperties("doctor-service")
public class
DoctorConfiguration {
	private int bq666;
}
