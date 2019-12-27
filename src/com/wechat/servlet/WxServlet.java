package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.wechat.service.WxService;

@WebServlet("/wxServlet")
public class WxServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(WxServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		boolean checkResult = WxService.checkMessage(signature, timestamp, nonce);
		if (checkResult) {
			logger.debug("微信校驗成功！");
			ServletOutputStream outputStream = resp.getOutputStream();
			outputStream.print(echostr);
		} else {
			logger.info("微信校驗失敗！");
		}
	}

	/**
	 * 接收消息和返回消息
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Map<String, String> requestMap =  WxService.parseRequest(req.getInputStream());
		//封装返回消息的xml
		String retMsg = WxService.genRetMsg(requestMap);
		logger.info("返回的消息xml："+retMsg);
		//将消息返回
		PrintWriter writer = resp.getWriter();
		writer.print(retMsg);
		writer.flush();
		writer.close();
	}

}
