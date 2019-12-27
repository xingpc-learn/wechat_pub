package com.wechat.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletInputStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.aip.ocr.AipOcr;
import com.thoughtworks.xstream.XStream;
import com.wechat.entity.message.BaseMessage;
import com.wechat.entity.message.ImageMessage;
import com.wechat.entity.message.MusicMessage;
import com.wechat.entity.message.NewsMessage;
import com.wechat.entity.message.TextMessage;
import com.wechat.entity.message.VideoMessage;
import com.wechat.entity.message.VoiceMessage;
import com.wechat.entity.token.AccessToken;
import com.wechat.utils.TiRobotUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author xingpc
 * @Date 2019年11月8日上午9:04:48
 */
public class WxService {

	private static Logger logger = LoggerFactory.getLogger(WxService.class);

	// 配置聊天机器人申请的KEY
	public static final String APPKEY = "926006a4435d0e1b540a3af47fcc9ea3";

	private static AccessToken token;

	/**
	 * @Description 對來自微信的signature進行校驗
	 * @param signature 微信加密签名
	 * @param timestamp 时间戳
	 * @param nonce     随机数
	 * @return
	 */
	public static boolean checkMessage(String signature, String timestamp, String nonce) {
		logger.info("微信发送过来的signature：" + signature);
		String token = ConstantField.Token;
		String[] message = { token, timestamp, nonce };
		// 1.将TOKEN,timeStamp,nonce按字典顺序排序
		Arrays.sort(message);
		// 2.将三个参数拼接成字符串
		String messageStr = "";
		for (int i = 0; i < message.length; i++) {
			messageStr += message[i];
		}
		// 对字符串进行sha1加密
		String digeStr = sha1(messageStr);
		logger.info("经过处理后的字符串：" + digeStr);
		return signature.equalsIgnoreCase(digeStr);
	}

	/**
	 * @Description sha1加密
	 * @param messageStr 待加密字符串
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String sha1(String messageStr) {
		try {
			MessageDigest digest = MessageDigest.getInstance("sha1");
			byte[] digestMessage = digest.digest(messageStr.getBytes());
			char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			// 创建缓冲池，拼接字符串
			StringBuffer sb = new StringBuffer();
			for (byte digest1 : digestMessage) {
				sb.append(chars[(digest1 >> 4) & 15]);
				sb.append(chars[digest1 & 15]);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * @Description 解析接收的报文
	 * @param inputStream
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseRequest(ServletInputStream inputStream) {
		try {
			Map<String, String> requestMap = new HashMap<String, String>();
			// 读取输入流
			SAXReader reader = new SAXReader();
			Document douDocument = reader.read(inputStream);
			// 获取报文根节点
			Element rootElement = douDocument.getRootElement();
			// 获取根节点下边的节点
			List<Element> elements = rootElement.elements();
			// 遍历获取所有节点
			for (Element e : elements) {
				requestMap.put(e.getName(), e.getStringValue());
			}
			logger.info("接收到的报文:" + requestMap);
			return requestMap;
		} catch (DocumentException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 封装返回消息成xml格式
	 * 
	 * @Description TODO
	 * @param requestMap
	 * @return
	 */
	public static String genRetMsg(Map<String, String> requestMap) {
		BaseMessage baseMsg = null;
		// 获取请求消息类型
		String msgType = requestMap.get("MsgType");
		switch (msgType) {
		case "text":
			baseMsg = dealTextMessage(requestMap);
			break;
		case "image":
			baseMsg = delPicEvent(requestMap);
			break;
		case "voice":

			break;
		case "video":

			break;
		case "shortvideo":

			break;
		case "location":

			break;
		case "link":

			break;
		case "event":
			baseMsg = dealEventMessage(requestMap);
			break;
		default:
			break;
		}
		// 对返回的数据对象进行处理,返回xml
		if (baseMsg != null) {
			return beanToXml(baseMsg);
		}
		return null;
	}

	/**
	 * 处理时间类消息
	 * 
	 * @Description TODO
	 * @param requestMap
	 * @return
	 */
	public static BaseMessage dealEventMessage(Map<String, String> requestMap) {
		String event = requestMap.get("Event");
		BaseMessage msg = new BaseMessage(requestMap);
		// 时间类型可能有多种，进行判断
		switch (event) {
		case "VIEW":

			break;
		case "pic_photo_or_album":
			
			break;
		case "CLICK":
			msg = dealClickEvent(requestMap);
			break;

		default:
			break;
		}
		return msg;
	}

	/**
	 * @Description 处理图片类消息
	 * @param requestMap
	 * @return
	 */
	@SuppressWarnings(value = {"unchecked"})
	public static BaseMessage delPicEvent(Map<String, String> requestMap) {
		// 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("language_type", "CHN_ENG");
	    options.put("detect_direction", "true");
	    options.put("detect_language", "true");
	    options.put("probability", "true");
		// 初始化一个AipOcr
        AipOcr client = new AipOcr(ConstantField.APP_ID_BAIDU, ConstantField.API_KEY, ConstantField.SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        
        // 调用接口
        String path = requestMap.get("PicUrl");
        org.json.JSONObject res = client.generalUrl(path, options);
        String jsonString = res.toString();
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        //获取jsonObject值
        JSONArray array = jsonObject.getJSONArray("words_result");
        Iterator<JSONObject> iterator = array.iterator();
        StringBuffer sBuffer = new StringBuffer();
        while (iterator.hasNext()) {
			JSONObject jsonObject2 = (JSONObject) iterator.next();
			sBuffer.append(jsonObject2.get("words"));
		}
		return new TextMessage(requestMap, sBuffer.toString());
	}

	/**
	 * 处理点击时间消息
	 * 
	 * @Description TODO
	 * @param requestMap
	 * @return
	 */
	public static BaseMessage dealClickEvent(Map<String, String> requestMap) {
		String key = requestMap.get("EventKey");
		switch (key) {
		case "1-1":
			return new TextMessage(requestMap, "一首成都送给你！");

		case "3-1":
			return new TextMessage(requestMap, "魂斗罗玩不玩！");

		default:
			break;
		}
		return null;
	}

	/**
	 * 对返回的数据对象进行处理，返回xml
	 * 
	 * @Description TODO
	 * @param baseMsg
	 * @return
	 */
	public static String beanToXml(BaseMessage baseMsg) {
		XStream xStream = new XStream();
		xStream.processAnnotations(TextMessage.class);
		xStream.processAnnotations(ImageMessage.class);
		xStream.processAnnotations(VoiceMessage.class);
		xStream.processAnnotations(VideoMessage.class);
		xStream.processAnnotations(MusicMessage.class);
		xStream.processAnnotations(NewsMessage.class);
		return xStream.toXML(baseMsg);
	}

	/**
	 * 处理返回数据，将要返回的数据封装成对象
	 * 
	 * @Description TODO
	 * @param requestMap
	 * @return
	 */
	public static BaseMessage dealTextMessage(Map<String, String> requestMap) {
		// 用户发过来的数据
		String msg = requestMap.get("Content");
		// 调用方法返回聊天的内容
		String retMsg = chat(msg);
		TextMessage textMsg = new TextMessage(requestMap, retMsg);
		return textMsg;
	}

	/**
	 * @Description 调用图灵机器人
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String chat(String msg) {
		String result = null;
		String url = "http://op.juhe.cn/iRobot/index";// 请求接口地址
		Map params = new HashMap();// 请求参数
		params.put("key", APPKEY);// 您申请到的本接口专用的APPKEY
		params.put("info", msg);// 要发送给机器人的内容，不要超过30个字符
		params.put("dtype", "");// 返回的数据的格式，json或xml，默认为json
		params.put("loc", "");// 地点，如北京中关村
		params.put("lon", "");// 经度，东经116.234632（小数点后保留6位），需要写为116234632
		params.put("lat", "");// 纬度，北纬40.234632（小数点后保留6位），需要写为40234632
		params.put("userid", "");// 1~32位，此userid针对您自己的每一个用户，用于上下文的关联

		try {
			result = TiRobotUtil.net(url, params, "POST");
			JSONObject object = JSONObject.fromObject(result);
			if (object.getInt("error_code") == 0) {
				return object.getJSONObject("result").getString("text");
			} else {
				logger.debug("error_code:" + object.get("error_code") + "-----" + "reason:" + object.get("reason"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取token
	 * 
	 * @Description TODO
	 */
	public static void getToken() {
		// 拼接token路径
		String urlStr = ConstantField.GET_TOKEN_STRING;
		urlStr = urlStr.replace("APPID", ConstantField.APP_ID).replace("APPSECRET", ConstantField.APP_SECRET);
		// 调用工具类获取token
		String result = TiRobotUtil.submit(urlStr);
		// 获取token
		JSONObject object = JSONObject.fromObject(result);
		String accessToken = object.getString("access_token");
		String expireIn = object.getString("expires_in");
		token = new AccessToken(accessToken, Long.parseLong(expireIn));
	}

	/**
	 * 对外暴露token
	 * 
	 * @Description TODO
	 * @return
	 */
	public static String giveToken() {
		if (token == null || token.isExpired()) {
			getToken();
		}
		return token.getAccessToken();
	}

}
