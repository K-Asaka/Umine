package com.example.umine;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ButtonFragment extends Fragment {

	static int cnt = 0;
	String cntText = "";
	MediaPlayer sound = null;

	private static final String TAG = "ButtonFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.d(TAG, "onCreateView呼ばれた");
		View v = inflater.inflate(R.layout.btn_count_layout, container, false);

		return v;
	}

	@Override
	public void onStart() {
		super.onStart();
		Button countBtn = (Button) getActivity().findViewById(R.id.countBtn);
		Button tweetBtn = (Button) getActivity().findViewById(R.id.tweet);
		TextView text = (TextView) getActivity().findViewById(R.id.none);
		final TextView counter = (TextView) getActivity().findViewById(R.id.counter);
		sound = MediaPlayer.create(getActivity(), R.raw.umai);
		ImageView caputure = (ImageView) getActivity().findViewById(R.id.caputure);
		Bitmap bmp = ((BitmapDrawable) caputure.getDrawable()).getBitmap();

		if (bmp == null) {
			text.setText("写真がありません");
		}

		countBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				cnt += 1;
				sound.start();
				cntText = String.valueOf(cnt);
				counter.setText(cntText);
			}
		});

		tweetBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

	}
}
