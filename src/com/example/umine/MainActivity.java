package com.example.umine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	static int cnt ;
	String cntText = "";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button countBtn = (Button)findViewById(R.id.countBtn);
		Button tweetBtn = (Button)findViewById(R.id.tweet);
		final TextView counter = (TextView)findViewById(R.id.counter);


		countBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				cnt += 1;
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
