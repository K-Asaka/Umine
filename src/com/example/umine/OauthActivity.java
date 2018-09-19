package com.example.umine;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class OauthActivity extends Activity {
	private ImageView btn;
	private Twitter mTwitter;
	private RequestToken mRquestToken;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oauth);
		btn = (ImageView) findViewById(R.id.btn_oauth);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startOauth();
			}
		});
	}

	// 認証開始
	public void startOauth() {
		AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = new ProgressDialog(Twitter_Util.getContext());
				dialog.setTitle("Please wait");
				dialog.setMessage("Get accessToken...");
				dialog.show();
			}

			@Override
			protected String doInBackground(Void... params) {
				mTwitter = Twitter_Util.getTwitter();
				try {
					mRquestToken = mTwitter
							.getOAuthRequestToken("myapp://callback/");
					return mRquestToken.getAuthorizationURL();
				} catch (TwitterException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(String url) {
				dialog.dismiss();
				if (url != null) {
					Intent intent = new Intent(Intent.ACTION_VIEW,
							Uri.parse(url));
					startActivity(intent);
				} else {
					Log.v("url", "null");
				}
			}

		};
		task.execute();
	}

	// コールバック
	@Override
	protected void onNewIntent(Intent intent) {
		Log.v("callback", "callbackされました");
		final String verifier = intent.getData().getQueryParameter(
				"oauth_verifier");

		AsyncTask<Void, Void, AccessToken> task = new AsyncTask<Void, Void, AccessToken>() {

			@Override
			protected AccessToken doInBackground(Void... params) {
				try {

					return mTwitter.getOAuthAccessToken(mRquestToken, verifier);
				} catch (TwitterException e) {

					e.printStackTrace();
				}

				return null;
			}

			@Override
			protected void onPostExecute(AccessToken result) {
				Twitter_Util.setAccessToken(result);
				Toast.makeText(Twitter_Util.getContext(), "認証完了",
						Toast.LENGTH_SHORT).show();
				Destory();

				super.onPostExecute(result);
			}

		};
		task.execute();

	}

	public void Destory() {
		finish();
	}
}
