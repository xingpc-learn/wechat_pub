/**
 * 
 */
package com.wechat.entity.token;

/**
 * @author xingpc
 * @Date 2019年11月20日下午10:58:40
 */
public class AccessToken {

	private String accessToken;
	
	private long expireTime;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	/**
	 * @param accessToken token
	 * @param expireTime 过期时间
	 */
	public AccessToken(String accessToken, long expireIn) {
		super();
		this.accessToken = accessToken;
		expireTime = System.currentTimeMillis()+expireIn;
	}
	
	/**
	 * 判断是否超时
	 * @Description TODO
	 * @return
	 */
	public boolean isExpired() {
		return System.currentTimeMillis()>expireTime;
	}
}
