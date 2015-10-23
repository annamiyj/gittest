package com.baidu.recordreplay.test.lib;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.ArrayList;

import com.baidu.cafe.CafeTestCase;
import com.baidu.cafe.local.LocalLib;
import com.robotium.solo.By;

import android.R;
import android.R.integer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.recordreplay.test.lib.MyEntry;
import com.baidu.recordreplay.test.lib.ToPage;
import com.baidu.recordreplay.test.lib.SetStep;

public class MyAssert {
	
//	/**
//	 * 文字常量 a
//	 * 上门洗车页，您的联系方式默认文案
//	 */
//	public static String washcar_nophone_txt(LocalLib local) {
//		String nophonetxt = "您的联系方式";//上门洗车页，您的联系方式默认文案
//		return nophonetxt;
//	}
//	
//	/**
//	 * 文字常量 a
//	 * 上门洗车页，标准洗车header文案
//	 */
//	public static String washcar_header_txt(LocalLib local) {
//		String washcarheadertxt = "上门洗车";//一键洗车页header文案
//		return washcarheadertxt;
//	}
//	
//	/**
//	 * 文字常量 a
//	 * 首页，一键下单按钮文案
//	 */
//	public static String home_quickorder_txt(LocalLib local) {
//		String homequickordertxt = "一键下单";//首页一键下单文案
//		return homequickordertxt;
//	}
//	
//	/**
//	 * 文字常量 a
//	 * 上门洗车--选择套餐，header文案
//	 * 1.7.5，update
//	 */
//	public static String seleproduct_header_txt(LocalLib local) {
//		String seleproductheadertxt = "选择服务";//一键洗车选择套餐header文案
//		return seleproductheadertxt;
//	}

	/**
	 * 获取文字
	 * header文案区域文字
	 */
	public static String getall_header_txt(LocalLib local) {
		TextView bzxc = (TextView)MyEntry.headertxt(local);
		String bzxctxt = bzxc.getText().toString();
		return bzxctxt;
	}
	
	
	/**
	 * 获取区域文字
	 * 标准洗车页，获取联系方式区域手机号
	 */
	public static String getwashcar_phone_txt(LocalLib local) {
		String phonetxt = MyEntry.phoneentry(local).getText().toString();
		return phonetxt;
	}
	
	/**
	 * 获取区域文字
	 * 标准洗车页，获取车辆停放地址区域文字
	 */
	public static String getwashcar_address_txt(LocalLib local) {
		TextView address = (TextView)MyEntry.caraddressentry(local);
		String addresstxt = address.getText().toString();
		return addresstxt;
	}
	
	/**
	 * 获取区域文字
	 * 标准洗车页，获取车辆信息文字
	 */
	public static String getwashcar_carinfo_txt(LocalLib local) {
		TextView carinfo = (TextView)MyEntry.carinfoentry(local);
		String carinfotxt = carinfo.getText().toString();
		return carinfotxt;
	}
	
	/**
	 * 获取区域文字
	 * 标准洗车页，您希望的服务时间文字
	 */
	public static String getwashcar_servicetime_txt(LocalLib local) {
		TextView servicetime = (TextView)MyEntry.carservicetimeentry(local);
		String servicetimetxt = servicetime.getText().toString();
		return servicetimetxt;
	}
	
	/**
	 * 获取区域文字
	 * 标准洗车页，内饰区域文字
	 */
	public static String getwashcar_interior_txt(LocalLib local) {
		TextView interior = (TextView)MyEntry.interiorentry(local);
		String interiortxt = interior.getText().toString();
		return interiortxt;
	}
	
	/**
	 * 获取区域文字
	 * 标准洗车页，内饰价格文字
	 */
	public static String getwashcar_interiorprice_txt(LocalLib local) {
		TextView interiorprice = (TextView)MyEntry.interiortxt(local);
		String interiorpricetxt = interiorprice.getText().toString();
		return interiorpricetxt;
	}
	
	/**
	 * 获取文字
	 * 标准洗车页，红包区域文字
	 */
	public static String getwashcar_redpackage_txt(LocalLib local) {
		TextView redpackage = (TextView)local.getView("com.ganji.android.ccar:id/txt_red_package");
		String redpackagetxt = redpackage.getText().toString();
		return redpackagetxt;
	}
	
	/**
	 * 获取文字
	 * 修改资料页，昵称框内区域文字
	 */
	public static String getprofile_name_txt(LocalLib local) {
		EditText name = (EditText)MyEntry.profilenametxt(local);
		String nametxt = name.getText().toString();
		return nametxt;
	}
	
	/**
	 * 获取文字
	 * 登录 页，手机号输入区域文字
	 */
	public static String getlogin_phone_txt(LocalLib local) {
		String phonetxt = MyEntry.phoneinput(local).getText().toString();
		return phonetxt;
	}
	
	/**
	 * 获取文字
	 * 登录 页，动态获取 验证码按钮上文字
	 */
	public static String getlogin_validatebutton_txt(LocalLib local) {
		String validatebuttontxt = MyEntry.validatebutton(local).getText().toString();
		return validatebuttontxt;
	}
	
	/**
	 * 获取文字
	 * 车辆信息页，获取车辆地区文字
	 */
	public static String getcarplate_carinfo_txt(LocalLib local) {
		String carplatetxt = MyEntry.carplate(local).getText().toString();
		return carplatetxt;
	}
	
	/**
	 * 获取文字
	 * 车辆信息页，确定按钮文字
	 */
	public static String getsubmit_carinfo_txt(LocalLib local) {
		String submittxt = MyEntry.submitbutton(local).getText().toString();
		return submittxt;
	}
	
	/**
	 * 获取“位置”文字
	 * 车辆停放位置页
	 */
	public static String getweizhi_caraddress_txt(LocalLib local) {
		String weizhitxt = MyEntry.weizhi(local).getText().toString();
		return weizhitxt;
	}
	
	/**
	 * 获取备注文字
	 * 详细位置描述页
	 */
	public static String getprompt_caraddressdetail_txt(LocalLib local) {
		String prompttxt = MyEntry.caraddressprompt(local).getText().toString();
		return prompttxt;
	}

	/**
	 * 邀请记录文字
	 * 任务页
	 */
	public static String getinviterecord_task_txt(LocalLib local) {
		String inviterecordtxt = MyEntry.inviterecord(local).getText().toString();
		return inviterecordtxt;
	}
	
	/**
	 * 我的奖品文字
	 * 任务页
	 */
	public static String getmyaward_task_txt(LocalLib local) {
		String myawardtxt = MyEntry.myaward(local).getText().toString();
		return myawardtxt;
	}
	
	/**
	 * 
	 * 
	 */
	public static String getsubmit_productdetail_txt(LocalLib local) {
		String submittxt = MyEntry.productorderbutton(local).getText().toString();
		return submittxt;
	}
	
	
	
	
	/**
	 * 我的
	 * 获取昵称文字
	 */
	public static String getmy_name_txt(LocalLib local) {
		TextView myname = (TextView)MyEntry.profilenametxt(local);
		String mynametxt = myname.getText().toString();
		return mynametxt;
	}
	
	/**
	 * 我的-常用地址-添加常用地址
	 * 获取地址name文字
	 */
	public static String getaddfavoriteaddress_name_txt(LocalLib local) {
		TextView favoriteaddressname = (TextView)MyEntry.favoriteaddressnameinput(local);
		String favoriteaddressnametxt = favoriteaddressname.getText().toString();
		return favoriteaddressnametxt;
	}
	
	/**
	 * 我的-常用地址-添加常用地址
	 * 获取地址content文字
	 */
	public static String getaddfavoriteaddress_content_txt(LocalLib local) {
		TextView favoriteaddresscontent = (TextView)MyEntry.addfavoriteaddresscontentinput(local);
		String favoriteaddresscontenttxt = favoriteaddresscontent.getText().toString();
		return favoriteaddresscontenttxt;
	}
	
	/**
	 * 我的-常用地址-添加家的地址
	 * address文字
	 */
	public static String getaddhomeaddress_address_txt(LocalLib local) {
		TextView homeaddress = (TextView)MyEntry.addhomeaddressaddressinput(local);
		String homeaddresstxt = homeaddress.getText().toString();
		return homeaddresstxt;
	}
	
	/**
	 * 我的-常用地址-添加家的地址
	 * content文字
	 */
	public static String getaddhomeaddress_content_txt(LocalLib local) {
		TextView homeaddresscontent = (TextView)MyEntry.addhomeaddresscontentinput(local);
		String homeaddresscontenttxt = homeaddresscontent.getText().toString();
		return homeaddresscontenttxt;
	}
	
	/**
	 * 我的-常用地址-添加公司的地址
	 * address文字
	 */
	public static String getaddcompanyaddress_address_txt(LocalLib local) {
		TextView companyaddress = (TextView)MyEntry.addhomeaddressaddressinput(local);
		String companyaddresstxt = companyaddress.getText().toString();
		return companyaddresstxt;
	}
	
	/**
	 * 我的-常用地址-添加公司的地址
	 * content文字
	 */
	public static String getaddcompanyaddress_content_txt(LocalLib local) {
		TextView companyaddresscontent = (TextView)MyEntry.addhomeaddresscontentinput(local);
		String companyaddresscontenttxt = companyaddresscontent.getText().toString();
		return companyaddresscontenttxt;
	}
	
	
	
	/**
	 * 我的-常用地址
	 * 第三个地址的name 文字
	 */
	public static String getfavoriteaddress_name_txt(LocalLib local) {
		TextView favoriteaddressname = (TextView)MyEntry.favoriteaddressnametxt(local);
		String favoriteaddressnametxt = favoriteaddressname.getText().toString();
		return favoriteaddressnametxt;
	}
	
	/**
	 * 我的-常用地址
	 * 家地址的 文字
	 */
	public static String getfavoriteaddress_home_txt(LocalLib local) {
		TextView homeadd = (TextView)local.getView("com.ganji.android.ccar:id/txt_home");//获取常用地址页的地址文字
		String homeaddtxt = homeadd.getText().toString();
		return homeaddtxt;
	}
	
	/**
	 * 我的-常用地址
	 * 公司地址的 文字
	 */
	public static String getfavoriteaddress_company_txt(LocalLib local) {
		TextView companyadd = (TextView)local.getView("com.ganji.android.ccar:id/txt_company");//获取常用地址页的地址文字
		String companyaddtxt = companyadd.getText().toString();
		return companyaddtxt;
	}
	
	/**
	 * 我的-我的车辆
	 * 第三个车牌号文字
	 */
	public static String getmycar_carnum_txt(LocalLib local) {
		String mycarnumtxt = MyEntry.mycarplatetxt(local).getText().toString();
		return mycarnumtxt;
	}
	
	/**
	 * 我的-我的车辆
	 * 第三个车辆类型文字
	 */
	public static String getmycar_cartype_txt(LocalLib local) {
		String mycartypetxt = MyEntry.mycartypetxt(local).getText().toString();
		return mycartypetxt;
	}
	
	/**
	 * 我的-我的车辆-添加常用车辆
	 * 车牌号文字
	 */
	public static String getaddfavoritecar_carnum_txt(LocalLib local) {
		String mycarprincetxt = MyEntry.addmycarprinceinput(local).getText().toString();
		String mycarnumtxt = MyEntry.addmycarnuminput(local).getText().toString();
		return mycarprincetxt+mycarnumtxt;
	}
	
	/**
	 * 我的-我的车辆-添加常用车辆
	 * 车辆品牌和颜色
	 */
	public static String getaddfavoritecar_cartypecolor_txt(LocalLib local) {
		String mycartypetxt = MyEntry.addmycartypeinput(local).getText().toString();
		String mycarcolortxt = MyEntry.addmycarcolorinput(local).getText().toString();
		return mycartypetxt+"  "+mycarcolortxt;
	}
	
	
	
	
	
	
}
