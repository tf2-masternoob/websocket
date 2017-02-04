package com.tf2center.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class TF2CenterWebsocketHandler extends TextWebSocketHandler {

	private final Map<String, WebsocketManager> managers = new ConcurrentHashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("afterConnectionEstablished(): " + session.getId());
		managers.put(session.getId(), new WebsocketManager(session));
		System.out.println("managers: " + managers.size());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.err.println("handleTextMessage(): " + session.getId() + " | " + message.getPayload());
	}

	@Override
	protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
		System.err.println("handlePongMessage(): " + session.getId());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		managers.remove(session.getId());
		System.err.println("Closing: "+session.getId()+": "+status);
	}

	@Scheduled(fixedRate = 2000)
	public void run() {
		sendAll("heart beat: " + System.currentTimeMillis());
	}

	// TODO make @async
	public void sendAll(String str) {
		for (WebsocketManager manager : managers.values()) {
			manager.send(str);
		}
	}

}