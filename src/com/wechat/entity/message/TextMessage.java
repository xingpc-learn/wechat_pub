package com.wechat.entity.message;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author xingpc
 * @Date 2019年11月12日下午9:53:26
 */
@XStreamAlias("xml")
public class TextMessage extends BaseMessage {

	@XStreamAlias("Content")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @param reqeMap
	 * @param content 文本消息内容
	 */
	public TextMessage(Map<String, String> reqestMap, String content) {
		super(reqestMap);
		this.content = content;
		this.setMsgType("text");
	}
	
}
