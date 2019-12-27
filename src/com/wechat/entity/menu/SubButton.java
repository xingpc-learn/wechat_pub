/**
 * 
 */
package com.wechat.entity.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xingpc
 * @Date 2019年11月21日下午8:58:30
 */
public class SubButton extends AbstractButton {

	private List<AbstractButton> sub_button = new ArrayList<AbstractButton>();

	public List<AbstractButton> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<AbstractButton> sub_button) {
		this.sub_button = sub_button;
	}

	/**
	 * @param name
	 */
	public SubButton(String name) {
		super(name);
	}

}
