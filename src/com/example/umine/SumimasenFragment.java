package com.example.umine;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class SumimasenFragment extends Fragment {
	private static final String TAG = "SumimasenFragment";
	private ImageButton btnSumimasen = null;
//	private MediaPlayer sound = null;
	private SoundPool sound;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.v(TAG, "onAttache()が呼ばれました。");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.v(TAG, "onCreate()が呼ばれました。");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v(TAG, "onCreateView()が呼ばれました。");
		View view = inflater.inflate(R.layout.sumimasen_fragment, container, false);
//		sound = MediaPlayer.create(getActivity(), R.raw.excuseme);
		sound = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
		final int souceId = sound.load(getActivity(), R.raw.excuseme, 1);
		btnSumimasen = (ImageButton)view.findViewById(R.id.btnSumimasen);
		btnSumimasen.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				sound.play(souceId, 1.0F, 1.0F, 1, 0, 1.0F);
			}
		});
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.v(TAG, "onActivityCreated()が呼ばれ	ました。");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.v(TAG, "onStart()が呼ばれました。");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v(TAG, "onResume()が呼ばれました。");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v(TAG, "onPause()が呼ばれました。");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.v(TAG, "onStop()が呼ばれました。");
	}

}
