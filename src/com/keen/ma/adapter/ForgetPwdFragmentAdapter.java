package com.keen.ma.adapter;

import com.keen.ma.fragment.FindPwdByEmailFragment;
import com.keen.ma.fragment.FindPwdByPhoneFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * �л��һ����뷽ʽ��ViewPager��������
 * 
 * @author KEEN
 * @lastChangeTime 2014��8��18�� ����9:43:15
 */
public class ForgetPwdFragmentAdapter extends FragmentPagerAdapter {

	public ForgetPwdFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {

		if (position == 0) {
			return new FindPwdByPhoneFragment();
		} else if (position == 1) {
			return new FindPwdByEmailFragment();
		}
		return null;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		String title = "";
		switch (position) {
		case 0:
			title = "�ֻ����һ�";
			break;
		case 1:
			title = "�����һ�";
			break;
		}
		return title;
	}

	@Override
	public int getCount() {
		return 2;
	}

}
