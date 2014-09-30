package com.keen.ma.utils;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import com.keen.ma.R;
import com.keen.ma.bean.MenuItemBean;
import com.keen.ma.bean.ScreenPixelsBean;

public class MenuBaseUtils {

	/**
	 * �̶������ļ��е����飬ת���ɲ˵���
	 * 
	 * @param context
	 * @param icons
	 * @return
	 */
	public static ArrayList<MenuItemBean> arrayToList(Context context) {
		ArrayList<MenuItemBean> list = new ArrayList<MenuItemBean>();
		String[] array = context.getResources().getStringArray(
				R.array.menu_item_array);
		int[] icons = getIconsByArray(context);
		for (int i = 0; i < array.length; i++) {
			MenuItemBean itemBean = new MenuItemBean();
			itemBean.setMenuItemIcon(icons[i]);
			itemBean.setMenuItemName(array[i]);
			list.add(itemBean);
		}
		return list;
	}

	/**
	 * �������ƽ���Fragment
	 * 
	 * @param context
	 * @param packageName
	 * @param position
	 * @param flag
	 *            //�����һ��item����������1��ʾһ����2��ʾ����
	 * @return
	 */
	public static Fragment getFragmentByName(Context context,
			String packageName, int position) {
		String[] fragArray = context.getResources().getStringArray(
				R.array.menu_fragment_array);
		try {
			return (Fragment) Class.forName(
					packageName + "." + fragArray[position]).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��xml�ַ�������ͨ�����ƻ�ȡͼƬ
	 * 
	 * @param context
	 * @return
	 */
	private static int[] getIconsByArray(Context context) {
		String[] iconNames = context.getResources().getStringArray(
				R.array.menu_icon_array);
		int[] icons = new int[iconNames.length];
		try {
			Class<com.keen.ma.R.drawable> cls = R.drawable.class;
			for (int i = 0; i < iconNames.length; i++) {
				icons[i] = cls.getDeclaredField(iconNames[i]).getInt(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return icons;
	}

	/**
	 * ��ȡ��Ļ����
	 * 
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static ScreenPixelsBean getScreenPixels(Context context) {
		ScreenPixelsBean screenPixelsBean = new ScreenPixelsBean();
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		screenPixelsBean.setScreenHeight(wm.getDefaultDisplay().getHeight());
		screenPixelsBean.setScreenWidth(wm.getDefaultDisplay().getWidth());
		return screenPixelsBean;
	}

}
