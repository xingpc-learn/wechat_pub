package com.wechat.entity.message;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author xingpc
 * @Date 2019年11月12日下午10:29:28
 */
@XStreamAlias("xml")
public class VideoMessage extends BaseMessage {

	@XStreamAlias("Video")
	private Video video;

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	/**
	 * @param reqestMap
	 * @param video
	 */
	public VideoMessage(Map<String, String> reqestMap, Video video) {
		super(reqestMap);
		this.setMsgType("video");
		this.video = video;
	}
	
	
	
}
