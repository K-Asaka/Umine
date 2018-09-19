package com.example.umine;

import android.support.v4.app.Fragment;
import android.media.AudioManager;
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
	private SoundPool voice = null;
	private int umaiVoice;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v(TAG, "onCreateView()が呼ばれました。");
		View view = inflater.inflate(R.layout.umine_fragment, container, false);
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
	public void onResume() {
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
	}

	@Override
	public void onPause() {
		super.onPause();
		voice.release();
	}
}
