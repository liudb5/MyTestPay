package com.carrot.paysdk.api;
/**
 * 项目名称：	TestAndroidGradle
 * 类名称：	CarrotPaySDKInterface
 * 类描述：	定义支付接口
 * 创建人：	liudb
 * 创建时间：	2015 2015年11月3日 下午1:19:18
 * 联系方式：	liudb5@gmail.com (国内用这个。。。)
 * @version	V 0.0.1
 * 修改人：	
 * 修改时间：
 * 修改备注：
 */
public interface CarrotPaySDKInterface {
	/**
	 * 初始化支付SDK
	 * @param	CU_type			联通支付类型
	 * @param	Ipay_appid		爱贝appid
	 * @param	app_name		应用名称
	 * @param	app_company		公司名称
	 * @param	telphone_number	电话
	 * */
	public void carrot_initPaySDK(
			int CU_type,
			String Ipay_appid,
			String app_name,
			String app_company,
			String telphone_number);
	/**
	 * 初始化广告SDK
	 * @param	DOMO_ID		多盟广告ID
	 * @param	Main_AD_ID	主屏显示的广告ID
	 * @param	Map_AD_ID	大地图页面显示的广告ID
	 * @param	Left_AD_ID	大地图滑动到最左边显示的广告ID
	 * @param	Right_AD_ID	大地图滑动到最右边显示的广告ID
	 * @param	GoMap_AD_ID	返回到大地图显示的广告ID
	 * */
	public void carrot_initAdSDK(
			String DOMO_ID,
			String Main_AD_ID,
			String Map_AD_ID,
			String Left_AD_ID,
			String Right_AD_ID,
			String GoMap_AD_ID);
	/**
	 * 初始化分享SDK
	 * @param	WX_appid 	微信appid
	 * @param	Sina_appkey	新浪appkey
	 * */
	public void carrot_initShareSDK(String WX_appid,String Sina_appkey);
	/**
	 * 支付
	 * @param	productID	购买道具ID
	 * @param	ct_alias	计费点对应电信代码
	 * @param	cm_index	计费点对应移动序列号
	 * @param	cm_repeat	移动计费点是否可重复
	 * @param	cu_props	联通计费点描述
	 * @param	cu_money	联通计费点所需钱数
	 * @param	cu_vaccode	计费点对应联通计费代码
	 * @param	cu_custom	联通第三方计费点
	 * @param	ia_money	爱贝计费点所需钱数
	 * @param	ia_waresid	爱贝支付计费点类型
	 * @param	ia_url		爱贝支付url
	 * @param	ia_appid	爱贝appid
	 * @param	ia_appkey	爱贝appkey
	 * @param	mm_code		移动MM支付计费点代码
	 * @param	isUseSim	sim卡是否可用
	 * @param	order		订单号
	 * */
	public void carrot_Pay(int productID, String ct_alias, String cm_index,
			boolean cm_repeat, boolean isUseSim, String cu_custom, String ia_money,
			String ia_waresid, String ia_url, String ia_appid,
			String ia_appkey, String mm_code, String order,CarrotPayCallBack callback);
	/**
	 * 分享
	 * @param	img_path	分享的图片路径
	 * @param	text_msg	分享的信息
	 * */
	public void carrot_Share(String img_path, String text_msg);
	
	public static int CARROT_PAY_SUCCESS = 1;
	public static int CARROT_PAY_CANCEL = 2;
	public static int CARROT_PAY_FAILD = 3;
	public static int CARROT_PAY_UNKNOW = 4;
	public interface CarrotPayCallBack {  
	    public void onPayEnd(int isSuccess);  
	} 
}
