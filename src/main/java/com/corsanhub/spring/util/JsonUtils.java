package com.corsanhub.spring.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.corsanhub.spring.dto.StatusDTO;
import com.corsanhub.spring.exception.BaseException;

public class JsonUtils {
	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.disable(MapperFeature.AUTO_DETECT_CREATORS, MapperFeature.AUTO_DETECT_FIELDS, MapperFeature.AUTO_DETECT_GETTERS,
				MapperFeature.AUTO_DETECT_IS_GETTERS);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	private JsonUtils() {
	}

	public static StatusDTO deserialize(String json) throws Exception {
		logger.debug("json: " + json);
		StatusDTO dto = mapper.readValue(json, StatusDTO.class);
		logger.debug("dto: " + dto);

		return dto;
	}

	public static String toJson(Object obj) {
		try {
			String jsonStr = mapper.writeValueAsString(obj);
			return jsonStr;
		}
		catch (JsonProcessingException ex) {
			ex.printStackTrace();
			throw new BaseException(ex);
		}
	}

}