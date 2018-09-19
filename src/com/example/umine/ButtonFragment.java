package com.example.umine;

import java.io.File;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ButtonFragment extends Fragment {

	static int cnt = 0;
	private String cntText = "";
	TwitterSend send;
	final String PATH = Environment.getExternalStorageDirectory().getPath()
			+ CameraFragment.DIRPATH;
	SoundPool sound_;

	private static final String TAG = "ButtonFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println(PATH);
		Log.d(TAG, "onCreateView呼ばれた");
		View v = inflater.inflate(R.layout.btn_count_layout, container, false);

		return v;
	}

	@Override
	public void onStart() {
		super.onStart();

		ImageButton countBtn = (ImageButton) getActivity().findViewById(
				R.id.countBtn);
		ImageButton tweetBtn = (ImageButton) getActivity().findViewById(
				R.id.tweet);
		ImageButton destroyBtn = (ImageButton) getActivity().findViewById(
				R.id.destroy);

		TextView text = (TextView) getActivity().findViewById(R.id.none);
		final TextView counter = (TextView) getActivity().findViewById(
				R.id.counter);

		final EditText twtext = (EditText) getActivity().findViewById(
				R.id.twtext);
		final EditText shopName = (EditText) getActivity().findViewById(
				R.id.shopName);

		sound_ = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		final int soundId = sound_.load(getActivity(), R.raw.umai, 1);

		ImageView caputure = (ImageView) getActivity().findViewById(
				R.id.caputure);
		Bitmap bmp = ((BitmapDrawable) caputure.getDrawable()).getBitmap();

		/*
		 * shigure
		 */

		final File dir = new File(PATH);
		if (!dir.exists()) {
			dir.mkdir();
		}
		System.out.println(dir.getPath().toString());
		Bitmap bitmap = BitmapFactory.decodeFile(dir.getPath() + "/"
				+ CameraFragment.PICPATH);
		Matrix matrix=new Matrix();
		matrix.postRotate(90);
		caputure.setImageBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,false) );

		/*
		 *
		 * shigure/>
		 */
		if (bmp == null) {
			text.setText("写真がありません");
		}

		countBtn.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					sound_.play(soundId, 1.0F, 1.0F, 0, 0, 1.0F);
					cnt += 1;
					cntText = String.valueOf(cnt);
					counter.setText(cntText);
					break;

				default:
					break;
				}
				return false;
			}
		});

		tweetBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!Twitter_Util.serchAccessToken()) {
					Log.v("MainActivity", "start oauth");
					Intent intent = new Intent(getActivity(),
							OauthActivity.class);
					startActivity(intent);
				} else {
					send = new TwitterSend(getActivity());
					send.execute(cnt, shopName.getText().toString() + "\n"
							+ twtext.getText().toString(), dir.getPath() + "/"
							+ CameraFragment.PICPATH);
				}

			}
		});
		destroyBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						getActivity());
				alert.setTitle("アカウント解除");
				alert.setMessage("アプリとアカウントとの連携を解除しますか？");
				alert.setPositiveButton("はい",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Twitter_Util.keyDestroy();
							}
						});

				alert.setNegativeButton("いいえ",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {

							}
						});
				alert.show();
			}
		});
	}
}
