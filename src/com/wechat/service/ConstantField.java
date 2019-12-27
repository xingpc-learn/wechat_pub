
package com.wechat.service;

/**
 * @author xingpc
 * @Date 2019年11月10日上午10:40:15
 */
public class ConstantField {

	// 微信令牌token
	public static final String Token = "helloTest";

	// 微信请求获取token路径
	public static final String GET_TOKEN_STRING = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	// 微信appID
	public static final String APP_ID = "wx4f46c66ab631ca82";

	// 微信appAPPSecret
	public static final String APP_SECRET = "357c89fe783b27d7f301a5fccc99b65d";

	public static final String MENU_URL_STRING = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	// 百度AI图像识别
	// 设置APPID/AK/SK
	public static final String APP_ID_BAIDU = "17828235";
	public static final String API_KEY = "VE9eZPXtGG9CnmKKkdKG22UG";
	public static final String SECRET_KEY = "Q9KKD4Fg4fY5WkE52epSDlLdjGg7wx06";

}
