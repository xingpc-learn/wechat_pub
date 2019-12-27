package com.wechat.test;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;

import com.baidu.aip.ocr.AipOcr;

/**
 * @author xingpc
 * @Date 2019年12月3日下午9:03:46
 */
public class PicRecoginazeTest {
	
	//设置APPID/AK/SK
    public static final String APP_ID = "17828235";
    public static final String API_KEY = "VE9eZPXtGG9CnmKKkdKG22UG";
    public static final String SECRET_KEY = "Q9KKD4Fg4fY5WkE52epSDlLdjGg7wx06";
	
    @Test
	public void testPic() {
		// 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "E:\\downloads\\rec.png";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString(2));
	}

}
