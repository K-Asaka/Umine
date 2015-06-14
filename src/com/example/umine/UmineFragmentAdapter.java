package com.example.umine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class UmineFragmentAdapter extends FragmentPagerAdapter {
	private static final int fragmentNum = 5;

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
		case 3:
			return new ButtonFragment();
		case 4:
			return new SumimasenFragment();
		default:
			return new Fragment();
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
