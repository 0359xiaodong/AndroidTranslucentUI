package com.keen.ma.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.keen.ma.MyApplication;
import com.keen.ma.R;
import com.keen.ma.utils.ToolUtils;

/**
 * ��������
 * 
 * @author KEEN
 * @lastChangeTime 2014��8��22�� ����3:18:34
 */
public class FeedbackActivity extends BaseActivity {

	private EditText feedbackET;
	private Button feedbackSubmitBtn;
	private MyApplication myApp = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myApp = (MyApplication)getApplication();
		myApp.addActivity(this);
		setContentLayout(R.layout.activity_feedback);
		setTitleText("��������");

		feedbackET = (EditText) findViewById(R.id.feedback_content);
		feedbackSubmitBtn = (Button) findViewById(R.id.feedback_submit_btn);
		feedbackSubmitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (ToolUtils.isNullString(feedbackET.getText().toString()
						.trim())) {
					Toast.makeText(FeedbackActivity.this, "�������뷴������",
							Toast.LENGTH_SHORT).show();
					return;
				} else {
					Toast.makeText(FeedbackActivity.this, "�ύ�����ɹ����ǳ���л���ı��������",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
