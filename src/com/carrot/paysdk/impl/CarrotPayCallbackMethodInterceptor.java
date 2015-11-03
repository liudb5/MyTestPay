package com.carrot.paysdk.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import android.os.Message;
import android.util.Log;

/**
 * 项目名称：	TestAndroidGradle
 * 类名称：	CarrotPayCallbackMethodInterceptor
 * 类描述：	处理回调函数
 * 创建人：	liudb
 * 创建时间：	2015 2015年11月3日 下午1:19:52
 * 联系方式：	liudb5@gmail.com (国内用这个。。。)
 * @version	V 0.0.1
 * 修改人：	
 * 修改时间：
 * 修改备注：
 */
public class CarrotPayCallbackMethodInterceptor implements InvocationHandler {
	CarrotPaySdkFinal csf;
	public CarrotPayCallbackMethodInterceptor (CarrotPaySdkFinal paytype){
		csf = paytype;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		switch (csf) {
		case CMCC_PAY:
			for(int j = 0; j < args.length;j++){
				if(args[j].getClass().getName().equalsIgnoreCase("java.lang.Integer")){
					Integer result = (Integer)args[j];
					switch (result) {
					case 1:
						Message msg_cmcc_success = new Message();
						msg_cmcc_success.what = CarrotPaySDK.CARROT_PAY_SUCCESS;
						CarrotPaySDK.carrotSdkHleper.cmcc_PayendHandler.sendMessage(msg_cmcc_success);
						break;
					case 2:
						Message msg_cmcc_faild = new Message();
						msg_cmcc_faild.what = CarrotPaySDK.CARROT_PAY_FAILD;
						CarrotPaySDK.carrotSdkHleper.cmcc_PayendHandler.sendMessage(msg_cmcc_faild);
						break;
					default:
						Message msg_cmcc_faild2 = new Message();
						msg_cmcc_faild2.what = CarrotPaySDK.CARROT_PAY_FAILD;
						CarrotPaySDK.carrotSdkHleper.cmcc_PayendHandler.sendMessage(msg_cmcc_faild2);
						break;
					}
				}
			}
			break;
		case CUCC_PAY:
			for(int j = 0; j < args.length;j++){
				if(args[j].getClass().getName().equalsIgnoreCase("java.lang.Integer")){
					Integer result = (Integer)args[j];
					switch (result) {
					case 9:
						Message msg_cucc_success = new Message();
						msg_cucc_success.what = CarrotPaySDK.CARROT_PAY_SUCCESS;
						CarrotPaySDK.carrotSdkHleper.cucc_PayendHandler.sendMessage(msg_cucc_success);
						break;
					case 2:
						Message msg_cucc_faild = new Message();
						msg_cucc_faild.what = CarrotPaySDK.CARROT_PAY_FAILD;
						CarrotPaySDK.carrotSdkHleper.cucc_PayendHandler.sendMessage(msg_cucc_faild);
						break;
					case 3:
						Message msg_cucc_cancel = new Message();
						msg_cucc_cancel.what = CarrotPaySDK.CARROT_PAY_CANCEL;
						CarrotPaySDK.carrotSdkHleper.cucc_PayendHandler.sendMessage(msg_cucc_cancel);
						break;
					default:
						Message msg_cucc_faild2 = new Message();
						msg_cucc_faild2.what = CarrotPaySDK.CARROT_PAY_FAILD;
						CarrotPaySDK.carrotSdkHleper.cucc_PayendHandler.sendMessage(msg_cucc_faild2);
						break;
					}
				}
			}
			break;
		case CTCC_PAY:
			if(method.getName().equalsIgnoreCase("paySuccess")){
				Message msg_ctcc_success = new Message();
				msg_ctcc_success.what = CarrotPaySDK.CARROT_PAY_SUCCESS;
				CarrotPaySDK.carrotSdkHleper.ctcc_PayendHandler.sendMessage(msg_ctcc_success);
			}else if(method.getName().equalsIgnoreCase("payCancel")){
				Message msg_ctcc_cancel = new Message();
				msg_ctcc_cancel.what = CarrotPaySDK.CARROT_PAY_CANCEL;
				CarrotPaySDK.carrotSdkHleper.ctcc_PayendHandler.sendMessage(msg_ctcc_cancel);
			}else if(method.getName().equalsIgnoreCase("payFailed")){
				for(int j = 0; j < args.length;j++){
					if(args[j].getClass().getName().equalsIgnoreCase("java.lang.Integer")){
						Integer result = (Integer)args[j];
						switch (result) {
						case -207:
							Message msg_ctcc_success = new Message();
							msg_ctcc_success.what = CarrotPaySDK.CARROT_PAY_SUCCESS;
							CarrotPaySDK.carrotSdkHleper.ctcc_PayendHandler.sendMessage(msg_ctcc_success);
							break;
						default:
							Message msg_ctcc_faild2 = new Message();
							msg_ctcc_faild2.what = CarrotPaySDK.CARROT_PAY_FAILD;
							CarrotPaySDK.carrotSdkHleper.ctcc_PayendHandler.sendMessage(msg_ctcc_faild2);
							break;
						}
					}
				}
			}
			break;
		case OTHER_PAY:
			Log.e("___________OTHER_______________", "____________________"+method.getName());
			
			for(int j = 0; j < args.length;j++){
				Log.e("___________OTHER_______________"+args[j].getClass().getName(), "____________________"+args[j].toString());
				
			}
			Log.e("___________OTHER_______________", "______333333______________");
			break;
		default:
			break;
		}
		
        return null;  
	}

}
