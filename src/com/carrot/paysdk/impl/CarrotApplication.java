/**
 * @author liudb
 * */
package com.carrot.paysdk.impl;

import android.app.Application;

/**
 * 项目名称：	TestAndroidGradle
 * 类名称：	CarrotApplication
 * 类描述：	在Application 中添加SDK需要内容
 * 创建人：	liudb
 * 创建时间：	2015 2015年11月3日 下午1:19:36
 * 联系方式：	liudb5@gmail.com (国内用这个。。。)
 * @version	V 0.0.1
 * 修改人：	
 * 修改时间：
 * 修改备注：
 */
public class CarrotApplication extends Application {
	//是否含有移动支付SDK
	boolean useCMBilling = false;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		try {
            Class.forName("cn.cmgame.billing.api.GameInterface");
            useCMBilling = true;
        } catch (ClassNotFoundException ignored) {

        }
		if(useCMBilling){
			System.loadLibrary("megjb");
		}
	}
}
