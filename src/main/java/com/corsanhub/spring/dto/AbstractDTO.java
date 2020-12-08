package com.corsanhub.spring.dto;

import com.corsanhub.spring.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public abstract class AbstractDTO {

	public String toString() {
		return JsonUtils.toJson(this);
	}

}
