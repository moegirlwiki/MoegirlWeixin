package org.moegirlwiki.plugins.messagerobot.utils.weixin;

import java.io.IOException;
import java.util.Arrays;

import org.moegirlwiki.plugins.messagerobot.utils.http.UrlConnectionUtil;
import org.moegirlwiki.plugins.messagerobot.utils.http.mapping.Parameter;

/**
 * login weixin
 * @author xuechong
 *
 */
public class Login {
	private Login(){}
	
	private static final String WEIXIN_LOGIN_URL = "https://mp.weixin.qq.com/cgi-bin/loginpage?t=wxm2-login&lang=zh_CN";
	/**
	 * login weixin & return the token id
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException 
	 */
	public static String login(String username,String password) throws IOException{
		Parameter usernameParam = Parameter.newInstance("username", username);
		Parameter pwdParam = Parameter.newInstance("pwd", password);
		Parameter imgCode = Parameter.newInstance("imgcode","");
		Parameter fParam = Parameter.newInstance("f", "json");
		
		return UrlConnectionUtil.postReuqest(WEIXIN_LOGIN_URL, 
				Arrays.asList(new Parameter[]{
						usernameParam,pwdParam,imgCode,fParam
				}));
	}
	
}
