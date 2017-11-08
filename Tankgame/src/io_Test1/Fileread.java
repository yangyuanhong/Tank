package io_Test1;

import java.io.File;
import java.io.IOException;

public class Fileread {
public static void main(String[] args) {
//	
//	File  f=new File ("D:\\JAVA\\briup\\workspace\\File\\worde.txt");
//	System.out.println("文件路途"+f.getAbsolutePath());
//	System.out.println("文件大小"+f.length());
//	System.out.println("是否可读"+f.canRead());
//	File fi=new File ("D:\\JAVA\\briup\\workspace\\File\\ff\\aa.txt");
//	if(!fi.exists())
//	{
//		try {
//			fi.createNewFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}else {
//		System.out.println("文件存在，不能创建");
//	}
	File fiw=new File ("D:\\JAVA\\briup\\workspace");
	if(fiw.isDirectory())
	{
		System.out.println("文件夹存在！");
		File [] list=fiw.listFiles();
		for (int i = 0; i < list.length; i++) {
			System.out.println("文件名:"+list[i].getName());
		}
	}else {
	
			fiw.mkdir();
		
	}
}
}
