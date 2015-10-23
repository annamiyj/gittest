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
import android.widget.CheckBox;
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
	//所有入口
	//一级入口
	//活动入口


	/**
	 * 选择服务城市入口
	 * 位置：首页header左侧
	 */
	public static TextView selectservicecityentry(LocalLib local) {
		TextView servicecity = (TextView)local.getView("com.ganji.android.ccar:id/home_city_btn");
		return servicecity;
	}
	
	/**
	 * 获取文字
	 * header文案区域文字
	 */
	public static TextView headertxt(LocalLib local) {
		TextView header = (TextView)local.getView("com.ganji.android.ccar:id/center_text");
		return header;
	}
	

	/**
	 * 优惠活动入口
	 * 位置：首页
	 */
	public static LinearLayout actionentry(LocalLib local) {
		LinearLayout action = (LinearLayout)local.getView("com.ganji.android.ccar:id/promotions");//1.7.5，update
		return action;
	}
	
	/**
	 * 上门洗车入口
	 * 位置：首页
	 */
	public static LinearLayout washcarentry(LocalLib local) {
		LinearLayout washcar = (LinearLayout)local.getView("com.ganji.android.ccar:id/quick_order");//1.7.5，update
		return washcar;
	}

//	/**
//	 * 任务入口
//	 * 位置：首页
//	 */
//	public static RelativeLayout taskentry(LocalLib local) {
////		RelativeLayout task = (RelativeLayout)local.getView("lay_btn2");
//		RelativeLayout task = (RelativeLayout)local.getView("com.ganji.android.ccar:id/rl_task_list");//1.7.5
//		return task;
//	}

//	/**
//	 * 套餐入口
//	 * 位置：首页
//	 * 1.7.5,delete
//	 */
//	public static LinearLayout productentry(LocalLib local) {
//		LinearLayout product = (LinearLayout)local.getView("lay_btn3");
//		return product;
//	}

	/**
	 * VIP下单入口
	 * 位置：首页
	 */
	public static LinearLayout VIPorderentry(LocalLib local) {
		LinearLayout VIPorder = (LinearLayout)local.getView("com.ganji.android.ccar:id/vip_order");//1.7.5，update
		return VIPorder;
	}
	
	/**
	 * 服务介绍入口
	 * 位置：首页
	 */
	public static LinearLayout serviceintroduceentry(LocalLib local) {
		LinearLayout serviceintroduce = (LinearLayout)local.getView("com.ganji.android.ccar:id/service_introduce");//1.7.5,update
		return serviceintroduce;
	}
	
	/**
	 * 洗车指数入口
	 * 位置：首页
	 */
	public static LinearLayout washindexentry(LocalLib local) {
		LinearLayout washindex = (LinearLayout)local.getView("com.ganji.android.ccar:id/weather_details");
		return washindex;
	}
	
	/**
	 * 洗车指数天气文字,1.7.5，add
	 * 位置：首页
	 */
	public static TextView weatherdescriptionentry(LocalLib local) {
		TextView weatherdescription = (TextView)local.getView("com.ganji.android.ccar:id/weather_description");//1.7.5，add
		return weatherdescription;
	}
	
	/**
	 * 洗车指数条件文字，1.7.5，add
	 * 位置：首页
	 */
	public static TextView weatherwashcarconditionentry(LocalLib local) {
		TextView weatherwashcarcondition = (TextView)local.getView("com.ganji.android.ccar:id/weather_wash_car_condition");//1.7.5,add
		return weatherwashcarcondition;
	}

	/**
	 * 首页入口
	 * 首页底部tab1
	 */
	public static RelativeLayout homepageentry(LocalLib local) {
		RelativeLayout home = (RelativeLayout)local.getView("com.ganji.android.ccar:id/rl_home");//1.7.5，update
		return home;
	}

	/**
	 * 我的订单入口
	 * 首页底部tab2
	 */
	public static RelativeLayout myneedentry(LocalLib local) {
		RelativeLayout myneed = (RelativeLayout)local.getView("com.ganji.android.ccar:id/rl_order_list");//1.7.5，update
		return myneed;
	}
	
	/**
	 * 任务入口
	 * 首页底部tab3
	 */
	public static RelativeLayout taskentry(LocalLib local) {
		RelativeLayout task = (RelativeLayout)local.getView("com.ganji.android.ccar:id/rl_task_list");//1.7.5，update
		return task;
	}

	/**
	 * 我的页面入口
	 * 首页底部tab4
	 */
	public static RelativeLayout mypageentry(LocalLib local) {
		RelativeLayout mypage = (RelativeLayout)local.getView("com.ganji.android.ccar:id/rl_my_page");//1.7.5，update
		return mypage;
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
	 * 内饰价格+3元文字
	 * 位置：上门洗车页
	 */
	public static TextView interiortxt(LocalLib local) {
		TextView interiortxt = (TextView)local.getView("com.ganji.android.ccar:id/txt_price");
		return interiortxt;
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
	 * 次卡选择按钮
	 * 位置：上门洗车页
	 */
	public static CheckBox timecardcheckbox(LocalLib local) {
		CheckBox timecardcheckbox = (CheckBox)local.getView("com.ganji.android.ccar:id/time_card_enable_switch");
		return timecardcheckbox;
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
	 * 确定按钮
	 * 位置：上门洗车-进入车辆信息页
	 */
	public static TextView submitbutton(LocalLib local) {
		TextView submit = (TextView)local.getView("com.ganji.android.ccar:id/txt_title_right");
		return submit;
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
//		View canclebutton= local.getView("com.ganji.android.ccar:id/dialog_item_text", 4);
		View canclebutton= local.getText("稍后支付");
		return canclebutton;
	}
	
	/**
	 * 余额支付按钮
	 * 点击立即下单后
	 */
	public static TextView balancepaybutton(LocalLib local) {
		TextView balancepay = (TextView)local.getView("com.ganji.android.ccar:id/item_dialog_custom_tv");
		return balancepay;
	}

	/**
	 * 个人头像
	 * 我的页面
	 */
	public static ImageView profileimg(LocalLib local) {
		ImageView profileimg = (ImageView)local.getView("com.ganji.android.ccar:id/img_profile");
		return profileimg;
	}
	
	/**
	 * 我的消息入口
	 * 我的页面
	 */
	public static RelativeLayout mymessageentry(LocalLib local) {
		RelativeLayout mymessage = (RelativeLayout)local.getView("com.ganji.android.ccar:id/lay_message");
		return mymessage;
	}
	
	
	/**
	 * 余额入口
	 * 我的页面
	 */
	public static LinearLayout balanceentry(LocalLib local) {
		LinearLayout balance = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_my_balance");
		return balance;
	}
	
	/**
	 * 次卡入口
	 * 我的页面
	 */
	public static LinearLayout timecardentry(LocalLib local) {
		LinearLayout timecard = (LinearLayout)local.getView("com.ganji.android.ccar:id/tab_my_time_card_layout");
		return timecard;
	}
	
	/**
	 * 优惠劵入口
	 * 我的页面
	 */
	public static LinearLayout couponentry(LocalLib local) {
		LinearLayout couponen = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_my_coupon");
		return couponen;
	}
	
	/**
	 * 红包入口
	 * 我的页面
	 */
	public static LinearLayout myredpackageentry(LocalLib local) {
		LinearLayout myredpackage = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_my_red_package");
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
	 * 我的页面扫一扫入口
	 * 我的页面
	 */
	public static LinearLayout qrcodemypageentry(LocalLib local) {
		LinearLayout qrcodemypage = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_my_qrcode");
		return qrcodemypage;
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
	

	
	
	
	
	
	
	
	


//	//变量
//	/**
//	 * 线上万能手机号1 a
//	 */
//	public static String phonenum(LocalLib local) {
////		String phonenum = "18501192536";	//online
//		String phonenum = "14444444444";	//test
//		return phonenum;
//	}	
//	
//	/**
//	 * 线上万能手机号2，主要 用于测试首次下单一元 a
//	 */
//	public static String firstphonenum(LocalLib local) {
//		String phonenumfirst = "18904041414";	//online
////		String phonenumfirst = "14444444444";	//test
//		return phonenumfirst;
//	}
//
//	/**
//	 * 线上万能手机号1，打星号的 a
//	 */
//	public static String phonenumxing(LocalLib local) {
////		String phonenumfirst = "185****2536";	//online
//		String phonenumfirst = "1********44";	//test
//		return phonenumfirst;
//	}
//	
//	/**
//	 * 线上万能手机号1和2的密码 a
//	 */
//	public static String password(LocalLib local) {
////		String password = "3423";
//		String password = "752455";
//		return password;
//	}
//	
//	/**
//	 * 线上账号余额 a
//	 */
//	public static String balance(LocalLib local) {
////		String balance = "1112";
//		String balance = "7498.54";
//		return balance;
//	}
//	
//	/**
//	 * 线上次卡次数 a
//	 */
//	public static String timecard(LocalLib local) {
////		String timecard = "110";
//		String timecard = "2322";
//		return timecard;
//	}
//	
//	/**
//	 * 线上次卡次数--用过一次后 a
//	 */
//	public static String timecarduse(LocalLib local) {
////		String timecard = "109";
//		String timecard = "1222";
//		return timecard;
//	}
//	
//	/**
//	 * 线上优惠券数量 a
//	 */
//	public static String coupons(LocalLib local) {
////		String coupons = "2";//online
//		String coupons = "8";//test
//		return coupons;
//	}
//	
//	/**
//	 * 线上北京套餐数量 a
//	 */
//	public static String productbj(LocalLib local) {
////		String productbj = "2";
//		String productbj = "31";
//		return productbj;
//	}
//	
//	/**
//	 * 线上开通城市数量 a
//	 */
//	public static String citynum(LocalLib local) {
////		String citynum = "2";
//		String citynum = "11";
//		return citynum;
//	}
	
	
	

}
