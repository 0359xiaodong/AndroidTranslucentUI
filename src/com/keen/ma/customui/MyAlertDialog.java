package com.keen.ma.customui;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.keen.ma.R;

/**
 * �Զ��嵯����
 * 
 * @author KEEN
 * @lastChangeTime 2014��8��18�� ����3:17:59
 */
public class MyAlertDialog extends Dialog {

	private View.OnClickListener negativeClickListener = null;
	private View.OnClickListener neutralClickListener = null;
	private View.OnClickListener positiveClickListener = null;
	private TextView tvTitle = null;
	private TextView tvMessage = null;
	private Button btnNegative = null;
	private Button btnNeutral = null;
	private Button btnPositive = null;
	private View lineOne = null;
	private View lineTwo = null;
	
	
	@SuppressWarnings("deprecation")
	public MyAlertDialog(Context context, AlertDialogType alertDialogType) {
		super(context,R.style.CustomAlertDialog);
		
		this.setCanceledOnTouchOutside(false);
		this.setContentView(R.layout.my_alert_dialog_view);
		LinearLayout rootView = (LinearLayout) findViewById(R.id.root_view);
		tvTitle = (TextView) findViewById(R.id.alert_title);
		tvMessage = (TextView) findViewById(R.id.alert_message);
		btnNegative = (Button) findViewById(R.id.negative_btn);
		btnNeutral = (Button) findViewById(R.id.neutral_btn);
		btnPositive = (Button) findViewById(R.id.positive_btn);
		lineOne = (View)findViewById(R.id.line_one);
		lineTwo  = (View)findViewById(R.id.line_two);
		tvTitle.setText("��ܰ��ʾ");
		btnNegative.setText("ȡ��");
		btnNeutral.setText("����");
		btnPositive.setText("ȷ��");
		btnNegative.setOnClickListener(defaultOCL);
		btnNeutral.setOnClickListener(defaultOCL);
		btnPositive.setOnClickListener(defaultOCL);

		// �Ի���Ŀ���趨Ϊ��Ļ���*0.8
		int width = (int) (getWindow().getWindowManager().getDefaultDisplay()
				.getWidth() * 0.9);
		LayoutParams params = (LayoutParams) rootView.getLayoutParams();
		params.width = width;
		rootView.setLayoutParams(params);
		
		switch (alertDialogType) {
		case OneButton://ֻ��ʾPositiveButton
			btnPositive.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.alert_view_no_topcorner_bg));
			btnNegative.setVisibility(View.GONE);
			btnNeutral.setVisibility(View.GONE);
			lineOne.setVisibility(View.GONE);
			lineTwo.setVisibility(View.GONE);
			break;
		case TwoButton://ֻ��ʾPositiveButton��NegativeButton
			btnNeutral.setVisibility(View.GONE);
			lineOne.setVisibility(View.GONE);
			break;
		case ThreeButton://����Button����ʾ
			//Ĭ�ϲ�����
			break;
			
		default:
			break;
		}
		
	}

	public static enum AlertDialogType {
		OneButton, TwoButton, ThreeButton;
	}

	View.OnClickListener defaultOCL = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.positive_btn:
				if (positiveClickListener != null) {
					positiveClickListener.onClick(v);
				}
				break;
			case R.id.negative_btn:
				if (negativeClickListener != null) {
					negativeClickListener.onClick(v);
				}
				break;
			case R.id.neutral_btn:
				if (neutralClickListener != null) {
					neutralClickListener.onClick(v);
				}
				break;
			default:
				break;
			}
			dismiss();
		}

	};

	public MyAlertDialog setNegativeButton(CharSequence text,
			View.OnClickListener listener) {
		if (text != null) {
			this.btnNegative.setText(text);
		}
		if (listener != null) {
			this.negativeClickListener = listener;
		}
		return this;
	}

	public MyAlertDialog setNeutralButton(CharSequence text,
			View.OnClickListener listener) {
		if (text != null) {
			this.btnNeutral.setText(text);
		}
		if (listener != null) {
			this.neutralClickListener = listener;
		}
		return this;
	}

	public MyAlertDialog setPositiveButton(CharSequence text,
			View.OnClickListener listener) {
		if (text != null) {
			this.btnPositive.setText(text);
		}
		if (listener != null) {
			this.positiveClickListener = listener;
		}
		return this;
	}

	public MyAlertDialog setTitleText(CharSequence text) {
		this.tvTitle.setText(text);
		return this;
	}

	public MyAlertDialog setMessage(CharSequence text) {
		this.tvMessage.setText(text);
		return this;
	}

}
