package com.wechat.entity.message;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author xingpc
 * @Date 2019年11月12日下午10:22:42
 */
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {

	@XStreamAlias("MediaId")
	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * @param reqeMap
	 * @param mediaId
	 */
	public ImageMessage(Map<String, String> reqestMap, String mediaId) {
		super(reqestMap);
		this.mediaId = mediaId;
		this.setMsgType("image");
	}
	
}
