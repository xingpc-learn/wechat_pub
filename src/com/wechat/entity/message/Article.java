package com.wechat.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author xingpc
 * @Date 2019年11月12日下午10:41:09
 */
public class Article {

	@XStreamAlias("Title")
	private String title;//图文消息标题
	
	@XStreamAlias("Description")
	private String description;//图文消息描述
	
	@XStreamAlias("PicUrl")
	private String picUrl;//图片链接，支持JPG\PNG
	
	@XStreamAlias("Url")
	private String url;//点击图文消息跳转链接

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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
