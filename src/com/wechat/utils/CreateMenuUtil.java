/**
 * 
 */
package com.wechat.utils;

import java.io.IOException;

import com.wechat.entity.menu.Button;
import com.wechat.entity.menu.ClickButton;
import com.wechat.entity.menu.PicPhotoOrAlbum;
import com.wechat.entity.menu.SubButton;
import com.wechat.entity.menu.ViewButton;
import com.wechat.service.ConstantField;
import com.wechat.service.WxService;

import net.sf.json.JSONObject;

/**
 * @author xingpc
 * @Date 2019年11月21日下午9:29:45
 */
public class CreateMenuUtil {

	public static void main(String[] args) {
		try {
		Button button = new Button();
		// 创建第一个一级菜单
		button.getButton().add(new ClickButton("今日歌曲", "1-1"));
		// 创建第二个一级菜单
		button.getButton().add(new ViewButton("百度一下", "http://www.baidu.com"));
		// 创建第三个一级菜单的子菜单
		SubButton sButton = new SubButton("娱乐");
		sButton.getSub_button().add(new ClickButton("游戏", "3-1"));
		sButton.getSub_button().add(new ViewButton("头条", "http://news.163.com"));
		sButton.getSub_button().add(new PicPhotoOrAlbum("传图片", "3-3"));
		//将子菜单放入第三个一级菜单
		button.getButton().add(sButton);
		JSONObject jsonObject = JSONObject.fromObject(button);
		//调用方法发送菜单
		String url = ConstantField.MENU_URL_STRING.replace("ACCESS_TOKEN", WxService.giveToken());
		//发送的json字符串
		String retStr = TiRobotUtil.postRequest(url, jsonObject.toString());
		System.out.println(retStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
