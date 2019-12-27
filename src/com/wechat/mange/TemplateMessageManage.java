package com.wechat.mange;

import org.junit.Test;

import com.wechat.service.WxService;
import com.wechat.utils.TiRobotUtil;

/**
 * @author xingpc
 * @Date 2019年12月17日下午7:31:54
 */
public class TemplateMessageManage {

	private final String templateSetUrl = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=";

	private final String templateGetUrl = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=";
	
	@Test
	public void set() throws Exception {
		String access_token = WxService.giveToken();
		String url = templateSetUrl + access_token;
		String data = "{\r\n" + 
				"    \"industry_id1\":\"1\",\r\n" + 
				"    \"industry_id2\":\"4\"\r\n" + 
				"}";
		TiRobotUtil.postRequest(url, data);
	}
	
	@Test
	public void get() {
		String access_token = WxService.giveToken();
		String url = templateGetUrl + access_token;
		String info = TiRobotUtil.submit(url);
		System.out.println(info);
	}
	
}
