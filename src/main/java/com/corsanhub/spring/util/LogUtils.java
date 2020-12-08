package com.corsanhub.spring.util;

import static net.logstash.logback.marker.Markers.append;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class LogUtils {

	public static Marker marker(Object obj) {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
		Integer lineNumber = ste.getLineNumber();
		String methodName = ste.getMethodName();

		Marker marker = MarkerFactory.getMarker(obj.toString());
		try {
			InetAddress host = InetAddress.getLocalHost();
			marker.add(append("host", host.getHostName() + "/" + host.getHostAddress()));
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}

		marker.add(append("method", methodName).and(append("lineNumber", lineNumber)));
		return marker;

	}
}