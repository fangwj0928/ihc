/**
 * 
 */
package com.vijay.ihc.security.core.properties;

/**
 * 认证成功后的响应类型，跳转页面，或返回Json
 */
public enum LoginResponseType {
	
	/**
	 * 跳转
	 */
	REDIRECT,

	/**
	 * 返回json
	 */
	JSON

}
