package com.clinicsmicroservices.doctor.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

	private Date timeStamp;
	private String message;
}
