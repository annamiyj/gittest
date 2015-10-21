package com.baidu.recordreplay.test.lib;

import static org.junit.Assert.*;

import org.junit.Test;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.baidu.cafe.CafeTestCase;
import com.baidu.cafe.local.LocalLib;
import com.baidu.recordreplay.test.MyTest;
import com.robotium.solo.By;

import android.R.integer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MyEntry {

	/**
	 * 选择服务城市入口
	 * 位置：首页header左侧
	 */
	public static TextView selectservicecityentry(LocalLib local) {
		TextView servicecity = (TextView)local.getView("txt_title_left");
		return servicecity;
	}

	/**
	 * 活动入口
	 * 位置：首页header右侧
	 */
	public static TextView actionentry(LocalLib local) {
		TextView action = (TextView)local.getView("txt_title_right");
		return action;
	}
	
	/**
	 * 上门洗车入口
	 * 位置：首页
	 */
	public static LinearLayout washcarentry(LocalLib local) {
		LinearLayout washcar = (LinearLayout)local.getView("lay_btn1");
		return washcar;
	}

	/**
	 * 任务入口
	 * 位置：首页
	 */
	public static RelativeLayout taskentry(LocalLib local) {
		RelativeLayout task = (RelativeLayout)local.getView("lay_btn2");
		return task;
	}

	/**
	 * 套餐入口
	 * 位置：首页
	 */
	public static LinearLayout productentry(LocalLib local) {
		LinearLayout product = (LinearLayout)local.getView("lay_btn3");
		return product;
	}

	/**
	 * VIP下单入口
	 * 位置：首页
	 */
	public static LinearLayout VIPorderentry(LocalLib local) {
		LinearLayout VIPorder = (LinearLayout)local.getView("lay_btn4");
		return VIPorder;
	}
	
	/**
	 * 服务介绍入口
	 * 位置：首页
	 */
	public static LinearLayout serviceintroduceentry(LocalLib local) {
		LinearLayout serviceintroduce = (LinearLayout)local.getView("lay_btn5");
		return serviceintroduce;
	}
	
	/**
	 * 洗车指数入口
	 * 位置：首页
	 */
	public static RelativeLayout washindexentry(LocalLib local) {
		RelativeLayout washindex = (RelativeLayout)local.getView("lay_btn6");
		return washindex;
	}

	/**
	 * 首页入口
	 * 首页底部tab1
	 */
	public static View homepageentry(LocalLib local) {
		View home = local.getView("com.ganji.android.ccar:id/img_tab_icon",0);
		return home;
	}

	/**
	 * 我的订单入口
	 * 首页底部tab2
	 */
	public static View myneedentry(LocalLib local) {
		View myneed = local.getView("com.ganji.android.ccar:id/img_tab_icon",1);
		return myneed;
	}

	/**
	 * 我的页面入口
	 * 首页底部tab3
	 */
	public static View mypageentry(LocalLib local) {
		View myneed = local.getView("com.ganji.android.ccar:id/img_tab_icon",2);
		return myneed;
	}
	
	/**
	 * 选择服务入口（套餐图片）
	 * 位置：上门洗车页
	 */
	public static ImageView selectproductentry(LocalLib local) {
		ImageView productsele = (ImageView)local.getView("img_ads");
		return productsele;
	}
	
	/**
	 * 套餐title
	 * 位置：上门洗车页
	 */
	public static View producttitle(LocalLib local) {
		View producttitle = local.getView("txt_name");
		return producttitle;
	}
	
	/**
	 * 套餐content
	 * 位置：上门洗车页
	 */
	public static View productcontent(LocalLib local) {
		View productcontent = local.getView("txt_desc");
		return productcontent;
	}
	
	/**
	 * 套餐价格
	 * 位置：上门洗车页
	 */
	public static View productprice(LocalLib local) {
		View productprice = local.getView("txt_price_now");
		return productprice;
	}

	/**
	 * 联系方式入口
	 * 位置：上门洗车页
	 */
	public static EditText phoneentry(LocalLib local) {
		EditText phone = (EditText)local.getView("com.ganji.android.ccar:id/et_content",0);
		return phone;
	}
	
	
	/**
	 * 车辆信息入口
	 * 位置：上门洗车页
	 */
	public static EditText carinfoentry(LocalLib local) {
		EditText carinfo = (EditText)local.getView("com.ganji.android.ccar:id/et_content",2);
		return carinfo;
	}
	
	/**
	 * 车辆停放地址入口
	 * 位置：上门洗车页
	 */
	public static EditText caraddressentry(LocalLib local) {
		EditText caraddress = (EditText)local.getView("com.ganji.android.ccar:id/et_content",4);
		return caraddress;
	}

	/**
	 * 车辆服务时间入口
	 * 位置：上门洗车页
	 */
	public static TextView carservicetimeentry(LocalLib local) {
		TextView carservicetime = (TextView)local.getView("com.ganji.android.ccar:id/et_content1");
		return carservicetime;
	}
	
	/**
	 * 内饰区域
	 * 位置：上门洗车页
	 */
	public static TextView interiorentry(LocalLib local) {
		TextView interior = (TextView)local.getView("com.ganji.android.ccar:id/txt_interior");
		return interior;
	}
	
	/**
	 * 红包区域
	 * 位置：上门洗车页
	 */
	public static View redpackageentry(LocalLib local) {
		View redpackage = local.getView("com.ganji.android.ccar:id/txt_red_package_label");
		return redpackage;
	}	
	
	/**
	 * 单选按钮
	 * 位置：标准洗车-选择服务页
	 */
	public static RadioButton itemradiobutton(LocalLib local) {
		RadioButton itemradio = (RadioButton)local.getView("com.ganji.android.ccar:id/item_rbtn");
		return itemradio;
	}
	
	/**
	 * 套餐图片
	 * 位置：标准洗车-选择服务页
	 */
	public static ImageView itemimage(LocalLib local) {
		ImageView itemimage = (ImageView)local.getView("item_img_icon");
		return itemimage;
	}
	
	/**
	 * 套餐名称
	 * 位置：标准洗车-选择服务页
	 */
	public static TextView itemname(LocalLib local) {
		TextView itemname = (TextView)local.getView("txt_name");
		return itemname;
	}
	
	/**
	 * 套餐描述
	 * 位置：标准洗车-选择服务页
	 */
	public static TextView itemcontent(LocalLib local) {
		TextView itemcontent = (TextView)local.getView("txt_desc");
		return itemcontent;
	}
	
	/**
	 * 套餐价格
	 * 位置：标准洗车-选择服务页
	 */
	public static TextView itemprice(LocalLib local) {
		TextView itemprice = (TextView)local.getView("price_now_tv");
		return itemprice;
	}
	
	/**
	 * 查看按钮
	 * 位置：标准洗车-选择服务页
	 */
	public static TextView itemviewdetail(LocalLib local) {
		TextView itemviewdetail = (TextView)local.getView("com.ganji.android.ccar:id/view_detail_tv");
		return itemviewdetail;
	}
	
	/**
	 * “登录”按钮
	 * 位置：登录页/修改手机号页
	 */
	public static Button loginbutton(LocalLib local) {
		Button phone = (Button)local.getView("com.ganji.android.ccar:id/btn_long");
		return phone;
	}
	
	/**
	 * 手机号输入框
	 * 位置：登录页/修改手机号页
	 */
	public static EditText phoneinput(LocalLib local) {
		EditText phoneinput = (EditText)local.getView("com.ganji.android.ccar:id/edit_validate_phonenumber");
		return phoneinput;
	}
	
	/**
	 * 动态密码输入框
	 * 位置：登录页/修改手机号页
	 */
	public static EditText passwordinput(LocalLib local) {
		EditText passwordinput = (EditText)local.getView("com.ganji.android.ccar:id/edit_validate_code");
		return passwordinput;
	}
	
	
	/**
	 * 动态获取验证码按钮
	 * 位置：登录页/修改手机号页
	 */
	public static Button validatebutton(LocalLib local) {
		Button validatebutton = (Button)local.getView("com.ganji.android.ccar:id/btn_validate_or_retry");
		return validatebutton;
	}
	
	/**
	 * 车辆地区文字
	 * 位置：上门洗车-进入车辆信息页
	 */
	public static TextView carplate(LocalLib local) {
		TextView carplate = (TextView)local.getView("com.ganji.android.ccar:id/txt_plate_label");
		return carplate;
	}
	
	/**
	 * 车牌号区域
	 * 位置：上门洗车-进入车辆信息页
	 */
	public static EditText carnum(LocalLib local) {
		EditText carnum = (EditText)local.getView("com.ganji.android.ccar:id/et_name");
		return carnum;
	}
	
	/**
	 * 车辆品牌区域
	 * 位置：上门洗车-进入车辆信息页
	 */
	public static TextView cartype(LocalLib local) {
		TextView cartype = (TextView)local.getView("com.ganji.android.ccar:id/txt_car_type");
		return cartype;
	}
	
	/**
	 * 车身颜色区域
	 * 位置：上门洗车-进入车辆信息页
	 */
	public static TextView carcolor(LocalLib local) {
		TextView carcolor = (TextView)local.getView("com.ganji.android.ccar:id/txt_car_color");
		return carcolor;
	}
	
	/**
	 * 详细位置输入框
	 * 位置：详细位置描述页
	 */
	public static LinearLayout caraddress(LocalLib local) {
		LinearLayout caraddress = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_edittext_mark");
		return caraddress;
	}
	
	/**
	 * 详细位置备注输入框
	 * 位置：详细位置描述页
	 */
	public static EditText caraddressdetail(LocalLib local) {
		EditText caraddressdetail = (EditText)local.getView("com.ganji.android.ccar:id/et_content2");
		return caraddressdetail;
	}
	
	/**
	 * 详细位置备注输入框
	 * 位置：详细位置描述页
	 */
	public static TextView caraddressprompt(LocalLib local) {
		TextView caraddressprompt = (TextView)local.getView("com.ganji.android.ccar:id/txt_prompt2");
		return caraddressprompt;
	}
	
	/**
	 * 我的奖品入口
	 * 任务页
	 */
	public static TextView myaward(LocalLib local) {
		LinearLayout myaward = (LinearLayout)local.getView("com.ganji.android.ccar:id/layout_my_award");
		TextView myawardtxt = (TextView)myaward.getChildAt(0);
		return myawardtxt;
	}
	
	/**
	 * 邀请记录入口
	 * 任务页
	 */
	public static TextView inviterecord(LocalLib local) {
		LinearLayout inviterecord = (LinearLayout)local.getView("com.ganji.android.ccar:id/layout_invite_record");
		TextView inviterecordtxt = (TextView)inviterecord.getChildAt(0);
		return inviterecordtxt;
	}
	
	
	
	
	
	/**
	 * 立即下单按钮
	 * 位置：标准下单页
	 */
	public static Button orderbutton(LocalLib local) { 
		Button orderbutton = (Button)local.getView("com.ganji.android.ccar:id/btn_long");
		return orderbutton;
	}
	
	/**
	 * 第一个订单入口
	 * 我的订单页
	 */
	public static View thefirstorderentry(LocalLib local) {
		View thefirstorder = local.getView("com.ganji.android.ccar:id/txt_date", 0);
		return thefirstorder;
	}

	

	
	/**
	 * 搜索车辆停放地址入口
	 * 位置：选择车辆停放位置页
	 */
	public static View searchcaraddressentry(LocalLib local) {
		View searchcaraddress = local.getView("com.ganji.android.ccar:id/lay_edittext_mark");
		return searchcaraddress;
	}	

	/**
	 * 选择车辆停放地址入口
	 * 位置：选择车辆停放位置页
	 */
	public static LinearLayout choosecaraddressentry(LocalLib local) {
		LinearLayout choosecaraddress = (LinearLayout)local.getView("com.ganji.android.ccar:id/et_content");
//		TextView txt = (TextView)choosecaraddress.getChildAt(1);
		return choosecaraddress;
	}	
	

	/**
	 * 服务覆盖区域文案
	 * 位置：选择车辆停放位置页
	 */
	public static TextView servicediqutxt(LocalLib local) {
		TextView servicediqutxt = (TextView)local.getView("com.ganji.android.ccar:id/tv_service_tips");
		return servicediqutxt;
	}
	
	/**
	 * “位置”文案
	 * 位置：选择车辆停放位置页
	 */
	public static TextView weizhi(LocalLib local) {
		TextView weizhi = (TextView)local.getView("com.ganji.android.ccar:id/txt_content");
		return weizhi;
	}
	
	
	
	
	/**
	 * 立即下单按钮
	 * 位置：套餐下单页
	 */
	public static Button productorderbutton(LocalLib local) {
		Button orderbutton = (Button)local.getView("btn_buy");
		return orderbutton;
	}
	
	/**
	 * 立即付款按钮
	 * 订单详情页，待付款订单
	 */
	public static View nopayorderbutton(LocalLib local) {
		View nopayorderbutton= local.getView("btn_pay");
		return nopayorderbutton;
	}

	/**
	 * 取消按钮
	 * 点击立即下单后
	 */
	public static View canclebutton(LocalLib local) {
		View canclebutton= local.getView("com.ganji.android.ccar:id/dialog_item_text", 4);
		return canclebutton;
	}

	/**
	 * 个人头像
	 * 我的页面
	 */
	public static View profileimg(LocalLib local) {
		View profileimg = local.getView("com.ganji.android.ccar:id/img_profile");
		return profileimg;
	}
	
	/**
	 * 余额入口
	 * 我的页面
	 */
	public static RelativeLayout balanceentry(LocalLib local) {
		RelativeLayout balance = (RelativeLayout)local.getView("com.ganji.android.ccar:id/lay_my_balance");
		return balance;
	}
	
	/**
	 * 优惠劵入口
	 * 我的页面
	 */
	public static RelativeLayout couponentry(LocalLib local) {
		RelativeLayout couponen = (RelativeLayout)local.getView("com.ganji.android.ccar:id/lay_my_coupon");
		return couponen;
	}
	
	/**
	 * 红包入口
	 * 我的页面
	 */
	public static RelativeLayout myredpackageentry(LocalLib local) {
		RelativeLayout myredpackage = (RelativeLayout)local.getView("com.ganji.android.ccar:id/lay_my_red_package");
		return myredpackage;
	}
	
	/**
	 * 我的奖品入口
	 * 我的页面
	 */
	public static LinearLayout myawardentry(LocalLib local) {
		LinearLayout myaward = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_my_award");
		return myaward;
	}
	
	/**
	 * 我的邀请码入口
	 * 我的页面
	 */
	public static LinearLayout myinvitecodeentry(LocalLib local) {
		LinearLayout myinvitecode = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_my_invite_code");
		return myinvitecode;
	}
	
	/**
	 * 我的资料入口
	 * 我的页面
	 */
	public static LinearLayout myprofileentry(LocalLib local) {
		LinearLayout myprofile = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_my_profile");
		return myprofile;
	}
	
	/**
	 * 常用地址入口
	 * 我的页面
	 */
	public static LinearLayout myaddressentry(LocalLib local) {
		LinearLayout myaddress = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_favorite_address");
		return myaddress;
	}

	/**
	 * 我的车辆入口
	 * 我的页面
	 */
	public static LinearLayout mycarentry(LocalLib local) {
		LinearLayout mycar = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_car");
		return mycar;
	}
	
	/**
	 * 我的消息入口
	 * 我的页面
	 */
	public static LinearLayout mymessageentry(LocalLib local) {
		LinearLayout mymessage = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_message");
		return mymessage;
	}
	
	/**
	 * 关于赶集易洗车入口
	 * 我的页面
	 */
	public static LinearLayout mymoreentry(LocalLib local) {
		LinearLayout mymore = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_more");
		return mymore;
	}
	
//	优惠劵入口
	
	
	/**
	 * 我的-退出登录按钮
	 * 退出登录按钮
	 */
	public static Button logoutbutton(LocalLib local) {
		Button logoutbutton = (Button)local.getView("com.ganji.android.ccar:id/btn_login");
		return logoutbutton;
	}
	
	/**
	 * 我的-修改个人资料
	 * 昵称框
	 */
	public static EditText profilenameinput(LocalLib local) {
		EditText profilename = (EditText)local.getView("com.ganji.android.ccar:id/iv_myself_name");
		return profilename;
	}
	
	/**
	 * 保存按钮
	 * 我的-修改个人资料
	 */
	public static View profilesavebutton(LocalLib local) {
		View profilesavebutton = local.getView("com.ganji.android.ccar:id/iv_myself_confirm");
		return profilesavebutton;
	}
	
	/**
	 * 我的
	 * 姓名文字区域
	 */
	public static TextView profilenametxt(LocalLib local) {
		TextView profilenametxt = (TextView)local.getView("com.ganji.android.ccar:id/txt_name");
		return profilenametxt;
	}
	
	/**
	 * 我的-常用地址
	 * name文字区域
	 */
	public static TextView favoriteaddressnametxt(LocalLib local) {
		TextView favoriteaddressnametxt = (TextView)local.getView("com.ganji.android.ccar:id/txt_content", 0);
		return favoriteaddressnametxt;
	}
	
	/**
	 * 我的-常用地址-添加常用地址
	 * name输入框
	 */
	public static EditText favoriteaddressnameinput(LocalLib local) {
		EditText favoriteaddressname = (EditText)local.getView("com.ganji.android.ccar:id/et_name");
		return favoriteaddressname;
	}

	/**
	 * 我的-常用地址-添加常用地址
	 * content输入框
	 */
	public static EditText addfavoriteaddresscontentinput(LocalLib local) {
		EditText favoriteaddresscontent = (EditText)local.getView("com.ganji.android.ccar:id/et_content",0);
		return favoriteaddresscontent;
	}

	/**
	 * 我的-常用地址-添加家地址
	 * address输入框
	 */
	public static TextView addhomeaddressaddressinput(LocalLib local) {
		TextView address = (TextView)local.getView("com.ganji.android.ccar:id/txt_address");
		return address;
	}
	
	/**
	 * 我的-常用地址-添加家地址
	 * content输入框
	 */
	public static EditText addhomeaddresscontentinput(LocalLib local) {
		EditText homeaddresscontent = (EditText)local.getView("com.ganji.android.ccar:id/et_content");
		return homeaddresscontent;
	}

	/**
	 * 我的-我的车辆
	 * 第三个车牌号文字
	 */
	public static TextView mycarplatetxt(LocalLib local) {
		TextView mycarplate = (TextView)local.getView("com.ganji.android.ccar:id/txt_car_plate",2);
		return mycarplate;
	}
	
	/**
	 * 我的-我的车辆
	 * 第三个车辆类型区域
	 */
	public static TextView mycartypetxt(LocalLib local) {
		TextView mycartype = (TextView)local.getView("com.ganji.android.ccar:id/txt_car_type",2);
		return mycartype;
	}
	
	/**
	 * 我的-我的车辆-添加车辆
	 * 车牌号省区域
	 */
	public static TextView addmycarprinceinput(LocalLib local) {
		TextView addmycarprince = (TextView)local.getView("com.ganji.android.ccar:id/txt_plate_label");
		return addmycarprince;
	}
	
	/**
	 * 我的-我的车辆-添加车辆
	 * 车牌号区域输入框
	 */
	public static EditText addmycarnuminput(LocalLib local) {
		EditText addmycarnum = (EditText)local.getView("com.ganji.android.ccar:id/et_name");
		return addmycarnum;
	}
	
	/**
	 * 我的-我的车辆-添加车辆
	 * 车辆类型输入框
	 */
	public static TextView addmycartypeinput(LocalLib local) {
		TextView addmycartype = (TextView)local.getView("com.ganji.android.ccar:id/txt_car_type");
		return addmycartype;
	}
	
	/**
	 * 我的-我的车辆-添加车辆
	 * 车辆颜色输入框
	 */
	public static TextView addmycarcolorinput(LocalLib local) {
		TextView addmycarcolor = (TextView)local.getView("com.ganji.android.ccar:id/txt_car_color");
		return addmycarcolor;
	}
	

	
	
	
	
	
	
	
	


	//变量
	/**
	 * 线上万能手机号1
	 */
	public static String phonenum(LocalLib local) {
		String phonenum = "18501192536";	//online
//		String phonenum = "14444444444";	//test
		return phonenum;
	}	
	
	/**
	 * 线上万能手机号2，主要 用于测试首次下单一元
	 */
	public static String firstphonenum(LocalLib local) {
		String phonenumfirst = "18904041414";	//online
//		String phonenumfirst = "14444444444";	//test
		return phonenumfirst;
	}

	/**
	 * 线上万能手机号1和2的密码
	 */
	public static String password(LocalLib local) {
		String password = "3423";
		return password;
	}
	

}
