package com.keen.ma.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.keen.ma.MyApplication;
import com.keen.ma.R;
import com.keen.ma.customui.MyCircleImageView;
import com.keen.ma.utils.ToolUtils;

/**
 * @author KEEN
 * @lastChangeTime 2014��8��13�� ����2:49:16
 */
public class LoginActivity extends Activity implements OnClickListener{

	private MyApplication myApp = null;
	private SharedPreferences sp = null;
	private TextView forgetPwdTV;
	private MyCircleImageView createNewAccountView;
	private Button loginBtn;
	private EditText accountET,passwordET;
	private boolean accountIsValid = false;
	private boolean pwdIsValid = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		myApp = (MyApplication)getApplication();
		initUI();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sp = getSharedPreferences("KeenW", Context.MODE_PRIVATE);
		int num = sp.getInt("wallPaperNumber", 0);
		myApp.setCurrentWallPaper(this, num);
	}
	
	/**
	 * ��ʼ���ؼ�
	 */
	private void initUI(){
		
		forgetPwdTV = (TextView)findViewById(R.id.forget_pwd_tv);
		forgetPwdTV.setOnClickListener(this);
		createNewAccountView = (MyCircleImageView)findViewById(R.id.create_account);
		createNewAccountView.setOnClickListener(this);
		loginBtn = (Button)findViewById(R.id.login_btn);
		loginBtn.setOnClickListener(this);
		
		accountET = (EditText)findViewById(R.id.account_et);
		accountET.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				accountIsValid = ToolUtils.checkAccountType(s.toString());
				System.out.println("========================"+accountIsValid);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(accountIsValid){
					accountET.setBackgroundResource(R.drawable.login_edittext_valid_bg);
				}else
					accountET.setBackgroundResource(R.drawable.login_edittext_invalid_bg);
			}
		});
		
		passwordET = (EditText)findViewById(R.id.password_et);
		passwordET.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				pwdIsValid = ToolUtils.checkPasswordType(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(pwdIsValid){
					passwordET.setBackgroundResource(R.drawable.login_edittext_valid_bg);
				}else
					passwordET.setBackgroundResource(R.drawable.login_edittext_invalid_bg);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.forget_pwd_tv:
			startActivity(new Intent(LoginActivity.this,ForgetPwdActivity.class));
			break;
		case R.id.create_account:
			startActivity(new Intent(LoginActivity.this,CreateAccountActivity.class));
			break;
		case R.id.login_btn:
			checkLogin();
			break;
		
		default:
			break;
		}
	}

	/**
	 * ��¼��֤
	 */
	private void checkLogin(){
		
		if(!accountIsValid){//�˻�����ȷ
			accountET.setError(Html.fromHtml("<font size=5 color=red>"+"�˻���ʽ����ȷ"+"</font>"));
			accountET.requestFocus();
			return;
		}else if(!pwdIsValid){//���벻��ȷ
			passwordET.setError(Html.fromHtml("<font size=5 color=red>"+"���볤�Ȳ���С��8"+"</font>"));
			passwordET.requestFocus();
			return;
		}/*else if(ToolUtils.isNullString(passwordET.getText().toString())){//�û���������֤ʧ��
			accountET.setError(Html.fromHtml("<font size=5 color=red>"+"��¼ʧ�ܣ������������˻�����"+"</font>"));
			accountET.requestFocus();
			return;
		}*/else {//�û���������֤�ɹ�
			startActivity(new Intent(LoginActivity.this,HomeActivity.class));
			finish();
		}
	}

}
