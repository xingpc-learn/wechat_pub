package com.wechat.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author xingpc
 * @Date 2019年11月12日下午10:35:06
 */
public class Music {
	
	@XStreamAlias("Title")
	private String title;//音乐标题
	
	@XStreamAlias("Description")
	private String description;//音乐描述
	
	@XStreamAlias("MusicUrl")
	private String musicUrl;//音乐链接

	@XStreamAlias("HQMusicUrl")
	private String hqMusicUrl;//高质量音乐链接
	
	@XStreamAlias("ThumbMediaId")
	private String thumbMediaId;//缩略图媒体id

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

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHqMusicUrl() {
		return hqMusicUrl;
	}

	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	
	
	
}
