/**
 * 
 */
package com.wechat.entity.menu;

/**
 * @author xingpc
 * @Date 2019年11月21日下午8:40:23
 */
public abstract class AbstractButton {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param name
	 */
	public AbstractButton(String name) {
		super();
		this.name = name;
	}

}
