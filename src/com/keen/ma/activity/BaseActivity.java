package com.keen.ma.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.keen.ma.MyApplication;
import com.keen.ma.R;
import com.keen.ma.customui.MyCircleImageView;

/**
 * 
 * ����Activity,�����Activity���̳��Դ���
 * 
 * @author KEEN
 * @lastChangeTime 2014��8��19�� ����3:22:28
 */

@SuppressLint("Registered")
public class BaseActivity extends Activity {

	private MyApplication myApp = null;
	private SharedPreferences sp = null;
	
	private View contentView = null;
	private LinearLayout rootContent = null;
	private TextView titleTV;
	private MyCircleImageView leftImageView, rightImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myApp = (MyApplication)getApplication();
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		// contentViewҪ������
		setContentView(R.layout.activity_base);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.activity_base_headview);
		titleTV = (TextView) findViewById(R.id.title);
		titleTV.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		leftImageView = (MyCircleImageView) findViewById(R.id.head_left_btn);
		rightImageView = (MyCircleImageView) findViewById(R.id.head_right_btn);

		rootContent = (LinearLayout) findViewById(R.id.contentView);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sp = getSharedPreferences("KeenW", Context.MODE_PRIVATE);
		int num = sp.getInt("wallPaperNumber", 0);
		myApp.setCurrentWallPaper(this, num);
	}
	
	// �������ò����ļ�
	protected void setContentLayout(int viewId) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		contentView = inflater.inflate(viewId, null);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		contentView.setLayoutParams(layoutParams);
		if (null != rootContent) {
			rootContent.addView(contentView);
		}
	}

	// ���ñ�����
	protected void setTitleText(String titleText) {
		titleTV.setText(titleText);
	}

	// �������IamgeView�ļ���
	protected void setLeftIamgeViewListener(OnClickListener listener) {
		leftImageView.setOnClickListener(listener);
	}

	protected void setLeftImageViewResource(int drawableId) {
		leftImageView.setImageResource(drawableId);
	}

	protected void setLeftIamgeViewHide() {
		leftImageView.setVisibility(View.GONE);
	}

	protected void setLeftIamgeViewShow() {
		leftImageView.setVisibility(View.VISIBLE);
	}

	// �����ұ�IamgeView�ļ���
	protected void setRightIamgeViewListener(OnClickListener listener) {
		rightImageView.setOnClickListener(listener);
	}

	protected void setRightImageViewResource(int drawableId) {
		rightImageView.setImageResource(drawableId);
	}

	protected void setRightIamgeViewHide() {
		rightImageView.setVisibility(View.GONE);
	}

	protected void setRightIamgeViewShow() {
		rightImageView.setVisibility(View.VISIBLE);
	}

}
