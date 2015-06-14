package com.example.umine;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class UmineFragment extends Fragment {
	private static final String TAG = "UmineFragment";
	private ImageButton btnUmai = null;
	private MediaPlayer sound = null;
	private SoundPool voice = null;
	private int umaiVoice;

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
		View view = inflater.inflate(R.layout.umine_fragment, container, false);
		sound = MediaPlayer.create(getActivity(), R.raw.umai);
		btnUmai = (ImageButton)view.findViewById(R.id.btnUmai);
		btnUmai.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				voice.play(umaiVoice, 1.0F, 1.0F, 0, 0, 1.0F);
			}
		});
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.v(TAG, "onActivityCreated()が呼ばれました。");
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
		voice = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		voice.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				if (status != 0) {
					Log.v("LOADVOICD", "読み込み中");
				}
			}
		});
		umaiVoice = voice.load(getActivity(), R.raw.umai, 1);
		
		Log.v(TAG, "onResume()が呼ばれました。");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		voice.release();
		Log.v(TAG, "onPause()が呼ばれました。");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.v(TAG, "onStop()が呼ばれました。");
	}

}
