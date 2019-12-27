package com.wechat.entity.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xingpc
 * @Date 2019年11月21日下午8:38:06
 */
public class Button {

	private List<AbstractButton> button = new ArrayList<AbstractButton>();

	public List<AbstractButton> getButton() {
		return button;
	}

	public void setButton(List<AbstractButton> button) {
		this.button = button;
	}

}
