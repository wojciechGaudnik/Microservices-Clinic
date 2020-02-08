package com.clinics.common;

import java.util.ArrayList;
import java.util.List;

public interface ROLE {
	String SYSTEM_ADMIN = "system_admin";
	String DOCTOR = "doctor";
	String PATIENT = "patient";
	String ASSISTANT = "assistant";

	default List<String> getAllRoles(){
		return new ArrayList<>(){{
			add("[ROLE_" + SYSTEM_ADMIN + "]");
			add("[ROLE_" + DOCTOR + "]");
			add("[ROLE_" + PATIENT + "]");
			add("[ROLE_" + ASSISTANT + "]");
		}};
	}
}
