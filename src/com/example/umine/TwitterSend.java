package com.example.umine;

import java.io.File;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class TwitterSend extends AsyncTask<Object, Void, Void> {
	private final String APIKEY = "1g9K20f8lHSzS6A9kySjYbzJC";
	private final String APIKEY_SECRET = "1C8TWAerYSp5BEq4SoHREdkL0Fgvdy3mMzUl17MMhEMZIAFgsU";
	private final String ACCESSTOKEN = "3181118606-EusyuFlVqO3Eco5HeDZGLwgwRq0en1P1uGFSwVj";
	private final String ACCESSTOKEN_SECRET = "3p2cr0u40pTtRZtxAIQPKPwDSgVxKYn4Xl9koyZtdRTVr";
	private Twitter mTwitter;
	private String msg;
	private String count;
	private ProgressDialog dialog;
	private Context con;

	public TwitterSend(Context context) {
		con = context;
	}

	@Override
	protected void onPreExecute() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPreExecute();
		dialog = new ProgressDialog(con);
		dialog.setTitle("pleas wait...");
		dialog.setMessage("Twitter update now");
		dialog.show();
	}

	@Override
	protected Void doInBackground(Object... params) {
		try {
			Log.v("log", "twittersend(log)");
			System.out.println("TwitterSend");

			count = String.valueOf(params[0]);
			System.out.println("カウント：" + count);
			msg = String.valueOf(params[1]);
			System.out.println("メッセージ：" + msg);
			File imgfile = new File(String.valueOf(params[2]));
			System.out.println("画像取得");
			mTwitter = new TwitterFactory().getInstance();
			mTwitter.setOAuthConsumer(APIKEY, APIKEY_SECRET);
			AccessToken accessToken = new AccessToken(ACCESSTOKEN,
					ACCESSTOKEN_SECRET);
			mTwitter.setOAuthAccessToken(accessToken);
			StatusUpdate status = new StatusUpdate("msg:" + msg + "\ncount:"
					+ count);
			status.media(imgfile);
			mTwitter.updateStatus(status);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {

		super.onPostExecute(result);
		dialog.dismiss();
		System.out.println("ぽすと");
	}

}
