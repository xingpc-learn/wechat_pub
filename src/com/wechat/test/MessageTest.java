package com.wechat.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import com.wechat.entity.message.TextMessage;
import com.wechat.service.WxService;

/**
 * @author xingpc
 * @Date 2019年11月12日下午10:53:34
 */
public class MessageTest {

	@Test
	public void testMsg() {
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("ToUserName","1111");
		requestMap.put("FromUserName","2222");
		requestMap.put("CreateTime","123456");
		TextMessage textMsg = new TextMessage(requestMap,"helloWorld");
		assertEquals(textMsg.getContent(), "helloWorld");
	}
	
	@Test
	public void testGetToken() {
		System.out.println(WxService.giveToken());
		System.out.println(WxService.giveToken());
	}
	
}
