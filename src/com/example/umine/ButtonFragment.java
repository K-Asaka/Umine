package com.example.umine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ButtonFragment extends Fragment {

	static int cnt ;
	String cntText = "";


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.btn_count_layout, container, false);

		Button countBtn = (Button)getActivity().findViewById(R.id.countBtn);
		Button tweetBtn = (Button)getActivity().findViewById(R.id.tweet);
		final TextView counter = (TextView)getActivity().findViewById(R.id.counter);


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
		return v;
	}
}
