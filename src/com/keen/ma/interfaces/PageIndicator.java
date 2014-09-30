/**
 * ViewPager ҳ���л�����
 */
package com.keen.ma.interfaces;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

/**
 * @author wan
 *
 */
public interface PageIndicator extends OnPageChangeListener{
	
	/**
	 * ��ViewPager
	 * @param view
	 */
	void setViewPager(ViewPager view);
	
	/**
	 * ��ViewPager
	 * @param view
	 * @param initialPosition ��ʼ��ѡ���page��position
	 */
	void setViewPager(ViewPager view,int initialPosition);
	
	/**
	 * ����ViewPager�ĵ�ǰpage
	 * @param itemPosition
	 */
	void setCurrentItem(int itemPosition);
	
	/**
	 * ����ViewPager��ҳ���л�����
	 * @param listener
	 */
	void setOnPageChangeListener(OnPageChangeListener listener);
	
	/**
	 * ����Indicator
	 */
	void notifyDataSetChanged();
}
