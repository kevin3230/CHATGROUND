package com.chatground.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.chatground.jedis.JedisHandleMessage;
import com.chatground.model.ChatGroundMessage;

@ServerEndpoint("/ChatGroundWS/{memNo}")
public class ChatGroundWS {
	private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();
	
	@OnOpen
	public void onOpen(@PathParam("memNo") String memNo, Session userSession) throws IOException{
		/* save the new user in the map */
		sessionMap.put(memNo, userSession);
		int onlineCounter = sessionMap.keySet().size();
		ChatGroundMessage chatgroundMessage = new ChatGroundMessage();
		chatgroundMessage.setType("open");
		chatgroundMessage.setOnlineCounter(onlineCounter);
		String chatgroundMessageGson = gson.toJson(chatgroundMessage);
		Collection<Session> sessions = sessionMap.values();
		for(Session session : sessions) {
			if(session.isOpen()) {
				session.getAsyncRemote().sendText(chatgroundMessageGson);
			}
		}
		
//		System.out.printf("Session ID = %s, connected; userName = %s%nusers: %s%n", userSession.getId(),
//				memNo, sessionMap.keySet());
	}
	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatGroundMessage chatgroundMessage = gson.fromJson(message, ChatGroundMessage.class);
		String sender = chatgroundMessage.getSender();
		MembersService memSvc = new MembersService();
		Integer memNo = Integer.parseInt(sender);
		String senderName = memSvc.findByPrimaryKey(memNo).getMemNickName();
		chatgroundMessage.setSender(senderName);
		String chatgroundMessageGson = gson.toJson(chatgroundMessage);
		
		Collection<Session> sessions = sessionMap.values();
		for(Session session : sessions) {
			if(session.isOpen()) {
				session.getAsyncRemote().sendText(chatgroundMessageGson);
				JedisHandleMessage.saveChatGroundMessage(sender, message);
			}
		}
		
//		System.out.println("Message received: " + chatgroundMessage.getMessage());
//		System.out.println(message);
	}
	
	@OnError
	public void onError(Session session, Throwable e) {
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String memNoClose = null;
		Set<String> memNos = sessionMap.keySet();
		for(String memNo : memNos) {
			if(sessionMap.get(memNo).equals(userSession)) {
				memNoClose = memNo;
				sessionMap.remove(memNo);
				break;
			}
		}
		
		if(memNoClose != null) {
			ChatGroundMessage chatgroundMessage = new ChatGroundMessage();
			chatgroundMessage.setType("close");
			chatgroundMessage.setOnlineCounter(sessionMap.keySet().size());
			String chatgroundMessageJson = gson.toJson(chatgroundMessage);
			Collection<Session> sessions = sessionMap.values();
			for(Session session : sessions) {
				session.getAsyncRemote().sendText(chatgroundMessageJson);
			}
		}
//		System.out.printf("session ID = %s, disconnected; close code = %d%nusers: %s%n", userSession.getId(),
//				reason.getCloseCode().getCode(), memNos);
	}
	

	public static void main(String[] args) {

	}

}
