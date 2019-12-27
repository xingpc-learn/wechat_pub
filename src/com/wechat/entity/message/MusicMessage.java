/**
 * 
 */
package com.wechat.entity.message;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author xingpc
 * @Date 2019年11月12日下午10:37:47
 */
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage {

	@XStreamAlias("Music")
	private Music music;

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	/**
	 * @param reqestMap
	 * @param music
	 */
	public MusicMessage(Map<String, String> reqestMap, Music music) {
		super(reqestMap);
		this.setMsgType("music");
		this.music = music;
	}

}
