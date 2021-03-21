package com.chatground.jedis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	//key使用members表格的memNo{PK}
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	public static List<String> getHistoryMsg(String sender){
		String senderKey = new StringBuilder("ChatGround").append(":").append(sender).toString();
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		List<String> historyMsg = jedis.lrange(senderKey, 0, -1);
		jedis.close();
		return historyMsg;
	}
	
//	public static List<String> getHistoryMsg(){
//		String senderKey = "ChatGround";
//		Jedis jedis = pool.getResource();
//		jedis.auth("123456");
//		List<String> historyMsg = jedis.lrange(senderKey, 0, -1);
//		jedis.close();
//		return historyMsg;
//	}
	
	//將聊天訊息留存紀錄
	public static void saveChatGroundMessage(String sender, String message) {
		
		String senderKey = new StringBuilder("ChatGround").append(":").append(sender).toString();
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		jedis.rpush(senderKey, message);
		
		jedis.close();
	}
	
}
