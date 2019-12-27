/**
 * 
 */
package com.wechat.test;

import org.junit.Test;

import com.wechat.entity.menu.Button;
import com.wechat.entity.menu.ClickButton;
import com.wechat.entity.menu.PicPhotoOrAlbum;
import com.wechat.entity.menu.SubButton;
import com.wechat.entity.menu.ViewButton;
import net.sf.json.JSONObject;

/**
 * @author xingpc
 * @Date 2019年11月21日下午9:03:14
 */
public class ButtonTest {

	@Test
	public void testButton() {
		Button button = new Button();
		// 创建第一个一级菜单
		button.getButton().add(new ClickButton("今日歌曲", "1-1"));
		// 创建第二个一级菜单
		button.getButton().add(new ViewButton("百度一下", "http://www.baidu.com"));
		// 创建第三个一级菜单的子菜单
		SubButton sButton = new SubButton("娱乐");
		sButton.getSub_button().add(new ClickButton("游戏", "3-1"));
		sButton.getSub_button().add(new ViewButton("头条", "http://news.163.com"));
		sButton.getSub_button().add(new PicPhotoOrAlbum("图片", "3-3"));
		button.getButton().add(sButton);
		JSONObject jsonObject = JSONObject.fromObject(button);
		System.out.println(jsonObject);
	}

}
