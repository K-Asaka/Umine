package com.example.umine;

import java.io.File;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.os.AsyncTask;
import android.os.Environment;

public class TwitterSend extends AsyncTask<Object, Void, Void> {
	private final String APIKEY = "1g9K20f8lHSzS6A9kySjYbzJC";
	private final String APIKEY_SECRET = "1C8TWAerYSp5BEq4SoHREdkL0Fgvdy3mMzUl17MMhEMZIAFgsU";
	private final String ACCESSTOKEN = "3181118606-EusyuFlVqO3Eco5HeDZGLwgwRq0en1P1uGFSwVj";
	private final String ACCESSTOKEN_SECRET = "3p2cr0u40pTtRZtxAIQPKPwDSgVxKYn4Xl9koyZtdRTVr";
	private Twitter mTwitter;
	private String msg;
	private String count;
	private final String IMAGE_PATH = Environment.getExternalStorageDirectory()
			.getPath() + "/test/tmp.png";

	@Override
	protected Void doInBackground(Object... params) {
		try {
			count = String.valueOf(params[0]);
			msg = String.valueOf(params[1]);
			System.out.println(IMAGE_PATH);
			File imgfile = new File(IMAGE_PATH);

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

	}

}
