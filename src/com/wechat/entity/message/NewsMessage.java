package com.wechat.entity.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author xingpc
 * @Date 2019年11月12日下午10:43:47
 */
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage {

	@XStreamAlias("ArticleCount")
	private String articleCount;
	
	@XStreamAlias("Articles")
	private List<Article> articles = new ArrayList<Article>();

	public String getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	/**
	 * @param reqestMap
	 * @param articleCount
	 * @param articles
	 */
	public NewsMessage(Map<String, String> reqestMap, String articleCount, List<Article> articles) {
		super(reqestMap);
		this.setMsgType("news");
		this.articleCount = articleCount;
		this.articles = articles;
	}
	
}
