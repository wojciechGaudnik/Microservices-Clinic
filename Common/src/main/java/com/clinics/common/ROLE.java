package com.clinics.common;

import java.util.ArrayList;
import java.util.List;

public interface ROLE {
	String SYSTEM_ADMIN = "ADMIN";
	String DOCTOR = "DOCTOR";
	String PATIENT = "PATIENT";
	String ASSISTANT = "ASSISTANT";

	default List<String> getAllRoles(){
		return new ArrayList<>(){{
			add("ROLE_" + SYSTEM_ADMIN);
			add("ROLE_" + DOCTOR);
			add("ROLE_" + PATIENT);
			add("ROLE_" + ASSISTANT);
		}};
	}
}
