package com.clinics.common.security;

import java.util.ArrayList;
import java.util.List;

public interface Role {
	String SYSTEM_ADMIN = "system_admin";
	String DOCTOR = "doctor";
	String PATIENT = "patient";
	String ASSISTANT = "assistant";

	default List<String> getAllRoles(){
		return new ArrayList<>(){{
			add(SYSTEM_ADMIN);
			add(DOCTOR);
			add(PATIENT);
			add(ASSISTANT);
		}};
	}
}
