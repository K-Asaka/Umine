package com.example.umine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class TitleActivity extends Activity {
	private ImageView img;
	private final Context context = this;
	private final Handler handler = new Handler();
	private final Runnable runnable = new Runnable() {

		@Override
		public void run() {
			Intent intent = new Intent(context, MainActivity.class);
			startActivity(intent);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);
		handler.postDelayed(runnable, 5000);
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		img = (ImageView) findViewById(R.id.imageView1);
		alphaAnimation.setDuration(4000);
		img.startAnimation(alphaAnimation);

	}
}
