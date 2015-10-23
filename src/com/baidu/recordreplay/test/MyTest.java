package com.baidu.recordreplay.test;
//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.baidu.cafe.CafeTestCase;
import com.baidu.cafe.local.*;
import com.robotium.solo.By;
import com.robotium.solo.WebElement;

import android.R;
import android.R.integer;
import android.R.layout;
import android.R.string;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
//import android.graphics.Camera;
import android.hardware.Camera;
import android.content.Intent;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.provider.MediaStore;





import com.baidu.recordreplay.test.lib.ChangL;
import com.baidu.recordreplay.test.lib.MyEntry;
import com.baidu.recordreplay.test.lib.ToPage;
import com.baidu.recordreplay.test.lib.SetStep;
import com.baidu.recordreplay.test.lib.MyAssert;
import com.baidu.recordreplay.test.lib.ChangL;

import java.util.ArrayList;

import xuxu.autotest.AdbDevice;
import xuxu.autotest.element.Position;
import xuxu.autotest.element.Element;



@SuppressWarnings("all")

public class MyTest extends CafeTestCase {
    private static final String     LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.ganji.android.car.activities.CHomeActivity";
    private static final String     TARGET_PACKAGE                   = "com.ganji.android.ccar";
    private static Class<?>         launcherActivityClass;
	private AdbDevice adbDevice;//4.14+ 跨进程xuxu.autotest.AdbDevice
	private Position position;//4.14+ 跨进程xuxu.autotest.position
	private Camera mCamera;//5.11+ 释放摄像头 
    static {
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
/*** */    public MyTest() {
        super(launcherActivityClass);
		adbDevice = new AdbDevice();//4.14+
		position = new Position();//4.14+
    }
    @Override
    protected void setUp() {
        try {
            super.setUp();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void tearDown() {
        try {
        	this.local.finishOpenedActivities();//+
        	getActivity().finish();//+
            super.tearDown();
        }catch(Exception e) {
            e.printStackTrace();
        }
    	
//		try {
//			this.local.finishOpenedActivities();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		this.activity.finish();
//		getActivity().finish();
//		super.tearDown();
    }
    
    
    /**
     * @Name 0001_guidepage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1 
     * @Grade 中级
     * @Channel 
     * @Describe 首次启动，正常启动引导页；
     * @FunctionPoint 首次启动，正常启动引导页；
     */
    private void test_0001_guidepage() {
//    	remote.clearApplicationUserData("com.ganji.android.ccar");//清理缓存
//    	local.sleep(3000);
    	assertTrue("CurrentActivity is not CHomeActivity", local.waitForActivity("CHomeActivity"));//断言：当前activity是否是CFeatureActivity
		for (int i=1;i<=3;i++) {//四张引导图，滑动三次
			local.sleep(2000);
			local.scrollToSide(local.RIGHT);//从右往左滑动
//			local.takeScreenshot("GuideMap"+i);//第i张引导图截图
			local.screenShotNamedCaseName("pic_000"+i);
		}
		local.sleep(2000);
		local.clickOnView(local.getView("com.ganji.android.ccar:id/finish_btn"));//点击开始体验按钮
		local.sleep(1500);
//		local.sleep(5000);
		assertTrue("Can not go to CHomeActivity from FeatureActivity!", local.waitForActivity("CHomeActivity"));//断言：是否进入首页
    }
    
    /**
     * @Name 1010_homepage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1 
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，非首次启动，正常进入首页，检查页面元素；
     * @FunctionPoint 未登录，非首次启动，正常进入首页，检查页面元素；2.0.1，首页改版
     */
    public void test_1010_homepage() {
    	Log.d("test", "test_1002_homepage start");
//        local.sleep(3000);
		assertTrue("CurrentActivity is not CHomeActivity!", local.waitForActivity("CHomeActivity",5000));//断言：判断当前页是否CHomeactivity
//		local.takeScreenshot("HomePage");//home页截图
		assertEquals("No \"赶集易洗车\" text！ ", "赶集易洗车", MyAssert.getall_header_txt(local));//断言：header文案
		assertTrue("No Localtion view!", local.waitForView(local.getView("txt_title_left")));//断言：判断是否显示地区
		assertTrue("No saoyisao view!", local.waitForView(local.getView("com.ganji.android.ccar:id/btn_title_right")));//断言：判断是否显示扫一扫
		assertTrue("No banner view!", local.waitForView(local.getView("viewpager")));//断言：判断banner图区域
		TextView weatherdegree = (TextView)local.getView("com.ganji.android.ccar:id/weather_degree");//温度显示
		String weatherdegreetxt = weatherdegree.getText().toString();
		assertTrue("没有显示温度！", weatherdegreetxt.contains("℃"));
		TextView washcar = (TextView)MyEntry.washcarentry(local).getChildAt(1);//一键下单文字区域
		String washcartxt = washcar.getText().toString();
		assertEquals("No \"一键下单\" text！", ChangL.homequickordertxt, washcartxt);//断言：功能区：一键下单文案
		String washdescriptiontxt = MyEntry.weatherdescriptionentry(local).getText().toString();//洗车天气文字
		assertTrue("没有显示洗车天气！", !washdescriptiontxt.isEmpty());
		String washcarconditiontxt = MyEntry.weatherwashcarconditionentry(local).getText().toString();
		assertTrue("没有显示洗车条件！", !washcarconditiontxt.isEmpty());
		TextView serviceintro = (TextView)MyEntry.serviceintroduceentry(local).getChildAt(1);//服务介绍文字区域
		String serviceintrotxt = serviceintro.getText().toString();
		assertEquals("No \"服务介绍\" text！", "服务介绍", serviceintrotxt);//断言：功能区：服务介绍文案
		TextView VIP = (TextView)MyEntry.VIPorderentry(local).getChildAt(1);//VIP下单文字区域
		String VIPtxt = VIP.getText().toString();
		assertEquals("No \"VIP下单\" text！", "VIP下单", VIPtxt);//断言：功能区：VIP下单文案
		TextView action = (TextView)MyEntry.actionentry(local).getChildAt(1);//优惠活动文字
		String actiontxt = action.getText().toString();
		assertEquals("No \"优惠活动\" text！", "优惠活动", actiontxt);//断言：优惠活动入口
		local.scrollDown();//上拉
		LinearLayout product = (LinearLayout)local.getView("com.ganji.android.ccar:id/home_promotion_container");//套餐区域
		assertEquals("套餐数量不正确！", ChangL.productbj, product.getChildCount());//套餐数量，线上还得改
		TextView leftproducttitle = (TextView)local.getView("com.ganji.android.ccar:id/left_title");//第一个左侧套餐名称
		ImageView leftproducticon = (ImageView)local.getView("com.ganji.android.ccar:id/left_icon");//第一个左侧套餐icon
		TextView rightproducttitle = (TextView)local.getView("com.ganji.android.ccar:id/right_title");//第一个右侧套餐名称
		ImageView rightproducticon = (ImageView)local.getView("com.ganji.android.ccar:id/right_icon");//第一个右侧套餐icon
		String leftproducttitletxt = leftproducttitle.getText().toString();
		String rightproducttitletxt = rightproducttitle.getText().toString();
		assertTrue("没有套餐icon！", local.waitForView(leftproducticon)&&local.waitForView(rightproducticon));
		assertTrue("套餐名称不正确！", leftproducttitletxt.contains("套餐")&&rightproducttitletxt.contains("套餐"));
    }
    
    /**
     * @Name 1015_selectcitypage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1 
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，点击Header左侧按钮，正常进入服务城市页，检查页面元素；
     * @FunctionPoint 未登录，点击Header左侧按钮，正常进入服务城市页，检查页面元素；2.0.1，update，城市数量断言
     */
    public void test_1015_selectcitypage() {
    	Log.d("test", "test_1003_selectcitypage  start");
    	ToPage.toselectcitypage(local);//进入选择服务城市页面
    	local.assertCurrentActivity("CurrentActivity is not SelectCityActivity", "SelectCityActivity");//断言：判断当前activity是否SelectCityActivity
//		local.takeScreenshot("LocationPage");
		//审查页面元素
    	assertEquals("No \"服务城市\" text！ ", "服务城市", MyAssert.getall_header_txt(local));//断言：header文案
    	TextView currentcity = (TextView)local.getView("com.ganji.android.ccar:id/row_title",0);//当前定位城市文字区域
    	String currentcitytxt = currentcity.getText().toString();
    	assertEquals("No \"当前定位城市\" text！", "当前定位城市", currentcitytxt);//断言：列表，当前定位城市
    	TextView opencity = (TextView)local.getView("com.ganji.android.ccar:id/row_title",2);//目前卡通城市文字区域
    	String opencitytxt = opencity.getText().toString();
    	ListView list = (ListView)local.getView("com.ganji.android.ccar:id/lv_city");//城市列表
    	assertEquals("开通的城市数量不正确！", ChangL.citynum, list.getChildCount());//城市数量，线上还得改
//    	assertEquals("No \"目前开通城市\" text！", "目前开通城市", opencitytxt);//断言：列表，当前定位城市
		local.clickOnText("北京");//点击北京返回首页
//		local.sleep(3000);
		assertTrue("CurrentActivity is not CHomeActivity!", local.waitForActivity("CHomeActivity"));//断言：判断当前页是否CHomeactivity
		local.assertCurrentActivity("Not go to CHomeActivity from SelectCityActivity！", "CHomeActivity");//断言：判断是否返回到首页
    }   
    
    /**
     * @Name test_1020_actionlistpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1 
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，点击Header右侧按钮，正常进入活动列表页，检查页面元素；
     * @FunctionPoint 未登录，点击Header右侧按钮，正常进入活动列表页，检查页面元素；
     */
    public void test_1020_actionlistpage() {
    	Log.d("test", "test_1004_actionlistpage  start");
    	ToPage.toactionpage(local);//进入活动页
//    	local.assertCurrentActivity("CurrentActivity is not SLFragmentActivity", "SLFragmentActivity");//断言：判断当前activity是否SLFragmentActivity
    	//审查页面元素
    	assertEquals("No \"优惠活动\" text！ ", "优惠活动", MyAssert.getall_header_txt(local));//断言：header文案
		assertTrue("No action_picture view!", local.waitForView(local.getView("img_ads")));//断言：判断活动图片view
		assertTrue("No action_text view!", local.waitForView(local.getView("txt_name")));//断言：判断活动文字view
    	for (int i=1;i<=3;i++) {//往下捯，捯三次
    		local.scrollDown();
//    		local.sleep(2000);
//    		local.takeScreenshot("Action"+i);
    	}
    }
    
    /**
     * @Name 1025_actiondetailpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1 
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，活动详情页，任意一个活动检查是否为webview；
     * @FunctionPoint 未登录，活动详情页，任意一个活动检查是否为webview；
     */
    public void test_1025_actiondetailpage() {
    	Log.d("test", "test_1005_actiondetail  start");
    	ToPage.toactionpage(local);//点击进入活动页面
    	local.clickOnImage(1);//点击第三个活动进入detail页
//    	local.assertCurrentActivity("CurrentActivity is not CWebViewActivity", "CWebViewActivity");//断言：判断当前是否是webviewactivity
    	assertTrue("CurrentActivity is not webview！",local.waitForView(local.getView("com.ganji.android.ccar:id/webview")));//断言：判断当前是否是webview
    }
    
    /**
     * @Name 1030_washpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1 
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，点击上门洗车，正常进入上门洗车页，检查页面元素；
     * @FunctionPoint 未登录，点击上门洗车，正常进入上门洗车页，检查页面元素；2.0.1，update，下单页改版
     */
    public void test_1030_washpage() {
    	Log.d("test", "test_1006_washpage  start");
    	ToPage.towashcarpage(local);
    	//审查页面元素
    	assertTrue("No \"一键洗车\" text！ ", local.waitForText(ChangL.washcarheadertxt));//断言：header文案
    	assertTrue("No \"一键洗车\" image！", local.waitForView(MyEntry.selectproductentry(local)));//断言：套餐选择区域（一键洗车）--图片
    	assertTrue("No \"一键洗车\" title！", local.waitForView(MyEntry.producttitle(local)));//断言：套餐选择区域（一键洗车）--一键洗车标题
    	assertTrue("No \"一键洗车\" content！", local.waitForView(MyEntry.productcontent(local)));//断言：套餐选择区域（一键洗车）--一键洗车详细描述
    	assertTrue("No \"一键洗车\" price now！", local.waitForView(MyEntry.productprice(local)));//断言：套餐选择区域（一键洗车--一键洗车现在价格
    	assertEquals("No \"需要清洗内饰\" area！", "需要清洗内饰", MyAssert.getwashcar_interior_txt(local));//断言：是否需要清洗内饰选择区域
    	assertEquals("No \"清洗内饰+3.00元\" txt！", "+"+ChangL.interiorprice+"元", MyAssert.getwashcar_interiorprice_txt(local));//断言：是否需要清洗内饰价格区域文字
    	String phonetxt = MyEntry.phoneentry(local).getHint().toString();//您的联系方式默认文案
    	assertTrue("No \"您的联系方式\" area！", "您的联系方式".equals(phonetxt)||ChangL.phonenum.equals(MyAssert.getwashcar_phone_txt(local)));//断言：手机号输入区域，显示您的联系方式文案或手机号
    	String carinfotxt = MyEntry.carinfoentry(local).getHint().toString();//您的车牌号码默认文案
    	assertTrue("No \"您的车牌号码\" area！", "您的车牌号码".equals(carinfotxt)||MyAssert.getwashcar_carinfo_txt(local).contains("TESTED"));//断言：车牌号码输入区域
    	String caraddresstxt = MyEntry.caraddressentry(local).getHint().toString();//车辆停放地址默认文案
    	assertTrue("No \"车辆停放地址\" area！", "车辆停放地址".equals(caraddresstxt)||!MyAssert.getwashcar_address_txt(local).isEmpty());//断言：车辆停放地址输入区域
    	String cartimetxt = MyEntry.carservicetimeentry(local).getHint().toString();//您希望服务的时间默认文案
    	assertTrue("No \"您希望服务的时间\" area！", "您希望服务的时间".equals(cartimetxt));//断言：服务时间输入区域
//    	assertTrue("No \"您希望服务的时间\" area！", local.waitForView(local.getView("com.ganji.android.ccar:id/et_content2")));
    	TextView coupon = (TextView)local.getView("txt_coupon_label");//“优惠券”文案区域
    	String coupontxt = coupon.getText().toString();
    	assertEquals("优惠券", coupontxt);//断言：优惠券选择区域
//    	assertTrue("No \"红包\" area！", "红包".equals(MyAssert.getwashcar_redpackage_txt(local)));//断言：红包选择区域
    	TextView redpackage = (TextView)local.getView("com.ganji.android.ccar:id/txt_red_package_label");//“红包”文案区域
    	String redpackagetxt = redpackage.getText().toString(); 
    	assertEquals("红包", redpackagetxt);
    	TextView timecard = (TextView)local.getView("com.ganji.android.ccar:id/time_card_disable_title");//“次卡”文案区域
    	String timecardtxt = timecard.getText().toString(); 
    	assertEquals("次卡", timecardtxt);
    	String phoneareatxt = MyEntry.phoneentry(local).getText().toString();//您的联系方式默认文案
    	LinearLayout ll =(LinearLayout)local.getView("com.ganji.android.ccar:id/lay_mode_container");
    	int sum = ll.getChildCount();
    	if ("".equals(phoneareatxt)) { ////断言：邀请码输入区域（未登录和首次下单时有，非首次下单没有）
    		assertTrue("No \"邀请码\" area！", local.waitForText("邀请码"));
    		assertEquals("lay_mode_container sum is error!", 23, sum);
    	}
		else
			assertEquals("lay_mode_container sum is error!", 20, sum);	
    	assertTrue("No \"立即下单\" button！", local.waitForView(MyEntry.orderbutton(local)));//立即支付按钮
    }
    
    /**
     * @Name 1035_poductselepage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1 
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，点击标准服务区域进入选择服务页面；
     * @FunctionPoint 未登录，点击标准服务区域进入选择服务页面；2.0.1，update，套餐数量判断
     */
    public void test_1035_poductselepage() {
    	Log.d("test", "test_1007_poductsele  start");
    	ToPage.toselectproductpage(local);//进入选择服务页
    	assertEquals("No \"选择服务\" text!", ChangL.seleproductheadertxt, MyAssert.getall_header_txt(local));//断言：Header文案
    	assertTrue("No RadioButton!", local.waitForView(MyEntry.itemradiobutton(local)));//断言：是否有数据--复选框
    	assertTrue("No image!", local.waitForView(MyEntry.itemimage(local)));//断言：是否有数据--图片
    	assertTrue("No product name!", local.waitForView(MyEntry.itemname(local)));//断言：是否有数据--套餐名称
    	assertTrue("No product detail!", local.waitForView(MyEntry.itemcontent(local)));//断言：是否有数据--套餐描述
    	assertTrue("No product price!", local.waitForView(MyEntry.itemprice(local)));//断言：是否有数据--价格
    	assertTrue("No product detailbutton!", local.waitForView(MyEntry.itemviewdetail(local)));//断言：是否有数据--查看按钮
    	assertTrue("No \"套餐\" text!", local.waitForView(local.getText("套餐")));//断言：是否有数据--套餐文字
    	ListView list = (ListView)local.getView("android:id/list");////获取套餐数量
    	int num = list.getCount();
    	assertEquals("product num is wrong!", ChangL.productselebj,num);//套餐为4个，num就为7
    }
    
    /**
     * @Name 1040_setphonpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，上门洗车页，点击联系方式，正常进入登录页，检查页面元素；
     * @FunctionPoint 未登录，上门洗车页，点击联系方式，正常进入登录页，检查页面元素；
     */
    public void test_1040_setphonpage() {
    	Log.d("test", "test_1040_setphonpage  start");
    	ToPage.towashcarpage(local);//进入上门洗车页
//	    assertTrue("Xichepage is not open！", local.waitForView(local.getView("progressbar")));//用loading去判断，没成功
    	local.clickOnView(MyEntry.phoneentry(local));//点击您的联系方式输入框
//    	local.sleep(3000);
    	assertTrue("未成功跳转到登录页！", local.waitForText("登录"));
    	//审查页面元素
    	assertEquals("No \"登录\" text！ ", "登录", MyAssert.getall_header_txt(local));//断言：header文案
    	String phonetxt = MyEntry.phoneinput(local).getHint().toString();
    	assertEquals("No \"请输入您的手机号\" area！", "请输入您的手机号", phonetxt);
    	String passwordinputtxt = MyEntry.passwordinput(local).getHint().toString();
    	assertEquals("No \"请输入您的手机动态密码\" area！", "请输入您的手机动态密码", passwordinputtxt);
    	assertEquals("No \"获取动态 验证码\" area！", "获取动态密码", MyAssert.getlogin_validatebutton_txt(local));
    	String txt = MyEntry.loginbutton(local).getText().toString();//获取登录文字
    	assertEquals("No \"登录\" button！", "登录", txt);//断言：登录按钮
    }
    
    /**
     * @Name 1045_carinfopage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，上门洗车页，点击车辆信息，正常进入车辆信息页，检查页面元素；
     * @FunctionPoint 未登录，上门洗车页，点击车辆信息，正常进入车辆信息页，检查页面元素；
     */
    public void test_1045_carinfopage() {
    	Log.d("test", "test_1009_carinfopage  start");
    	ToPage.towashcarpage(local);
		if(local.waitForText("您的车牌号码")) {//未提交过车辆信息时
			local.clickOnView(MyEntry.carinfoentry(local));//点击您的车牌号码输入框
//	    	local.sleep(3000);
			assertTrue(local.waitForText("车辆信息"));
	    	//审查页面元素
	    	assertEquals("No \"车辆信息\" text！ ", "车辆信息", MyAssert.getall_header_txt(local));//断言：header文案
	    	assertEquals("No \"京\" area", "京", MyAssert.getcarplate_carinfo_txt(local));//断言：默认京字
	    	String carnumtxt = MyEntry.carnum(local).getHint().toString();//车牌号文字
	    	String cartypetxt = MyEntry.cartype(local).getHint().toString();//车辆品牌文字
	    	String carcolortxt = MyEntry.carcolor(local).getHint().toString();//车身颜色文字
	    	assertEquals("No \"车牌号\" area！", "车牌号", carnumtxt);//断言：车牌号输入框
	    	assertEquals("No \"选择车辆品牌\" area！", "选择车辆品牌", cartypetxt);//断言：选择车辆品牌输入框
	    	assertEquals("No \"选择车身颜色\" area！", "选择车身颜色", carcolortxt);//断言：选择车身颜色输入框
	    	assertEquals("No \"确定\" button！", "确定", MyAssert.getsubmit_carinfo_txt(local));//断言：确定按钮
		}
		else {//已有车辆信息时
			local.clickOnView(MyEntry.carinfoentry(local));//点击您的车牌号码输入框
//	    	local.sleep(3000);
			assertTrue(local.waitForText("车辆信息"));
	    	//审查页面元素
			assertEquals("No \"车辆信息\" text！ ", "车辆信息", MyAssert.getall_header_txt(local));//断言：header文案
			assertTrue("No \"京\" area", local.waitForView(MyEntry.carplate(local)));//断言：默认车辆地区区域
	    	assertTrue("No \"车牌号\" area！ ", local.waitForView(MyEntry.carnum(local)));//断言：车牌号输入框
	    	assertTrue("No \"选择车辆品牌\" area！ ", local.waitForView(MyEntry.cartype(local)));//断言：选择车辆品牌输入框
	    	assertTrue("No \"选择车身颜色\" area！ ", local.waitForView(MyEntry.carcolor(local)));//断言：选择车身颜色
	    	assertEquals("No \"确定\" button！", "确定", MyAssert.getsubmit_carinfo_txt(local));//断言：确定按钮
		}
    }
    
    /**
     * @Name 1050_caraddresspage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，上门洗车页，点击车辆停放地址，正常进入车辆停放地址页，检查页面元素；
     * @FunctionPoint 未登录，上门洗车页，点击车辆停放地址，正常进入车辆停放地址页，检查页面元素；
     */
    public void test_1050_caraddresspage() {
    	Log.d("test", "test_1010_caraddresspage  start");
    	ToPage.towashcarpage(local);
		local.clickOnView(MyEntry.caraddressentry(local));//点击车辆停放地址输入框
//    	local.sleep(3000);
		assertTrue(local.waitForText("选择车辆停放位置"));
    	//审查页面元素
    	assertEquals("No \"选择车辆停放位置\" text！ ", "选择车辆停放位置", MyAssert.getall_header_txt(local));//断言：header文案
    	assertTrue("No \"选择车辆停放位置\" area！ ", local.waitForText("选择车辆停放位置")&&local.waitForView(MyEntry.choosecaraddressentry(local)));//断言：选择车辆停放位置
    	String contenttxt = MyEntry.servicediqutxt(local).getText().toString();
    	assertTrue("No \"提示文案\" text！ ", !contenttxt.isEmpty());
//      assertTrue("No \"历史地址记录\" list！", local.waitForView(local.getView("lay_favorite_address_home")));//断言：历史地址记录区域(已登录，且非首次下单，之前填写过地址)
    }
    
    /**
     * @Name 1055_mapselectpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，上门洗车页，点击车辆停放地址--选择车辆停放地址，正常进入地图选点页，检查页面元素；
     * @FunctionPoint 未登录，上门洗车页，点击车辆停放地址--选择车辆停放地址，正常进入地图选点页，检查页面元素；
     */
    public void test_1055_mapselectpage() {
    	Log.d("test", "test_1011_mapselectpage  start");
    	ToPage.towashcarpage(local);
		local.clickOnView(MyEntry.caraddressentry(local));//点击车辆停放地址输入框
//		local.sleep(1000);
		assertTrue(local.waitForText("选择车辆停放位置"));
		local.clickOnView(MyEntry.choosecaraddressentry(local));//点击选择车辆停放地址
//    	local.sleep(3000);
    	assertTrue(local.waitForText("位置"));
    	//检查页面元素
    	assertTrue("No \"地图\" area！ ", local.waitForView(local.getView("bmapView")));//断言：地图区域
    	assertEquals("No \"[位置]\" text!", "[位置]", MyAssert.getweizhi_caraddress_txt(local));//断言：位置文案
    	assertTrue("No \"位置list\" area！ ", local.waitForView(local.getView("txt_content")));//断言：list区域（开启定位）
    	assertTrue("No \"下一步\" area！ ", local.waitForView(local.getView("txt_title_right")));//断言：下一步按钮
    }
    
    /**
     * @Name 1060_caraddressdetailsetpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，上门洗车页，点击车辆停放地址--选择车辆停放地址--任选一个地址点击下一步，正常进入详细位置描述页，检查页面元素；
     * @FunctionPoint 未登录，上门洗车页，点击车辆停放地址--选择车辆停放地址--任选一个地址点击下一步，正常进入详细位置描述页，检查页面元素；
     */
    public void test_1060_caraddressdetailsetpage() {
    	Log.d("test", "test_1012_caraddressdetailsetpage  start");
    	ToPage.towashcarpage(local);
		local.clickOnView(MyEntry.caraddressentry(local));//点击车辆停放地址输入框
//		local.sleep(1500);
		assertTrue(local.waitForText("选择车辆停放位置"));
		local.clickOnView(MyEntry.choosecaraddressentry(local));//点击选择车辆停放地址
//    	local.sleep(1500);
    	assertTrue(local.waitForText("位置"));
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_title_right"));//点击下一步
//    	local.sleep(3000);
    	assertTrue(local.waitForText("详细位置描述"));
    	//检查页面元素
    	TextView addresscontent = (TextView)local.getView("com.ganji.android.ccar:id/center_text",2);
		String addresscontenttxt = addresscontent.getText().toString();
    	assertEquals("No \"详细位置描述\" text!", "详细位置描述", addresscontenttxt);//断言：Header文案
    	TextView submit = (TextView)local.getView("txt_title_right",2);
    	String submittxt = submit.getText().toString();
    	assertEquals("No \"确定\" button！", "确定", submittxt);//断言：确定按钮
    	assertTrue("No \"位置显示\" area!", local.waitForView(MyEntry.caraddress(local)));//断言：位置显示
    	assertTrue("No \"详细位置输入框\" area!", local.waitForView(MyEntry.caraddressdetail(local)));//断言：详细位置输入框
//    	assertTrue("No \"位置显示\" area!", local.waitForView(MyEntry.caraddress(local)));//断言：位置显示
    	assertEquals("No \"备注\" area!", "备注(选填,如:新龙城13号楼下白色高尔夫)", MyAssert.getprompt_caraddressdetail_txt(local));
    }
    
    /**
     * @Name 1065_taskpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，点击任务，正常进入任务列表页，检查页面元素；
     * @FunctionPoint 未登录，点击任务，正常进入任务列表页，检查页面元素；2.0.1，update，任务入口改变
     */
    public void test_1065_taskpage() {
    	Log.d("test", "test_1013_taskpage  start");
    	ToPage.totaskpage(local);
    	///审查页面元素
//    	assertTrue("未成功跳转到任务页！", local.waitForText("任务"));
    	TextView header = (TextView)local.getView("com.ganji.android.ccar:id/center_text", 2);
    	String headertxt = header.getText().toString();
    	assertEquals("No \"任务\" text！ ", "任务", headertxt);//断言：header文案
    	assertEquals("No \"我的奖品\" tab！", "我的奖品", MyAssert.getmyaward_task_txt(local));//断言：我的奖品tab
    	assertEquals("No \"邀请记录\" tab！", "邀请记录", MyAssert.getinviterecord_task_txt(local));//断言：邀请记录tab
//    	assertTrue("No data!", (local.waitForView(local.getText("分享"))&&local.waitForView(local.getText("洗车"))&&local.waitForView(local.getText("邀请"))));//断言：是否有数据
    	assertTrue("No data!", (local.waitForView(local.getText("洗车"))&&local.waitForView(local.getText("邀请"))));//断言：是否有数据
    }
    
//    /**
//     * @Name 1070_productlistpage
//     * @Catalogue 赶集易洗车C端 Android
//     * @Subcatalog 2.0.1
//     * @Grade 中级
//     * @Channel 
//     * @Describe 未登录，点击套餐，正常进入套餐列表页，检查页面元素；
//     * @FunctionPoint 未登录，点击套餐，正常进入套餐列表页，检查页面元素；2.0.1，delete，没有套餐入口了
//     */
//    public void test_1070_productlistpage() {
//    	Log.d("test", "test_1014_productlistpage  start");
//    	local.waitForActivity("CHomeActivity");
////    	local.clickOnView(MyEntry.productentry(local));//点击套餐 2.0.1
////    	local.sleep(5000);
//    	assertTrue("未成功跳转到任务页！", local.waitForText("套餐"));
//    	//审查页面元素
//    	assertEquals("No \"套餐\" text！ ", "套餐", MyAssert.getall_header_txt(local));//断言：Header文案
//    	assertTrue("No data！", local.waitForView(local.getView("list")));//是否有list
//    	assertTrue("No picture!", local.waitForView(local.getView("img_ads")));//是否有图片
//    	assertTrue("No product title!", local.waitForView(local.getView("txt_name")));//是否有套餐标题
//    	assertTrue("No product content!", local.waitForView(local.getView("txt_desc")));//是否有套餐描述
//    	assertTrue("No product price_now!", local.waitForView(local.getView("txt_price_now")));//是否有套餐现价
//    	assertTrue("No product price_original!", local.waitForView(local.getView("txt_price_ori")));//是否有套餐原价
//    }
    
    /**
     * @Name 1075_productdetailpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，套餐列表页，任意点击一个套餐正常进入套餐详情页，检查页面元素；
     * @FunctionPoint 未登录，套餐列表页，任意点击一个套餐正常进入套餐详情页，检查页面元素；2.0.1,update,套餐入口改变
     */
    public void test_1075_productdetailpage() {
    	Log.d("test", "test_1015_productdetailpage  start");
    	ToPage.totaskdetailpage(local);//进入套餐详情页
    	//检查页面元素
    	assertTrue("header text is error!"+MyAssert.getall_header_txt(local), MyAssert.getall_header_txt(local).contains("套餐"));//header文案，包含套餐俩字
    	ImageView pic = (ImageView)local.getView("iv_promotion_image");
    	assertTrue("No promotion_image!", local.waitForView(pic));//断言：头图
    	assertTrue("No price!", local.waitForView(local.getView("tv_price")));//断言：现价显示
    	assertTrue("No original_price!", local.waitForView(local.getView("tv_original_price")));//断言：原价显示
    	assertEquals("No buy button!", "立即下单", MyAssert.getsubmit_productdetail_txt(local));//断言：立即下单按钮
    	LinearLayout detail = (LinearLayout)local.getView("com.ganji.android.ccar:id/layout_details");
    	TextView detailtxt = (TextView)detail.getChildAt(0);
    	String taocanneirong = detailtxt.getText().toString();
    	assertEquals("No \"套餐内容\" text!", "套餐内容", taocanneirong);
    	TextView wenxintishi = (TextView)local.getView("com.ganji.android.ccar:id/tv_warm_tips");//温馨提示文案
    	LinearLayout fu = (LinearLayout)wenxintishi.getParent();
    	TextView wenxintishitxtarea = (TextView)fu.getChildAt(0);
    	String wenxintishitxt = wenxintishitxtarea.getText().toString();
    	assertEquals("No \"温馨提示\" text!", "温馨提示", wenxintishitxt);
//    	assertTrue("No \"温馨提示\" text!", local.waitForText("温馨提示"));//断言：套餐内容，温馨提示文案，待优化
    	assertTrue("No image!", local.waitForView(local.getView("iv_image")));//断言：套餐内容图片
    	assertTrue("No kindly reminder text!", local.waitForView(local.getView("tv_warm_tips")));//断言：温馨提示内容
    }
    
    /**
     * @Name 1080_productorderpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，套餐详情页，点击立即抢购，进入套餐下单页，检查页面元素；
     * @FunctionPoint 未登录，套餐详情页，点击立即抢购，进入套餐下单页，检查页面元素；1.7.0，update，新增无次卡入口判断；2.0.1，进入套餐详情页流程改变
     */
    public void test_1080_productorderpage() {
    	Log.d("test", "test_1016_productorderpage  start");
    	ToPage.totaskdetailpage(local);//进入套餐详情页
    	local.clickOnView(MyEntry.productorderbutton(local));//点击立即下单按钮
    	local.searchText("您",5000);//+
    	//检查页面元素
    	assertTrue("header text is error!", MyAssert.getall_header_txt(local).contains("套餐"));//header文案，包含套餐俩字
//    	assertTrue("header text is error!", MyAssert.getall_header_txt(local).contains("接口"));//header文案，包含套餐俩字
    	assertTrue("No \"套餐洗车\" image！", local.waitForView(local.getView("img_ads")));//断言：套餐选择区域（套餐洗车）--图片
    	assertTrue("No \"套餐洗车\" title！", local.waitForView(local.getView("txt_name")));//断言：套餐选择区域（套餐洗车）--套餐洗车标题
    	assertTrue("No \"套餐洗车\" content！", local.waitForView(local.getView("txt_desc")));//断言：套餐选择区域（套餐洗车）--套餐洗车详细描述
    	assertTrue("No \"套餐洗车\" price now！", local.waitForView(local.getView("txt_price_now")));//断言：套餐选择区域（套餐洗车）--套餐洗车现在价格
    	String phonetxt = MyEntry.phoneentry(local).getHint().toString();//您的联系方式默认文案
    	assertTrue("No \"您的联系方式\" area！", "您的联系方式".equals(phonetxt)||ChangL.phonenum.equals(MyAssert.getwashcar_phone_txt(local)));//断言：手机号输入区域，显示您的联系方式文案或手机号
    	String carinfotxt = MyEntry.carinfoentry(local).getHint().toString();//您的车牌号码默认文案
    	assertTrue("No \"您的车牌号码\" area！", "您的车牌号码".equals(carinfotxt)||MyAssert.getwashcar_carinfo_txt(local).contains("TESTED"));//断言：车牌号码输入区域
    	String caraddresstxt = MyEntry.caraddressentry(local).getHint().toString();//车辆停放地址默认文案
    	assertTrue("No \"车辆停放地址\" area！", "车辆停放地址".equals(caraddresstxt)||!MyAssert.getwashcar_address_txt(local).isEmpty());//断言：车辆停放地址输入区域
    	String cartimetxt = MyEntry.carservicetimeentry(local).getHint().toString();//您希望服务的时间默认文案
    	assertTrue("No \"您希望服务的时间\" area！", "您希望服务的时间".equals(cartimetxt));//断言：服务时间输入区域
//    	assertFalse("Have \"需要清洗内饰\" area！", local.waitForText("需要清洗内饰"));//断言：套餐下单页没有是否需要清洗内饰选择区域
    	assertTrue("No \"优惠券\" area！", local.waitForView(local.getView("txt_coupon_label")));//断言：优惠券选择区域
    	assertTrue("No \"红包\" area！", local.waitForView(local.getView("com.ganji.android.ccar:id/lay_red_package")));//断言：红包选择区域
    	TextView coupon = (TextView)local.getView("txt_coupon_label");//“优惠券”文案区域
    	String coupontxt = coupon.getText().toString();
    	assertTrue("No \"优惠券\" area！", coupontxt.contains("优惠"));//断言：优惠券选择区域
    	TextView redpackage = (TextView)local.getView("com.ganji.android.ccar:id/txt_red_package_label");//“红包”文案区域
    	String redpackagetxt = redpackage.getText().toString(); 
    	assertEquals("红包", redpackagetxt);
//    	assertFalse("Have \"次卡\" area！", local.waitForText("次卡"));//断言：套餐下单页没有次卡择区域
    	assertTrue("No \"立即下单\" button！", local.waitForView(MyEntry.orderbutton(local)));//立即支付按钮
    	LinearLayout ll =(LinearLayout)local.getView("com.ganji.android.ccar:id/lay_mode_container");
    	int sum = ll.getChildCount();
    	assertEquals("lay_mode_container sum is error!", 19, sum);
    }
    
    /**
     * @Name 1085_VIPorderpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，点击VIP下单，进入VIP下单页，检查页面元素；
     * @FunctionPoint 未登录，点击VIP下单，进入VIP下单页，检查页面元素；
     */
    public void test_1085_VIPorderpage() {
    	Log.d("test", "test_1017_VIPorderpage  start");
    	local.waitForActivity("CHomeActivity");
    	local.clickOnView(MyEntry.VIPorderentry(local));//点击VIP下单
//    	local.sleep(3000);
    	EditText code = (EditText)local.getView("et_cooperation_code");//合作码输入框
    	assertTrue("未成功跳转到VIP下单页！", local.waitForView(code));
    	//检查页面元素
    	assertEquals("header text is error!", "VIP下单", MyAssert.getall_header_txt(local));//断言：header文案
    	assertTrue("No saoyisao view!", local.waitForView(local.getView("com.ganji.android.ccar:id/btn_title_right")));//断言：判断是否显示扫一扫
    	assertTrue("No cooperation input area!", local.waitForView(code));//断言：合作码输入框
    	assertTrue("No next_button!", local.waitForView(local.getView("btn_next")));//断言：下一步按钮
    	RelativeLayout rl = (RelativeLayout)local.getView("com.ganji.android.ccar:id/lay_desc1");//获取vip下单区域文字
    	LinearLayout ll = (LinearLayout)rl.getParent();
    	TextView tv = (TextView)ll.getChildAt(0);
    	String viptxt = tv.getText().toString(); 
    	assertEquals("No \"VIP下单说明\" text!", "VIP下单说明", viptxt);
//    	assertTrue("No \"VIP下单说明\" text!", local.waitForText("VIP下单说明"));//断言：VIP下单说明文字
    	assertTrue("No content introduce!", local.waitForView(local.getView("tv_desc1"))&&local.waitForView(local.getView("tv_desc2"))&&local.waitForView(local.getView("tv_desc3"))&&local.waitForView(local.getView("tv_desc4")));//断言：文字介绍，四块
    	assertTrue("No image introduce!", local.waitForView(local.getView("iv_desc1"))&&local.waitForView(local.getView("iv_desc2"))&&local.waitForView(local.getView("iv_desc3"))&&local.waitForView(local.getView("iv_desc4")));//断言：图片，四张
    }
    
    /**
     * @Name 1086_VIPorderdetailpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，点击VIP下单，进入VIP下单页，输入VIP码，进入详情页，检查页面元素;
     * @FunctionPoint 未登录，点击VIP下单，进入VIP下单页，输入VIP码，进入详情页，检查页面元素；2.0.1,add
     */
    public void test_1086_VIPorderdetailpage() {
    	local.waitForActivity("CHomeActivity");
    	local.clickOnView(MyEntry.VIPorderentry(local));//点击VIP下单
//    	local.sleep(3000);
    	EditText code = (EditText)local.getView("et_cooperation_code");//合作码输入框
    	local.enterText(code, ChangL.VIPcode);//输入VIP码
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_next"));//点击下一步按钮
//    	//检查页面元素
    	assertTrue("header text is error!", local.waitForText("选择服务"));//断言：header文案
    	local.waitForText(ChangL.VIPcode);
    	TextView companyname = (TextView)local.getView("com.ganji.android.ccar:id/company_name");//公司名称
    	String companynametxt = companyname.getText().toString();
    	assertEquals("VIP companyname is error!", ChangL.VIPcompanyname, companynametxt);
    	TextView vipcode = (TextView)local.getView("com.ganji.android.ccar:id/company_code");//vipcode
    	String vipcodetxt = vipcode.getText().toString();
    	assertEquals("VIP code is error!", ChangL.VIPcode, vipcodetxt);
    	TextView serviceaddress = (TextView)local.getView("com.ganji.android.ccar:id/service_address");//服务地址
    	String serviceaddresstxt = serviceaddress.getText().toString();
    	assertEquals("VIP service adderess is error!", ChangL.VIPserviceaddress, serviceaddresstxt);
    	TextView title = (TextView)local.getView("com.ganji.android.ccar:id/item_cooperate_select_title");//title
    	assertTrue("No VIP cooperate title!", local.waitForView(title));
    	TextView content = (TextView)local.getView("com.ganji.android.ccar:id/item_cooperate_select_content");//content
    	assertTrue("No VIP cooperate content!", local.waitForView(content));
    	ImageView image = (ImageView)local.getView("com.ganji.android.ccar:id/item_cooperate_img_ad");//image
    	assertTrue("No VIP cooperate image!", local.waitForView(image));
    	TextView price = (TextView)local.getView("com.ganji.android.ccar:id/price_now");//price
    	assertTrue("No VIP cooperate price!", local.waitForView(price));
    	ImageView xiang = (ImageView)local.getView("com.ganji.android.ccar:id/xiang");//xiang
    	assertTrue("No VIP cooperate xiang!", local.waitForView(xiang));
    	TextView priceori = (TextView)local.getView("com.ganji.android.ccar:id/txt_price_ori");//priceori
    	assertTrue("No VIP cooperate priceori!", local.waitForView(priceori)); 
    	Button btn = (Button)local.getView("com.ganji.android.ccar:id/btn");//btn
    	assertTrue("No VIP cooperate btn!", local.waitForView(btn)); 
    }
    
    /**
     * @Name 1090_seviceintropage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，点击服务介绍，正常进入服务介绍页，检查页面元素；
     * @FunctionPoint 未登录，点击服务介绍，正常进入服务介绍页，检查页面元素；
     */
    public void test_1090_seviceintropage() {
    	Log.d("test", "test_1018_seviceintropage  start");
    	local.waitForActivity("CHomeActivity");
    	local.clickOnView(MyEntry.serviceintroduceentry(local));//点击服务介绍
//    	local.sleep(6000);
    	assertTrue("未成功跳转到服务介绍页！", local.waitForView(local.getView("webview")));
    	//检查页面元素
    	assertEquals("header text is error!", "服务介绍", MyAssert.getall_header_txt(local));//断言：header文案
    	TextView share = (TextView)local.getView("txt_title_right");//分享按钮
    	String sharetxt = share.getText().toString();
    	assertEquals("No \"分享\" text!", "分享", sharetxt);//断言：分享按钮
    	assertTrue("Currentpage not have webview!", local.waitForView(local.getView("webview")));//断言：判断当前是否是webview
    }
    
    /**
     * @Name 1095_washindexpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，点击洗车指数，进入洗车指数页面，检查页面元素；
     * @FunctionPoint 未登录，点击洗车指数，进入洗车指数页面，检查页面元素；
     */
    public void test_1095_washindexpage() {
    	Log.d("test", "test_1019_washindexpage  start");
    	local.waitForActivity("CHomeActivity");
    	local.clickOnView(MyEntry.washindexentry(local));//点击洗车指数
//    	local.sleep(3000);
    	LinearLayout weather = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_weather_container2");//天气区域
    	assertTrue("未成功跳转到洗车指数页！", local.waitForView(weather));
    	//检查页面元素
    	assertEquals("header text is error!", "洗车指数", MyAssert.getall_header_txt(local));//断言：header文案
    	TextView createat = (TextView)local.getView("com.ganji.android.ccar:id/txt_create_at");//更新于区域文案
    	String createattxt = createat.getText().toString();
    	assertTrue("No \"更新于\" text!", createattxt.contains("更新于"));//断言：更新于文案
    	TextView today = (TextView)local.getView("com.ganji.android.ccar:id/textview_today",0);//今天区域文案
    	String todaytxt = today.getText().toString();
    	assertTrue("No \"今天\" text!", todaytxt.contains("今天"));//断言：今天文案
    	assertTrue("No today date!", local.waitForView(local.getView("textview_date")));//断言：今天日期
    	assertTrue("No today weather icon!", local.waitForView(local.getView("imageview_weather_icon")));//断言：今天天气icon
    	assertTrue("No today weather info!", local.waitForView(local.getView("textview_weather_info")));//断言：今天天气信息
    	assertTrue("No today weather temperature!", local.waitForView(local.getView("textview_weather_temperature")));//断言：今天温度
    	assertTrue("No today weather status!", local.waitForView(local.getView("textview_weather_status")));//断言：今天天气状态
    	TextView tomorrow = (TextView)local.getView("com.ganji.android.ccar:id/textview_today",1);//明天区域文案
    	String tomorrowtxt = tomorrow.getText().toString();
    	TextView tomorrowafter = (TextView)local.getView("com.ganji.android.ccar:id/textview_today",2);//后天区域文案
    	String tomorrowaftertxt = tomorrowafter.getText().toString();
    	assertTrue("No \"明天\" text or area!", local.waitForView(local.getView("lay_weather_container2"))&&"明天".equals(tomorrowtxt));//断言：明天区域，明天文案
    	assertTrue("No \"后天\" text or area!", local.waitForView(local.getView("lay_weather_container3")));//断言：后天区域，后天文案
    }
    
    /**
     * @Name 1099_mypagenologin
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，点击进入我的，正常进入我的页面，检查页面元素；
     * @FunctionPoint 未登录，点击进入我的，正常进入我的页面，检查页面元素；1.7.0，add，新增标准洗车次卡入口检查；
     */
    public void test_1099_mypagenologin() {
    	Log.d("test", "test_1024_mypagenologin  start");
//    	local.sleep(3000);
    	assertTrue("CurrentActivity is not CHomeActivity", local.waitForActivity("CHomeActivity"));//断言：当前activity是否是CFeatureActivity
    	SetStep.logout(local);//判断是否未登录，点击我的，查看登录状态
    	//检查页面元素
    	assertTrue("No img profile area!", local.waitForView(local.getView("img_profile")));//断言：头像
    	assertEquals("No \"点击登录\" text!", "点击登录", MyAssert.getmy_name_txt(local));//断言：点击登录
    	ImageView mymessage = (ImageView)MyEntry.mymessageentry(local).getChildAt(0);//我的消息图标
    	assertTrue("No \"我的消息\" area!", local.waitForView(mymessage));//断言：我的消息
    	LinearLayout balance = (LinearLayout)MyEntry.balanceentry(local).getChildAt(1);
    	TextView balancetxtarea = (TextView)balance.getChildAt(0);//余额文字区域
    	String balancetxt = balancetxtarea.getText().toString();
    	assertEquals("No \"余额\" text!", "余额", balancetxt);//断言：余额
    	LinearLayout timecard = (LinearLayout)MyEntry.timecardentry(local).getChildAt(1);
    	TextView timecardtxtarea = (TextView)timecard.getChildAt(0);//次卡文字区域
    	String timecardtxt = timecardtxtarea.getText().toString();
    	assertEquals("No \"次卡\" text!", "次卡", timecardtxt);//断言：次卡
    	LinearLayout coupons = (LinearLayout)MyEntry.couponentry(local).getChildAt(1);
    	TextView couponstxtarea = (TextView)coupons.getChildAt(0);//优惠券文字区域
    	String couponstxt = couponstxtarea.getText().toString();
    	assertEquals("No \"优惠券\" text!", "优惠券", couponstxt);//断言：优惠券
    	LinearLayout redpackage = (LinearLayout)MyEntry.myredpackageentry(local).getChildAt(1);
    	TextView redpackagetxtarea = (TextView)redpackage.getChildAt(0);//红包文字区域
    	String redpackagetxt = redpackagetxtarea.getText().toString();
    	assertEquals("No \"红包\" text!", "红包", redpackagetxt);//断言：红包
    	TextView myaddress = (TextView)MyEntry.myaddressentry(local).getChildAt(1);//常用地址字区域
    	String myaddresstxt = myaddress.getText().toString();
    	assertEquals("No \"常用地址\" text!", "常用地址", myaddresstxt);//断言：常用地址
    	TextView mycar = (TextView)MyEntry.mycarentry(local).getChildAt(1);//我的车辆字区域
    	String mycartxt = mycar.getText().toString();
    	assertEquals("No \"我的车辆\" text!", "我的车辆", mycartxt);//断言：我的车辆
    	TextView myaward = (TextView)MyEntry.myawardentry(local).getChildAt(1);//我的奖品字区域
    	String myawardtxt = myaward.getText().toString();
    	assertEquals("No \"我的奖品\" text!", "我的奖品", myawardtxt);//断言：我的奖品
    	TextView myinvitecode = (TextView)MyEntry.myinvitecodeentry(local).getChildAt(1);//我的邀请码字区域
    	String myinvitecodetxt = myinvitecode.getText().toString();
    	assertEquals("No \"我的邀请码\" text!", "我的邀请码", myinvitecodetxt);//断言：我的邀请码
     	TextView mymore = (TextView)MyEntry.mymoreentry(local).getChildAt(1);//关于赶集易洗车字区域
    	String mymoretxt = mymore.getText().toString();
    	assertEquals("No \"关于\" text!", "关于", mymoretxt);//断言：关于赶集易洗车
    }
    
    /**
     * @Name 1100_changephonpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，上门洗车页，点击联系方式，弹出修改手机号提示，点击确定，正常进入修改手机号页面，检查页面元素；
     * @FunctionPoint 已登录，上门洗车页，点击联系方式，弹出修改手机号提示，点击确定，正常进入修改手机号页面，检查页面元素；
     */
    public void test_1100_changephonpage() {
    	Log.d("test", "test_1040_setphonpage  start");
    	ToPage.towashcarpage(local);//进入上门洗车页
//   	assertTrue("未成功跳转到标准洗车页！", local.waitForText(ChangL.washcarheadertxt));
    	assertTrue("未成功跳转到标准洗车页！", local.waitForText("需要清洗内饰"));
    	SetStep.setphone(local);//确认是否登录，没登录先登录
    	local.sleep(800);
    	local.clickOnView(MyEntry.phoneentry(local));//再次点击手机号区域
    	while (!local.waitForView(local.getView("btn_datetime_sure")))
    		local.clickOnView(MyEntry.phoneentry(local));//再次点击手机号区域
//    	local.waitForDialogToOpen();
    	local.clickOnView(local.getView("btn_datetime_sure"));//点击确定
    	local.sleep(1000);
    	//确认是否到修改手机号页面，页面元素检查
    	assertEquals("No \"修改手机号\" text！ ", "修改手机号", MyAssert.getall_header_txt(local));//断言：header文案
    	String phonetxt = MyEntry.phoneinput(local).getHint().toString();
    	assertEquals("No \"请输入您的手机号\" area！", "请输入您的手机号", phonetxt);
    	String passwordinputtxt = MyEntry.passwordinput(local).getHint().toString();
    	assertEquals("No \"请输入您的手机动态密码\" area！", "请输入您的手机动态密码", passwordinputtxt);
    	assertEquals("No \"获取动态 验证码\" area！", "获取动态密码", MyAssert.getlogin_validatebutton_txt(local));
    	String txt = MyEntry.loginbutton(local).getText().toString();//获取登录文字
    	assertEquals("No \"登录\" button！", "登录", txt);//断言：登录按钮
    }
    
    /**
     * @Name 1101_myneedspage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，点击我的订单，正常进入我的订单列表页，检查页面元素；
     * @FunctionPoint 已登录，点击我的订单，正常进入我的订单列表页，检查页面元素；2.0.1，update,增加删除订单按钮
     */
    public void test_1101_myneedspage() {
    	Log.d("test", "test_1020_myneedspage  start");
    	local.waitForActivity("CHomeActivity");
    	ToPage.towashcarpage(local);//进入上门洗车页
    	SetStep.setphone(local);//登录或判断后点击返回
		local.clickOnView(local.getView("btn_title_left"));
//    	local.sleep(2000);
//    	local.waitForActivity("CHomeActivity");
    	ToPage.toorderpage(local);//进入订单页面
    	local.waitForText("月");
    	local.waitForText("日");
//    	local.sleep(2000);
    	//检查页面元素
    	TextView header = (TextView)local.getView("com.ganji.android.ccar:id/center_text", 1);
    	String headertxt = header.getText().toString();
    	assertEquals("No \"我的订单\" text！ ", "我的订单", headertxt);//断言：header文案
//    	sassertTrue("header text is error!", local.waitForText("我的订单"));//断言：header文案
    	assertTrue("No date!", local.waitForView(local.getView("txt_date")));//断言：日期时间
    	assertTrue("No needs status!", local.waitForView(local.getView("tv_needs_status")));//断言：订单状态
    	assertTrue("No needs type!", local.waitForView(local.getView("txt_type")));//断言：洗车类型
    	assertTrue("No needs interior area!", local.waitForView(local.getView("tv_interior")));//断言：清洗内饰区域
    	assertTrue("No needs address!", local.waitForView(local.getView("txt_address")));//断言：洗车地址
    	assertTrue("No needs carnumber!", local.waitForView(local.getView("txt_car_number")));//断言：车牌号码
    	assertTrue("No needs deletebutton!", local.waitForView(local.getView("com.ganji.android.ccar:id/delete_order_btn")));
    }
    
    /**
     * @Name 1105_notpayorderpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的订单列表页，点击进入一个待付款状态的订单，正常进入待付款订单详情页，检查页面元素；
     * @FunctionPoint 已登录，我的订单列表页，点击进入一个待付款状态的订单，正常进入待付款订单详情页，检查页面元素；1.7.0,update,取消次卡选择；2.0.1,update,新增内饰价格
     */
    public void test_1105_notpayorderpage() {
    	Log.d("test", "test_1021_notpayorderpage  start");
    	ToPage.towashcarpage(local);//进入上门洗车页
    	local.sleep(1000);
       	SetStep.setphone(local);//判断是否登录，可省
    	SetStep.setcarinfo(local, "NOPAY1");//设置车辆信息
    	SetStep.setcaraddress(local);//设置车辆停放位置
    	SetStep.setwashcartime(local);//设置服务时间
    	SetStep.checkinterior(local);//点击清理内饰，默认是未选中的
    	SetStep.cancletimecard(local);//取消次卡选择
    	local.sleep(600);
    	local.clickOnView(MyEntry.orderbutton(local));//点击立即下单
    	local.clickOnView(MyEntry.canclebutton(local));//点击取消
    	assertTrue("Submit order failure！", local.waitForText("我的订单"));//是否跳转到我的订单页
    	local.clickOnView(MyEntry.thefirstorderentry(local));//点击第一个订单进入详情
//    	local.sleep(1500);
    	assertTrue("未成功跳转到待付款订单详情页！", local.waitForText("待付款"));
    	//检查页面元素
    	assertEquals("Current status is not notpay!", "待付款", MyAssert.getall_header_txt(local));//断言：header文案，不知道为什么老是取到“我的订单”
    	TextView canclebutton = (TextView)local.getView("com.ganji.android.ccar:id/txt_title_right");
    	String canclebuttontxt = canclebutton.getText().toString();//获取取消订单文字
    	assertEquals("No \"取消订单\" button!", "取消订单", canclebuttontxt);//断言：取消订单按钮
    	assertTrue("No product picture!", local.waitForView(local.getView("img_ads")));//断言：订单图片
    	assertTrue("No product name!", local.waitForView(local.getView("txt_name")));//断言：订单产品名称
    	assertTrue("No product detail!", local.waitForView(local.getView("txt_desc")));//断言：订单产品描述
    	assertTrue("No product price!", local.waitForView(local.getView("txt_price_now")));//断言：订单产品价格
    	TextView interior = (TextView)local.getView("com.ganji.android.ccar:id/wash_interior");
    	String interiorareatxt = interior.getText().toString();
    	assertEquals("No wash_interior!", "是否清洗内饰：清洗", interiorareatxt);//断言：内饰
    	TextView interiorprice = (TextView)local.getView("com.ganji.android.ccar:id/neishi_price");//内饰价格
    	String interiorpricetxt = interiorprice.getText().toString();
    	assertEquals("washinteriorprice is not 0yuan!", ChangL.interiorprice, interiorpricetxt);//断言：内饰价格
    	TextView washcartime = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_consume_time");
    	LinearLayout washcartimefu = (LinearLayout)washcartime.getParent();
    	TextView servicetime = (TextView)washcartimefu.getChildAt(0);//服务时间文字区域
    	String servicetimetxt = servicetime.getText().toString();
    	assertEquals("No wash_car_time!", "服务时间:", servicetimetxt);//断言：服务时间
    	TextView caraddress = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_address");
    	LinearLayout caraddressfu = (LinearLayout)caraddress.getParent();
    	TextView caraddressarea = (TextView)caraddressfu.getChildAt(0);//车辆位置文字区域
    	String caraddressareatxt = caraddressarea.getText().toString();
    	assertEquals("No wash_car_address!", "车辆位置:", caraddressareatxt);//断言：车辆位置
    	TextView carnum = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_number");
    	LinearLayout carnumfu = (LinearLayout)carnum.getParent();
    	TextView carnumarea = (TextView)carnumfu.getChildAt(0);//车牌文字区域
    	String carnumareatxt = carnumarea.getText().toString();
    	assertEquals("No wash_car_number!", "车        牌:", carnumareatxt);//断言：车牌
    	TextView cartype = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_type");
    	LinearLayout cartypefu = (LinearLayout)cartype.getParent();
    	TextView cartypearea = (TextView)cartypefu.getChildAt(0);//车型文字区域
    	String cartypeareatxt = cartypearea.getText().toString();
    	assertEquals("No wash_car_type!", "车        型:", cartypeareatxt);//断言：车型
    	TextView carprice = (TextView)local.getView("com.ganji.android.ccar:id/tv_wash_car_price");
    	RelativeLayout carpricefu = (RelativeLayout)carprice.getParent();
    	TextView carpricearea = (TextView)carpricefu.getChildAt(0);//实付款文字区域
    	String carpriceareatxt = carpricearea.getText().toString();
    	assertEquals("No tv_wash_car_price!", "实  付  款:", carpriceareatxt);//断言：实付款
    	Button submit = (Button)local.getView("com.ganji.android.ccar:id/btn_pay");
    	String submittxt = submit.getText().toString();
    	assertEquals("No btn_pay button!", "立即付款", submittxt);//断言：立即付款按钮
    	//扫尾，取消、删除订单
    	local.clickOnView(canclebutton);//点击取消订单
    	assertTrue("未弹出取消订单dialog！", local.waitForText("是否取消订单？"));
    	local.waitForView(local.getView("com.ganji.android.ccar:id/btn_datetime_sure"));
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_datetime_sure"));//点击确定
    }
    
    /**
     * @Name 1110_payorderpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的订单列表页，点击进入一个现金支付预约成功状态的订单，正常进入预约成功页，检查页面元素（非自动分单）；
     * @FunctionPoint 已登录，我的订单列表页，点击进入一个现金支付预约成功状态的订单，正常进入预约成功页，检查页面元素；
     */
    public void test_1110_payorderpage() {
    	Log.d("test", "test_1022_payorderpage  start");
    	ToPage.towashcarpage(local);//进入上门洗车页
    	SetStep.washcarorder(local);//提交一个订单
//    	local.sleep(1500);
    	SetStep.pay(local);//支付
//    	local.sleep(2000);
//    	local.clickOnView(MyEntry.thefirstorderentry(local));//点击第一个订单进入详情
    	//审查页面元素
    	assertTrue("Current status is not notpay!", local.waitForText("订单详情"));//断言：header文案
//    	assertEquals("Current status is not notpay!", "预约成功", MyAssert.getall_header_txt(local));//断言：header文案，不知道为什么老是取到“我的订单”
    	TextView canclebutton = (TextView)local.getView("com.ganji.android.ccar:id/txt_title_right");
    	String canclebuttontxt = canclebutton.getText().toString();//获取取消订单文字
    	assertEquals("No \"取消订单\" button!", "取消订单", canclebuttontxt);//断言：取消订单按钮
    	TextView successcontent = (TextView)local.getView("com.ganji.android.ccar:id/reservation_successful_content");
    	String successcontenttxt = successcontent.getText().toString();
    	assertEquals("No order content text!", "正在为您分配师傅，请耐心等待。", successcontenttxt);//断言：预约成功文案
    	TextView washcategory = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_category");
    	LinearLayout washcategoryfu = (LinearLayout)washcategory.getParent();
    	TextView washcategoryarea = (TextView)washcategoryfu.getChildAt(0);//服务项目文字区域
    	String washcategorytxt = washcategoryarea.getText().toString();
    	assertEquals("No wash_car_time!", "服务项目:", washcategorytxt);//断言：服务项目
    	TextView washcarcontent = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_content");
    	LinearLayout washcarcontentfu = (LinearLayout)washcarcontent.getParent();
    	TextView washcarcontentarea = (TextView)washcarcontentfu.getChildAt(0);//服务内容文字区域
    	String wwashcarcontenttxt = washcarcontentarea.getText().toString();
    	assertEquals("No wash_car_time!", "服务内容:", wwashcarcontenttxt);//断言：服务内容
    	TextView washcartime = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_consume_time");
    	LinearLayout washcartimefu = (LinearLayout)washcartime.getParent();
    	TextView servicetime = (TextView)washcartimefu.getChildAt(0);//服务时间文字区域
    	String servicetimetxt = servicetime.getText().toString();
    	assertEquals("No wash_car_time!", "服务时间:", servicetimetxt);//断言：服务时间
    	TextView caraddress = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_address");
    	LinearLayout caraddressfu = (LinearLayout)caraddress.getParent();
    	TextView caraddressarea = (TextView)caraddressfu.getChildAt(0);//车辆位置文字区域
    	String caraddressareatxt = caraddressarea.getText().toString();
    	assertEquals("No wash_car_address!", "车辆位置:", caraddressareatxt);//断言：车辆位置
    	TextView carnum = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_number");
    	LinearLayout carnumfu = (LinearLayout)carnum.getParent();
    	TextView carnumarea = (TextView)carnumfu.getChildAt(0);//车牌文字区域
    	String carnumareatxt = carnumarea.getText().toString();
    	assertEquals("No wash_car_number!", "车        牌:", carnumareatxt);//断言：车牌
    	TextView cartype = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_type");
    	LinearLayout cartypefu = (LinearLayout)cartype.getParent();
    	TextView cartypearea = (TextView)cartypefu.getChildAt(0);//车型文字区域
    	String cartypeareatxt = cartypearea.getText().toString();
    	assertEquals("No wash_car_type!", "车        型:", cartypeareatxt);//断言：车型
    	TextView interior = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_interior");
    	LinearLayout interiorfu = (LinearLayout)interior.getParent();
    	TextView interiorarea = (TextView)interiorfu.getChildAt(0);//内饰文字区域
    	String interiorareatxt = interiorarea.getText().toString();
    	assertEquals("No wash_interior!", "内        饰:", interiorareatxt);//断言：内饰
    	TextView carprice = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_price");
    	LinearLayout carpricefu = (LinearLayout)carprice.getParent();
    	TextView carpricearea = (TextView)carpricefu.getChildAt(0);//实付款文字区域
    	String carpriceareatxt = carpricearea.getText().toString();
    	assertEquals("No tv_wash_car_price!", "实  付  款:", carpriceareatxt);//断言：实付款
    }

    /**
     * @Name 1114_kefupage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的订单列表页，点击进入任意一个订单页面，点击在线客服进入在线客服页，检查页面元素；
     * @FunctionPoint 已登录，我的订单列表页，点击进入任意一个订单页面，点击在线客服进入在线客服页，检查页面元素；2.0.1,add
     */
    public void test_1114_kefupage() {
    	ToPage.toorderpage(local);//进入订单页面
    	local.sleep(1000);
    	local.clickOnView(MyEntry.thefirstorderentry(local));//点击第一个订单进入详情
    	local.waitForView(local.getView("com.ganji.android.ccar:id/zi_xun_ke_fu"));
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/zi_xun_ke_fu"));//点击客服按钮
    	local.waitForView(local.getView("com.ganji.android.ccar:id/online"));
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/online"));//点击在线客服
    	local.sleep(1000);
    	//审查页面元素
//    	assertTrue("未成功跳转到在线客服页！", local.waitForText("在线客服")&&local.waitForView(local.getView("com.ganji.android.ccar:id/conversation_message_other_portrait"))&&local.waitForView(local.getView("com.ganji.android.ccar:id/conversation_message_content_left_tv")));
    }
    
    /**
     * @Name 1115_repundpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的订单列表页，点击进入一个现金支付退款成功状态的订单，正常进入退款成功页，检查页面元素；
     * @FunctionPoint 已登录，我的订单列表页，点击进入一个现金支付退款成功状态的订单，正常进入退款成功页，检查页面元素；
     */
    public void test_1115_repundpage() {
    	Log.d("test", "test_1023_repundpage  start");
//    	local.sleep(2000);
    	local.waitForActivity("CHomeActivity");
    	ToPage.toorderpage(local);//用1022的预约成功的订单，进入我的订单页
    	local.clickOnView(MyEntry.thefirstorderentry(local));//点击第一个订单进入详情
    	SetStep.payordercancle(local);//取消订单
//    	local.clickOnView(local.getView("btn_title_left"));//点击返回按钮
    	local.sleep(1500);
    	local.clickOnView(MyEntry.thefirstorderentry(local));//点击第一个订单进入详情	
    	local.waitForText("退款成功");
    	//审查页面元素
    	assertTrue("Current status is not refund!", local.waitForText("订单详情"));//断言：header文案
    	local.scrollDown();//下拉到最下面
    	TextView repund = (TextView)local.getView("com.ganji.android.ccar:id/common_content");
    	String repundtxt = repund.getText().toString();
    	assertEquals("No repund content!", "退款成功，请您及时查看您的余额，如有疑问请及时与我们联系：400-733-5500。", repundtxt);//断言：文案
    }
    
    /**
     * @Name 1116_payorderusetimecardpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，点击订单，找次卡支付的订单进入，检查页面元素；
     * @FunctionPoint 已登录，点击订单，找次卡支付的订单进入，检查页面元素；1.7.0，add，新增次卡支付流程；
     */
    public void test_1116_payorderusetimecardpage() {
    	Log.d("test", "test_1022_payorderpage  start");
    	ToPage.towashcarpage(local);//进入上门洗车页
    	SetStep.setphone(local);//提交一个订单
    	SetStep.setcarinfo(local, "CKZF11");
    	SetStep.setcaraddress(local);
    	SetStep.setwashcartime(local);
//    	local.sleep(1500);
    	local.clickOnView(MyEntry.orderbutton(local));//点击立即下单
    	//检查页面元素
    	assertTrue("Header txt is error!", local.waitForText("订单详情"));//断言：header文案
    	TextView canclebutton = (TextView)local.getView("com.ganji.android.ccar:id/txt_title_right");
    	String canclebuttontxt = canclebutton.getText().toString();//获取取消订单文字
    	assertEquals("No \"取消订单\" button!", "取消订单", canclebuttontxt);//断言：取消订单按钮
    	TextView successcontent = (TextView)local.getView("com.ganji.android.ccar:id/reservation_successful_content");
    	String successcontenttxt = successcontent.getText().toString();
    	assertEquals("No order content text!", "正在为您分配师傅，请耐心等待。", successcontenttxt);//断言：预约成功文案
    	TextView washcategory = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_category");
    	LinearLayout washcategoryfu = (LinearLayout)washcategory.getParent();
    	TextView washcategoryarea = (TextView)washcategoryfu.getChildAt(0);//服务项目文字区域
    	String washcategorytxt = washcategoryarea.getText().toString();
    	assertEquals("No wash_car_time!", "服务项目:", washcategorytxt);//断言：服务项目
    	TextView washcarcontent = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_content");
    	LinearLayout washcarcontentfu = (LinearLayout)washcarcontent.getParent();
    	TextView washcarcontentarea = (TextView)washcarcontentfu.getChildAt(0);//服务内容文字区域
    	String wwashcarcontenttxt = washcarcontentarea.getText().toString();
    	assertEquals("No wash_car_time!", "服务内容:", wwashcarcontenttxt);//断言：服务内容
    	TextView washcartime = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_consume_time");
    	LinearLayout washcartimefu = (LinearLayout)washcartime.getParent();
    	TextView servicetime = (TextView)washcartimefu.getChildAt(0);//服务时间文字区域
    	String servicetimetxt = servicetime.getText().toString();
    	assertEquals("No wash_car_time!", "服务时间:", servicetimetxt);//断言：服务时间
    	TextView caraddress = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_address");
    	LinearLayout caraddressfu = (LinearLayout)caraddress.getParent();
    	TextView caraddressarea = (TextView)caraddressfu.getChildAt(0);//车辆位置文字区域
    	String caraddressareatxt = caraddressarea.getText().toString();
    	assertEquals("No wash_car_address!", "车辆位置:", caraddressareatxt);//断言：车辆位置
    	TextView carnum = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_number");
    	LinearLayout carnumfu = (LinearLayout)carnum.getParent();
    	TextView carnumarea = (TextView)carnumfu.getChildAt(0);//车牌文字区域
    	String carnumareatxt = carnumarea.getText().toString();
    	assertEquals("No wash_car_number!", "车        牌:", carnumareatxt);//断言：车牌
    	TextView cartype = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_type");
    	LinearLayout cartypefu = (LinearLayout)cartype.getParent();
    	TextView cartypearea = (TextView)cartypefu.getChildAt(0);//车型文字区域
    	String cartypeareatxt = cartypearea.getText().toString();
    	assertEquals("No wash_car_type!", "车        型:", cartypeareatxt);//断言：车型
    	TextView interior = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_interior");
    	LinearLayout interiorfu = (LinearLayout)interior.getParent();
    	TextView interiorarea = (TextView)interiorfu.getChildAt(0);//内饰文字区域
    	String interiorareatxt = interiorarea.getText().toString();
    	assertEquals("No wash_interior!", "内        饰:", interiorareatxt);//断言：内饰
    	LinearLayout timecard = (LinearLayout)local.getView("com.ganji.android.ccar:id/ll_wash_count_card");
    	TextView timecardarea = (TextView)timecard.getChildAt(0);//次卡款文字区域
    	String timecardareatxt = timecardarea.getText().toString();
    	assertEquals("No timecard txt!", "次        卡:", timecardareatxt);//断言：次卡
    	TextView timecardnum = (TextView)local.getView("com.ganji.android.ccar:id/wash_count_card");//次数文案
    	String timecardnumtxt = timecardnum.getText().toString();
    	assertTrue("No timecard detail txt or timecard num is error!"+timecardnumtxt, timecardnumtxt.contains("消费1次，剩余"+ChangL.timecarduse+"次"));//断言：次卡使用详细文案
    }
    
    
    /**
     * @Name 1117_repundtimecardpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，点击订单，找次卡支付的退款订单进入，检查页面元素；
     * @FunctionPoint 已登录，点击订单，找次卡支付的退款订单进入，检查页面元素；1.7.0，add，新增次卡预约成功订单取消订单流程；
     */
    public void test_1117_repundtimecardpage() {
    	Log.d("test", "test_1023_repundpage  start");
//    	local.sleep(2000);
    	local.waitForActivity("CHomeActivity");
    	ToPage.toorderpage(local);//用1116的预约成功的订单，进入我的订单页
    	local.clickOnView(MyEntry.thefirstorderentry(local));//点击第一个订单进入详情
    	assertTrue("未成功跳转到预约成功订单详情页！", local.waitForText("预约成功"));
    	SetStep.payorderusetimecardcancle(local);//取消订单
    	local.sleep(500);
    	//审查页面元素
    	assertTrue("Current status is not refund!", local.waitForText("订单详情"));//断言：header文案
    	local.waitForView(local.getView("com.ganji.android.ccar:id/common_content"));
    	TextView repund = (TextView)local.getView("com.ganji.android.ccar:id/common_content");
    	String repundtxt = repund.getText().toString();
    	assertEquals("No timecard repund content!", "您的订单已取消成功,请查看”我的-洗车次卡-我的次卡“中的剩余次数", repundtxt);//断言：文案
    	TextView cancle =(TextView)local.getView("com.ganji.android.ccar:id/title");//取消订单成功文案
    	String cancletxt = cancle.getText().toString();
    	assertEquals("current status is not cancled!", "取消订单成功", cancletxt);
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_title_left"));//点击返回
    	ToPage.tomypage(local);//点击我的
    	TextView timecardnum = (TextView)local.getView("com.ganji.android.ccar:id/txt_timecard_label");
    	String timecardnumtxt = timecardnum.getText().toString();//剩余次卡数量
    	assertEquals("cacle order of timecard is failed!", ChangL.timecard, timecardnumtxt);//断言，是否取消成功
    }

    /**
     * @Name 1120_taskstatepage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 未登录，点击任务，正常进入任务列表页，检查页面元素，增加对“领取”的判断；
     * @FunctionPoint 未登录，点击任务，正常进入任务列表页，检查页面元素，增加对“领取”的判断；2.0.5,add
     */
    public void test_1120_taskstatepage() {
    	ToPage.totaskpage(local);
    	///审查页面元素
    	assertTrue("No data!", (local.waitForView(local.getText("洗车"))&&local.waitForView(local.getText("邀请"))&&local.waitForView(local.getText("领取"))));//断言：是否有数据
    }
    
    /**
     * @Name 1125_mypagelogin
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，点击进入我的，正常进入我的页面，检查页面元素；
     * @FunctionPoint 已登录，点击进入我的，正常进入我的页面，检查页面元素；1.7.0，update，新增标准洗车次卡入口检查；2.0.1，update，页面布局改变；
     */
    public void test_1125_mypagelogin() {
    	Log.d("test", "test_1025_mypagelogin  start");
    	SetStep.login(local);//判断是否未登录，点击我的，查看登录状态
    	local.scrollUp();//往上拉一下
    	//检查页面元素
    	assertTrue("No img profile area!", local.waitForView(local.getView("img_profile")));//断言：头像
    	TextView name = (TextView)local.getView("com.ganji.android.ccar:id/txt_name");
    	String nametxt = name.getText().toString();
//    	assertEquals("No login name text!", ChangL.name, nametxt);//断言：用户名，线上用户名是"QA测试号"
    	assertTrue("No login name text!", "QA测试号".equals(nametxt)||"修改昵称".equals(nametxt));
    	ImageView mymessage = (ImageView)MyEntry.mymessageentry(local).getChildAt(0);//我的消息图标
    	assertTrue("No \"我的消息\" area!", local.waitForView(mymessage));//断言：我的消息
    	LinearLayout balance = (LinearLayout)MyEntry.balanceentry(local).getChildAt(1);
    	TextView balancetxtarea = (TextView)balance.getChildAt(0);//余额文字区域
    	String balancetxt = balancetxtarea.getText().toString();
    	assertEquals("No \"余额\" text!", "余额", balancetxt);//断言：余额
    	TextView balanceprice = (TextView)local.getView("com.ganji.android.ccar:id/txt_balance_price");//余额数
    	String balancepricetxt = balanceprice.getText().toString();//余额数文字
    	assertEquals("余额数不正确！", ChangL.balance, balancepricetxt);
    	LinearLayout timecard = (LinearLayout)MyEntry.timecardentry(local).getChildAt(1);
    	TextView timecardtxtarea = (TextView)timecard.getChildAt(0);//次卡文字区域
    	String timecardtxt = timecardtxtarea.getText().toString();
    	assertEquals("No \"次卡\" text!", "次卡", timecardtxt);//断言：次卡
    	TextView timecardnum = (TextView)local.getView("com.ganji.android.ccar:id/txt_timecard_label");//次卡次数
    	String timecardnumtxt = timecardnum.getText().toString();//次卡次数文字
    	assertEquals("次卡次数不正确！", ChangL.timecard, timecardnumtxt);
    	LinearLayout coupons = (LinearLayout)MyEntry.couponentry(local).getChildAt(1);
    	TextView couponstxtarea = (TextView)coupons.getChildAt(0);//优惠券文字区域
    	String couponstxt = couponstxtarea.getText().toString();
    	assertEquals("No \"优惠券\" text!", "优惠券", couponstxt);//断言：优惠券
    	TextView couponsprice = (TextView)local.getView("com.ganji.android.ccar:id/txt_coupons_price");//优惠券次数
    	String couponspricetxt = couponsprice.getText().toString();//优惠券数量文字
    	assertEquals("优惠劵数量不正确！", ChangL.coupons, couponspricetxt);
    	
    	TextView redpackageprice = (TextView)local.getView("com.ganji.android.ccar:id/txt_red_package_price");//红包数量
    	String redpackagepricetxt = redpackageprice.getText().toString();//红包数量文字
    	float redpackagepricenum = Float.parseFloat(redpackagepricetxt);
//    	int redpackagepricenum = Integer.parseInt(redpackagepricetxt);
    	assertTrue("红包为0！", redpackagepricenum>0.00);
//    	assertTrue("红包为0！", redpackagepricenum>0);
    	
    	LinearLayout redpackage = (LinearLayout)MyEntry.myredpackageentry(local).getChildAt(1);
    	TextView redpackagetxtarea = (TextView)redpackage.getChildAt(0);//红包文字区域
    	String redpackagetxt = redpackagetxtarea.getText().toString();
    	assertEquals("No \"红包\" text!", "红包", redpackagetxt);//断言：红包
    	TextView myaddress = (TextView)MyEntry.myaddressentry(local).getChildAt(1);//常用地址字区域
    	String myaddresstxt = myaddress.getText().toString();
    	assertEquals("No \"常用地址\" text!", "常用地址", myaddresstxt);//断言：常用地址
    	TextView mycar = (TextView)MyEntry.mycarentry(local).getChildAt(1);//我的车辆字区域
    	String mycartxt = mycar.getText().toString();
    	assertEquals("No \"我的车辆\" text!", "我的车辆", mycartxt);//断言：我的车辆
    	TextView myaward = (TextView)MyEntry.myawardentry(local).getChildAt(1);//我的奖品字区域
    	String myawardtxt = myaward.getText().toString();
    	assertEquals("No \"我的奖品\" text!", "我的奖品", myawardtxt);//断言：我的奖品
    	TextView myinvitecode = (TextView)MyEntry.myinvitecodeentry(local).getChildAt(1);//我的邀请码字区域
    	String myinvitecodetxt = myinvitecode.getText().toString();
    	assertEquals("No \"我的邀请码\" text!", "我的邀请码", myinvitecodetxt);//断言：我的邀请码
    	TextView qrcode = (TextView)MyEntry.qrcodemypageentry(local).getChildAt(1);//扫一扫区域
    	String qrcodetxt = qrcode.getText().toString();
    	assertEquals("No \"扫一扫码\" text!", "扫一扫", qrcodetxt);//断言：扫一扫
     	TextView mymore = (TextView)MyEntry.mymoreentry(local).getChildAt(1);//关于赶集易洗车字区域
    	String mymoretxt = mymore.getText().toString();
    	assertEquals("No \"关于\" text!", "关于", mymoretxt);//断言：关于赶集易洗车
    	
    }
    
    /**
     * @Name 1130_updateprofilepage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击头像，正常进入修改个人资料页，检查页面元素；
     * @FunctionPoint 已登录，我的页面，点击头像，正常进入修改个人资料页，检查页面元素；
     */
    public void test_1130_updateprofilepage() {
    	local.sleep(1500);
    	SetStep.login(local);//判断当前是否登录
    	local.scrollUp();
		local.clickOnView(local.getView("img_profile"));//点击头像
		local.sleep(1500);
		//检查页面元素
		assertTrue("No \"修改个人资料\" text!", local.waitForText("修改个人资料"));//断言：Header文案
		assertTrue("No img area!", local.waitForView(local.getView("iv_myself_portrait")));//断言：头像区域
		TextView name = (TextView)local.getView("iv_myself_name");
		String nametxt = name.getText().toString();
		assertTrue("No name area!", "请填写昵称".equals(nametxt)||ChangL.name.equals(nametxt)||"修改昵称".equals(nametxt));//断言：昵称文字
		TextView phone = (TextView)local.getView("iv_myself_phone");
		String phonetxt = phone.getText().toString();
		assertEquals("No phone area!", ChangL.phonenumxing, phonetxt);
		assertTrue("No sex area!", local.waitForView(local.getView("man"))&&local.waitForView(local.getView("women")));//断言：性别
    }
    
    /**
     * @Name 1135_balancepage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击余额，正常进入余额页，检查页面元素；
     * @FunctionPoint 已登录，我的页面，点击余额，正常进入余额页，检查页面元素；
     */
    public void test_1135_balancepage() {
    	ToPage.tomypage(local);//进入我的页面
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/lay_my_balance"));//点击余额
    	local.waitForView(local.getView("com.ganji.android.ccar:id/balance_first_view1"));//首次打开余额会有一个引导层
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/balance_first_view1"));//点击知道了按钮
    	local.sleep(1000);
    	//检查页面元素
		assertTrue("No \"余额\" text!", local.waitForText("余额"));//断言：Header文案
		TextView balancedetail = (TextView)local.getView("txt_title_right");
		String balancedetailtxt = balancedetail.getText().toString();
		assertEquals("No \"余额明细\" button!", "余额明细", balancedetailtxt);//断言：余额明细按钮
		assertTrue("No icon area!", local.waitForView(local.getView("img_icon")));//断言：icon
		TextView balanceprice = (TextView)local.getView("balance_txt");
		LinearLayout fu = (LinearLayout)balanceprice.getParent();
		TextView balance = (TextView)fu.getChildAt(0);
		String balancetxt = balance.getText().toString();
		String balancenum = balanceprice.getText().toString();
		assertEquals("No \"账户余额\" button!", "账户余额: ", balancetxt);//断言：账户余额细按钮
		assertEquals("\"账户余额\" error!Not ¥"+ChangL.balance+"!", "¥"+ChangL.balance, balancenum);//断言：余额数，报警
		TextView title = (TextView)local.getView("com.ganji.android.ccar:id/txt_title");
		String titletxt = title.getText().toString();
		TextView subtitle = (TextView)local.getView("com.ganji.android.ccar:id/txt_sub_title");
		String subtitletxt = subtitle.getText().toString();
		assertTrue("No charge and give area!", titletxt.contains("充值")&&subtitletxt.contains("送"));//断言：充值送
		TextView submit = (TextView)local.getView("com.ganji.android.ccar:id/txt_submit");
		String submittxt = submit.getText().toString();
		assertEquals("No submit button!", "立即充值", submittxt);//立即充值按钮
	}
    
    /**
     * @Name 1140_balancedetailpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击余额-余额明细，正常进入余额明细页，检查页面元素；
     * @FunctionPoint 已登录，我的页面，点击余额-余额明细，正常进入余额明细页，检查页面元素；
     */
    public void test_1140_balancedetailpage() {
    	ToPage.tobalance(local);//进入余额页面
    	local.sleep(1000);
    	local.clickOnView(local.getView("txt_title_right"));//点击余额明细按钮
    	local.waitForView(local.getView("com.ganji.android.ccar:id/total_balance_num"));
    	//检查页面元素
		assertTrue("No \"余额明细\" text!", local.waitForText("余额明细"));//断言：Header文案
		local.waitForText("账户余额");
		local.waitForText("退款");
		TextView balancenum = (TextView)local.getView("com.ganji.android.ccar:id/total_balance_num");
		String banlannumtxt = balancenum.getText().toString();
		LinearLayout fu = (LinearLayout)balancenum.getParent();
		TextView balance = (TextView)fu.getChildAt(1);
		String balancetxt = balance.getText().toString();
		assertEquals("No \"账户余额\" txt!", "账户余额: ", balancetxt);
		assertEquals("\"账户余额\" error!Not ¥"+ChangL.balance+"!", "¥"+ChangL.balance, banlannumtxt);//报警128.25
		assertTrue("No balance_detail_item_name area!", local.waitForView(local.getView("balance_detail_item_name")));//断言：余额名称
		assertTrue("No balance_detail_item_time area!", local.waitForView(local.getView("balance_detail_item_time")));//断言：余额时间
		assertTrue("No charge and give area!", local.waitForView(local.getView("balance_detail_item_num")));//断言：余额金额
    }
    
    /**
     * @Name 1141_buytimecardpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击标准洗车次卡--购买次卡tab，正常进入购买次卡页面，检查页面元素；
     * @FunctionPoint 已登录，我的页面，点击标准洗车次卡--购买次卡tab，正常进入购买次卡页面，检查页面元素；1.7.0，add，标准洗车次卡页；
     */
    public void test_1141_buytimecardpage() {
    	ToPage.totimecard(local);//进入标准洗车次卡页
    	//检查页面元素
    	assertEquals("Header txt is error!", "洗车次卡", MyAssert.getall_header_txt(local));//断言：Header文案
		TextView timecarddetail = (TextView)local.getView("txt_title_right");
		String timecarddetailtxt = timecarddetail.getText().toString();
		assertEquals("No \"明细\" button!", "明细", timecarddetailtxt);//断言：明细按钮
		assertTrue("No icon area!", local.waitForView(local.getView("com.ganji.android.ccar:id/count_card_icon")));//断言：icon
		TextView timecardexplain = (TextView)local.getView("com.ganji.android.ccar:id/count_card_explain");//次卡说明
		String timecardexplaintxt = timecardexplain.getText().toString();
		assertEquals("No timecard explain erea!", "次卡说明", timecardexplaintxt);//断言：次卡说明文案
		TextView timecardnum = (TextView)local.getView("com.ganji.android.ccar:id/left_count_card_num");//次卡剩余数量
		String timecardnumtxt = timecardnum.getText().toString();
		assertTrue("No timecardnum area!", timecardnumtxt.contains("总剩余")&&timecardnumtxt.contains("次适用于当前城市"));//断言：次卡剩余次数文案
		RelativeLayout buycountcard = (RelativeLayout)local.getView("com.ganji.android.ccar:id/buy_count_card");//购买次卡tab
		TextView buycard = (TextView)buycountcard.getChildAt(0);//购买次卡tab
		String buycardtxt = buycard.getText().toString();
		assertEquals("No \"购买次卡\" tab！", "购买次卡", buycardtxt);
		RelativeLayout mycountcard = (RelativeLayout)local.getView("com.ganji.android.ccar:id/my_count_card");//我的次卡tab
		TextView mycount = (TextView)mycountcard.getChildAt(0);//购买次卡tab
		String mycounttxt = mycount.getText().toString();
		assertEquals("No \"我的次卡\" tab！", "我的次卡", mycounttxt);
		LinearLayout carditem = (LinearLayout)local.getView("com.ganji.android.ccar:id/count_card_item_container");//数据区域
		assertTrue("No carditem area！", local.waitForView(carditem));//断言：是否有购买列表
		ImageView radio = (ImageView)local.getView("com.ganji.android.ccar:id/image_click");//radio
		assertTrue("No radionbutton！", local.waitForView(radio));//断言：是否有购买列表
		TextView title = (TextView)local.getView("com.ganji.android.ccar:id/txt_title");//title
		String titletxt = title.getText().toString();
		assertTrue("No timecard title!", titletxt.contains("赠送"));//断言：次卡title
		TextView subtitle = (TextView)local.getView("com.ganji.android.ccar:id/txt_sub_title");//赠送次数区域
		String subtitletxt = subtitle.getText().toString();
		assertTrue("No timecardnum area!", subtitletxt.contains("次"));//断言：次卡次数
		TextView validtime = (TextView)local.getView("com.ganji.android.ccar:id/txt_valid_time");//有效期
		String validtimetxt = validtime.getText().toString();
		assertTrue("No validtime area!", validtimetxt.contains("有效期"));//断言：有效期
		TextView city = (TextView)local.getView("com.ganji.android.ccar:id/item_card_citys");//适用城市
		String citytxt = city.getText().toString();
		assertTrue("No city area!", citytxt.contains("适用城市"));//断言：适用城市
		TextView submit = (TextView)local.getView("com.ganji.android.ccar:id/txt_submit");//立即购买按钮
		String submittxt = submit.getText().toString();
		assertEquals("No \"立即充值\" button!", "立即充值", submittxt);
	}
    
    /**
     * @Name 1142_mytimecardpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击标准洗车次卡--我的次卡tab，正常进入我的次卡页面，检查页面元素；
     * @FunctionPoint 已登录，我的页面，点击标准洗车次卡--我的次卡tab，正常进入我的次卡页面，检查页面元素；1.7.0，add，我的次卡页；
     */
    public void test_1142_mytimecardpage(){
    	ToPage.totimecard(local);//进入标准洗车次卡页
    	local.waitForText("赠送");//有时候页面加载不出来，报空指针
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/my_count_card"));//点击我的次卡tab
    	local.waitForText("剩余次数");//有时候页面加载不出来，报空指针
    	//检查页面元素
    	ListView list = (ListView)local.getView("android:id/list");//list
    	TextView explain = (TextView)list.getChildAt(1);//说明文案
    	String explaintxt = explain.getText().toString();
    	assertEquals("No explain txt!", "您消费时,我们会优先从即将过期的次卡中扣除次数", explaintxt);
    	LinearLayout item = (LinearLayout)local.getView("com.ganji.android.ccar:id/my_count_card_item");//item
    	TextView leftcountnum = (TextView)local.getView("com.ganji.android.ccar:id/left_count");//剩余次数
    	LinearLayout fu = (LinearLayout)leftcountnum.getParent();//父节点
    	TextView leftcount = (TextView)fu.getChildAt(0);//剩余次数文案
    	String leftcounttxt = leftcount.getText().toString();
    	assertEquals("No leftcount area!", "剩余次数: ", leftcounttxt);//断言：剩余次数文案
    	String leftcountnumtxt = leftcountnum.getText().toString();//剩余次数
    	assertEquals("left num is error!", ChangL.timecardleft, leftcountnumtxt);//断言：剩余次数
    	TextView presentcount = (TextView)fu.getChildAt(2);//赠送文案
    	String presentcounttxt = presentcount.getText().toString();
    	assertEquals("No leftcount area!", " +赠送 ",presentcounttxt);//断言：赠送次数文案
    	TextView presentnum = (TextView)local.getView("com.ganji.android.ccar:id/present_left_count");//赠送次数
    	String presentnumtxt = presentnum.getText().toString();
    	assertEquals("present num is error!", ChangL.timecardpresent, presentnumtxt);//断言：赠送次数
    	TextView countcardtype = (TextView)local.getView("com.ganji.android.ccar:id/count_card_type");//次卡标题
    	String countcardtypetxt = countcardtype.getText().toString();
    	assertEquals("timecard title is error!--\"自动化次卡\"", ChangL.timecardtitle, countcardtypetxt);
    	TextView validdate = (TextView)local.getView("com.ganji.android.ccar:id/valid_date");//有效期
    	String validdatetxt = validdate.getText().toString();
    	assertTrue("No \"有效期\" area!", validdatetxt.contains("有效期:")&&validdatetxt.contains("至"));
    	TextView city = (TextView)local.getView("com.ganji.android.ccar:id/my_count_card_city");//适用城市
		String citytxt = city.getText().toString();
		assertTrue("No city area!", citytxt.contains("适用城市："));//断言：适用城市
    }
    
    /**
     * @Name 1143_timecardexplainpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击标准洗车次卡--次卡说明，正常进入使用说明页面，检查页面元素；
     * @FunctionPoint 已登录，我的页面，点击标准洗车次卡--次卡说明，正常进入使用说明页面，检查页面元素；1.7.0，add，次卡说明页；
     */
    public void test_1143_timecardexplainpage(){
    	ToPage.totimecard(local);//进入标准洗车次卡页
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/count_card_explain"));//点击次卡说明
    	//检查页面元素
    	assertTrue("No \"次卡充值消费说明\" text!", local.waitForText("次卡充值消费说明"));//断言：Header文案
		TextView q = (TextView)local.getView("com.ganji.android.ccar:id/anchor2");
		RelativeLayout fu = (RelativeLayout)q.getParent();
		TextView a = (TextView)fu.getChildAt(2);//A字
		TextView title = (TextView)local.getView("com.ganji.android.ccar:id/item_common_instruction_question");//第一条问题标题txt
		String qtxt = q.getText().toString();
		String atxt = a.getText().toString();
		String titletxt = title.getText().toString();
		assertTrue("No \"Q&A\" text!", qtxt.equals("Q：")&&atxt.equals("A："));//断言：Q和A文字
		assertEquals("No detail text!", "什么是次卡？", titletxt);
    }
    
    /**
     * @Name 1144_balancedetailpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击标准洗车次卡--明细，正常进入次卡明细页面，检查页面元素；
     * @FunctionPoint 已登录，我的页面，点击标准洗车次卡--明细，正常进入次卡明细页面，检查页面元素；1.7.0，add，次卡明细页；
     */
    public void test_1144_timecarddetailpage() {
    	ToPage.totimecard(local);//进入标准洗车次卡页面
    	local.sleep(1000);
    	local.clickOnView(local.getView("txt_title_right"));//点击明细按钮
    	//检查页面元素
		assertTrue("No \"次卡明细\" text!", local.waitForText("次卡明细"));//断言：Header文案
		local.searchText("总剩余");
		TextView timecardnum = (TextView)local.getView("com.ganji.android.ccar:id/left_count_card_number");
		String timecardnumtxt = timecardnum.getText().toString();
		assertEquals("No timecardnum txt!", "总剩余"+ChangL.timecard+"次，"+ChangL.timecardbj+"次适用于当前城市", timecardnumtxt);//剩余次数文案，线上还需要修改数量
		TextView carditemname = (TextView)local.getView("com.ganji.android.ccar:id/count_card_detail_item_name");
		assertTrue("No count_card_detail_item_name area!", local.waitForView(carditemname));//断言：次卡名称
		TextView carditemnum = (TextView)local.getView("com.ganji.android.ccar:id/count_card_detail_item_num");
		assertTrue("No count_card_detail_item_num area!", local.waitForView(carditemnum));//断言：次卡数量
		TextView carditemtime = (TextView)local.getView("com.ganji.android.ccar:id/count_card_detail_item_time");
		assertTrue("No count_card_detail_item_time area!", local.waitForView(carditemtime));//断言：次卡数量
    }
    
    /**
     * @Name 1145_couponpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击优惠劵，正常进入优惠劵页面，检查页面元素；
     * @FunctionPoint 已登录，我的页面，点击优惠劵，正常进入优惠劵页面，检查页面元素；2.0.1，update，优惠券页面元素名称改变
     */
    public void test_1145_couponpage() {
    	ToPage.tomycoupon(local);//进入我的优惠券页
//    	local.hideSoftKeyboard();//收起键盘
    	//检查页面元素
    	assertEquals("优惠券", MyAssert.getall_header_txt(local));//断言：Header文案
//		assertTrue("No \"优惠券\" text!", local.waitForText("优惠券"));  ////断言：Header文案（有时候找不到优惠券文案）
//		assertTrue("No \"优惠券\" text!", local.waitForView(local.getView("center_text")));//断言：Header文案
		TextView coupondetail = (TextView)local.getView("txt_title_right");
		String coupondetailtxt = coupondetail.getText().toString();
		assertEquals("No \"使用说明\" button!", "使用说明", coupondetailtxt);//断言：使用说明按钮
		EditText code = (EditText)local.getView("com.ganji.android.ccar:id/txt_code");
		String codetxt = code.getHint().toString();
		assertEquals("No exchange input area!", "请输入优惠券兑换码", codetxt);
		TextView submit = (TextView)local.getView("com.ganji.android.ccar:id/txt_submit");
		String submittxt = submit.getText().toString();
		assertEquals("No exchange submit button!", "兑换", submittxt);
		assertTrue("No coupon price!", local.waitForView(local.getView("txt_price")));//断言：优惠券价格
		assertTrue("No coupon name!", local.waitForView(local.getView("txt_coupon_name")));//断言：优惠券名称
		assertTrue("No coupon summary!", local.waitForView(local.getView("txt_summary")));//断言：优惠券描述
		assertTrue("No coupon time!", local.waitForView(local.getView("txt_time")));//断言：优惠券期限
		assertTrue("No coupon activity name!", local.waitForView(local.getView("txt_activity_name")));//断言：优惠券订单类型
    }
    
    /**
     * @Name 1150_coupondesignpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击优惠劵--使用说明，正常进入优惠劵使用说明页面，检查页面元素；
     * @FunctionPoint 已登录，我的页面，点击优惠劵--使用说明，正常进入优惠劵使用说明页面，检查页面元素；2.0.1，update，优惠券页面元素名称改变
     */
    public void test_1150_coupondesignpage() {
    	ToPage.tomycoupon(local);//进入我的优惠券页
    	local.sleep(1000);
    	local.clickOnView(local.getView("txt_title_right"));//点击使用说明按钮
    	//检查页面元素
		assertTrue("No \"使用说明\" text!", local.waitForText("使用说明"));//断言：Header文案
		TextView q = (TextView)local.getView("com.ganji.android.ccar:id/anchor1");
		RelativeLayout fu = (RelativeLayout)q.getParent();
		TextView a = (TextView)fu.getChildAt(2);//A字
		TextView title = (TextView)fu.getChildAt(1);//第一条问题标题txt
		String qtxt = q.getText().toString();
		String atxt = a.getText().toString();
		String titletxt = title.getText().toString();
		assertTrue("No \"Q&A\" text!", qtxt.equals("Q：")&&atxt.equals("A："));//断言：Q和A文字
		assertEquals("No detail text!", "如何获得和兑换优惠券？", titletxt);
    }
    
    
    /**
     * @Name 1155_redpackagepage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击红包，正常进入红包页面，检查页面元素；
     * @FunctionPoint 已登录，我的页面，点击红包，正常进入红包页面，检查页面元素；
     */
    public void test_1155_redpackagepage() {
    	ToPage.tomyredpackage(local);//进入我的红包页面
    	//检查页面元素
		assertTrue("No \"红包\" text!", local.waitForText("红包"));//断言：Header文案
		TextView redpackagetail = (TextView)local.getView("txt_title_right");
		String redpackagetailtxt = redpackagetail.getText().toString();
		assertEquals("No \"使用说明\" button!", "使用说明", redpackagetailtxt);//断言：使用说明按钮
		TextView redpackagesum = (TextView)local.getView("com.ganji.android.ccar:id/tv_red_package_sum");
		LinearLayout fu = (LinearLayout)redpackagesum.getParent();
		TextView redpackage = (TextView)fu.getChildAt(0);//红包文字区域
		String redpackagetxt = redpackage.getText().toString();//红包文字
		String redpackagesumtxt = redpackagesum.getText().toString();//红包数量文字
		assertTrue("No redpackage price area!", redpackagesumtxt.contains("¥")&&redpackagesumtxt.contains("元"));//断言：红包金额
		assertEquals("No redpackage txt!", "红包: ", redpackagetxt);//断言：红包文字
		assertTrue("No redpackage status area!", local.waitForView(local.getView("tv_red_package_status")));//断言：红包状态
		assertTrue("No redpackage expire time!", local.waitForView(local.getView("tv_expire_time")));//断言：红包有效期
		assertTrue("No redpackage get time!", local.waitForView(local.getView("tv_receive_time")));//断言：红包得到日期
		assertTrue("No redpackage amount!", local.waitForView(local.getView("tv_red_package_amount")));//断言：红包单笔金额
    }
    
    /**
     * @Name 1160_redpackagedesignpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击红包--使用说明，正常进入红包使用说明页面，检查页面元素；
     * @FunctionPoint 已登录，我的页面，点击红包--使用说明，正常进入红包使用说明页面，检查页面元素；
     */
    public void test_1160_redpackagedesignpage() {
    	ToPage.tomyredpackage(local);//进入我的红包页面
    	local.clickOnView(local.getView("txt_title_right"));//点击使用说明按钮
    	//检查页面元素
		assertTrue("No \"使用说明\" text!", local.waitForText("使用说明"));//断言：Header文案
		TextView q = (TextView)local.getView("com.ganji.android.ccar:id/anchor1");
		RelativeLayout fu = (RelativeLayout)q.getParent();
		TextView a = (TextView)fu.getChildAt(2);//A字
		TextView title = (TextView)fu.getChildAt(1);//第一条问题标题txt
		String qtxt = q.getText().toString();
		String atxt = a.getText().toString();
		String titletxt = title.getText().toString();
		assertTrue("No \"Q&A\" text!", qtxt.equals("Q：")&&atxt.equals("A："));//断言：Q和A文字
		assertEquals("No detail text!", "赶集易洗车红包是什么？", titletxt);
    }
    
    /**
     * @Name 1165_myawardpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击奖品，正常进入我的奖品页面，检查页面元素；
     * @FunctionPoint 已登录，我的页面，点击奖品，正常进入我的奖品页面，检查页面元素；
     */
    public void test_1165_myawardpage() {
    	ToPage.tomyaward(local);//进入我的奖品页面
    	//检查页面元素
		assertEquals("No \"我的奖品\" text!", "我的奖品", MyAssert.getall_header_txt(local));//断言：Header文案
		assertTrue("No award picture!", local.waitForView(local.getView("iv_award")));//断言：奖品图片
		assertTrue("No award name!", local.waitForView(local.getView("tv_name")));//断言：奖品名称
    }
    
//    /**
//     * @Name 1170_myprofilepage
//     * @Catalogue 赶集易洗车C端 Android
//     * @Subcatalog 2.0.1
//     * @Grade 中级
//     * @Channel 
//     * @Describe 已登录，我的页面，点击我的资料，正常进入修改个人资料页面，检查页面元素；
//     * @FunctionPoint 已登录，我的页面，点击我的资料，正常进入修改个人资料页面，检查页面元素；2.0.1,delete，没有我的资料入口了
//     */
//    public void test_1170_myprofilepage() {
//    	ToPage.tomyprofile(local);//进入我的资料页面
//		//检查页面元素
//		assertEquals("No \"修改个人资料\" text!", "修改个人资料", MyAssert.getall_header_txt(local));//断言：Header文案
//		assertTrue("No img area!", local.waitForView(local.getView("iv_myself_portrait")));//断言：头像区域
//		TextView name = (TextView)local.getView("iv_myself_name");
//		String nametxt = name.getText().toString();
//		assertTrue("No name area!", "请填写昵称".equals(nametxt)||ChangL.name.equals(nametxt));//断言：昵称文字
//		TextView phone = (TextView)local.getView("iv_myself_phone");//手机号
//		String phonetxt = phone.getText().toString();
//		assertEquals("No phone area!", ChangL.phonenumxing, phonetxt);//断言：手机号
//		assertTrue("No sex area!", local.waitForView(local.getView("man"))&&local.waitForView(local.getView("women")));//断言：性别
//    }
    
    /**
     * @Name 1175_favoriteaddresspage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击常用地址，正常进入常用地址页面，审查页面元素；
     * @FunctionPoint 已登录，我的页面，点击常用地址，正常进入常用地址页面，审查页面元素；
     */
    public void test_1175_favoriteaddresspage() {
    	ToPage.tomyaddress(local);//进入常用地址页面
		//检查页面元素
		assertEquals("No \"常用地址\" text!", "常用地址", MyAssert.getall_header_txt(local));//断言：Header文案
		TextView add = (TextView)local.getView("txt_title_right");
		String addtxt = add.getText().toString();
		assertEquals("No \"添加\" button!", "添加", addtxt);//断言：添加按钮
		assertTrue("No icon!", local.waitForView(local.getView("img_test_channel")));//断言：icon
		ImageView img = (ImageView)local.getView("com.ganji.android.ccar:id/img_test_channel");
		LinearLayout fu = (LinearLayout)img.getParent();
		TextView content = (TextView)fu.getChildAt(1);//添加常用地址说明文案
		String contenttxt = content.getText().toString();
		assertEquals("No content text!", "添加常用地址,轻松享用一键上门洗车服务", contenttxt);//断言：文案
		TextView home = (TextView)local.getView("com.ganji.android.ccar:id/txt_home_label");
		String hometxt = home.getText().toString();//“家”文字
		TextView homeaddress = (TextView)local.getView("com.ganji.android.ccar:id/txt_home");
		String homeaddresstxt = homeaddress.getText().toString();//家地址文字
		assertEquals("No home address area!", "家", hometxt);//断言：家文字
		assertTrue("No home address area!", !homeaddresstxt.isEmpty());//断言：家地址不为空
		TextView company = (TextView)local.getView("com.ganji.android.ccar:id/txt_company_label");
		String companytxt = company.getText().toString();//“公司”文字
		TextView companyaddress = (TextView)local.getView("com.ganji.android.ccar:id/txt_company");
		String companyaddresstxt = companyaddress.getText().toString();//公司地址文字
		assertEquals("No company address area!", "公司", companytxt);//断言：公司文字
		assertTrue("No company address area!", !companyaddresstxt.isEmpty());//断言：公司地址不为空
    }
    
    /**
     * @Name 1180_addfavoriteaddresspage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击常用地址--添加，正常进入添加常用地址页面，审查页面元素；
     * @FunctionPoint 已登录，我的页面，点击常用地址--添加，正常进入添加常用地址页面，审查页面元素；
     */
    public void test_1180_addfavoriteaddresspage() {
    	ToPage.tomyaddress(local);//进入常用地址页面
    	local.clickOnView(local.getView("txt_title_right"));//点击添加
    	if (local.waitForText("不能添加更多常用地址")) {//判断是否添加满5个
    		local.sleep(1000);
    		local.clickOnView(local.getView("txt_home"));//点击家的地址
        	//检查页面元素
    		assertTrue("No \"添加常用地址\" text!", local.waitForText("添加常用地址"));//断言：Header文案
    		assertTrue("No icon!", local.waitForView(local.getView("img_test_channel")));//断言：icon
    		ImageView img = (ImageView)local.getView("com.ganji.android.ccar:id/img_test_channel");
			LinearLayout fu = (LinearLayout)img.getParent();
			TextView content = (TextView)fu.getChildAt(1);//提示文案
			String contenttxt = content.getText().toString();//文案
			assertEquals("No content txt!", "添加常用地址,轻松享用一键上门洗车服务", contenttxt);//断言：是否有提示文案
			String nametxt = MyEntry.favoriteaddressnameinput(local).getText().toString();//名字默认文案
			assertEquals("No address name area!", "家", nametxt);//断言：名字输入框内默认文案
    		TextView address = (TextView)local.getView("txt_address");//断言：地址
    		String addresstxt = address.getText().toString();
    		assertTrue("No address area!", !addresstxt.isEmpty());//判断内容不为空即可
    		TextView etcontent = (TextView)local.getView("et_content");//断言：备注
    		String etcontenttxt = etcontent.getText().toString();
    		assertTrue("No content area!", !contenttxt.isEmpty());//判断内容不为空即可
    		Button submit = (Button)local.getView("com.ganji.android.ccar:id/btn_long");
			String submittxt = submit.getText().toString();//保存按钮文字
			assertEquals("No submit button!", "保存", submittxt);//断言：保存按钮
    	}
    	else {
	    	//检查页面元素
			assertTrue("No \"添加常用地址\" text!", local.waitForText("添加常用地址"));//断言：Header文案
			assertTrue("No icon!", local.waitForView(local.getView("img_test_channel")));//断言：icon
			ImageView img = (ImageView)local.getView("com.ganji.android.ccar:id/img_test_channel");
			LinearLayout fu = (LinearLayout)img.getParent();
			TextView content = (TextView)fu.getChildAt(1);//提示文案
			String contenttxt = content.getText().toString();//文案
			assertEquals("No content txt!", "添加常用地址,轻松享用一键上门洗车服务", contenttxt);//断言：是否有提示文案
			String nametxt = MyEntry.favoriteaddressnameinput(local).getText().toString();//名字默认文案
			assertEquals("No address name area!", "起个好记的名字吧(学校/工厂)", nametxt);//断言：名字输入框内默认文案
			TextView address = (TextView)local.getView("com.ganji.android.ccar:id/txt_address");
			String addresstxt = address.getHint().toString();//地址默认文案
			assertEquals("No address area!", "请您选择地址", addresstxt);//断言：地址输入框内默认文案
			EditText etcontent = (EditText)local.getView("com.ganji.android.ccar:id/et_content");
			String etcontenttxt = etcontent.getHint().toString();//备注默认文案
			assertEquals("No address etcontent area!", "填写备注,方便找到您的车辆", etcontenttxt);//断言：备注输入框内默认文案
			Button submit = (Button)local.getView("com.ganji.android.ccar:id/btn_long");
			String submittxt = submit.getText().toString();//保存按钮文字
			assertEquals("No submit button!", "保存", submittxt);//断言：保存按钮
    	}
    }
    
    /**
     * @Name 1185_sethomeaddresspage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击常用地址--家，正常进入家的添加常用地址页面，审查页面元素；
     * @FunctionPoint 已登录，我的页面，点击常用地址--家，正常进入家的添加常用地址页面，审查页面元素；
     */
    public void test_1185_sethomeaddresspage() {
    	ToPage.tomyaddress(local);//进入常用地址页面
		TextView sethome = (TextView)local.getView("com.ganji.android.ccar:id/txt_home");
		String sethometxt = sethome.getText().toString();//文案
		if (sethometxt.isEmpty()) {
			local.clickOnView(local.getView("txt_home"));//点击设置家的位置
	    	//检查页面元素
			ImageView img = (ImageView)local.getView("com.ganji.android.ccar:id/img_test_channel");
			LinearLayout fu = (LinearLayout)img.getParent();
			TextView content = (TextView)fu.getChildAt(1);//提示文案
			String contenttxt = content.getText().toString();//文案
			assertEquals("No content txt!", "添加常用地址,轻松享用一键上门洗车服务", contenttxt);//断言：是否有提示文案
			assertTrue("No \"添加常用地址\" text!", local.waitForText("添加常用地址"));//断言：Header文案
			assertTrue("No icon!", local.waitForView(local.getView("img_test_channel")));//断言：icon
			EditText name = (EditText)local.getView("com.ganji.android.ccar:id/et_name");
			String nametxt = name.getText().toString();
			assertEquals("No address name area!", "家", nametxt);//断言：家文字
			TextView address = (TextView)local.getView("com.ganji.android.ccar:id/txt_address");
			String addresstxt = address.getHint().toString();//地址默认文案
			assertEquals("No address area!", "请您选择地址", addresstxt);//断言：地址输入框内默认文案
			EditText etcontent = (EditText)local.getView("com.ganji.android.ccar:id/et_content");
			String etcontenttxt = etcontent.getHint().toString();//备注默认文案
			assertEquals("No address etcontent area!", "填写备注,方便找到您的车辆", etcontenttxt);//断言：备注输入框内默认文案
			Button submit = (Button)local.getView("com.ganji.android.ccar:id/btn_long");
			String submittxt = submit.getText().toString();//保存按钮文字
			assertEquals("No submit button!", "保存", submittxt);//断言：保存按钮
		}
		else {
			local.clickOnView(local.getView("txt_home"));//点击设置家的位置
	    	//检查页面元素
			ImageView img = (ImageView)local.getView("com.ganji.android.ccar:id/img_test_channel");
			LinearLayout fu = (LinearLayout)img.getParent();
			TextView content = (TextView)fu.getChildAt(1);//提示文案
			String contenttxt = content.getText().toString();//文案
			assertEquals("No content txt!", "添加常用地址,轻松享用一键上门洗车服务", contenttxt);//断言：是否有提示文案
			assertTrue("No \"添加常用地址\" text!", local.waitForText("添加常用地址"));//断言：Header文案
			assertTrue("No icon!", local.waitForView(local.getView("img_test_channel")));//断言：icon
			EditText name = (EditText)local.getView("com.ganji.android.ccar:id/et_name");
			String nametxt = name.getText().toString();
			assertEquals("No address name area!", "家", nametxt);//断言：家文字
			TextView address = (TextView)local.getView("txt_address");//断言：地址
    		String addresstxt = address.getText().toString();
    		assertTrue("No address area!", !addresstxt.isEmpty());//判断内容不为空即可
    		TextView etcontent = (TextView)local.getView("et_content");//断言：备注
    		String etcontenttxt = etcontent.getText().toString();
    		assertTrue("No content area!", !contenttxt.isEmpty());//判断内容不为空即可
    		Button submit = (Button)local.getView("com.ganji.android.ccar:id/btn_long");
			String submittxt = submit.getText().toString();//保存按钮文字
			assertEquals("No submit button!", "保存", submittxt);//断言：保存按钮
		}
	}
    
    /**
     * @Name 1190_setcompanyaddresspage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击常用地址--公司，正常进入公司的添加常用地址页面，审查页面元素；
     * @FunctionPoint 已登录，我的页面，点击常用地址--公司，正常进入公司的添加常用地址页面，审查页面元素；
     */
    public void test_1190_setcompanyaddresspage() {
    	ToPage.tomyaddress(local);//进入常用地址页面
    	TextView setcompany = (TextView)local.getView("com.ganji.android.ccar:id/txt_company");
		String setcompanytxt = setcompany.getText().toString();//文案
		if (setcompanytxt.isEmpty()) {
			local.clickOnView(local.getView("txt_company"));//点击设置公司的位置
	    	//检查页面元素
			ImageView img = (ImageView)local.getView("com.ganji.android.ccar:id/img_test_channel");
			LinearLayout fu = (LinearLayout)img.getParent();
			TextView content = (TextView)fu.getChildAt(1);//提示文案
			String contenttxt = content.getText().toString();//文案
			assertEquals("No content txt!", "添加常用地址,轻松享用一键上门洗车服务", contenttxt);//断言：是否有提示文案
			assertTrue("No \"添加常用地址\" text!", local.waitForText("添加常用地址"));//断言：Header文案
			assertTrue("No icon!", local.waitForView(local.getView("img_test_channel")));//断言：icon
			EditText name = (EditText)local.getView("com.ganji.android.ccar:id/et_name");
			String nametxt = name.getText().toString();
			assertEquals("No address name area!", "公司", nametxt);//断言：公司文字
			TextView address = (TextView)local.getView("com.ganji.android.ccar:id/txt_address");
			String addresstxt = address.getHint().toString();//地址默认文案
			assertEquals("No address area!", "请您选择地址", addresstxt);//断言：地址输入框内默认文案
			EditText etcontent = (EditText)local.getView("com.ganji.android.ccar:id/et_content");
			String etcontenttxt = etcontent.getHint().toString();//备注默认文案
			assertEquals("No address etcontent area!", "填写备注,方便找到您的车辆", etcontenttxt);//断言：备注输入框内默认文案
			Button submit = (Button)local.getView("com.ganji.android.ccar:id/btn_long");
			String submittxt = submit.getText().toString();//保存按钮文字
			assertEquals("No submit button!", "保存", submittxt);//断言：保存按钮
		}
		else {
			local.clickOnView(local.getView("txt_company"));//点击设置家的位置
	    	//检查页面元素
			ImageView img = (ImageView)local.getView("com.ganji.android.ccar:id/img_test_channel");
			LinearLayout fu = (LinearLayout)img.getParent();
			TextView content = (TextView)fu.getChildAt(1);//提示文案
			String contenttxt = content.getText().toString();//文案
			assertEquals("No content txt!", "添加常用地址,轻松享用一键上门洗车服务", contenttxt);//断言：是否有提示文案
			assertTrue("No \"添加常用地址\" text!", local.waitForText("添加常用地址"));//断言：Header文案
			assertTrue("No icon!", local.waitForView(local.getView("img_test_channel")));//断言：icon
			EditText name = (EditText)local.getView("com.ganji.android.ccar:id/et_name");
			String nametxt = name.getText().toString();
			assertEquals("No address name area!", "公司", nametxt);//断言：公司文字
			TextView address = (TextView)local.getView("txt_address");//断言：地址
    		String addresstxt = address.getText().toString();
    		assertTrue("No address area!", !addresstxt.isEmpty());//判断内容不为空即可
    		TextView etcontent = (TextView)local.getView("et_content");//断言：备注
    		String etcontenttxt = etcontent.getText().toString();
    		assertTrue("No content area!", !contenttxt.isEmpty());//判断内容不为空即可
    		Button submit = (Button)local.getView("com.ganji.android.ccar:id/btn_long");
			String submittxt = submit.getText().toString();//保存按钮文字
			assertEquals("No submit button!", "保存", submittxt);//断言：保存按钮
		}
	}
    
    /**
     * @Name 1195_mycarpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击我的车辆，正常进入我的车辆页面，审查页面元素；
     * @FunctionPoint 已登录，我的页面，点击我的车辆，正常进入我的车辆页面，审查页面元素；
     */
    public void test_1195_mycarpage() {
    	ToPage.tomycar(local);//进入我的页面
    	//检查页面元素
		assertEquals("No \"我的车辆\" text!", "我的车辆", MyAssert.getall_header_txt(local));//断言：Header文案
		TextView add = (TextView)local.getView("txt_title_right");
		String addtxt = add.getText().toString();
		assertEquals("No \"添加\" button!", "添加", addtxt);//断言：添加文案
		assertTrue("No icon!", local.waitForView(local.getView("img_test_channel")));//断言：icon
		ImageView img = (ImageView)local.getView("com.ganji.android.ccar:id/img_test_channel");
		LinearLayout fu = (LinearLayout)img.getParent();
		TextView content = (TextView)fu.getChildAt(1);//添加车辆信息说明文案
		String contenttxt = content.getText().toString();
		assertEquals("No content text!", "添加车辆信息,方便师傅快速找到", contenttxt);//断言：文案
		TextView setcar = (TextView)local.getView("com.ganji.android.ccar:id/txt_car_type");
		String setcartxt = setcar.getText().toString();//获取未设置时的文案
		assertEquals("No set car area!", "设置您的车辆,方便洗车师傅找到车", setcartxt);//未设置车时文案，老有。。
		assertTrue("No car num!", local.waitForView(local.getView("txt_car_plate")));//断言：车牌号
		assertTrue("No car type and color!", local.waitForView(local.getView("txt_car_type")));//断言：车类型和颜色
		assertTrue("No car list!", local.waitForView(local.getView("pull_list")));//断言：list
    }
    
    /**
     * @Name 1200_setcarpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击我的车辆-添加，正常进入添加车辆页面，审查页面元素；
     * @FunctionPoint 已登录，我的页面，点击我的车辆-添加，正常进入添加车辆页面，审查页面元素；
     */
    public void test_1200_setcarpage() {
    	ToPage.tomycar(local);//进入我的车辆页面
    	local.clickOnView(local.getView("txt_title_right"));//判断是否已经添加满5个，点击添加按钮
    	if (local.waitForText("无法添加")) {
    		local.clickOnView(local.getView("com.ganji.android.ccar:id/lay_add_car", 1));//点击下面已保存的一个车辆
    		local.sleep(1500);
	    	//检查页面元素
	    	assertTrue("No \"添加车辆\" text!", local.waitForText("添加车辆"));//断言：Header文案
			assertTrue("No car image!", local.waitForView(local.getView("img_add_car")));//断言：车辆图片
	    	TextView carplate = (TextView)local.getView("com.ganji.android.ccar:id/txt_plate_label");
	    	String carplatetxt = carplate.getText().toString();
	    	assertFalse("No \"京\" area!", carplatetxt.isEmpty());//京字，报警
	    	String carnumtxt = MyEntry.addmycarnuminput(local).getText().toString();//获取文案
	    	assertFalse("No \"车牌号\" area!", carnumtxt.isEmpty());//断言：车牌号输入框不为空
	    	String cartypetxt = MyEntry.addmycartypeinput(local).getHint().toString();//获取车辆品牌文案
	    	assertFalse("No \"车牌品牌\" area!", cartypetxt.isEmpty());//断言：车牌品牌不为空
	    	String carcolortxt = MyEntry.addmycarcolorinput(local).getHint().toString();//获取车辆颜色文案
	    	assertFalse("No \"车牌颜色\" area!", carcolortxt.isEmpty());//断言：车牌颜色不为空
	    	Button submit = (Button)local.getView("com.ganji.android.ccar:id/btn_long");
	    	String submittxt = submit.getText().toString();//保存按钮文字
	    	assertEquals("No save submit button!", "保存", submittxt);//断言：车身颜色输入框
    	}
    	else {
	    	local.sleep(1500);
	    	//检查页面元素
	    	assertTrue("No \"添加车辆\" text!", local.waitForText("添加车辆"));//断言：Header文案
			assertTrue("No car image!", local.waitForView(local.getView("img_add_car")));//断言：车辆图片
	    	TextView carplate = (TextView)local.getView("com.ganji.android.ccar:id/txt_plate_label");
	    	String carplatetxt = carplate.getText().toString();
	    	assertEquals("No \"京\" area!", "京", carplatetxt);//京字
	    	String carnumtxt = MyEntry.addmycarnuminput(local).getHint().toString();//获取默认文案
	    	assertEquals("No \"车牌号\" area!", "选择车牌号", carnumtxt);//断言：车牌号输入框
	    	String cartypetxt = MyEntry.addmycartypeinput(local).getHint().toString();//获取车辆品牌文案
	    	assertEquals("No \"车牌品牌\" area!", "选择车辆品牌", cartypetxt);//断言：车辆品牌输入框
	    	String carcolortxt = MyEntry.addmycarcolorinput(local).getHint().toString();//获取车辆颜色文案
	    	assertEquals("No \"车牌颜色\" area!", "选择车身颜色", carcolortxt);//断言：车身颜色输入框
	    	Button submit = (Button)local.getView("com.ganji.android.ccar:id/btn_long");
	    	String submittxt = submit.getText().toString();//保存按钮文字
	    	assertEquals("No save submit button!", "保存", submittxt);//断言：车身颜色输入框
    	}
	}
    
//    //UI：我的消息页(废弃)
//    public void test_1205_mymessage() {
//    	//进入我的页面
//    	ToPage.tomypage(local);
//    	//点击我的消息
//    	local.clickOnView(local.getView("lay_message"));
//    	local.sleep(1500);
//    	//检查页面元素
//		//断言：Header文案
//		assertTrue("No \"通知\" text!", local.waitForText("通知"));
//		//有消息时
//		if (local.waitForText("亲~您还没有任何的消息哦,请看看别的吧~")) {
//		}
//	}
    
    /**
     * @Name 1210_aboutpage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的页面，点击关于赶集易洗车，正常进入关于页面，审查页面元素；
     * @FunctionPoint 已登录，我的页面，点击关于赶集易洗车，正常进入关于页面，审查页面元素；
     */
    public void test_1210_aboutpage() {
    	ToPage.toaboutmore(local);//进入关于赶集易洗车页面
    	//检查页面元素
		assertEquals("No \"关于\" text!", "关于", MyAssert.getall_header_txt(local));//断言：Header文案
		
		ImageView img = (ImageView)local.getView("com.ganji.android.ccar:id/img_test_channel");
		LinearLayout fu = (LinearLayout)img.getParent();
		TextView ganji = (TextView)fu.getChildAt(1);//赶集易洗车文字
		String ganjitxt = ganji.getText().toString();
		assertEquals("No \"版本号信息\" text!", ChangL.versiontxt, ganjitxt);//断言：文案
//		TextView version = (TextView)local.getView("com.ganji.android.ccar:id/version_name");
//		String versiontxt = version.getText().toString();//版本文字
//		assertEquals("version is error!", "V2.0.1", versiontxt);//断言：版本
//		assertTrue("No version area!", versiontxt.contains("V"));//断言：版本
		LinearLayout checkversion = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_check_version");
		TextView checkversionarea = (TextView)checkversion.getChildAt(0);
		String checkversiontxt = checkversionarea.getText().toString();//版本更新文案
		assertEquals("No version update area!", "版本更新", checkversiontxt);//断言：版本更新
		assertTrue("No version update area!", local.waitForView(local.getView("tv_has_new_version")));//断言：版本更新
		LinearLayout phone = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_phone");
		TextView kefuarea = (TextView)phone.getChildAt(0);
		TextView phonearea = (TextView)phone.getChildAt(1);
		String kefuareatxt = kefuarea.getText().toString();//客服电话文案
		String phoneareatxt = phonearea.getText().toString();//电话文案
		assertEquals("No kefu phone area!", "客服电话:", kefuareatxt);//断言：客服电话
		assertEquals("No kefu phone area!", "4007-335-500", phoneareatxt);//断言：电话
		LinearLayout qqjl = (LinearLayout)local.getView("com.ganji.android.ccar:id/lay_qq");
		TextView qqjlarea = (TextView)qqjl.getChildAt(0);
		TextView qqarea = (TextView)qqjl.getChildAt(1);
		String qqareatxt = qqjlarea.getText().toString();//qq交流文案
		String qqtxt = qqarea.getText().toString();//qq号文案
		assertEquals("No QQ area!", "QQ交流群:", qqareatxt);//断言：qq交流群
		assertEquals("No QQ num!", "335701302", qqtxt);//断言：qq号
		assertTrue("No email!", local.waitForText("邮        箱")&&local.waitForText("xiche_help@ganji.com"));//断言：邮箱
    }
    
    /**
     * @Name 2006_getredpackage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，进入活动页面，领取红包；
     * @FunctionPoint 已登录，进入活动页面，领取红包；
     */
    public void test_2006_getredpackage() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
        
//        File file = new File("D:/lastruntime.txt");
//        String lastgetdate = (FileOperation.txt2String(file));
//        assertEquals(date, lastgetdate);
        
        ToPage.toactionpage(local);//进入活动页面
        local.clickOnText("红包");//点击疯狂抢红包
//        local.sleep(4000);
        local.waitForWebElement(By.xpath("//section[@class='wrap']/div[1]/div/div[1]/a"));
        local.clickOnWebElement(local.getWebElement(By.xpath("//section[@class='wrap']/div[1]/div/div[1]/a"), 0));//点击红包
//        local.sleep(2000);
        local.waitForWebElement(By.xpath("//section[@class='wrap']/div[1]/div/div"));
        WebElement we1 = (WebElement)local.getWebElement(By.xpath("//section[@class='wrap']/div[1]/div/div"), 0);//判断是否领取，第一次才可以这么判断
        String we1txt = we1.getText().toString();
//        local.sleep(1000);
//        local.takeScreenshot("getredpackage");
        if (!we1txt.contains("今天已经发过红包了")) {
       	 	local.waitForWebElement(By.xpath("//section[@class='wrap']/div[1]/div/div/div[1]"));
       	 	WebElement we = (WebElement)local.getWebElement(By.xpath("//section[@class='wrap']/div[1]/div/div/div[1]"), 0);//判断是否领取，第一次才可以这么判断
            String wetxt = we.getText().toString();
            assertEquals("get redpackage failed!", "点击右上角将红包分享至微信，每当好友领取您分享的红包时，您与好友都会获得一定金额的红包作为奖励！", wetxt);
//            assertTrue("get redpackage failed!","点击右上角将红包分享至微信，每当好友领取您分享的红包时，您与好友都会获得一定金额的红包作为奖励！".equals(wetxt));
            local.clickOnView(local.getView("btn_title_left"));//返回两次
            local.clickOnView(local.getView("btn_title_left"));//返回两次
            ToPage.tomyredpackage(local);//进入我的红包页
//            local.sleep(1500);
            local.waitForView(local.getView("com.ganji.android.ccar:id/tv_receive_time"));
            TextView dt1 = (TextView)local.getView("com.ganji.android.ccar:id/tv_receive_time",0);//重新获取最上面的日期区域
            String dttodaytxt1 =dt1.getText().toString();
            TextView gettype1 = (TextView)local.getView("com.ganji.android.ccar:id/tv_red_package_status",0);//获取第一个红包领取类型
            String gettypetxt1 = gettype1.getText().toString();
            assertTrue("get redpackage failed!", date.equals(dttodaytxt1)&&gettypetxt1.equals("红包天天抢不停"));//断言：是否领取成功
         }
    }
    
    /**
     * @Name 2040_searchcaradr
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，上门洗车页，点击车辆停放地址，搜索车辆停放地址；
     * @FunctionPoint 已登录，上门洗车页，点击车辆停放地址，搜索车辆停放地址；
     */
    public void test_2040_searchcaradr() {
    	Log.d("test", "test_2007_searchcaradr  start");
    	ToPage.towashcarpage(local);
//    	local.waitForView(MyEntry.caraddressentry(local));
//    	local.searchText("需要清洗内饰");
    	local.clickOnView(MyEntry.caraddressentry(local));//点击车辆停放地址
//    	local.sleep(1000);
    	local.waitForView(MyEntry.servicediqutxt(local),5000,true);
    	local.searchText("服务覆盖长安街到北五环，以及上地、回龙观地区");
		String contenttxt = MyEntry.servicediqutxt(local).getText().toString();
//		while(contenttxt.isEmpty()){
//			local.sleep(1000);
//			break;
//		}
    	assertTrue("No \"提示文案\" text！ ", !contenttxt.isEmpty());
//    	assertTrue("Current is not searchpage!",local.waitForText("服务覆盖长安街到北五环，以及上地、回龙观地区"));//断言：是否进入搜索页面
    	local.clickOnView(MyEntry.choosecaraddressentry(local));//点击选择车辆停放位置
    	local.clickOnView(MyEntry.searchcaraddressentry(local));//点击搜索车辆停放位置
    	EditText searchcaradd = (EditText)local.getView("com.ganji.android.ccar:id/search_history_et_content");//定位到输入框
		local.enterText(searchcaradd, "上地桥东");//在搜索车辆停放位置输入框输入内容
		local.sleep(3000);
		assertTrue("Current is not ListView!", local.waitForView(local.getView("search_list_half_screen"))); //判断是否出现listview
//		local.sleep(2000);
		local.hideSoftKeyboard();//收起键盘
		ListView list = (ListView)local.getView("com.ganji.android.ccar:id/search_history_listview");
		LinearLayout ll = (LinearLayout)list.getChildAt(2);//点击第三个
		local.clickOnView(ll);//点击第三个
//		local.clickInList(3);//点击第三个
		local.sleep(2000);
    	local.clickOnText("下一步");//点击下一步
//    	local.sleep(2000);
    	local.searchText("备注");
    	local.screenShotNamedCaseName("addressdetail");	
    	assertTrue("Current page is not \"详细位置描述\"！", local.waitForText("详细位置描述"));//断言：是否进入详细位置描述页
    	assertTrue("No MarkText!", local.waitForView(local.getView("lay_edittext_mark"))&&local.waitForView(local.getView("et_content2")));//断言：检查是否有地点和详细地址输入框
    	local.enterText(0, "搜索赶集小区 30号楼3门 333");//输入具体地址
    	local.clickOnText("确定");//点击确定
    	EditText caraddr = (EditText)(MyEntry.caraddressentry(local));//定位到设置车辆位置view
    	String caraddrtext = caraddr.getText().toString();
    	assertTrue("SetCarAddress fail!", local.waitForView(local.getView("et_content"))&&!"车辆停放地址".equals(caraddrtext)&&ChangL.washcarheadertxt.equals(MyAssert.getall_header_txt(local)));//断言：判断是否添加成功，是否跳转回标准洗车页
    	local.clickOnView(MyEntry.caraddressentry(local));//点击后应进入详细位置描述页
    	assertTrue("Current page is not \"详细位置描述\"！", local.waitForText("详细位置描述"));//断言：是否进入详细位置描述页
    	local.clickOnView(local.getView("btn_title_left"));//点击返回
    	local.sleep(2000);
    	assertTrue("Current page is not \"标准洗车\"！", ChangL.washcarheadertxt.equals(MyAssert.getall_header_txt(local)));//断言：是否进入标准洗车页
    }
  
    
    /**
     * @Name 2055_washcarchkinterior
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，选择标准洗车，下单，清理内饰，下单成功，并跳转到我的订单页；
     * @FunctionPoint 已登录，选择标准洗车，下单，清理内饰，下单成功，并跳转到我的订单页；1.7.0,update，新增取消次卡选择流程；2.0.1,update,清洗内饰区域改变；
     */
    public void test_2055_washcarchkinterior() {
    	ToPage.towashcarpage(local);//进入上门洗车页
//    	SetStep.checkinterior(local);//点击清理内饰，默认是未选中的
    	SetStep.setphone(local);//判断是否登录，可省
    	SetStep.setcarinfo(local, "QLNS22");//设置车辆信息
    	SetStep.setcaraddress(local);//设置车辆停放位置
    	SetStep.setwashcartime(local);//设置服务时间
    	SetStep.checkinterior(local);//点击清理内饰，默认是未选中的
    	SetStep.cancletimecard(local);//取消次卡选择
    	local.sleep(600);
    	local.clickOnView(MyEntry.orderbutton(local));//点击立即下单
//    	local.sleep(1500);
    	local.waitForView(local.getView("com.ganji.android.ccar:id/dialog_custiom_select_item_listview"));
    	SetStep.pay(local);//支付
    	TextView interior = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_interior");//判断是否有有清理内饰
    	String interiortext = interior.getText().toString();
    	assertEquals("it is not interior!", "清洗", interiortext);
    	SetStep.payordercancle(local);//扫尾，取消订单，退款
    	ToPage.toorderpage(local);//进入我的订单页
    	SetStep.deleorder(local);//删除订单
    }
    
    /**
     * @Name 2060_washcaruseredpackage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级	
     * @Channel 
     * @Describe 已登录，选择标准洗车，下单，使用红包，下单成功，并跳转到我的订单页；
     * @FunctionPoint 已登录，选择标准洗车，下单，使用红包，下单成功，并跳转到我的订单页；1.7.0,update，新增取消次卡选择流程；
     */
    public void test_2060_washcaruseredpackage() {
    	ToPage.towashcarpage(local);//进入上门洗车页
//    	local.sleep(1000);
    	local.scrollDown();//拉到最下
    	SetStep.cancletimecard(local);//取消次卡选择，判断红包
//    	local.sleep(1000);
    	local.waitForText("现有",1,5000);
    	assertTrue("No red package!", MyAssert.getwashcar_redpackage_txt(local).contains("现有")&&MyAssert.getwashcar_redpackage_txt(local).contains("可用")&&!"现有0.0元，可用0.0元".equals(MyAssert.getwashcar_redpackage_txt(local)));//看是否有红包
    	SetStep.setphone(local);//判断是否登录
    	SetStep.setcarinfo(local, "USERED");//设置车辆信息，可省
    	SetStep.setcaraddress(local);//设置车辆停放位置，可省
    	SetStep.setwashcartime(local);//设置服务时间
    	SetStep.cancletimecard(local);//取消次卡选择
    	CheckBox cb = (CheckBox)local.getView("com.ganji.android.ccar:id/chk_red_package");
    	if (!cb.isChecked())//选中使用红包，默认是选中的
    		local.clickOnView(local.getView("chk_red_package"));
    	TextView price = (TextView)local.getView("txt_price_now");//获取价格
    	String pricetext = price.getText().toString();
    	local.sleep(600);
    	if ("￥0".equals(pricetext)) {//判断使用红包后是否为0元
	    	local.clickOnView(MyEntry.orderbutton(local));//点击立即下单后直接跳转到预约成功页
        	assertTrue("Submit order failure for use red package！--0yuan", local.waitForText("预约成功")&&local.waitForText("红包抵扣"));//是否跳转到预约成功页
    	}
    	else {
    		local.clickOnView(MyEntry.orderbutton(local));//点击立即下单
    		local.sleep(1500);
    		SetStep.pay(local);//支付
        	TextView couponstitle = (TextView)local.getView("com.ganji.android.ccar:id/redpacket_or_coupons_title");
    		String couponstitletxt = couponstitle.getText().toString();//红包抵扣文字
        	assertEquals("submit order failure for use coupon！--no 0yuan", "红包抵扣:", couponstitletxt);//判断是否有红包抵扣字样

    	}
    	SetStep.payordercancle(local);//扫尾，取消订单，退款
    	ToPage.toorderpage(local);//进入我的订单页
    	SetStep.deleorder(local);//删除订单
    }
    
    /**
     * @Name 2065_washcarusecoupon
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，选择标准洗车，下单，使用优惠劵，下单成功，并跳转到我的订单页；
     * @FunctionPoint 已登录，选择标准洗车，下单，使用优惠劵，下单成功，并跳转到我的订单页；1.7.0,update，新增取消次卡选择流程；
     */
    public void test_2065_washcarusecoupon() {
    	ToPage.towashcarpage(local);//进入标准洗车页
    	SetStep.setphone(local);//判断是否登录
    	SetStep.setcarinfo(local, "USECOU");//设置车辆信息
    	SetStep.setcaraddress(local);//设置车辆停放位置
    	SetStep.setwashcartime(local);//设置服务时间
    	SetStep.cancletimecard(local);//取消次卡选择
    	CheckBox timecardcb = (CheckBox)local.getView("com.ganji.android.ccar:id/time_card_enable_switch");
    	while(timecardcb.isChecked()){
    		local.clickOnView(local.getView("txt_coupon_name"));//点击优惠券
    		break;
    	}
//    	local.sleep(1000);
    	local.screenShotNamedCaseName("pic_0001");
    	local.searchText("自动化");
    	ListView list = (ListView)local.getView("list");//优惠券列表
    	LinearLayout couponfirst = (LinearLayout)list.getChildAt(2);//第一个优惠券
    	local.clickOnView(couponfirst);//选择第一个优惠券
    	local.sleep(1500);
    	local.waitForView(local.getView("com.ganji.android.ccar:id/txt_coupon_result"));//已抵用xx元文字区域
    	TextView coupnresult = (TextView)local.getView("com.ganji.android.ccar:id/txt_coupon_result");//已抵用xx元文字区域
    	TextView unavalibleredpage = (TextView)local.getView("com.ganji.android.ccar:id/txt_red_package_unavalible");//红包与优惠券不可同时使用区域
    	String coupnresulttxt = coupnresult.getText().toString();
    	String unavalibleredpagetxt = unavalibleredpage.getText().toString();
    	assertTrue("use coupon failed-no \"已抵用\"!", coupnresulttxt.contains("已抵用"));//看是否选中优惠券
    	assertTrue("use coupon failed-no \"红包与优惠券不可同时使用\"!", unavalibleredpagetxt.contains("红包与优惠券不可同时使用"));//看是否选中优惠券
    	TextView price = (TextView)local.getView("txt_price_now",1);//获取价格
    	String pricetext = price.getText().toString();
    	if ("￥0".equals(pricetext)) {//判断使用红包后是否为0元
	    	local.clickOnView(MyEntry.orderbutton(local));//点击立即下单后直接跳转到预约成功页
        	assertTrue("Submit order failure for use red package！--0yuan", local.waitForText("预约成功")&&local.waitForText("优惠劵抵扣"));//是否跳转到预约成功页
    	}
    	else {
    		local.clickOnView(MyEntry.orderbutton(local));//点击立即下单
//    		local.sleep(1500);
    		local.waitForView(local.getView("com.ganji.android.ccar:id/dialog_custiom_select_item_listview"));
    		SetStep.pay(local);//支付
    		TextView couponstitle = (TextView)local.getView("com.ganji.android.ccar:id/redpacket_or_coupons_title");
    		String couponstitletxt = couponstitle.getText().toString();//优惠劵抵扣文字
        	assertEquals("submit order failure for use coupon！--no 0yuan", "优惠劵抵扣:", couponstitletxt);//判断是否有优惠券抵扣字样
    	}
    	SetStep.payordercancle(local);//扫尾，取消订单，退款
    	ToPage.toorderpage(local);//进入我的订单页
    	SetStep.deleorder(local);//删除订单
    }
    
    /**
     * @Name 2066_washcarusetimecard
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，选择标准洗车，下单，不使用任何优惠，不清理内饰，次卡支付下单成功，并跳转到我的订单页；
     * @FunctionPoint 已登录，选择标准洗车，下单，不使用任何优惠，不清理内饰，次卡支付下单成功，并跳转到我的订单页；1.7.0，add，标准洗车使用次卡支付；
     */
    public void test_2066_washcarusetimecard() {
    	ToPage.towashcarpage(local);//进入标准洗车页
    	SetStep.setphone(local);//判断是否登录
    	local.scrollDown();//往下拉
    	local.waitForView(local.getView("com.ganji.android.ccar:id/time_card_enable_content"));
    	TextView cardcontent = (TextView)local.getView("com.ganji.android.ccar:id/time_card_enable_content");
    	String cardcontenttxt = cardcontent.getText().toString();
    	assertEquals("No timecard!", "当前城市剩余次数"+ChangL.timecardbj+"次", cardcontenttxt);//判断是否有次数
    	CheckBox enableswitch = (CheckBox)local.getView("com.ganji.android.ccar:id/time_card_enable_switch");
    	assertTrue("No select timecard!", enableswitch.isChecked());//判断是否是选中状态
    	local.scrollUp();//往上拉
    	SetStep.setcarinfo(local, "USETIM");//设置车辆信息
    	SetStep.setcaraddress(local);//设置车辆停放位置
    	SetStep.setwashcartime(local);//设置服务时间
		local.clickOnView(MyEntry.orderbutton(local));//点击立即下单
//		local.sleep(1500);
		assertTrue("未成功跳转到订单详情页！", local.waitForText("订单详情"));
		local.waitForView(local.getView("com.ganji.android.ccar:id/wash_count_card"));
		TextView timecardcost = (TextView)local.getView("com.ganji.android.ccar:id/wash_count_card");
		String timecardcosttxt = timecardcost.getText().toString();//次卡消费文字
		LinearLayout fu = (LinearLayout)timecardcost.getParent();
		TextView timecard = (TextView)fu.getChildAt(0);//次卡文案
		String timecardtxt = timecard.getText().toString();
		assertEquals("submit order failure for use timecard！--No \"次卡\" txt！", "次        卡:", timecardtxt);//判断是否有次卡字样
    	assertEquals("submit order failure for use timecard！--cost timecard num error!", "消费1次，剩余"+ChangL.timecarduse+"次", timecardcosttxt);//判断消费次数和剩余次数是否正确
    	SetStep.payorderusetimecardcancle(local);//扫尾，取消订单，退款
    	local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_title_left"));//点击返回
    	ToPage.toorderpage(local);//进入我的订单页
    	SetStep.deleorder(local);//删除订单
    }
    
    /**
     * @Name 2070_washcarproduct
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，选择套餐，下单，不使用任何优惠，下单成功，并跳转到我的订单页；
     * @FunctionPoint 已登录，选择套餐，下单，不使用任何优惠，下单成功，并跳转到我的订单页；
     */
    public void test_2070_washcarproduct() {
    	SetStep.productselect(local);//进入上门洗车页，在选择服务页，选择套餐
		SetStep.setphone(local);//判断是否登录
    	SetStep.setcarinfo(local, "USENUL");//设置车辆信息
    	SetStep.setcaraddress(local);//设置车辆停放位置
    	SetStep.setwashcartime(local);//设置服务时间
    	if (!("现有0.0元，可用0.0元".equals(MyAssert.getwashcar_redpackage_txt(local)))) {//判断有没有红包，不用红包	
    		local.clickOnView(local.getView("chk_red_package"));//取消红包选择
    		assertTrue("cancle redpackage failed", !"现有0.0元，可用0.0元".equals(MyAssert.getwashcar_redpackage_txt(local)));//确认红包是否取消成功
    	}
    	local.sleep(1000);
    	local.clickOnView(MyEntry.orderbutton(local));//点击立即下单
//    	local.sleep(1000);
    	local.waitForView(local.getView("com.ganji.android.ccar:id/dialog_custiom_select_item_listview"));
    	SetStep.pay(local);//支付
    	SetStep.payordercancle(local);//扫尾，取消订单，点击取消订单按钮
    	ToPage.toorderpage(local);//进入我的订单页
    	SetStep.deleorder(local);//删除订单
    }
    
    /**
     * @Name 2075_washcarproductuseredpack
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，选择套餐，使用红包，下单成功，并跳转到我的订单页；
     * @FunctionPoint 已登录，选择套餐，使用红包，下单成功，并跳转到我的订单页；
     */
    public void test_2075_washcarproductuseredpack() {
    	SetStep.productselect(local);//进入上门洗车页，在选择服务页，选择套餐
    	assertTrue("No red package!", MyAssert.getwashcar_redpackage_txt(local).contains("现有")&&MyAssert.getwashcar_redpackage_txt(local).contains("可用")&&!"现有0.0元，可用0.0元".equals(MyAssert.getwashcar_redpackage_txt(local)));//看是否有红包
    	SetStep.setphone(local);//判断是否登录
    	SetStep.setcarinfo(local, "USERED");//设置车辆信息
    	SetStep.setcaraddress(local);//设置车辆停放位置
    	SetStep.setwashcartime(local);//设置服务时间
    	CheckBox cb = (CheckBox)local.getView("com.ganji.android.ccar:id/chk_red_package");
    	if (!cb.isChecked())//选中使用红包，默认是选中的
    		local.clickOnView(local.getView("chk_red_package"));
//    	assertTrue("use red package failed!", !local.waitForText("优惠券与红包不可同时使用"));//看是否选中红包
    	TextView price = (TextView)local.getView("txt_price_now",1);//获取价格
    	String pricetext = price.getText().toString();
    	if ("￥0".equals(pricetext)) {//判断使用红包后是否为0元
    		local.sleep(1500);
	    	local.clickOnView(MyEntry.orderbutton(local));//点击立即下单后直接跳转到预约成功页
        	assertTrue("Submit order failure for use red package！--0yuan", local.waitForText("预约成功")&&local.waitForText("红包抵扣"));//是否跳转到预约成功页
    	}
    	else {
    		local.sleep(1500);
    		local.clickOnView(MyEntry.orderbutton(local));//点击立即下单
//    		local.sleep(1500);
    		local.waitForView(local.getView("com.ganji.android.ccar:id/dialog_custiom_select_item_listview"));
    		SetStep.pay(local);//支付
    		TextView couponstitle = (TextView)local.getView("com.ganji.android.ccar:id/redpacket_or_coupons_title");
    		String couponstitletxt = couponstitle.getText().toString();//红包抵扣文字
        	assertEquals("submit order failure for use coupon！--no 0yuan", "红包抵扣:", couponstitletxt);//判断是否有红包抵扣字样
    	}
    	SetStep.payordercancle(local);//扫尾，取消订单，退款
    	ToPage.toorderpage(local);//进入我的订单页
    	SetStep.deleorder(local);//删除订单
    }
    
  	/**
     * @Name 2080_washcarproductuseconpon
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，选择套餐，使用优惠券，下单成功，并跳转到我的订单页；
     * @FunctionPoint 已登录，选择套餐，使用优惠券，下单成功，并跳转到我的订单页；
  	 */
    public void test_2080_washcarproductuseconpon() {
    	SetStep.productselect(local);//进入标准洗车页，在选择服务页，选择套餐
    	SetStep.setphone(local);//判断是否登录
    	SetStep.setcarinfo(local, "USECOU");//设置车辆信息
    	SetStep.setcaraddress(local);//设置车辆停放位置
    	SetStep.setwashcartime(local);//设置服务时间
    	if (MyAssert.getwashcar_redpackage_txt(local).contains("现有")&&MyAssert.getwashcar_redpackage_txt(local).contains("可用")&&!"现有0.0元，可用0.0元".equals(MyAssert.getwashcar_redpackage_txt(local))) {//判断是否有红包，没有的话直接点击优惠券，有的话先取消红包，再点击优惠券
    		local.sleep(500);
    		local.clickOnView(local.getView("chk_red_package"));//取消红包选择
//    		local.sleep(2000);
    		local.searchText("请选择优惠券");
    		local.clickOnView(local.getView("txt_coupon_name"));//点击优惠券
    	}
    	else {
//    		local.sleep(2000);
    		local.searchText("请选择优惠券");
    		local.clickOnView(local.getView("txt_coupon_name"));//点击优惠券
    	}
//    	local.sleep(1000);
    	local.searchText("自动化");
    	ListView list = (ListView)local.getView("list");//优惠券列表
    	LinearLayout couponfirst = (LinearLayout)list.getChildAt(2);//第一个优惠券
    	local.clickOnView(couponfirst);//选择第一个优惠券
    	local.sleep(1500);
    	local.searchText("需要清洗内饰");
    	TextView coupnresult = (TextView)local.getView("com.ganji.android.ccar:id/txt_coupon_result");
    	TextView unavalibleredpage = (TextView)local.getView("com.ganji.android.ccar:id/txt_red_package_unavalible");
    	String coupnresulttxt = coupnresult.getText().toString();
    	String unavalibleredpagetxt = unavalibleredpage.getText().toString();
    	assertTrue("use coupon failed!1", coupnresulttxt.contains("已抵用"));//看是否选中优惠券
    	assertTrue("use coupon failed!2", unavalibleredpagetxt.contains("红包与优惠券不可同时使用"));//看是否选中优惠券
    	
    	TextView price = (TextView)local.getView("txt_price_now",1);//获取价格
    	String pricetext = price.getText().toString();
    	if ("￥0".equals(pricetext)) {//判断使用红包后是否为0元
	    	local.clickOnView(MyEntry.orderbutton(local));//点击立即下单后直接跳转到预约成功页
        	assertTrue("Submit order failure for use red package！--0yuan", local.waitForText("预约成功")&&local.waitForText("优惠劵抵扣"));//是否跳转到预约成功页
    	}
    	else {
    		local.clickOnView(MyEntry.orderbutton(local));//点击立即下单
//    		local.sleep(1500);
//    		local.waitForView(local.getView("com.ganji.android.ccar:id/dialog_custiom_select_item_listview"));
    		local.waitForText("余额支付");
    		SetStep.pay(local);//支付
    		TextView couponstitle = (TextView)local.getView("com.ganji.android.ccar:id/redpacket_or_coupons_title");
    		String couponstitletxt = couponstitle.getText().toString();//优惠劵抵扣文字
        	assertEquals("submit order failure for use coupon！--no 0yuan", "优惠劵抵扣:", couponstitletxt);//判断是否有优惠券抵扣字样
    	}
    	SetStep.payordercancle(local);//扫尾，取消订单，退款
    	ToPage.toorderpage(local);//进入我的订单页
    	SetStep.deleorder(local);//删除订单
	}

    /**
     * @Name 2081_payorderaotupage
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 中级
     * @Channel 
     * @Describe 已登录，我的订单列表页，点击进入一个现金支付预约成功状态的订单，正常进入预约成功页，检查页面元素（自动分单）；
     * @FunctionPoint 已登录，我的订单列表页，点击进入一个现金支付预约成功状态的订单，正常进入预约成功页，检查页面元素（自动分单）；
     */
    public void test_2081_payorderautopage() {
    	Log.d("test", "test_1022_payorderpage  start");
    	ToPage.towashcarpage(local);//进入上门洗车页
    	SetStep.setphone(local);//判断是否登录
    	SetStep.setcarinfo(local,"NOPAY1");//设置车辆信息
    	SetStep.setcaraddressauto(local);//设置车辆停放位置（自动分单的地址）
    	SetStep.setwashcartimeauto(local);//设置服务时间
    	SetStep.cancletimecard(local);//取消次卡选择
    	local.sleep(1000);
    	local.clickOnView(MyEntry.orderbutton(local));//点击立即下单
//    	local.sleep(3000);
    	local.waitForDialogToOpen();
    	assertTrue("Submit order failure！", local.waitForDialogToOpen());//判断是否弹出选择支付方式的提示
    	while(!local.searchText("选择支付方式")){
    		local.clickOnView(MyEntry.orderbutton(local));//点击立即下单
    	}
//    	local.sleep(1500);
    	SetStep.pay(local);//支付
//    	local.sleep(2000);
//    	local.clickOnView(MyEntry.thefirstorderentry(local));//点击第一个订单进入详情
    	//审查页面元素
    	assertTrue("Current status is not notpay!", local.waitForText("订单详情"));//断言：header文案
//    	assertEquals("Current status is not notpay!", "预约成功", MyAssert.getall_header_txt(local));//断言：header文案，不知道为什么老是取到“我的订单”
    	TextView canclebutton = (TextView)local.getView("com.ganji.android.ccar:id/txt_title_right");
    	String canclebuttontxt = canclebutton.getText().toString();//获取取消订单文字
    	assertEquals("No \"取消订单\" button!", "取消订单", canclebuttontxt);//断言：取消订单按钮
    	TextView successcontent = (TextView)local.getView("com.ganji.android.ccar:id/reservation_successful_content");
    	String successcontenttxt = successcontent.getText().toString();
    	assertEquals("No order content text!", "师傅分配成功，会在预约时间为您服务，请等待。", successcontenttxt);//断言：自动分单成功文案
    	TextView washcategory = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_category");
    	LinearLayout washcategoryfu = (LinearLayout)washcategory.getParent();
    	TextView washcategoryarea = (TextView)washcategoryfu.getChildAt(0);//服务项目文字区域
    	String washcategorytxt = washcategoryarea.getText().toString();
    	assertEquals("No wash_car_time!", "服务项目:", washcategorytxt);//断言：服务项目
    	TextView washcarcontent = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_content");
    	LinearLayout washcarcontentfu = (LinearLayout)washcarcontent.getParent();
    	TextView washcarcontentarea = (TextView)washcarcontentfu.getChildAt(0);//服务内容文字区域
    	String wwashcarcontenttxt = washcarcontentarea.getText().toString();
    	assertEquals("No wash_car_time!", "服务内容:", wwashcarcontenttxt);//断言：服务内容
    	TextView washcartime = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_consume_time");
    	LinearLayout washcartimefu = (LinearLayout)washcartime.getParent();
    	TextView servicetime = (TextView)washcartimefu.getChildAt(0);//服务时间文字区域
    	String servicetimetxt = servicetime.getText().toString();
    	assertEquals("No wash_car_time!", "服务时间:", servicetimetxt);//断言：服务时间
    	TextView caraddress = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_address");
    	LinearLayout caraddressfu = (LinearLayout)caraddress.getParent();
    	TextView caraddressarea = (TextView)caraddressfu.getChildAt(0);//车辆位置文字区域
    	String caraddressareatxt = caraddressarea.getText().toString();
    	assertEquals("No wash_car_address!", "车辆位置:", caraddressareatxt);//断言：车辆位置
    	TextView carnum = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_number");
    	LinearLayout carnumfu = (LinearLayout)carnum.getParent();
    	TextView carnumarea = (TextView)carnumfu.getChildAt(0);//车牌文字区域
    	String carnumareatxt = carnumarea.getText().toString();
    	assertEquals("No wash_car_number!", "车        牌:", carnumareatxt);//断言：车牌
    	TextView cartype = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_type");
    	LinearLayout cartypefu = (LinearLayout)cartype.getParent();
    	TextView cartypearea = (TextView)cartypefu.getChildAt(0);//车型文字区域
    	String cartypeareatxt = cartypearea.getText().toString();
    	assertEquals("No wash_car_type!", "车        型:", cartypeareatxt);//断言：车型
    	TextView interior = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_interior");
    	LinearLayout interiorfu = (LinearLayout)interior.getParent();
    	TextView interiorarea = (TextView)interiorfu.getChildAt(0);//内饰文字区域
    	String interiorareatxt = interiorarea.getText().toString();
    	assertEquals("No wash_interior!", "内        饰:", interiorareatxt);//断言：内饰
    	TextView carprice = (TextView)local.getView("com.ganji.android.ccar:id/wash_car_price");
    	LinearLayout carpricefu = (LinearLayout)carprice.getParent();
    	TextView carpricearea = (TextView)carpricefu.getChildAt(0);//实付款文字区域
    	String carpriceareatxt = carpricearea.getText().toString();
    	assertEquals("No tv_wash_car_price!", "实  付  款:", carpriceareatxt);//断言：实付款
    	SetStep.payordercancle(local);//扫尾，取消订单，退款
    	ToPage.toorderpage(local);//进入我的订单页
    	SetStep.deleorder(local);//删除订单
    }
	
//	/**
//     * @Name 2085_sharetask
//     * @Catalogue 赶集易洗车C端 Android
//     * @Subcatalog 2.0.1
//     * @Grade 高级
//     * @Channel 
//     * @Describe 操作：启动->点击任务”->点击分享->分享到微信朋友圈->判断是否分享成功
//     * @FunctionPoint 操作：启动->点击任务”->点击分享->分享到微信朋友圈->判断是否分享成功
//	 */
//	private void test_2085_sharetask() {
//		ToPage.totaskpage(local);//进入任务页面
//		local.clickOnView(local.getView("com.ganji.android.ccar:id/btn", 3));//点击分享按钮
//		local.clickOnView(local.getView("com.ganji.android.ccar:id/lay_friends"));//点击分享到朋友圈
//		local.sleep(4000);
//		adbDevice.tap(position.findElementById("com.tencent.mm:id/bmp"));//输入框
//		adbDevice.sendText("测试测试测试");//输入内容
//		adbDevice.tap(position.findElementById("com.tencent.mm:id/d4"));//点击发送按钮
//		assertTrue("share task failed!", local.waitForText("亲，分享成功了"));//断言：判断是否分享成功
//	} 
    
	/**
	 * @Name 2095_orderlistmore
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，我的订单页，下拉加载数据；
     * @FunctionPoint 已登录，我的订单页，下拉加载数据；
	 */
	public void test_2095_orderlistmore() {
		ToPage.toorderpage(local);//进入订单页面
//		while (!local.waitForText("无更多内容")) {//往下捯
		ListView listview = (ListView) local.getView("android:id/list");//上拉刷新操作
		int[] location = new int[2];
		listview.getLocationOnScreen(location);
		location[1] = location[1] + listview.getBottom();
		if (local.waitForView(listview)) {//获取上拉加载更多拖动点的坐标
			int newlistcount, listcount = listview.getCount();
			while (true) {
				local.scrollListToLine(listview, listcount);
				local.sleep(500);
				local.dragScreenToUp(250);
	//		  	local.drag(location[0] + 10f, location[0] + 10f,location[1] - 10f, location[0] - 100f, 100);
			  	local.sleep(2000);
	//  		assertFalse("get more balance failed!2", local.waitForText("获取数据失败")&&local.waitForText("加载失败"));
			  	newlistcount = listview.getCount();
			  	if (newlistcount >= listcount+10) {//加载两组就行
			  		break;
			  	}
			  	else
			  		assertEquals("我的订单未加载出10条！", newlistcount, listcount+10);
			}
		}
		int listcount = listview.getCount();//获取当前list数据数量，22
		assertEquals("get more balance failed!", 22, listcount);
}
	
	/**
	 * @Name 2100_cancleordernopay
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，下单成功后，未支付，点击取消订单，取消成功后跳转到订单已取消页；
     * @FunctionPoint 已登录，下单成功后，未支付，点击取消订单，取消成功后跳转到订单已取消页；
	 */
	public void test_2100_cancleordernopay() {
		ToPage.towashcarpage(local);//进入上门洗车页
		SetStep.washcarorder(local);//下单
		local.clickOnView(MyEntry.canclebutton(local));//点击取消
		local.sleep(1500);
		local.clickOnView(MyEntry.thefirstorderentry(local));//点击第一个订单进入详情页
		SetStep.nopayordercancle(local);//取消订单-待付款
	}
	
	/**
	 * @Name 2110_delecancleorder
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，点击订单已取消的订单，删除订单；
     * @FunctionPoint 已登录，点击订单已取消的订单，删除订单；2.0.1，update，删除按钮
	 */
	public void test_2110_delecancleorder() {
		local.waitForText("订单");
		local.clickOnView(MyEntry.myneedentry(local));//进入订单列表页
		local.sleep(1500);
		local.waitForText("订单已取消");
		local.scrollUp();
		SetStep.deleorder(local);
	}
	
	/**
	 * @Name 2115_updateprofile
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，我的页面，点击头像，修改个人资料；
     * @FunctionPoint 已登录，我的页面，点击头像，修改个人资料；
	 */
	public void test_2115_updateprofile() {
		ToPage.tomypage(local);//进入我的页面
		local.clickOnView(MyEntry.profileimg(local));//点击个人头像
		local.clearEditText(MyEntry.profilenameinput(local));//清除输入框内容
		local.enterText(MyEntry.profilenameinput(local), "修改昵称");//修改昵称为“修改昵称”
		local.clickOnView(MyEntry.profilesavebutton(local));//点击保存
//		local.sleep(1000);
		local.searchText("修改昵称");
		local.waitForView(MyEntry.profilenametxt(local));
//		assertTrue("save profile failed!", local.waitForText("修改成功"));//判断是否修改成功
		assertEquals("save profile failed!", "修改昵称", (MyAssert.getmy_name_txt(local)));
//		assertTrue("save profile failed!", "修改昵称".equals(MyAssert.getmy_name_txt(local)));
		//再改回来
		local.clickOnView(MyEntry.profileimg(local));//点击个人头像
		local.clearEditText(MyEntry.profilenameinput(local));//清除输入框内容
		local.enterText(MyEntry.profilenameinput(local), ChangL.name);//修改昵称为“QA测试号”
		local.clickOnView(MyEntry.profilesavebutton(local));//点击保存
		local.searchText(ChangL.name);
		local.sleep(500);
	}
	
//	/**
//	 * @Name 2116_updateprofileimg
//     * @Catalogue 赶集易洗车C端 Android
//     * @Subcatalog 2.0.1
//     * @Grade 高级
//     * @Channel 
//     * @Describe 已登录，我的页面，点击头像，修改个人头像；
//     * @FunctionPoint 已登录，我的页面，点击头像，修改个人头像；
//	 */
//	private void test_2116_updateprofileimg() {
//		ToPage.tomypage(local);//进入我的页面
//		local.clickOnView(MyEntry.profileimg(local));//点击个人头像
//		local.clickOnView(local.getView("com.ganji.android.ccar:id/iv_myself_portrait"));//点击头像区域
////		local.sleep(1000);
//		local.waitForView(local.getView("com.ganji.android.ccar:id/dialog_item_text"));
//		local.clickOnView(local.getView("com.ganji.android.ccar:id/dialog_item_text", 1));//点击拍照
//		local.sleep(5000);
//		Element pia = position.findElementById("com.android.camera:id/v6_shutter_button_internal");//拍照按钮
//		adbDevice.tap(pia);  //点击拍照按钮
//		local.sleep(5000);
//		Element gou = position.findElementById("com.android.camera:id/v6_btn_done");//勾
//		adbDevice.tap(gou);
//		local.sleep(5000);
//		Element apply = position.findElementById("com.miui.gallery:id/wallpaper_apply");//应用按钮
//		adbDevice.tap(apply);//点击应用按钮
//		local.sleep(3000);
//		local.clickOnView(local.getView("com.ganji.android.ccar:id/iv_myself_confirm"));//点击保存按钮
//		assertTrue("save profile failed！", local.waitForText("保存成功"));//断言：是否保存成功
//		local.sleep(500);
//		if(mCamera !=null){ 
//            mCamera.release();// 为其它应用释放摄像头
//            mCamera =null; 
//        } 
//	} 
	
	/**
	 * @Name 2120_balancedetailmore
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，我的页面，点击余额-余额明细，数据多于10条时，下拉刷新；
     * @FunctionPoint 已登录，我的页面，点击余额-余额明细，数据多于10条时，下拉刷新；
	 */
	public void test_2120_balancedetailmore() {
		ToPage.tobalance(local);//进入余额页面
    	local.clickOnView(local.getView("txt_title_right"));//点击余额明细按钮
    	local.sleep(2000);
    	local.waitForText("余额明细");
    	local.searchText("退款");
    	ListView listview = (ListView) local.getView("android:id/list");//上拉刷新操作
        int[] location = new int[2];
        listview.getLocationOnScreen(location);
        location[1] = location[1] + listview.getBottom();
        if (local.waitForView(listview)) {//获取上拉加载更多拖动点的坐标
            int newlistcount, listcount = listview.getCount();
            while (true) {
            	local.scrollListToLine(listview, listcount);
            	local.sleep(500);
            	local.drag(location[0] + 10f, location[0] + 10f,
                        location[1] - 10f, location[0] - 100f, 100);
            	local.sleep(2000);
//            	assertFalse("get more balance failed!2", local.waitForText("获取数据失败")&&local.waitForText("加载失败"));
                newlistcount = listview.getCount();
                if (newlistcount >= listcount+10) {//加载两组就行
                    break;
                }
            }
        }
        int listcount = listview.getCount();//获取当前list数据数量，22
        assertEquals("get more balance failed!", 22, listcount);
//        local.drag(location[0] + 10f, location[0] + 10f,
//                location[1] - 10f, location[0] - 100f, 50);//再拉一下，才出现已经到底了。。。
//        assertTrue("get more balance failed!", local.waitForText("已经到底了"));
////        assertTrue("get more balance failed!", !local.waitForText("加载失败"));//判断是否有加载失败
	}
	
	
	/**
	 * @Name 2121_timecarddetailmore
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，我的页面，点击标准洗车次卡--明细，上拉页面加载数据；
     * @FunctionPoint 已登录，我的页面，点击标准洗车次卡--明细，上拉页面加载数据；1.7.0，add，次卡明细加载数据；
	 */
	public void test_2121_timecarddetailmore() {
		ToPage.totimecard(local);//进入标准洗车次卡页面
    	local.sleep(1000);
    	local.clickOnView(local.getView("txt_title_right"));//点击明细按钮
    	//检查页面元素
    	local.sleep(2000);
    	ListView listview = (ListView) local.getView("android:id/list");//上拉刷新操作
        int[] location = new int[2];
        listview.getLocationOnScreen(location);
        location[1] = location[1] + listview.getBottom();
        if (local.waitForView(listview)) {//获取上拉加载更多拖动点的坐标
            int newlistcount, listcount = listview.getCount();
            while (true) {
            	local.scrollListToLine(listview, listcount);
            	local.sleep(500);
            	local.drag(location[0] + 10f, location[0] + 10f,
                        location[1] - 10f, location[0] - 100f, 100);
            	local.sleep(2000);
//            	assertFalse("get more balance failed!2", local.waitForText("获取数据失败")&&local.waitForText("加载失败"));
                newlistcount = listview.getCount();
                if (newlistcount >= listcount+10) {//加载两组就行
                    break;
                }
            }
        }
        int listcount = listview.getCount();//获取当前list数据数量，22
        assertEquals("加载次卡明细数量不正确!", 22, listcount);
//        local.drag(location[0] + 10f, location[0] + 10f,
//                location[1] - 10f, location[0] - 100f, 50);//再拉一下，才出现已经到底了。。。
//        assertTrue("get more balance failed!", local.waitForText("已经到底了"));
////        assertTrue("get more balance failed!", !local.waitForText("加载失败"));//判断是否有加载失败
	}
	
	/**
	 * @Name 2125_editfavoriteaddress
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，我的页面，点击常用地址--添加，添加或编辑常用地址；
     * @FunctionPoint 已登录，我的页面，点击常用地址--添加，添加或编辑常用地址；
	 */
	public void test_2125_editfavoriteaddress() {
		ToPage.tomyaddress(local);//进入常用地址页面
    	local.clickOnView(local.getView("txt_title_right"));//点击添加
    	if (local.waitForText("不能添加更多常用地址")) {//判断是否添加满5个，编辑地址
    		for (int i=1;i<=2;i++) {
    			local.sleep(1000);
        		local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_content2", 0));//点击第三个地址
//        		EditText name = (EditText)Mylib.getfavoriteaddressname(local);//定义地址名称区域
        		EditText name = (EditText)MyEntry.favoriteaddressnameinput(local);
        		local.clearEditText(name);//清空文字
        		String nametxt = name.getText().toString();
        		while(!nametxt.isEmpty())
        			local.clearEditText(name);//清空文字
//        		local.sleep(1500);
        		if (i==1)
        			local.enterText(MyEntry.favoriteaddressnameinput(local), "修改名称"+i);//修改地址名称
        		else
        			local.enterText(MyEntry.favoriteaddressnameinput(local), "a123"+i);//修改地址名称
        		SetStep.setaddrandcontent(local,"修改地址content");
//        		local.sleep(1500);
        		local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_long"));//点击保存按钮
//        		local.sleep(2000);
        		assertTrue("未成功保存常用地址！", local.waitForText("保存常用地址成功"));
        		local.waitForView(MyEntry.favoriteaddressnametxt(local));
    			local.waitForText("家");
    			local.waitForText("公司");
        		if (i==1) {
//        			local.takeScreenshot("updaddname1");
        			assertEquals("update address name failed!1", "修改名称", MyAssert.getfavoriteaddress_name_txt(local));//断言：判断地址name是否修改成功
//        			assertTrue("update address name failed!1", "修改名称".equals(MyAssert.getfavoriteaddress_name_txt(local)));//断言：判断地址name是否修改成功
        		}
        		else {
//        			local.takeScreenshot("updaddname2");
        			assertEquals("update address name failed!2", "a123", MyAssert.getfavoriteaddress_name_txt(local));//断言：判断地址name是否修改成功
//        			assertTrue("update address name failed!2", "a123".equals(MyAssert.getfavoriteaddress_name_txt(local)));//断言：判断地址name是否修改成功
        		}
        		local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_content2", 0));//点击第三个地址
        		assertTrue("add/edit favoriteaddress content is failed!", "修改地址content".equals(MyAssert.getaddfavoriteaddress_content_txt(local)));//添加地址页，判断备注是否保存成功
        		local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_title_left"));
    		}
    	}
    	else {//添加地址
//			EditText name = (EditText)Mylib.getfavoriteaddressname(local);//定义地址名称区域
    		local.enterText(MyEntry.favoriteaddressnameinput(local), "添加名称");//修改地址名称
			local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_address"));//点击车辆地址
    		local.sleep(1000);
    		local.clickOnView(MyEntry.choosecaraddressentry(local));//点击选择车辆停放位置
        	local.sleep(3000);
        	local.clickInList(3);//任选一个，第三个，海淀区委党校
        	local.sleep(2000);
        	local.clickOnView(local.getView("txt_title_right"));//点击确定
    		local.sleep(1500);
//    		EditText content = (EditText)Mylib.getfavoriteaddresscontent(local);//定义地址名称区域
    		local.enterText(MyEntry.favoriteaddressnameinput(local), "添加地址content");//修改地址描述
    		local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_long"));
    		local.sleep(1500);
//			assertTrue("add address name failed!", "添加名称".equals(MyAssert.favoriteaddressname(local)));//断言：判断地址name是否修改成功
		}
	}
	
	/**
	 * @Name 2130_addoredithomeaddress
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，我的页面，点击常用地址--家，添加家的常用地址；第二次以后是编辑
     * @FunctionPoint 已登录，我的页面，点击常用地址--家，添加家的常用地址；第二次以后是编辑
	 */
	public void test_2130_addoredithomeaddress() {
		ToPage.tomyaddress(local);//进入常用地址页面
		for (int i=1;i<=2;i++) {
	    	local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_home"));//点击家的地址
	    	local.searchButton("保存");
	    	if (i==1){
	    		SetStep.setaddrandcontent(local,"修改home地址content");//设置家地址和备注
//	    		local.searchText("修改home地址content");
	    	}
	    	else{
	    		SetStep.setaddrandcontent(local,"海淀区温泉苗圃");//设置家地址和备注
//	    		local.searchText("海淀区温泉苗圃");
	    	}
	    	String homeaddress = MyAssert.getaddhomeaddress_address_txt(local);//获取家地址文字
	    	String homeaddresscontent = MyAssert.getaddhomeaddress_content_txt(local);//获取家地址备注文字
			local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_long"));//保存
			local.sleep(1000);
			local.searchText(MyAssert.getfavoriteaddress_home_txt(local));
//			assertTrue("add/edit homeaddress is failed!", MyAssert.getfavoriteaddress_home_txt(local).equals(homeaddress));//添加地址页，判断备注是否保存成功
			assertEquals("add/edit homeaddress is failed!", MyAssert.getfavoriteaddress_home_txt(local), homeaddress);//添加地址页，判断备注是否保存成功
			local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_home"));//点击家的地址
			local.sleep(1000);
			if (i==1)
				assertTrue("add/edit homeaddress content is failed!", "修改home地址content".equals(homeaddresscontent));//添加地址页，判断备注是否保存成功
			else
				assertTrue("add/edit homeaddress content is failed!", "海淀区温泉苗圃".equals(homeaddresscontent));//添加地址页，判断备注是否保存成功
    		local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_title_left"));//返回常用地址页
		}
	}
	
	/**
	 * @Name 2135_addoreditcompanyaddress
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，我的页面，点击常用地址--公司，添加公司的常用地址；第二次以后是编辑
     * @FunctionPoint 已登录，我的页面，点击常用地址--公司，添加公司的常用地址；第二次以后是编辑
	 */
	public void test_2135_addoreditcompanyaddress() {
		ToPage.tomyaddress(local);//进入常用地址页面
		for (int i=1;i<=2;i++) {
	    	local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_company"));//点击公司的地址
	    	if (i==1)
	    		SetStep.setaddrandcontent(local,"修改company地址content");//设置公司地址和备注
	    	else
	    		SetStep.setaddrandcontent(local,"海淀区温泉苗圃");//设置公司地址和备注
	    	String companyaddress = MyAssert.getaddcompanyaddress_address_txt(local);//获取公司地址文字
	    	String companyaddresscontent = MyAssert.getaddcompanyaddress_content_txt(local);//获取家地址备注文字
			local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_long"));//保存
			local.sleep(1500);
			assertTrue("add/edit companyaddress is failed!", MyAssert.getfavoriteaddress_company_txt(local).equals(companyaddress));//添加地址页，判断备注是否保存成功
			local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_company"));//点击公司的地址
			local.sleep(1000);
			if (i==1)
				assertTrue("add/edit homeaddress content is failed!", "修改company地址content".equals(companyaddresscontent));//添加地址页，判断备注是否保存成功
			else
				assertTrue("add/edit homeaddress content is failed!", "海淀区温泉苗圃".equals(companyaddresscontent));//添加地址页，判断备注是否保存成功
    		local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_title_left"));//返回常用地址页
		}
	}
	
	/**
	 * @Name 2140_editoreditcar
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，我的页面，点击我的车辆--添加，添加/编辑车辆；
     * @FunctionPoint 已登录，我的页面，点击我的车辆--添加，添加/编辑车辆；
	 */
	public void test_2140_editoreditcar() {
		ToPage.tomycar(local);//进入我的车辆页面
		for (int i=1;i<=2;i++) {
			local.sleep(2000);
			local.clickOnView(local.getView("com.ganji.android.ccar:id/lay_add_car",2));//点击第三个车辆
//			local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_title_right"));//点击添加按钮
	    	local.clickOnView(local.getView("txt_plate_label"));//选择地区
	    	local.sleep(2000);
	    	Random r = new Random();//任意选择一个牌子类型
	    	int carplate = r.nextInt(35);
	    	local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_car_plate", carplate));
	    	local.clearEditText(0);//输入车牌号，判断一下，如果为first手机号，输入车牌号FIRSTA，不是的 话就TESTED
	    	local.enterText(0, "ADDCAR");
	    	local.clickOnView(local.getView("txt_car_type"));//点击选择车辆品牌
//	    	local.sleep(1000);
	    	local.waitForView(local.getView("row_title"));
//	    	ListView type = (ListView)local.getView("com.ganji.android.ccar:id/gv_car_plate");//车类型
//	    	RelativeLayout cartype5 = (RelativeLayout)type.getChildAt(5);//品牌第五个
//	    	RelativeLayout cartype4 = (RelativeLayout)type.getChildAt(4);//品牌第五个
//	    	ListView plate = (ListView)local.getView("com.ganji.android.ccar:id/lv_car_plate");//车系
//	    	LinearLayout carplate2 = (LinearLayout)plate.getChildAt(2);//车系第2个
//	    	LinearLayout carplate4 = (LinearLayout)plate.getChildAt(4);//车系第4个
	    	
	    	if (i==1)
		    	local.clickOnView(local.getView("row_title", 5));//选择品牌，第五个为例
//	    		local.clickInList(5);
	    	else
	    		local.clickOnView(local.getView("row_title", 4));//选择品牌，第4个为例
//	    		local.clickInList(4);
	    	local.sleep(2000);
	    	if (i==1)
		    	local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_car_plate", 0));//选择车系，第二个为例
	    	else
		    	local.clickOnView(local.getView("com.ganji.android.ccar:id/txt_car_plate", 3));//选择车系，第二个为例
	    	local.sleep(2000);
	    	local.clickOnView(local.getView("txt_car_color"));//选择车身颜色
//		   	local.clickOnText("选择车身颜色");
//	    	ListView type = (ListView)local.getView("com.ganji.android.ccar:id/gv_car_plate");//车类型
	    	if (i==1)
	    		local.clickOnView(local.getView("txt_car_plate", 6));//选择车身颜色，以第六个为例
//	    		local.clickInList(6);//选择车身颜色，以第六个为例
	    	else
	    		local.clickOnView(local.getView("txt_car_plate", 4));//选择车身颜色，以第4个为例
//	    		local.clickInList(4);
	    	local.sleep(1000);
	    	String carnum = MyAssert.getaddfavoritecar_carnum_txt(local);//获取填写的车牌号
	    	String typecolor = MyAssert.getaddfavoritecar_cartypecolor_txt(local);//获取填写的车类型和颜色
	    	local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_long"));//点击保存
	    	local.sleep(1500);
	    	assertTrue("save carnum failed in mycarpage!", MyAssert.getmycar_carnum_txt(local).equals(carnum));//断言：判断车牌号是否保存成功
	    	assertTrue("save cartype and color failed in mycarpage!", MyAssert.getmycar_cartype_txt(local).equals(typecolor));//断言：判断车牌类型 和颜色是否保存成功
		}
	}
	
	/**
	 * @Name 2145_changephone
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，上门洗车页，点击手机号，修改手机号；
     * @FunctionPoint 已登录，上门洗车页，点击手机号，修改手机号；
     */
    public void test_2145_changephone() {
    	ToPage.towashcarpage(local);//进入上门洗车页
    	SetStep.setphone(local);//确认是否登录，没登录先登录
    	local.clickOnView(MyEntry.phoneentry(local));//再次点击手机号区域
    	local.waitForView(local.getView("com.ganji.android.ccar:id/btn_datetime_sure"));
    	local.clickOnView(local.getView("btn_datetime_sure"));//点击确定
    	local.enterText(0, ChangL.firstphonenum);//输入手机号
    	local.enterText(1, ChangL.password);//输入密码
    	local.clickOnButton("登录");//点击登录
    	local.sleep(2000);//断言：是否更换成功
    	assertTrue("Change phonenumber failed！", local.waitForText(ChangL.washcarheadertxt)&&local.waitForText(ChangL.firstphonenum));
//    	//更换回来
//    	local.clickOnView(MyEntry.phoneentry(local));//再次点击手机号区域
//    	local.clickOnView(local.getView("btn_datetime_sure"));//点击确定
//    	local.enterText(0, MyEntry.phonenum(local));//输入手机号
//    	local.enterText(1, MyEntry.password(local));//输入密码
//    	local.clickOnButton("登录");//点击登录
    }
	  
    /**
     * @Name 2150_firstoneyuan
     * @Catalogue 赶集易洗车C端 Android
     * @Subcatalog 2.0.1
     * @Grade 高级
     * @Channel 
     * @Describe 已登录，使用未购买过的手机号和车牌号，检查价格；
     * @FunctionPoint 已登录，使用未购买过的手机号和车牌号，检查价格；
     */
    public void test_2150_firstoneyuan() {
    	ToPage.towashcarpage(local);//进入标准洗车页面
    	EditText phone = (EditText)(MyEntry.phoneentry(local));//获取手机号输入框中的内容
    	String phonenum = phone.getText().toString();
    	//判断当前是否登录状态
		if (!"您的联系方式".equals(phonenum)) {//已登录
    		if (!ChangL.firstphonenum.equals(phonenum)) {//判断当前登录手机号是否为首次使用的手机号
            	local.clickOnView(MyEntry.phoneentry(local));//更换手机号
            	local.waitForView(local.getView("com.ganji.android.ccar:id/btn_datetime_sure"));
            	local.clickOnView(local.getView("btn_datetime_sure"));//点击确定
            	local.enterText(0, ChangL.firstphonenum);//输入手机号
            	local.enterText(1, ChangL.password);//输入密码
            	local.clickOnButton("登录");//点击登录
            	local.sleep(2000);
        	}
    	}
    	else {//未登录
    		local.clickOnView(MyEntry.phoneentry(local));//登录，点击您的联系方式
        	local.sleep(3000);
        	local.enterText(0, ChangL.firstphonenum);//输入手机号
        	local.enterText(1, ChangL.password);//输入密码
        	local.clickOnButton("登录");
    	}
    	SetStep.setcarinfofirst(local);//设置车辆信息
    	TextView oneyuan = (TextView)local.getView("com.ganji.android.ccar:id/txt_price_now",1);
    	String oneyuantxt = oneyuan.getText().toString();//1元文字
    	assertEquals("This is the first time but not 1 yuan!", "￥1", oneyuantxt);//判断是否为1元
    	local.clickOnView(MyEntry.phoneentry(local));//换回非first手机号
    	local.sleep(3000);
    	local.clickOnView(local.getView("btn_datetime_sure"));//点击确定
    	local.enterText(0, ChangL.phonenum);//输入手机号
    	local.enterText(1, ChangL.password);//输入密码
    	local.clickOnButton("登录");
    }
	  
	 /**
	  * @Name 2152_selectcity
	  * @Catalogue 赶集易洗车C端 Android
	  * @Subcatalog 2.0.1
	  * @Grade 高级
	  * @Channel 
	  * @Describe 已登录，选择其他服务地区，正常切换；
	  * @FunctionPoint 已登录，选择其他服务地区，正常切换；2.0.1，update，新增多城市
	  */
	 public void test_2152_selectcity() {
	// 	local.sleep(3000);
	 	local.waitForActivity("CHomeActivity");
	 	local.clickOnView(MyEntry.selectservicecityentry(local));//点击城市按钮
	 	local.sleep(2000);
		local.clickOnText(ChangL.citychange);//切换城市，点击重庆
		local.sleep(3000);
		local.assertCurrentActivity("Not go to other city successful！", "CHomeActivity");//断言：判断是否切换成功
		assertEquals("Not go to other city successful!", "赶集易洗车", MyAssert.getall_header_txt(local));
		assertTrue("Not go to other city successful!", local.waitForText("赶集易洗车")&&local.waitForText(ChangL.citychange));
//		TextView city = (TextView)MyEntry.selectservicecityentry(local).getChildAt(0);//文字
	    String str2=MyEntry.selectservicecityentry(local).getText().toString();
	    assertEquals("Current city is not "+ChangL.citychange+"!", ChangL.citychange, str2);//判断当前选择城市是否为所选城市
 }  
 
	 /**
	  * @Name 2153_changecity
	  * @Catalogue 赶集易洗车C端 Android
	  * @Subcatalog 2.0.1
	  * @Grade 高级
	  * @Channel 
	  * @Describe 已登录，选择其他服务地区后，重新打开程序，弹出切换dialog，正常切换；
	  * @FunctionPoint 已登录，选择其他服务地区后，重新打开程序，弹出切换dialog，正常切换；2.0.1,add，新增切换到北京
	  */
	 public void test_2153_changecity() {
	 	local.waitForActivity("CHomeActivity");
	 	if (local.waitForDialogToOpen()){
	 		assertTrue("未弹出切换城市对话框！", local.searchText("切换城市"));
		 	TextView changecity = (TextView)local.getView("com.ganji.android.ccar:id/txt_content");//对话框上文案
		 	String changecitytxt = changecity.getText().toString();
		 	assertEquals("切换城市文案不正确！", "系统定位到您在（北京），是否切换？", changecitytxt);
		 	local.waitForView(local.getView("com.ganji.android.ccar:id/btn_datetime_sure"));
		 	local.clickOnView(local.getView("com.ganji.android.ccar:id/btn_datetime_sure"));//点击切换按钮
		 	local.waitForText("城市切换成功");
		 	local.sleep(500);
		 	TextView currentcity = (TextView)local.getView("com.ganji.android.ccar:id/home_city_btn");//获取当前城市
		 	String currentcitytxt = currentcity.getText().toString();
		 	assertEquals("当前城市不是北京！", "北京", currentcitytxt);
	 	}
	 	else{//未开启定位的时候
	 		TextView city = (TextView)local.getView("com.ganji.android.ccar:id/home_city_btn");//城市文字
	 		String cityname = city.getText().toString();
	 		assertEquals("当前城市不是重庆！", ChangL.citychange, cityname);
	 		local.clickOnView(city);//点击重庆
	 		local.clickOnText("北京");//点击北京
	 		String citynamebj = city.getText().toString();
	 		assertEquals("当前城市不是北京！", ChangL.citychange, citynamebj);
	 	}
	 }

	  /**
	   * @Name test_2155_logout
	   * @Catalogue 赶集易洗车C端 Android
	   * @Subcatalog 2.0.1
	   * @Grade 高级
	   * @Channel 
	   * @Describe 退出，已登录，我的页面，点击退出；
	   * @FunctionPoint 退出，已登录，我的页面，点击退出；
	   */
	  public void test_2155_logout() {
	  	SetStep.logout(local);
	  }
	    
    
}