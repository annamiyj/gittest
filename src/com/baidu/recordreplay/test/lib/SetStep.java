package com.baidu.recordreplay.test.lib;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;
import org.w3c.dom.Text;

import android.R.integer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baidu.cafe.local.LocalLib;

public class SetStep {
	/**
	 * 功能:登录，test2000
	 * 操作：启动->进入我的页面->判断是否登录->点击“点此登录”->输入手机号和密码->点击“登录”->判断是否登录成功
	 */
	public static void login(LocalLib local) {
		local.sleep(2000);
		local.clickOnView(MyEntry.mypageentry(local));//判断是否未登录，点击我的，查看登录状态
    	if(local.waitForText("点此登录")) {//判断是否是未登录状态
    		local.clickOnView(local.getView("btn_login"));//点击点此登录
    		local.sleep(3000);
        	local.enterText(0, ChangL.phonenum);//输入手机号
        	local.enterText(1, ChangL.password);//输入密码
        	local.clickOnButton("登录");
        	local.sleep(2000);
        	String logouttxt = MyEntry.logoutbutton(local).getText().toString();//退出登录按钮文案
        	assertEquals("Login Fail!", "退出登录", logouttxt);//判断是否登录成功，跳转回上门洗车页
    	}
	}
	
	/**
	 * 功能：退出，test2155
	 * 操作：启动->进入我的页面->判断是否为未登录状态->点击“退出登录”->判断是否退出成功
	 * 1.7.5,update,退出流程
	 */
	public static void logout(LocalLib local) {
		local.sleep(2000);
		local.clickOnView(MyEntry.mypageentry(local));//判断是否未登录，点击我的，查看登录状态
		String nametxt = MyEntry.profilenametxt(local).getText().toString();//昵称文案
		if(!"点击登录".equals(nametxt)) {//判断是否是未登录状态
			local.sleep(500);
			local.waitForView(MyEntry.profileimg(local));
			local.clickOnView(MyEntry.profileimg(local));//点击头像
			assertTrue("未成功跳转到修改个人资料页！", local.waitForText("修改个人资料"));
    		local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_title_right"));//点击退出登录
//    		local.sleep(500);
//    		assertTrue("No logout dailog!", local.waitForDialogToOpen());//断言：是否有dailog弹出
    		local.clickOnView(local.getView("com.ganji.android.ccar:id/dialog_item_text",1));
    		local.sleep(500);
    		String nameaftertxt = MyEntry.profilenametxt(local).getText().toString();//点击登录文字
        	assertEquals("Logout Fail！", "点击登录", nameaftertxt);//判断是否退出成功
        	local.sleep(2000);
    	}
	}
	
	/**
	 * 功能：选中清理内饰
	 * 操作：点击清理内饰->判断是否选中
	 */
	public static void checkinterior(LocalLib local) {
		local.sleep(1000);
//		local.waitForView(local.getView("com.ganji.android.ccar:id/main_layout"));
//		local.sleep(500);
		local.waitForText("需要清洗内饰");
//		local.clickOnView(local.getView("com.ganji.android.ccar:id/main_layout"));//点击清理内饰，默认是未选中的
		local.clickOnText("需要清洗内饰");//选中清理内饰
		local.sleep(1000);
		CheckBox interiorcheck = (CheckBox)local.getView("com.ganji.android.ccar:id/chk_interior");
		while(!interiorcheck.isChecked()){
//			local.clickOnView(local.getView("com.ganji.android.ccar:id/main_layout"));//点击清理内饰，默认是未选中的
			local.clickOnText("需要清洗内饰");
			local.sleep(1000);
			if(interiorcheck.isChecked())
				break;
		}
//		local.sleep(2500);
    	assertTrue("interior is not checked!", interiorcheck.isChecked());
	}
	

	/**
	 * 功能：设置联系方式test2015
	 * 操作：判断是否登录->点击联系方式->输入手机号->输入密码->判断是否成功登录
	 */
	public static void setphone(LocalLib local) {
//    	local.sleep(2000);
		local.scrollUp();//上拉到顶部
    	String phone = MyEntry.phoneentry(local).getHint().toString();
    	String phone1 = MyEntry.phoneentry(local).getText().toString();
    	if((ChangL.nophonetxt).equals(phone)&&phone1.isEmpty()) {//判断是否是未登录状态
    		local.clickOnView(MyEntry.phoneentry(local));//点击您的联系方式
//        	local.sleep(2000);
    		assertTrue("未成功跳转到登录页面！", local.waitForText("登录"));
        	local.enterText(0, ChangL.phonenum);//输入手机号
        	local.enterText(1, ChangL.password);//输入密码
        	local.clickOnView(MyEntry.loginbutton(local));//点击登录按钮
//        	local.sleep(1500);
        	local.searchText("需要清洗内饰",10000);
        	local.searchText(ChangL.phonenum,10000);
//        	local.waitForView(MyEntry.phoneentry(local));
        	local.screenShotNamedCaseName("loginpage");
        	String phonetxt = MyEntry.phoneentry(local).getText().toString();//获取手机号
        	assertEquals("login Failed！", ChangL.phonenum, phonetxt);
//        	assertTrue("login Failed！", local.waitForText(MyEntry.phonenum(local))&&local.waitForText(MyAssert.washcar_header_txt(local)));//判断是否登录成功，跳转回上门洗车页
    	}
	}
	
	/**
	 * 功能：设置车辆信息--tested车牌号,test_2030_
	 * 操作：点击“您的车牌号码”->选择牌子地区->输入车牌号->点击“选择车辆品牌”->选第五个品牌->选第二个车系->点击“选择车身颜色”->选择第六个颜色->点击确定->判断是否保存成功
	 */
	public static void setcarinfo(LocalLib local, String carnum){
		String carinfotxt = MyEntry.carinfoentry(local).getText().toString();
		if (!carinfotxt.contains(carnum)||carinfotxt.isEmpty()) { //没有车辆信息再重新选择
//		if (carinfotxt.isEmpty()&&!carinfotxt.contains(carnum)) { //没有车辆信息再重新选择
			local.searchText("选择车牌");
			local.clickOnView(MyEntry.carinfoentry(local));//点击您的车牌号码
//	    	local.sleep(2000);
	    	assertTrue("未成功跳转到车辆信息页！", local.waitForText("车辆信息"));
	    	local.clickOnView(local.getView("txt_plate_label"));//选择地区
	    	local.searchText("选择车牌");
//	    	local.sleep(2000);
	    	local.waitForView(local.getView("com.ganji.android.ccar:id/txt_car_plate"));
	    	Random r = new Random();//任意选择一个牌子类型
	    	int carplate = r.nextInt(35);
	    	local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_car_plate", carplate));
	    	local.sleep(1000);
	    	EditText name = (EditText)local.getView("com.ganji.android.ccar:id/et_name");//输入框
	    	local.clearEditText(name);
	    	local.enterText(name, carnum);
//	    	local.clearEditText(0);//输入车牌号，输入车牌号TESTED
//	    	local.enterText(0, carnum);
	    	local.clickOnView(local.getView("txt_car_type"));//点击选择车辆品牌
	    	local.searchText("选择品牌");
	    	local.searchText("奥迪");
	    	local.screenShotNamedCaseName("xuanzepinpai");
	    	local.clickInList(5);//选择品牌，第五个为例
	    	local.sleep(500);
	    	local.clickInList(2);//选择车系，第二个为例
	    	local.sleep(500);
	    	local.screenShotNamedCaseName("readyxuanzecolor");
	    	local.clickOnView(local.getView("txt_car_color"));//选择车身颜色
	    	local.screenShotNamedCaseName("xuanzecolor");
	    	local.searchText("选择车身颜色");
	    	local.clickInList(6);//选择车身颜色，以第六个为例
	    	local.sleep(500);
	    	local.clickOnText("确定");//点击确定
	    	local.sleep(1000);
	    	local.searchText("优惠券");
	    	local.scrollUp();//拉到最上
//	    	assertTrue("未成功跳转到下单页！",local.waitForView(MyEntry.carinfoentry(local)));//等待跳转到上门洗车页
	    	assertTrue("未成功跳转到下单页！",local.waitForText(ChangL.phonenum));//等待跳转到上门洗车页
	    	local.sleep(1000);
	    	assertTrue("SetCarInfo fail!", MyAssert.getwashcar_carinfo_txt(local).contains(carnum));//断言：是否添加成功，且跳转回上门洗车页
//	    	assertTrue("Current page is not \"标准洗车/套餐\"！", ChangL.washcarheadertxt.equals(MyAssert.getall_header_txt(local))||MyAssert.getall_header_txt(local).contains("套餐")||MyAssert.getall_header_txt(local).contains("深清"));//断言：是否进入标准洗车页
	    	assertTrue("Current page is not \"标准洗车/套餐\"！", local.searchText("优惠券"));//断言：是否进入标准洗车页
		}
	}
	
	/**
	 * 功能：设置车辆信息--first
	 * 操作：点击“您的车牌号码”->选择牌子地区->输入车牌号->点击“选择车辆品牌”->选第五个品牌->选第二个车系->点击“选择车身颜色”->选择第六个颜色->点击确定->判断是否保存成功
	 */
	public static void setcarinfofirst(LocalLib local){
		String carinfotxt = MyEntry.carinfoentry(local).getText().toString();
		if (!carinfotxt.contains("FIRST1")) { //没有车辆信息再重新选择
			local.clickOnView(MyEntry.carinfoentry(local));//点击您的车牌号码
	    	local.sleep(2000);
	    	local.clickOnView(local.getView("txt_plate_label"));//选择地区
	    	local.sleep(2000);
	    	Random r = new Random();//任意选择一个牌子类型
	    	int carplate = r.nextInt(35);
	    	local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_car_plate", carplate));
	    	local.sleep(1000);
	    	local.clearEditText(0);//输入车牌号，判输入车牌号FIRST1
	    	local.enterText(0, "FIRST1");
	    	local.clickOnView(local.getView("txt_car_type"));//点击选择车辆品牌
	    	local.clickInList(5);//选择品牌，第五个为例
	    	local.clickInList(2);//选择车系，第二个为例
	    	local.sleep(2000);
	    	local.clickOnView(local.getView("txt_car_color"));//选择车身颜色
	    	local.clickInList(6);//选择车身颜色，以第六个为例
	    	local.sleep(1000);
	    	local.clickOnText("确定");//点击确定
	    	local.waitForView(MyEntry.carinfoentry(local));
	    	assertTrue("SetCarInfo fail!is "+MyAssert.getwashcar_carinfo_txt(local), MyAssert.getwashcar_carinfo_txt(local).contains("FIRST1"));//断言：是否添加成功，且跳转回上门洗车页，判断是否是first手机号
	    	assertTrue("Current page is not \"标准洗车/套餐\"！", ChangL.washcarheadertxt.equals(MyAssert.getall_header_txt(local))||MyAssert.getall_header_txt(local).contains("套餐"));//断言：是否进入标准洗车页
		}
	}
	
	/**
	 * 功能：设置车辆停放地址（任意选择一个）test2035
	 * 操作：点击“车辆停放地址”->点击“选择车辆停放地址”框->选择第三个地址->点击“下一步”->输入具体地址->点击确定->判断是否设置成功
	 */
	public static void setcaraddress(LocalLib local) {
		String caraddresstxt = MyEntry.caraddressentry(local).getText().toString();
		if (caraddresstxt.isEmpty()||!caraddresstxt.contains("清河")) {//如果为空，就重新选择
			local.clickOnView(MyEntry.caraddressentry(local));//点击车辆停放地址
//	    	local.sleep(2000);
	    	assertTrue("未成功跳转到车辆停放地址页！", local.waitForText("选择车辆停放位置"));
	    	local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_right", 3));//点击手动分单地址
	    	local.clickOnText("确定");
//	    	local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_title_right"));//点击确定
	    	local.waitForView(MyEntry.caraddressentry(local));//等待回到上门洗车页
	    	local.sleep(500);
		}
	}
	
	/**
	 * 功能：设置车辆停放地址（任意选择一个）test2035
	 * 操作：点击“车辆停放地址”->点击“选择车辆停放地址”框->选择第三个地址->点击“下一步”->输入具体地址->点击确定->判断是否设置成功
	 */
	public static void setcaraddressauto(LocalLib local) {
		String caraddresstxt = MyEntry.caraddressentry(local).getText().toString();
		if (caraddresstxt.isEmpty()||!caraddresstxt.contains("八维")) {//如果为空，就重新选择
			local.clickOnView(MyEntry.caraddressentry(local));//点击车辆停放地址
//	    	local.sleep(2000);
	    	assertTrue("未成功跳转到车辆停放地址页！", local.waitForText("选择车辆停放位置"));
	    	assertTrue("未成功跳转到车辆停放地址页！", local.waitForText("选择车辆停放位置"));
	    	local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_right", 4));//点击自动分单地址
	    	local.clickOnText("确定");
//	    	local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_title_right"));//点击确定
	    	local.waitForView(MyEntry.caraddressentry(local));//等待回到上门洗车页
	    	local.waitForText("需要清洗内饰");
	    	local.sleep(500);
		}
	}
	
	
	/**
	 * 功能：设置车辆服务时间，test_2045_
	 * 操作：点击“您希望服务的时间”->判断当前选择是套餐还是标准洗车->套餐选择第三天，标准洗车选择第四天->从第四（三）天开始选择全天->判断是否选择成功
	 */
	public static void setwashcartime(LocalLib local) {
		while(local.waitForText("您希望服务的时间")) {
    		local.clickOnView(MyEntry.carservicetimeentry(local));//设置服务时间，点击服务时间入口
//    		local.sleep(3000);
    		local.searchText("周");
    		local.searchText("今天");
    		local.waitForView(local.getView("android:id/content"));
    		int i;
			if (local.waitForView(local.getView("arrow_next")))//判断是套餐还是标准洗车，是否有向右箭头按钮
    			i=3;//套餐点击第三天
    		else
    			i=4;//标准洗车点击第四天
    		String date_and_time="data_and_time_"+Integer.toString(i) +"_date";
        	local.clickOnView(local.getView(date_and_time));//从第四天开始选择
        	local.sleep(500);
        	local.clickOnView(local.getView("peroid_time"));//点击全天(有可能会有预约满的情况)
        	local.sleep(500);
        	i--;
        	if (i==1)
        		assertTrue("carwash time is full！", local.waitForText("您希望服务的时间"));
    	}   	
	}
	
	/**
	 * 功能：设置车辆服务时间，test_2045_
	 * 操作：点击“您希望服务的时间”->判断当前选择是套餐还是标准洗车->套餐选择第三天，标准洗车选择第四天->从第四（三）天开始选择全天->判断是否选择成功
	 */
	public static void setwashcartimeauto(LocalLib local) {
		while(local.waitForText("您希望服务的时间")) {
    		local.clickOnView(MyEntry.carservicetimeentry(local));//设置服务时间，点击服务时间入口
//    		local.sleep(3000);
    		local.waitForView(local.getView("android:id/content"));
    		int i=1;
    		String date_and_time="data_and_time_"+Integer.toString(1) +"_date";
        	local.clickOnView(local.getView(date_and_time));//从第四天开始选择
        	local.sleep(500);
        	local.clickOnView(local.getView("peroid_time"));//点击全天(有可能会有预约满的情况)
        	local.sleep(500);
        	i--;
        	if (i==1)
        		assertTrue("carwash time is full！", local.waitForText("您希望服务的时间"));
    	}   	
	}

	/**
	 * 功能：标准下单，不支付--不使用任何优惠（已登录）test_2050_
	 * 操作：登录->设置车辆信息->设置车辆停放位置->设置服务时间->点击“立即下单”->判断是否弹出支付方式的提示
	 */
	public static void washcarorder(LocalLib local) {
    	SetStep.setphone(local);//判断是否登录
    	SetStep.setcarinfo(local,"NOPAY1");//设置车辆信息
    	SetStep.setcaraddress(local);//设置车辆停放位置
    	SetStep.setwashcartime(local);//设置服务时间
    	SetStep.cancletimecard(local);//取消次卡选择
    	local.sleep(1000);
    	local.clickOnView(MyEntry.orderbutton(local));//点击立即下单
//    	local.sleep(3000);
    	local.waitForDialogToOpen();
    	assertTrue("Submit order failure！", local.waitForDialogToOpen());//判断是否弹出选择支付方式的提示
	}

	/**
	 * 功能：支付功能,test_2085_
	 * 操作：点击“余额支付”->点击确定->判断是否支付成功
	 */
	public static void pay(LocalLib local) {
//		local.sleep(1500);
		local.clickOnView(MyEntry.balancepaybutton(local));//点击余额支付
//		local.clickOnText("余额支付");
//		local.sleep(1000);
		local.waitForView(local.getView("btn_ok"));
		local.searchText("账户余额");
		local.clickOnView(local.getView("btn_ok"));//点击确定
		local.sleep(500);
		assertTrue("pay order failure！", local.waitForText("预约成功"));//判断是否支付成功
	}

	/**
	 * 功能：取消订单-预约成功（现金支付）
	 * 操作：点击“取消订单”按钮->任意选择一个原因->点击确定->判断是否取消成功
	 */
	public static void payordercancle(LocalLib local) {
		local.sleep(500);
    	local.clickOnView(local.getView("txt_title_right"));//点击取消订单按钮
//    	local.sleep(500);
//    	local.waitForView(local.getView("com.ganji.android.ccar:id/item_content"));
    	local.searchText("取消订单原因");
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/item_content", 0));//任意选择一个原因
    	local.clickOnView(local.getView("txt_ok"));//点击确定
    	local.sleep(500);
    	local.searchText("退款成功");
    	TextView cancle =(TextView)local.getView("com.ganji.android.ccar:id/title",1);//取消订单成功文案
    	String cancletxt = cancle.getText().toString();
    	local.sleep(500);
    	local.screenShotNamedCaseName("cancleorder");
//    	assertEquals("current status is not cancled!", "取消订单成功", cancletxt);
    	assertTrue("current status is not cancled!"+cancletxt, "取消订单成功".equals(cancletxt)||"退款成功".equals(cancletxt));
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_title_left"));//点击返回
//    	local.waitForText("￥");
    	local.sleep(1500);
    	TextView balancenum = (TextView)local.getView("com.ganji.android.ccar:id/txt_balance_price");
    	String balancenumtxt = balancenum.getText().toString();//余额
    	while(balancenumtxt!="1112"){
    		local.sleep(1500);
    		break;
    	}
    	assertEquals("cancle order is failed!", ChangL.balance, balancenumtxt);//1.7.0,add,断言，是否取消成功
	}
	
	/**
	 * 功能：取消订单-次卡支付
	 * 操作：启动->进入上门洗车页->下单，不付费->进入我的订单页->进入该订单详情页->取消订单->判断是否取消成功
	 * 1.7.0，add，取消订单-次卡支付
	 */
	public static void payorderusetimecardcancle(LocalLib local) {
//		local.sleep(500);
		assertTrue("未成功跳转到订单详情页", local.waitForText("取消订单"));
//		local.clickOnView(local.getView("txt_title_right"));//点击取消订单按钮'
		local.clickOnText("取消订单");//点击取消订单按钮
    	local.sleep(800);
    	local.waitForView(local.getView("com.ganji.android.ccar:id/item_content"));
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/item_content", 0));//任意选择 一个原因
    	local.clickOnView(local.getView("txt_ok"));//点击确定
//    	local.sleep(1000);
//    	
//    	assertTrue("Current status is not refund!", local.waitForText("订单详情"));//断言：header文案
//    	TextView repund = (TextView)local.getView("com.ganji.android.ccar:id/common_content");
//    	String repundtxt = repund.getText().toString();
//    	assertEquals("No timecard repund content!", "您的订单已取消成功,请查看”我的-洗车次卡-我的次卡“中的剩余次数", repundtxt);//断言：文案
//    	
//    	TextView cancle =(TextView)local.getView("com.ganji.android.ccar:id/title");//取消订单成功文案
//    	String cancletxt = cancle.getText().toString();
//    	assertEquals("current status is not cancled!", "取消订单成功", cancletxt);
//    	local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_title_left"));//点击返回
//    	ToPage.tomypage(local);//点击我的
//    	TextView timecardnum = (TextView)local.getView("com.ganji.android.ccar:id/txt_timecard_label");
//    	String timecardnumtxt = timecardnum.getText().toString();//剩余次卡数量
//    	assertEquals("cacle order of timecard is failed!", "110次", timecardnumtxt);//断言，是否取消成功
	}
	
	
	
	/**
	 * 功能：取消订单-待支付状态
	 * 操作：点击“取消订单”按钮->点击确定->获取第一条订单状态->判断是否取消成功
	 * @param local
	 */
	public static void nopayordercancle(LocalLib local) {
		local.sleep(2000);
    	local.clickOnView(local.getView("txt_title_right"));//点击取消订单按钮
//    	local.sleep(1000);
    	local.waitForText("确定");
    	local.waitForView(local.getView("com.ganji.android.ccar:id/btn_datetime_sure"));
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_datetime_sure"));//点击确定
//    	local.sleep(1000);
    	local.dragScreenToDown(250);//下拉刷新一下
    	local.sleep(1000);
    	local.screenShotNamedCaseName("orderrefresh");
    	local.waitForView(local.getView("com.ganji.android.ccar:id/tv_needs_status"));
    	TextView status =(TextView)local.getView("com.ganji.android.ccar:id/tv_needs_status", 0);//跳转到我的订单页，获取第一条订单的状态
    	String statustext = status.getText().toString();
//    	assertTrue("To \"订单已取消\" page failed!", "订单已取消".equals(statustext));//已取消页，检查页面元素
    	assertEquals("To \"订单已取消\" page failed!", "订单已取消", statustext);//已取消页，检查页面元素
	}
	
	/**
	 * 功能：标准洗车页选择套餐test_2010_
	 * 操作：进入标准洗车页->点击标准服务区域->选择第三个套餐->判断是否选择成功
	 */
	public static void productselect(LocalLib local) {
		ToPage.towashcarpage(local);//进入标准洗车
		local.clickOnView(MyEntry.selectproductentry(local));//点击标准服务区域
		local.sleep(1000);
		local.searchText("打蜡");
//		local.waitForView(local.getView("com.ganji.android.ccar:id/price_now_tv"));
		while(!local.waitForView(local.getView("android:id/list"))){
			local.clickOnView(MyEntry.selectproductentry(local));//点击标准服务区域
			break;
		}
    	local.sleep(800);
    	local.waitForView(local.getView("android:id/list"));
    	//获取套餐数量
    	ListView list = (ListView)local.getView("android:id/list");
    	int num = list.getCount();
    	assertEquals("product num is wrong!", ChangL.productselebj,num);//套餐为4个，num就为7
    	int productnum = num-4;//productnum取3
    	Random r = new Random();
//    	int seleproductnum = r.nextInt(productnum)+1;//取1到4随机
    	int seleproductnum = r.nextInt(3)+1;//取1到4随机
//		assertEquals("no 3", 3,seleproductnum);
    	local.waitForView(local.getView("com.ganji.android.ccar:id/item_rbtn"));
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/item_rbtn", seleproductnum));//选择第3个套餐
    	local.sleep(2000);
    	assertFalse("No go to \"下单\" page！", local.waitForText(ChangL.washcarheadertxt));//断言：判断是否选择成功
	}
	
	/**
	 * 功能：取消次卡
	 * 操作：进入标准洗车页->取消次卡
	 */
	public static void cancletimecard(LocalLib local) {
		local.sleep(1000);
		local.scrollDown();//向上拉一下
		local.scrollDown();//向上拉一下
		local.sleep(1000);
		local.waitForText("次卡");
		while(MyEntry.timecardcheckbox(local).isChecked()){
			local.clickOnView(MyEntry.timecardcheckbox(local));//取消次卡
			break;
		}
//    	assertFalse("次卡不为非选中状态！", MyEntry.timecardcheckbox(local).isChecked());//判断checkbox是否为未选中状态
	}
	
	
	/**
	 * 功能：删除订单
	 * 操作：进入订单列表页->点击第一个订单的删除按钮->判断是否删除
	 */
	public static void deleorder(LocalLib local) {
		local.clickOnView(local.getView("com.ganji.android.ccar:id/delete_order_btn"));//点击第一个订单的删除按钮
		while(!local.searchText("是否删除订单")){
			local.clickOnView(local.getView("com.ganji.android.ccar:id/delete_order_btn"));//点击第一个订单的删除按钮
		}
		assertTrue("未弹出删除订单dialog！", local.searchText("是否删除订单"));
		local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_ok"));//点击确定
		assertTrue("delete cancleorder failed!", local.waitForText("删除成功"));
	}
	
	
	/**
	 * 添加常用地址页1
	 * 操作：设置地址和备注
	 */
	public static void setaddrandcontent(LocalLib local,String content) {
		local.clickOnView(MyEntry.addhomeaddressaddressinput(local));//点击车辆地址
//		local.sleep(1000);
		assertTrue("未成功跳转到选择车辆停放位置页！", local.waitForText("选择车辆停放位置"));
		local.clickOnView(MyEntry.choosecaraddressentry(local));//点击选择车辆停放位置
//    	local.sleep(3000);
		local.searchText("位置");
    	local.waitForView(local.getView("com.ganji.android.ccar:id/search_list_half_screen"));
    	local.searchText("海淀区");
    	local.clickInList(3);//任选一个，第三个，海淀区委党校
//    	local.sleep(2000);
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_title_right"));//点击确定
//    	local.clickOnText("确定");
//		local.sleep(1000);
    	while(!local.searchButton("保存")){
    		local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_title_right"));//点击确定
//    		local.clickOnText("确定");
    	}
//		local.waitForView(MyEntry.addfavoriteaddresscontentinput(local));
    	assertTrue("未成功跳转回添加常用地址页！", local.searchButton("保存"));
 		local.clearEditText(MyEntry.addfavoriteaddresscontentinput(local));//清空文字
		local.enterText(MyEntry.addfavoriteaddresscontentinput(local), content);//修改地址描述
	}
}
