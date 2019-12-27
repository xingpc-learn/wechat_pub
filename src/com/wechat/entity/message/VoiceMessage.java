package com.wechat.entity.message;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author xingpc
 * @Date 2019年11月12日下午10:27:00
 */
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage {

	@XStreamAlias("MediaId")
	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * @param reqestMap
	 * @param mediaId
	 */
	public VoiceMessage(Map<String, String> reqestMap, String mediaId) {
		super(reqestMap);
		this.setMsgType("voice");
		this.mediaId = mediaId;
	}
	
}
