package com.keen.ma.utils;

import android.os.Environment;

/**
 * ������
 * @author KeenW
 * @lastChangeTime 2014-8-24����3:00:13
 */
public class AppConstants {
	
	/*#########################�������ͳ���###############################*/
	
	public static final String SDCARD_BATH_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

	/*#########################ħ����Ƭ��س���###############################*/
	
	public static final String AVIARY_KEY = "e87969babcc74d24";
	public static final String AVIARY_SECRET = "d396a47bf508082f";
	
	/*#########################Sqlite���ݿ���س���###############################*/
	
	public static final String DB_NAME = "keenW.db";
	public static final int DB_VERSION = 1;	//���ݿ�汾��
	public static final String TABLE_USER = "user";//�û��������û���������
	
	
	
	
	
}
