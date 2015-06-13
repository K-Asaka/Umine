package com.example.umine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class UmineFragmentAdapter extends FragmentPagerAdapter {

	public UmineFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int pos) {
		switch (pos) {
		case 0:
			return new CameraFragment();
		case 1:
			return new SumimasenFragment();
		case 2:
			return new ButtonFragment();
		default:
			return new UmineFragment();
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "Page " + (position);
	}

}
