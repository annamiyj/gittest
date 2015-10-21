package com.baidu.recordreplay.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import static org.junit.Assert.assertTrue;


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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.io.BufferedWriter;
import java.io.FileWriter;


import com.baidu.recordreplay.test.lib.FileOperation;

public class test {
	
	public void test_2006_getredpackage() {
	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
	  String date = df.format(new Date());// new Date()为获取当前系统时间
	  
	  File file = new File("D:/lastruntime.txt");
	  String xx = FileOperation.txt2String(file);
	  System.out.println(xx);
	}
	  
}


//public class test {
//	
//	public static void main(String[] args){
//        File file = new File("D:/lastruntime.txt");
//        System.out.println(FileOperation.txt2String(file));
//    }

//}
