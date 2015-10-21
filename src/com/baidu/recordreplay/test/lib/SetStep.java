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

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
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
        	local.enterText(0, MyEntry.phonenum(local));//输入手机号
        	local.enterText(1, MyEntry.password(local));//输入密码
        	local.clickOnButton("登录");
        	local.sleep(2000);
        	String logouttxt = MyEntry.logoutbutton(local).getText().toString();//退出登录按钮文案
        	assertEquals("Login Fail!", "退出登录", logouttxt);//判断是否登录成功，跳转回上门洗车页
    	}
	}
	
	/**
	 * 功能：退出，test2155
	 * 操作：启动->进入我的页面->判断是否为未登录状态->点击“退出登录”->判断是否退出成功
	 */
	public static void logout(LocalLib local) {
		local.sleep(2000);
		local.clickOnView(MyEntry.mypageentry(local));//判断是否未登录，点击我的，查看登录状态
		String logouttxt = MyEntry.logoutbutton(local).getText().toString();//退出登录按钮文案
		if("退出登录".equals(logouttxt)) {//判断是否是未登录状态
//    	if(local.waitForText("退出登录")) {//判断是否是未登录状态
    		local.clickOnView(local.getView("btn_login"));//点击退出登录
    		assertTrue("No logout dailog!", local.waitForDialogToOpen());//点击退出登录
    		local.clickOnView(local.getView("com.ganji.android.ccar:id/dialog_item_text",1));
    		local.sleep(500);
    		String nametxt = MyEntry.profilenametxt(local).getText().toString();//游客文字
        	assertEquals("Logout Fail！", "游客", nametxt);//判断是否退出成功
        	local.sleep(2000);
    	}
	}

	/**
	 * 功能：设置联系方式test2015
	 * 操作：判断是否登录->点击联系方式->输入手机号->输入密码->判断是否成功登录
	 */
	public static void setphone(LocalLib local) {
//    	local.sleep(2000);
    	String phone = MyEntry.phoneentry(local).getHint().toString();
    	String phone1 = MyEntry.phoneentry(local).getText().toString();
    	if((MyAssert.washcar_nophone_txt(local)).equals(phone)&&phone1.isEmpty()) {//判断是否是未登录状态
    		local.clickOnView(MyEntry.phoneentry(local));//点击您的联系方式
//        	local.sleep(2000);
    		assertTrue("未成功跳转到登录页面！", local.waitForText("登录"));
        	local.enterText(0, MyEntry.phonenum(local));//输入手机号
        	local.enterText(1, MyEntry.password(local));//输入密码
        	local.clickOnView(MyEntry.loginbutton(local));//点击登录按钮
//        	local.sleep(1500);
        	local.waitForView(MyEntry.phoneentry(local));
        	String phonetxt = MyEntry.phoneentry(local).getText().toString();//获取手机号
        	assertEquals("login Failed！", MyEntry.phonenum(local), phonetxt);
//        	assertTrue("login Failed！", local.waitForText(MyEntry.phonenum(local))&&local.waitForText(MyAssert.washcar_header_txt(local)));//判断是否登录成功，跳转回上门洗车页
    	}
	}
	
	/**
	 * 功能：设置车辆信息--tested车牌号,test_2030_
	 * 操作：点击“您的车牌号码”->选择牌子地区->输入车牌号->点击“选择车辆品牌”->选第五个品牌->选第二个车系->点击“选择车身颜色”->选择第六个颜色->点击确定->判断是否保存成功
	 */
	public static void setcarinfo(LocalLib local){
		
		String carinfotxt = MyEntry.carinfoentry(local).getText().toString();
		if (!carinfotxt.contains("TESTED")) { //没有车辆信息再重新选择
			local.clickOnView(MyEntry.carinfoentry(local));//点击您的车牌号码
	    	local.sleep(2000);
	    	local.clickOnView(local.getView("txt_plate_label"));//选择地区
	    	local.sleep(2000);	
	    	Random r = new Random();//任意选择一个牌子类型
	    	int carplate = r.nextInt(35);
	    	local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_car_plate", carplate));
	    	local.clearEditText(0);//输入车牌号，输入车牌号TESTED
	    	local.enterText(0, "TESTED");
	    	local.clickOnView(local.getView("txt_car_type"));//点击选择车辆品牌
	    	local.clickInList(5);//选择品牌，第五个为例
	    	local.clickInList(2);//选择车系，第二个为例
	    	local.sleep(2000);
	    	local.clickOnView(local.getView("txt_car_color"));//选择车身颜色
	    	local.clickInList(6);//选择车身颜色，以第六个为例
	    	local.sleep(1000);
	    	local.clickOnText("确定");//点击确定
			assertTrue("SetCarInfo fail!", MyAssert.getwashcar_carinfo_txt(local).contains("TESTED"));//断言：是否添加成功，且跳转回上门洗车页
	    	assertTrue("Current page is not \"标准洗车/套餐\"！", "标准洗车".equals(MyAssert.getall_header_txt(local))||MyAssert.getall_header_txt(local).contains("套餐"));//断言：是否进入标准洗车页
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
	    	assertTrue("SetCarInfo fail!", MyAssert.getwashcar_carinfo_txt(local).contains("FIRST1"));//断言：是否添加成功，且跳转回上门洗车页，判断是否是first手机号
	    	assertTrue("Current page is not \"标准洗车/套餐\"！", "标准洗车".equals(MyAssert.getall_header_txt(local))||MyAssert.getall_header_txt(local).contains("套餐"));//断言：是否进入标准洗车页
		}
	}
	
	/**
	 * 功能：设置车辆停放地址（任意选择一个）test2035
	 * 操作：点击“车辆停放地址”->点击“选择车辆停放地址”框->选择第三个地址->点击“下一步”->输入具体地址->点击确定->判断是否设置成功
	 */
	public static void setcaraddress(LocalLib local) {
		String caraddresstxt = MyEntry.caraddressentry(local).getText().toString();
		if (caraddresstxt.isEmpty()) {//如果为空，就重新选择
			local.clickOnView(MyEntry.caraddressentry(local));//点击车辆停放地址
	    	local.sleep(2000);
	    	local.clickOnView(MyEntry.choosecaraddressentry(local));//点击选择车辆停放位置
	    	local.sleep(3000);
	    	local.clickInList(3);//任选一个，第三个，海淀区委党校
	    	local.sleep(2000);
	    	local.clickOnView(local.getView("txt_title_right"));//点击下一步
	    	local.sleep(1500);
	    	TextView addresscontent = (TextView)local.getView("com.ganji.android.ccar:id/center_text",2);
			String addresscontenttxt = addresscontent.getText().toString();
	    	assertEquals("No \"详细位置描述\" text!", "详细位置描述", addresscontenttxt);//断言：是否进入详细位置描述页
//	    	assertEquals("Current page is not \"详细位置描述\"！", "详细位置描述", MyAssert.getall_header_txt(local));//断言：是否进入详细位置描述页
	    	assertTrue("No MarkText!", local.waitForView(local.getView("lay_edittext_mark"))&&local.waitForView(local.getView("et_content2")));//断言：检查是否有地点和详细地址输入框
	    	local.enterText(MyEntry.caraddressdetail(local), "设置赶集小区 20号楼 2门 222");
	    	local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_title_right",2));//点击确定
	    	String caraddrtxt = MyEntry.caraddressentry(local).getText().toString();//获取文字
	    	assertTrue("SetCarAddress fail!", !"车辆停放地址".equals(caraddrtxt));//判断地址是否设置成功
	    	local.clickOnView(MyEntry.caraddressentry(local));//点击后应进入详细位置描述页
	    	assertEquals("No \"详细位置描述\" text!", "详细位置描述", addresscontenttxt);//断言：是否进入详细位置描述页
	    	local.clickOnView(local.getView("btn_title_left",2));//点击返回
	    	local.sleep(1500);
	    	assertTrue("Current page is not \"标准洗车/套餐\"！", "标准洗车".equals(MyAssert.getall_header_txt(local))||MyAssert.getall_header_txt(local).contains("套餐"));//断言：是否进入标准洗车页
		}
	}
	
	/**
	 * 功能：设置车辆服务时间，test_2045_
	 * 操作：点击“您希望服务的时间”->判断当前选择是套餐还是标准洗车->套餐选择第三天，标准洗车选择第四天->从第四（三）天开始选择全天->判断是否选择成功
	 */
	public static void setwashcartime(LocalLib local) {
		while(local.waitForText("您希望服务的时间")) {
    		local.clickOnView(MyEntry.carservicetimeentry(local));//设置服务时间，点击服务时间入口
    		local.sleep(3000);
    		int i;
			if (local.waitForView(local.getView("arrow_next")))//判断是套餐还是标准洗车，是否有向右箭头按钮
    			i=3;//套餐点击第三天
    		else
    			i=4;//标准洗车点击第四天
    		String date_and_time="data_and_time_"+Integer.toString(i) +"_date";
        	local.clickOnView(local.getView(date_and_time));//从第四天开始选择
        	local.sleep(1000);
        	local.clickOnView(local.getView("peroid_time"));//点击全天(有可能会有预约满的情况)
        	local.sleep(4000);
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
    	SetStep.setcarinfo(local);//设置车辆信息
    	SetStep.setcaraddress(local);//设置车辆停放位置
    	SetStep.setwashcartime(local);//设置服务时间
    	local.sleep(500);
    	local.clickOnView(MyEntry.orderbutton(local));//点击立即下单
    	local.sleep(3000);
    	assertTrue("Submit order failure！", local.waitForDialogToOpen());//判断是否弹出选择支付方式的提示
	}

	/**
	 * 功能：支付功能,test_2085_
	 * 操作：点击“余额支付”->点击确定->判断是否支付成功
	 */
	public static void pay(LocalLib local) {
//		local.sleep(1500);
		local.searchText("余额支付");
		local.waitForView(local.getView("com.ganji.android.ccar:id/dialog_item_text"));
		local.clickOnView(local.getView("com.ganji.android.ccar:id/dialog_item_text",1));//点击余额支付
//		local.sleep(500);
		local.searchText("支付金额");
		local.waitForView(local.getView("btn_ok"));
		local.clickOnView(local.getView("btn_ok"));//点击确定
		assertTrue("pay order failure！", local.waitForText("预约成功"));//判断是否支付成功
	}

	/**
	 * 功能：取消订单-预约成功，test_2105
	 * 操作：点击“取消订单”按钮->任意选择一个原因->点击确定->判断是否取消成功
	 */
	public static void payordercancle(LocalLib local) {
		assertTrue("没有取消订单按钮！", local.waitForText("取消订单"));
    	local.clickOnView(local.getView("txt_title_right"));//点击取消订单按钮
    	local.sleep(500);
    	local.waitForView(local.getView("com.ganji.android.ccar:id/item_content"));
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/item_content", 0));//任意选择 一个原因
    	local.clickOnView(local.getView("txt_ok"));//点击确定
    	local.sleep(1500);
    	TextView cancle =(TextView)local.getView("com.ganji.android.ccar:id/title",1);//取消订单成功文案
    	String cancletxt = cancle.getText().toString();
    	assertEquals("current status is not cancled!", "取消订单成功", cancletxt);
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_title_left"));//点击返回
    	local.sleep(500);
    	local.clickOnView(MyEntry.mypageentry(local));//点击我的
    	local.sleep(500);
    	local.waitForText("￥");
    	TextView balancenum = (TextView)local.getView("com.ganji.android.ccar:id/txt_balance_price");
    	String balancenumtxt = balancenum.getText().toString();//余额
    	assertEquals("cancle order is failed!", "￥1112", balancenumtxt);//1.7.0,add,断言，是否取消成功
	}
	
	/**
	 * 功能：取消订单-待支付状态
	 * 操作：点击“取消订单”按钮->点击确定->获取第一条订单状态->判断是否取消成功
	 * @param local
	 */
	public static void nopayordercancle(LocalLib local) {
		local.sleep(2000);
    	local.clickOnView(local.getView("txt_title_right"));//点击取消订单按钮
    	local.sleep(1000);
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_datetime_sure"));//点击确定
    	local.sleep(1000);
    	TextView status =(TextView)local.getView("com.ganji.android.ccar:id/tv_needs_status", 0);//跳转到我的订单页，获取第一条订单的状态
    	String statustext = status.getText().toString();
    	assertTrue("To \"订单已取消\" page failed!", "订单已取消".equals(statustext));//已取消页，检查页面元素
	}
	
	/**
	 * 功能：标准洗车页选择套餐test_2010_
	 * 操作：进入标准洗车页->点击标准服务区域->选择第三个套餐->判断是否选择成功
	 */
	public static void productselect(LocalLib local) {
    	ToPage.towashcarpage(local);//进入标准洗车
    	local.clickOnView(MyEntry.selectproductentry(local));//点击标准服务区域
    	local.sleep(1000);
    	local.waitForText("打蜡");
		local.waitForView(local.getView("com.ganji.android.ccar:id/price_now_tv"));
		while(!local.waitForView(local.getView("android:id/list"))){
			local.clickOnView(MyEntry.selectproductentry(local));//点击标准服务区域
			break;
		}
    	local.sleep(800);
    	//获取套餐数量
    	ListView list = (ListView)local.getView("android:id/list");
    	int num = list.getCount();
    	assertEquals("product num is not 8!", 8,num);//套餐为4个，num就为7
    	int productnum = num-4;//productnum取3
    	Random r = new Random();
    	int seleproductnum = r.nextInt(productnum)+1;//取1到4随机
//		assertEquals("no 3", 3,seleproductnum);
    	local.waitForView(local.getView("com.ganji.android.ccar:id/item_rbtn"));
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/item_rbtn", seleproductnum));//选择第3个套餐
    	local.sleep(2000);
    	assertFalse("No go to \"下单\" page！", local.waitForText("标准洗车"));//断言：判断是否选择成功
	}
	
	/**
	 * 添加常用地址页1
	 * 操作：设置地址和备注
	 */
	public static void setaddrandcontent(LocalLib local,String content) {
		local.clickOnView(MyEntry.addhomeaddressaddressinput(local));//点击车辆地址
		local.sleep(1000);
		local.clickOnView(MyEntry.choosecaraddressentry(local));//点击选择车辆停放位置
    	local.sleep(3000);
    	local.clickInList(3);//任选一个，第三个，海淀区委党校
    	local.sleep(2000);
    	local.clickOnView(local.getView("txt_title_right"));//点击确定
		local.sleep(1500);
 		local.clearEditText(MyEntry.addfavoriteaddresscontentinput(local));//清空文字
		local.enterText(MyEntry.addfavoriteaddresscontentinput(local), content);//修改地址描述
	}
}
