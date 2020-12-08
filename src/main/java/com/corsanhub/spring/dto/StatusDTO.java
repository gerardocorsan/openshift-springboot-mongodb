package com.corsanhub.spring.dto;

import java.io.Serializable;

public class StatusDTO extends AbstractDTO implements Serializable {
	private static final long serialVersionUID = -1923177406483987240L;

	private String key;
	private String status;

	public StatusDTO(String key, String status) {
		super();
		this.key = key;
		this.status = status;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
