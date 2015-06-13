package com.example.umine;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class UmineFragmentAdapter extends FragmentPagerAdapter {
	
	public UmineFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int pos) {
		switch (pos) {
		case 0:
			return new CameraFragment();
		// case 1はうまい！
		case 2:
			return new SumimasenFragment();
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
