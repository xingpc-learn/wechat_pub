package com.wechat.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author xingpc
 * @Date 2019年11月12日下午10:30:28
 */
public class Video {

	@XStreamAlias("MediaId")
	private String mediaId;//素材管理器中的id
	
	@XStreamAlias("Title")
	private String title;//视频消息的标题
	
	@XStreamAlias("Description")
	private String description;//视频消息的描述

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
