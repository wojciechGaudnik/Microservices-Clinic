package com.clinics.common.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.WordUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageCustom {

	@Builder.Default
	private Date timeStamp = new Date();
	@Builder.Default
	private HttpStatus status = HttpStatus.BAD_REQUEST;
	@Builder.Default
	private String error = String.valueOf(HttpStatus.BAD_REQUEST);
	@Builder.Default
	private HashMap<String, String> errors = null;
	private String path;
	@Builder.Default
	@JsonIgnore
	private WebRequest webRequest = null;

	public String getTimeStamp(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
		return simpleDateFormat.format(this.timeStamp);
	}

	public String getPath(){
		return (this.webRequest != null)?((ServletWebRequest) this.webRequest).getRequest().getRequestURI():null;

	}

	public int getStatus(){
		return status.value();
	}

	public String getError() {
		return WordUtils.capitalizeFully(error.replace("_", " ").replaceAll("(\\d)+\\s", ""));
	}
}
