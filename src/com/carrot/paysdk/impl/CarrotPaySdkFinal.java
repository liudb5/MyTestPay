package com.carrot.paysdk.impl;

/**
 * 项目名称：	TestAndroidGradle
 * 类名称：	CarrotPaySdkFinal
 * 类描述：	支付需要的一些常量
 * 创建人：	liudb
 * 创建时间：	2015 2015年11月3日 下午1:20:07
 * 联系方式：	liudb5@gmail.com (国内用这个。。。)
 * @version	V 0.0.1
 * 修改人：	
 * 修改时间：
 * 修改备注：
 */
public enum CarrotPaySdkFinal{
	/*
	 * 手机SIM卡类型判断
	 * */
	TYPE_CMCC,	//中国移动
	TYPE_CUCC,	//中国联通
	TYPE_CTCC,	//中国电信
	TYPE_IPAY,	//未识别到
	/*
	 * 初始化参数
	 * */
	INIT_CMCC,	//初始化移动SDK
	INIT_CUCC,	//初始化联通SDK
	INIT_CTCC,	//初始化电信SDK
	INIT_OTHER,	//初始化第三方SDK
	/*
	 * 支付参数
	 * */
	CMCC_PAY,	//移动支付
	CUCC_PAY,	//联通支付
	CTCC_PAY,	//电信支付
	OTHER_PAY;	//其他支付
	/**
	 * 通过序列号来找到当前枚举常量
	 * @param	ordinal		序列号
	 * 
	 * @author liudb	14-10-10
	 * */
	public static CarrotPaySdkFinal valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
