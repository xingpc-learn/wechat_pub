/**
 * 
 */
package com.wechat.entity.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xingpc
 * @Date 2019年11月21日下午8:53:11
 */
public class PicPhotoOrAlbum extends AbstractButton {

	private String type = "pic_photo_or_album";

	private String key;

	private List<AbstractButton> sub_button = new ArrayList<AbstractButton>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<AbstractButton> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<AbstractButton> sub_button) {
		this.sub_button = sub_button;
	}

	/**
	 * @param name
	 * @param key
	 */
	public PicPhotoOrAlbum(String name, String key) {
		super(name);
		this.key = key;
	}

}
