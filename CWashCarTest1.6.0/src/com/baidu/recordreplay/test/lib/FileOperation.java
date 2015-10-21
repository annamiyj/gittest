package com.baidu.recordreplay.test.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;




public class FileOperation {
    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String txt2String(File file){
        BufferedReader reader = null;
    	
        String result = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result = result + "\n" +s;
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
    public static void main(String[] args){
        File file = new File("D:/lastruntime.txt");
        System.out.println(txt2String(file));
    }
}