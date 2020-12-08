package com.corsanhub.spring.util;

import static com.corsanhub.spring.util.Environment.TIMEZONE;

import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AppProperties {
	private static Logger logger = LoggerFactory.getLogger(AppProperties.class);

	private static Map<String, String> propertiesMap = new TreeMap<>();
	private static Map<String, String> env = System.getenv();

	@Value("${application.timezone}")
	private String timeZone;

	private void addProperty(String name, String defaultValue) {
		String value = env.get(name);
		if (value != null) {
			logger.info("-> Adding application property from environment variable :" + name + "=\"" + value + "\"] ...");
			propertiesMap.put(name, value);
		}
		else {
			logger.info("-> Adding application property from default configuration:" + name + "=\"" + defaultValue + "\"] ...");
			propertiesMap.put(name, defaultValue);
		}
	}

	@PostConstruct
	private void init() {
		addProperty(TIMEZONE, timeZone);

	}

	public String getProperty(String name) {
		String property = propertiesMap.get(name);
		logger.debug("Getting property:[" + name + "=\"" + property + "\"] ...");
		return property;
	}

}