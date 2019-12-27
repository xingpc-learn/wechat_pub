/**
 * 
 */
package com.wechat.entity.menu;

/**
 * @author xingpc
 * @Date 2019年11月21日下午8:47:21
 */
public class ViewButton extends AbstractButton {

	private String type = "view";

	private String url;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param name
	 * @param url
	 */
	public ViewButton(String name, String url) {
		super(name);
		this.url = url;
	}

}
