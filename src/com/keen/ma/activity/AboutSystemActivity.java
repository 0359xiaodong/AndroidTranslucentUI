package com.keen.ma.activity;

import android.os.Bundle;

import com.keen.ma.MyApplication;
import com.keen.ma.R;

/**
 * ��������
 * @author KEEN
 * @lastChangeTime 2014��8��22�� ����9:27:14
 */
public class AboutSystemActivity extends BaseActivity {

	private MyApplication myApp = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myApp = (MyApplication)getApplication();
		myApp.addActivity(this);
		setContentLayout(R.layout.activity_about);
		setTitleText("��������");
	}
	
}
