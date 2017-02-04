package com.tf2center.websocket;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebsocketManager {

	private final WebSocketSession socket;

	public WebsocketManager(WebSocketSession socket) {
		this.socket = socket;
	}
	
	public String getId() {
		return socket.getId();
	}

	// TODO make @async
	public void send(String str) {
		try {
			System.out.println("send: " + str);
			socket.sendMessage(new TextMessage(str));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}