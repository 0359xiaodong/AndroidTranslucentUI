package com.keen.ma.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.keen.ma.R;
import com.keen.ma.utils.AutoSendSMS;
import com.keen.ma.utils.ToolUtils;

/**
 * �ֻ��һ������Fragment
 * @author KEEN
 * @lastChangeTime 2014��8��19�� ����11:44:02
 */
public final class FindPwdByPhoneFragment extends Fragment implements
		OnClickListener {

	private EditText phoneNumberET, codeET;
	private Button getCodeBtn, getPasswordBtn;
	private boolean numberIsValid = false;
	private boolean codeIsValid = false;
	private String code = "";//��֤��
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout contentView = (LinearLayout) inflater.inflate(
				R.layout.viewpager_phone, null);
		phoneNumberET = (EditText) contentView.findViewById(R.id.phone_number);
		codeET = (EditText) contentView.findViewById(R.id.code_et);
		getCodeBtn = (Button) contentView.findViewById(R.id.get_code_btn);
		getCodeBtn.setOnClickListener(this);
		getPasswordBtn = (Button) contentView.findViewById(R.id.find_pwd_btn);
		getPasswordBtn.setOnClickListener(this);
		phoneNumberET.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				numberIsValid = ToolUtils.isMobileNO(s.toString().trim());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if(numberIsValid){
					phoneNumberET.setBackgroundResource(R.drawable.login_edittext_valid_bg);
				}else
					phoneNumberET.setBackgroundResource(R.drawable.login_edittext_invalid_bg);
			}
		});
		
		codeET.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				codeIsValid = ToolUtils.isCode(s.toString().trim());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if(codeIsValid){
					codeET.setBackgroundResource(R.drawable.login_edittext_valid_bg);
				}else
					codeET.setBackgroundResource(R.drawable.login_edittext_invalid_bg);
			}
		});
		return contentView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.get_code_btn:
			if(numberIsValid){
				code = ToolUtils.randomCodeString();
				AutoSendSMS.autoSendCode(getActivity(), phoneNumberET.getText().toString().trim(), code);
			}else{
				Toast.makeText(getActivity(), "����������ȷ���ֻ���", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.find_pwd_btn:
			if(!numberIsValid){
				Toast.makeText(getActivity(), "����������ȷ���ֻ���", Toast.LENGTH_SHORT).show();
				return;
			}else if(!codeIsValid || !code.equals(codeET.getText().toString().trim())){
				Toast.makeText(getActivity(), "����������ȷ����֤��", Toast.LENGTH_SHORT).show();
				return;
			}else if(numberIsValid && codeIsValid && code.equals(codeET.getText().toString().trim())){
				Toast.makeText(getActivity(), "�һ�����ɹ�", Toast.LENGTH_SHORT).show();
			}
			
			break;
		default:
			break;
		}
	}

}
