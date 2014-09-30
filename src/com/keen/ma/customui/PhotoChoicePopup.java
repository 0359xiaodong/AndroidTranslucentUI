package com.keen.ma.customui;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.keen.ma.R;

/**
 * @author KeenW
 * @lastChangeTime 2014-8-15����9:24:06
 */
public class PhotoChoicePopup extends PopupWindow {

	private Button localImageBtn,cameraImageBtn,cancelBtn;
	
	public PhotoChoicePopup(Context context,OnClickListener clickListener) {

		final LinearLayout popupView = (LinearLayout) LayoutInflater.from(
				context).inflate(R.layout.photo_choice_popup, null);
		
		localImageBtn = (Button)popupView.findViewById(R.id.choose_local_image);
		localImageBtn.setOnClickListener(clickListener);
		cameraImageBtn = (Button)popupView.findViewById(R.id.choose_camera_image);
		cameraImageBtn.setOnClickListener(clickListener);
		cancelBtn = (Button)popupView.findViewById(R.id.choose_cancel);
		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		setContentView(popupView);
		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.WRAP_CONTENT);
		setFocusable(true);
		setAnimationStyle(R.style.BottomPopupAnimStyle);
		
		//ֻ��������һ������������popup����ʱ��Ӧ���ؼ�
		setBackgroundDrawable(context.getResources().getDrawable(R.drawable.photo_choice_popup_bg));
		
		// popupView���OnTouchListener�����жϻ�ȡ����λ�������ѡ������������ٵ�����
		popupView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int height = popupView.getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});

	}

	public PhotoChoicePopup(Context context) {
		super(context);
	}

}
