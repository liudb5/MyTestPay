package com.carrot.paysdk.impl;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.telephony.TelephonyManager;

import com.carrot.paysdk.api.CarrotPaySDKInterface;

/**
 * 项目名称：	TestAndroidGradle
 * 类名称：	CarrotPaySDK
 * 类描述：	实现支付接口
 * 创建人：	liudb
 * 创建时间：	2015 2015年11月3日 下午1:19:57
 * 联系方式：	liudb5@gmail.com (国内用这个。。。)
 * @version	V 0.0.1
 * 修改人：	
 * 修改时间：
 * 修改备注：
 */
public class CarrotPaySDK implements CarrotPaySDKInterface {
	protected static CarrotPaySDKHelper carrotSdkHleper;
	protected static Activity	mContext;
	public CarrotPaySDK(Activity context) {
		// TODO Auto-generated constructor stub
		mContext = context;
		carrotSdkHleper = new CarrotPaySDKHelper();
	}
	@Override
	public void carrot_initPaySDK(int CU_type, String Ipay_appid,String app_name,String app_company,String telphone_number) {
		// TODO Auto-generated method stub
		switch (getPhoneType()) {
		case TYPE_CMCC:
			Message msg_cmcc = new Message();
			String [] cmcc_init_str = {app_name,app_company,telphone_number};
			msg_cmcc.obj = cmcc_init_str;
			msg_cmcc.what = CarrotPaySdkFinal.INIT_CMCC.ordinal();
			carrotSdkHleper.sendMessage(msg_cmcc);
			break;
		case TYPE_CUCC:
			Message msg_cucc = new Message();
			msg_cucc.what = CarrotPaySdkFinal.INIT_CUCC.ordinal();
			msg_cucc.obj = CU_type;
			carrotSdkHleper.sendMessage(msg_cucc);
			break;
		case TYPE_CTCC:
			Message msg_ctcc = new Message();
			msg_ctcc.what = CarrotPaySdkFinal.INIT_CTCC.ordinal();
			carrotSdkHleper.sendMessage(msg_ctcc);
			break;
		case TYPE_IPAY:
			Message msg_other = new Message();
			msg_other.what = CarrotPaySdkFinal.INIT_OTHER.ordinal();
			carrotSdkHleper.sendMessage(msg_other);
			break;
		default:
			break;
		}
	}

	@Override
	public void carrot_initAdSDK(String DOMO_ID, String Main_AD_ID,
			String Map_AD_ID, String Left_AD_ID, String Right_AD_ID,
			String GoMap_AD_ID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void carrot_initShareSDK(String WX_appid, String Sina_appkey) {
		// TODO Auto-generated method stub

	}

	@Override
	public void carrot_Pay(int productID, String ct_alias, String cm_index,
			boolean cm_repeat, boolean cm_isUseSim, String cu_custom, String ia_money,
			String ia_waresid, String ia_url, String ia_appid,
			String ia_appkey, String mm_code, String cm_order,CarrotPayCallBack callback) {
		// TODO Auto-generated method stub
		switch (getPhoneType()) {
		case TYPE_CMCC:
			carrotSdkHleper.cmcc_pay(cm_isUseSim,cm_repeat,cm_index,cm_order,callback);
			break;
		case TYPE_CUCC:
			carrotSdkHleper.cucc_pay(cu_custom,callback);
			break;
		case TYPE_CTCC:
			carrotSdkHleper.ctcc_pay(ct_alias,callback);
			break;
		case TYPE_IPAY:
			break;
		default:
			break;
		}
	}

	@Override
	public void carrot_Share(String img_path, String text_msg) {
		// TODO Auto-generated method stub

	}
	/**
	 * 获取当前sim卡类型
	 * */
	private CarrotPaySdkFinal getPhoneType() {
		TelephonyManager tm = (TelephonyManager) mContext
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = tm.getSimOperator();
		if (imsi != null) {
			if (imsi.equalsIgnoreCase("46000") || imsi.equalsIgnoreCase("46002")
					|| imsi.equalsIgnoreCase("46007")) {
				// 中国移动
				return CarrotPaySdkFinal.TYPE_CMCC;
			} else if (imsi.equalsIgnoreCase("46001")) {
				// 中国联通
				return CarrotPaySdkFinal.TYPE_CUCC;
			} else if (imsi.equalsIgnoreCase("46003")) {
				// 中国电信
				return CarrotPaySdkFinal.TYPE_CTCC;
			} else {
				// 无法判断
				return CarrotPaySdkFinal.TYPE_IPAY;
			}
		} else {
			// imsi为空
			return CarrotPaySdkFinal.TYPE_IPAY;
		}
	}
}
