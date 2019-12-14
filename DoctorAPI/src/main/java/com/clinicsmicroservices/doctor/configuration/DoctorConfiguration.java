package com.clinicsmicroservices.doctor.configuration;

import lombok.*;
import lombok.extern.slf4j.Slf4j;


//@Setter
//@Getter
//@AllArgsConstructor(access = AccessLevel.PUBLIC)
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DoctorConfiguration {
	private int bq666;

	protected DoctorConfiguration(){
	}

	public DoctorConfiguration(int number) {
		super();
		this.bq666 = number;
	}

	public int getBq666() {
		return bq666;
	}
}
