package com.keen.ma.db;

import com.keen.ma.utils.AppConstants;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * ���ر����û�����
 * @author KEEN
 * @lastChangeTime 2014��8��25�� ����9:28:46
 */
public class DBHelper extends SQLiteOpenHelper{

	public DBHelper(Context context) {
		super(context, AppConstants.DB_NAME, null, AppConstants.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/*����user��*/
		String createTableUserSql = "create table " + AppConstants.TABLE_USER + " ( userId  varchar(50) primary key,"
									+ "userNickName varchar(40) not null,userPassword varchar(40) not null,userPhone char(11) not null unique,"
									+ "userEmail varchar(60) not null unique,userImagePath varchar(200),userLongitude double,userLatitude double )"; 
		db.execSQL(createTableUserSql);
		System.out.println("=================�������ݿ�ɹ�==================================");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
