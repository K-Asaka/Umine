package com.example.umine;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

public class Twitter_Util {
	// アプリケーションキーコード
	private static final String PREFNAME = "AccessData";// ダミーデータ
	private static final String TOKEN = "token";// ダミーデータ
	private static final String TOKEN_SECRET = "token_secret";
	private static final String APIKEY = "1g9K20f8lHSzS6A9kySjYbzJC";
	private static final String APIKEY_SECRET = "1C8TWAerYSp5BEq4SoHREdkL0Fgvdy3mMzUl17MMhEMZIAFgsU";
	private static Context context = null;
	private static Twitter mTwitter;

	// setter getter
	public static Context getContext() {
		return context;

	}

	public static void setContext(Context con) {
		context = con;

	}

	// Twitterの取得
	public static Twitter getTwitter() {
		mTwitter = new TwitterFactory().getInstance();
		mTwitter.setOAuthConsumer(APIKEY, APIKEY_SECRET);
		if (serchAccessToken()) {
			mTwitter.setOAuthAccessToken(getAccessToken());
		}
		return mTwitter;
	}

	// Store accessToken
	public static void setAccessToken(AccessToken accessToken) {
		SharedPreferences preferences = getContext().getSharedPreferences(
				PREFNAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(TOKEN, accessToken.getToken());
		editor.putString(TOKEN_SECRET, accessToken.getTokenSecret());
		editor.commit();
	}

	// getAccessToken
	public static AccessToken getAccessToken() {
		SharedPreferences preferences = getContext().getSharedPreferences(
				PREFNAME, Context.MODE_PRIVATE);

		String AccessToken = preferences.getString(TOKEN, null);
		String AccessTokenSecret = preferences.getString(TOKEN_SECRET, null);
		if (AccessToken != null && AccessTokenSecret != null) {
			return new AccessToken(AccessToken, AccessTokenSecret);
		} else {
			return null;
		}

	}// アクセストークンが存在すればTrue

	public static boolean serchAccessToken() {

		return getAccessToken() != null;
	}

	public static void keyDestroy() {
		SharedPreferences preferences = getContext().getSharedPreferences(
				PREFNAME, Context.MODE_PRIVATE);
		preferences.edit().clear().commit();
		Toast.makeText(getContext(), "解除しました", Toast.LENGTH_SHORT).show();
	}

}
