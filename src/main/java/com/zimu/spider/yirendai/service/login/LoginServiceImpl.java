package com.zimu.spider.yirendai.service.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.zimu.javacore.http.HttpUtils;
import com.zimu.javacore.security.CryptAES;
import com.zimu.javacore.security.MD5Utils;
import com.zimu.javacore.utils.MapUtils;
import com.zimu.javacore.utils.SignUtils;
import com.zimu.spider.yirendai.constant.YirendaiConstants;

/** 
 * @title 登录service
 * 
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:36
 * @version 1.0
 */
public class LoginServiceImpl implements LoginService {

	/**
	 * {"result":"success","errorCode":"","msg":"","data":{"passportId":"","token":"c86a33c8b1594a1aa6fc4cd7572a3e17","name":"*吉胜","accountStatus":"1","accountType":"1","accountTypeChangeable":"","accountBalance":"0","totalAmount":"","accumulatedIncome":"","rates":"0%","isNew":"","level":"000","yrbRatio":"","inviteCodeRewardCount":"","newUserRewardCount":""}}
	 */
	@Override
	public String doLogin(String account, String password) {
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildLoginParam(requestMap,account, password);
		bulidRequestParam(requestMap);
		String result = "";
		try {
			result = HttpUtils.sendPost(getUrl(), MapUtils.getParamStringEncoder(requestMap));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public void buildLoginParam(Map<String,Object> requestMap,String account, String password) {
		requestMap.put("account", CryptAES.AES_Encrypt(YirendaiConstants.AES_KEY, account));
		requestMap.put("password",  CryptAES.AES_Encrypt(YirendaiConstants.AES_KEY,password));
	}

	@Override
	public void bulidRequestParam(Map<String,Object> requestMap) {
		requestMap.put("channelId", YirendaiConstants.KEY_CHANNEL_ID);
		requestMap.put("clientType", YirendaiConstants.KEY_CLIENT_TYPE);
		requestMap.put("deviceNo", YirendaiConstants.KEY_DEVICE_NO);
		requestMap.put("marketId", YirendaiConstants.KEY_MARKET_ID);
		requestMap.put("secret", YirendaiConstants.KEY_SECRET);
		requestMap.put("sign", SignUtils.generateSign(requestMap));
	}

	public static void main(String[] args) {
		LoginServiceImpl login  = new LoginServiceImpl();
		String account = "13509692759";
		String password = "qiujisheng89";
		
		System.out.println(login.doLogin(account, password));
	}


	/* (non-Javadoc)
	 * @see com.zimu.spider.yirendai.service.base.BaseService#getUrl()
	 */
	@Override
	public String getUrl() {
		return YirendaiConstants.LOGIN_URL;
	}
}
