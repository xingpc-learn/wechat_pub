/**
 * 
 */
package com.wechat.entity.message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author xingpc
 * @Date 2019年11月12日下午9:43:52
 */
@XStreamAlias("xml")
public class BaseMessage {

	@XStreamAlias("ToUserName")
	private String toUserName;// 接收账号（收到的openID）
	
	@XStreamAlias("FromUserName")
	private String fromUserName;// 开发者微信号

	@XStreamAlias("CreateTime")
	private String createTime;// 消息创建时间

	@XStreamAlias("MsgType")
	private String msgType;// 消息类型

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public BaseMessage(Map<String, String> reqestMap) {
		this.toUserName = reqestMap.get("FromUserName");
		this.fromUserName = reqestMap.get("ToUserName");
		this.createTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	@Override
	public String toString() {
		return "BaseMessage [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + "]";
	}
	
}
