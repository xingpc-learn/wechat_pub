package com.wechat.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xingpc
 * @Date 2019年11月13日下午11:16:02
 */
public class TiRobotUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(TiRobotUtil.class);
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	/**
	 * 发送一个请求，请求数据
	 * 
	 * @Description TODO
	 * @param url
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static String postRequest(String url, String data) throws IOException {
		URL postUrl = new URL(url);
		// 打开一个连接
		URLConnection conn = postUrl.openConnection();
		// 设置可以发送请求数据
		conn.setDoOutput(true);
		// 发送数据
		OutputStream outputStream = conn.getOutputStream();
		outputStream.write(data.getBytes());
		// 接收返回的数据
		InputStream is = conn.getInputStream();
		byte[] by = new byte[1024];
		int len;
		StringBuilder sb = new StringBuilder();
		while ((len = is.read(by)) != -1) {
			sb.append(new String(by, 0, len));
		}
		if (is!=null) {
			is.close();
		}
		return sb.toString();
	}

	/**
	 * 发送请求获取token
	 * 
	 * @Description TODO
	 * @param urlStr 微信请求token路径
	 * @return
	 */
	public static String submit(String urlStr) {
		InputStream is = null;
		try {
			URL url = new URL(urlStr);
			// 打开一个链接
			URLConnection conn = url.openConnection();
			is = conn.getInputStream();
			byte[] by = new byte[1024];
			int len;
			StringBuilder sb = new StringBuilder();
			while ((len = is.read(by)) != -1) {
				sb.append(new String(by, 0, len));
			}
			return sb.toString();
		} catch (IOException e) {
			LOGGER.error("查询token异常：" + e.getMessage());
		}finally {
			try {
				if (is!=null) {
					is.close();
				}
			} catch (IOException e) {
				LOGGER.error("数据流关闭异常：" + e.getMessage());
			}
		}
		return null;
	}

	/**
	 *
	 * @param strUrl 请求地址
	 * @param params 请求参数
	 * @param method 请求方法
	 * @return 网络请求字符串
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String net(String strUrl, @SuppressWarnings("rawtypes") Map params, String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}

	// 将map型转为请求参数型
	@SuppressWarnings("rawtypes")
	public static String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
