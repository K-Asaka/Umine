package com.example.umine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class UmineFragmentAdapter extends FragmentPagerAdapter {
	private static final int fragmentNum = 4;

	public UmineFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int pos) {
		switch (pos) {
		case 0:
			return new UmineFragment();
		case 1:
			return new CameraFragment();
		case 2:
			return new ButtonFragment();
		case 3:
			return new SumimasenFragment();
		default:
			return new UmineFragment();
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragmentNum;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "Page " + (position);
	}

}
