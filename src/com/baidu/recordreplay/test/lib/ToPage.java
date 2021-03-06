package com.baidu.recordreplay.test.lib;

import static org.junit.Assert.*;

import org.junit.Test;

import com.baidu.cafe.local.LocalLib;
import com.baidu.recordreplay.test.lib.MyEntry;
public class ToPage {

	/**
	 * 功能：进入活动列表页
	 * 操作：首页->点击header右侧“活动”
	 */
	public static void toactionpage(LocalLib local) {
//		local.sleep(3000);
    	local.waitForActivity("CHomeActivity");
    	local.clickOnView(MyEntry.actionentry(local));//点击活动
//    	local.sleep(8000);
    	local.waitForView(local.getView("com.ganji.android.ccar:id/img_ads"));
	}
	
	/**
	 * 功能：进入我的页面
	 * 操作：首页->点击“我的”
	 */
	public static void tomypage(LocalLib local) {
		local.sleep(2000);
    	local.clickOnView(MyEntry.mypageentry(local));//点击我的页面
    	local.sleep(2000);
	}
	
	/**
	 * 功能：进入选择服务城市页面
	 * 操作：首页->点击header左侧服务城市
	 */
	public static void toselectcitypage(LocalLib local) {
		local.sleep(3000);
    	local.waitForActivity("CHomeActivity");
    	local.clickOnView(MyEntry.selectservicecityentry(local));//进入选择服务城市页面
    	local.sleep(2000);
	}
	
	/**
	 * 功能：进入选择服务页
	 * 操作：首页->点击上门洗车->点击标准服务区域
	 */
	public static void toselectproductpage(LocalLib local) {
		ToPage.towashcarpage(local);//点击开始洗车
    	local.clickOnView(MyEntry.selectproductentry(local));//点击标准服务区域
    	local.sleep(3000);
	}
	
	/**
	 * 功能：进入任务页
	 * 操作：首页->点击任务
	 */
	public static void totaskpage(LocalLib local) {
    	local.waitForActivity("CHomeActivity");
    	local.clickOnView(MyEntry.taskentry(local));//点击任务
    	local.sleep(5000);
	}
	
	/**
	 * 功能：进入上门洗车页
	 * 操作：首页->点击上门洗车
	 */
	public static void towashcarpage(LocalLib local) {
		local.waitForActivity("CHomeActivity");
    	local.clickOnView(MyEntry.washcarentry(local));//点击上门洗车
    	local.sleep(3000);
	}
	
	/**
	 * 功能：进入余额页
	 * 操作：首页->进入我的页面->点击余额
	 */
	public static void tobalance(LocalLib local) {
    	ToPage.tomypage(local);
    	local.clickOnView(local.getView("lay_my_balance"));//点击余额
    	local.sleep(1500);
	}
	
	/**
	 * 功能：进入我的优惠劵页面
	 * 操作：首页->进入我的页面->点击优惠劵
	 */
	public static void tomycoupon(LocalLib local) {
    	ToPage.tomypage(local);
    	local.clickOnView(local.getView("lay_my_coupon"));//点击优惠劵
    	local.sleep(1500);
	}
	
	/**
	 * 功能：进入红包页面
	 * 操作：首页->进入我的页面->点击红包
	 */
	public static void tomyredpackage(LocalLib local) {
    	ToPage.tomypage(local);
    	local.clickOnView(local.getView("lay_my_red_package"));//点击红包
    	local.sleep(1500);
	}
	
	/**
	 * 功能：进入我的奖品页面
	 * 操作：首页->进入我的页面->点击我的奖品
	 */
	public static void tomyaward(LocalLib local) {
    	ToPage.tomypage(local);
    	local.clickOnView(local.getView("lay_my_award"));//点击我的奖品
    	local.sleep(1500);
	}
	
	/**
	 * 功能：进入我的车辆页面
	 * 操作：首页->进入我的页面->点击我的车辆
	 */
	public static void tomycar(LocalLib local) {
    	ToPage.tomypage(local);
    	local.clickOnView(local.getView("lay_car"));//点击我的车辆
    	local.sleep(1500);
	}
	
	/**
	 * 功能：进入我的资料页面
	 * 操作：首页->进入我的页面->点击我的资料
	 */
	public static void tomyprofile(LocalLib local) {
    	ToPage.tomypage(local);
    	local.clickOnView(local.getView("lay_my_profile"));//点击我的资料
    	local.sleep(1500);
	}
	
	/**
	 * 功能：进入关于赶集易洗车页面
	 * 操作：首页->进入我的页面->点击关于赶集易洗车
	 */
	public static void toaboutmore(LocalLib local) {
    	ToPage.tomypage(local);
    	local.clickOnView(local.getView("lay_more"));//点击关于赶集易洗车
    	local.sleep(1500);
	}
	
	
	
	
	/**
	 * 功能：进入常用地址页面
	 * 操作：首页->进入我的页面->点击常用地址
	 */
	public static void tomyaddress(LocalLib local) {
    	ToPage.tomypage(local);
    	local.clickOnView(local.getView("lay_favorite_address"));//点击常用地址
    	local.sleep(1500);
	}
	
	/**
	 * 功能：进入订单页面
	 * 操作：首页->进入我的页面->点击我的车辆
	 */
	public static void toorderpage(LocalLib local) {
		local.sleep(2000);
    	local.clickOnView(MyEntry.myneedentry(local));//点击订单
    	local.sleep(1500);
	}
	
	
	
	
	

}
