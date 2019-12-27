package com.wechat.entity.menu;

/**
 * @author xingpc
 * @Date 2019年11月21日下午8:42:18
 */
public class ClickButton extends AbstractButton {

	private String type = "click";

	private String key;

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

	/**
	 * @param key
	 */
	public ClickButton(String name, String key) {
		super(name);
		this.key = key;
	}

}
