package com.tf2center.websocket.mvc;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HealthZController {
	
	public static final String URL = "/healthz";

	private static long startupTime;

	static {
		startupTime = System.currentTimeMillis();
	}

	@RequestMapping(URL)
	public String healthz() throws UnknownHostException {
		for (InetAddress addr : InetAddress.getAllByName(null)) {
			System.out.println(addr.getHostAddress());
		}
		System.out.println(InetAddress.getLocalHost().getHostAddress());
		
		if (!hasJustStarted()) {
			throw new RuntimeException("Health check failed");
		}
		return "index";
	}

	private static boolean hasJustStarted() {
		return System.currentTimeMillis() - startupTime < 30 * 1000;
	}
}