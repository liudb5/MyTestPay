/**
 * @author liudb
 * */
package com.carrot.paysdk.impl;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import com.carrot.paysdk.api.CarrotPaySDKInterface.CarrotPayCallBack;
import com.carrot.paysdk.util.CarrotFindClassAndInvokeMethod;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 项目名称：	TestAndroidGradle
 * 类名称：	CarrotPaySDKHelper
 * 类描述：	处理支付结果
 * 创建人：	liudb
 * 创建时间：	2015 2015年11月3日 下午1:20:15
 * 联系方式：	liudb5@gmail.com (国内用这个。。。)
 * @version	V 0.0.1
 * 修改人：	
 * 修改时间：
 * 修改备注：
 */
public class CarrotPaySDKHelper extends Handler {
	//这3个handler可以合为一个
	public Handler cmcc_PayendHandler;		//移动支付回调
	public Handler cucc_PayendHandler;		//联通支付回调
	public Handler ctcc_PayendHandler;		//电信支付回调
	protected CarrotFindClassAndInvokeMethod cfcaim = new CarrotFindClassAndInvokeMethod();
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		CarrotPaySdkFinal csf = CarrotPaySdkFinal.valueOf(msg.what);
		switch (csf) {
		case INIT_CMCC:
			String[] cmcc_param = (String[]) msg.obj;
			init_cmcc(cmcc_param[0], cmcc_param[1], cmcc_param[2]);
			break;
		case INIT_CUCC:
			int type = (Integer)msg.obj;
			init_cucc(type);
			break;
		case INIT_CTCC:
			init_ctcc();
			break;
		case INIT_OTHER:
			break;
		default:
			break;
		}
	}

	/**
	 * 初始化移动支付
	 * 
	 * @param app_name
	 *            应用名称
	 * @param app_company
	 *            公司名称
	 * @param telphone_number
	 *            电话
	 * */
	protected void init_cmcc(String app_name, String app_company,
			String telphone_number) {
		try {
			Object [] cmcc_init_obj = {CarrotPaySDK.mContext,app_name,app_company,telphone_number};
			Class<?> [] classparam = {Activity.class,String.class,String.class,String.class};
			cfcaim.invokeStaticMethod("cn.cmgame.billing.api.GameInterface","initializeApp",cmcc_init_obj,classparam);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("init_cmcc异常捕捉", "异常："+e.toString());
		}
	}
	
	/**
	 * 执行移动支付
	 * @param		isUsesim		sim卡是否可用
	 * @param		isRepeat		计费点是否可重复
	 * @param		index			计费点编号
	 * @param		order			订单号
	 * @param 		callback		支付回调
	 */
	protected void cmcc_pay(boolean isUsesim,boolean isRepeat,String index,String order,final CarrotPayCallBack callback){
		try {
			cmcc_PayendHandler = new CarrotHandler(CarrotPaySDK.mContext){
				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					super.handleMessage(msg);
					callback.onPayEnd(msg.what);
				}
			};
			// 传入参数
			Object [] cmcc_pay_param = {CarrotPaySDK.mContext,isUsesim,isRepeat,index,order,new Object()};
			// 参数对应的TYPE
			Class<?> [] cmcc_pay_paramtyp = {Context.class,boolean.class,boolean.class,String.class,String.class,Object.class};
			// "IPayCallback" 是移动支付的回调函数名
			cfcaim.invokeContainsInterfaceStaticMethod("cn.cmgame.billing.api.GameInterface", "doBilling", cmcc_pay_param,cmcc_pay_paramtyp,"IPayCallback",CarrotPaySdkFinal.CMCC_PAY);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("cmcc_pay异常捕捉", "异常："+e.toString());
		}
	}
	/**
	 * 初始化联通支付
	 * @param		type	打包类型：自签名：1，wo商店签名：0
	 * */
	protected void init_cucc(int type) {
		try {
			Object [] cucc_pay_param = {CarrotPaySDK.mContext,type};
			Class<?> [] cucc_pay_paramtyp = {Context.class,int.class};
			cfcaim.invokeMethod("com.unicom.dcLoader.Utils", "initSDK", cucc_pay_param,cucc_pay_paramtyp);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("init_cucc异常捕捉", "异常："+e.toString());
		}
	}
	/**
	 * 联通支付
	 * @param		customcode		计费点编号
	 * @param		callback		支付回调
	 * */
	protected void cucc_pay(String customcode,final CarrotPayCallBack callback){
		try {
			cucc_PayendHandler = new CarrotHandler(CarrotPaySDK.mContext){
				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					super.handleMessage(msg);
					callback.onPayEnd(msg.what);
				}
			};
			Object [] cmcc_pay_param = {CarrotPaySDK.mContext,customcode,new Object()};
			Class<?> [] cmcc_pay_paramtyp = {Context.class,String.class,Object.class};
			cfcaim.invokeContainsInterfaceMethod("com.unicom.dcLoader.Utils", "pay", cmcc_pay_param, cmcc_pay_paramtyp, "UnipayPayResultListener",CarrotPaySdkFinal.CUCC_PAY);
			
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("cucc_pay异常捕捉", "异常："+e.toString());
		}
	}
	/**
	 * 初始化电信支付
	 * */
	protected void init_ctcc(){
		try {
			Object [] ctcc_pay_param = {CarrotPaySDK.mContext};
			Class<?> [] ctcc_pay_paramtyp = {Context.class};
			cfcaim.invokeStaticMethod("cn.egame.terminal.paysdk.EgamePay", "init", ctcc_pay_param, ctcc_pay_paramtyp);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("ctcc_pay异常捕捉", "异常："+e.toString());
		}
	} 
	protected void ctcc_pay(String payAlias,final CarrotPayCallBack callback){
		ctcc_PayendHandler = new CarrotHandler(CarrotPaySDK.mContext){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				callback.onPayEnd(msg.what);
			}
		};
		HashMap<String, String> payParams=new HashMap<String, String>();
		payParams.put("toolsAlias", payAlias);
		Object [] ctcc_pay_param = {CarrotPaySDK.mContext,payParams,new Object()};
		Class<?> [] ctcc_pay_paramtyp = {Context.class,Map.class,Object.class};
		try {
			cfcaim.invokeContainsInterfaceStaticMethod("cn.egame.terminal.paysdk.EgamePay", "pay", ctcc_pay_param,ctcc_pay_paramtyp,"EgamePayListener",CarrotPaySdkFinal.CTCC_PAY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 同一个线程下的handler共享一个looper对象，
	 * 消息中保留了对handler的引用，只要有消息在队列中，
	 * 那么handler便无法被回收，
	 * 如果handler不是static那么使用Handler的Service和Activity就也无法被回收。
	 * 这就可能导致内存泄露。
	 * */
	protected static class CarrotHandler extends Handler{
		WeakReference<Activity> mActivity;  
		  
		CarrotHandler(Activity activity) {
			mActivity = new WeakReference<Activity>(activity);  
        }  
	}
}
