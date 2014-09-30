package com.keen.ma.utils;

import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * �Զ����Ͷ�����
 * @author KEEN
 * @lastChangeTime 2014��8��19�� ����10:40:13
 */
public class AutoSendSMS {

	private static SmsManager manager = null;
	private static String SENT_SMS_ACTION = "SENT_SMS_ACTION";
	private static String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";
	
	/**
	 * �Զ�������֤��
	 * @author KEEN
	 * @lastChangeTime 2014��8��19�� ����10:42:01
	 * @return
	 */
	public static void autoSendCode(Context context, String phoneNumber,
			String code) {
		if(manager == null)
			manager = SmsManager.getDefault();
		String msg = "�����紥�ֿɼ����û��˻������һ���֤�룺"+code+"��������ʱ�����֤����Ǳ��˲���������Ա����š�";
		if (msg.length() > 70) {
			List<String> divideContents = manager.divideMessage(msg);
			for (String text : divideContents) {
				manager.sendTextMessage(phoneNumber, null, text, autoSendCallBack(context, SENT_SMS_ACTION),
						autoReceiveCallback(context, DELIVERED_SMS_ACTION));
			}
		} else {
			manager.sendTextMessage(phoneNumber, null, msg, autoSendCallBack(context, SENT_SMS_ACTION),
					autoReceiveCallback(context, DELIVERED_SMS_ACTION));
		}

	}

	/**
	 * �Զ�������ͨ��Ϣ
	 * @author KEEN
	 * @lastChangeTime 2014��8��19�� ����10:42:34
	 * @return
	 */
	public static void autoSendMessage(Context context, String phoneNumber,
			String msg) {
		if(manager == null)
			manager = SmsManager.getDefault();
		if (msg.length() > 70) {
			List<String> divideContents = manager.divideMessage(msg);
			for (String text : divideContents) {
				manager.sendTextMessage(phoneNumber, null, text, autoSendCallBack(context, SENT_SMS_ACTION),
						autoReceiveCallback(context, DELIVERED_SMS_ACTION));
			}
		} else {
			manager.sendTextMessage(phoneNumber, null, msg, autoSendCallBack(context, SENT_SMS_ACTION),
					autoReceiveCallback(context, DELIVERED_SMS_ACTION));
		}
	}

	/**
	 * �����صķ���״̬
	 * ���ŷ��ͳɹ��Ļص�����
	 * @author KEEN
	 * @lastChangeTime 2014��8��19�� ����11:09:12
	 */
	private static PendingIntent autoSendCallBack(final Context context,String send_sms_action) {

		Intent sentIntent = new Intent(send_sms_action);
		PendingIntent sentPI = PendingIntent.getBroadcast(context, 0,sentIntent, 0);
		context.registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context _context, Intent _intent) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(context, "���ŷ��ͳɹ�", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(context, "���ŷ���ʧ��", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(context, "���ŷ���ʧ��", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(context, "���ŷ���ʧ��", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(send_sms_action));
		return sentPI;
	}
	
	/**
	 * �����صĽ���״̬
	 * @author KEEN
	 * @lastChangeTime 2014��8��19�� ����11:16:57
	 * @return
	 */
	private static PendingIntent autoReceiveCallback(final Context context,String receive_sms_action){
		
		Intent deliverIntent = new Intent(receive_sms_action);
		PendingIntent deliverPI = PendingIntent.getBroadcast(context, 0,
		       deliverIntent, 0);
		context.registerReceiver(new BroadcastReceiver() {
		   @Override
		   public void onReceive(Context _context, Intent _intent) {
			   switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(context,"�������Ѿ��ɹ�����", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(context,"������δ�ɹ�����", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(context,"������δ�ɹ�����", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(context,"������δ�ɹ�����", Toast.LENGTH_SHORT).show();
					break;
				}
		       
		   }
		}, new IntentFilter(receive_sms_action));
		return deliverPI;
	}

}
