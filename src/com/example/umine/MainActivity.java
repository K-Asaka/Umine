package com.example.umine;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	private TwitterSend task;
	private int cnt;
	private String msg;
	private File file;
	private final String PATH = Environment.getExternalStorageDirectory()
			.getPath() + "/test777/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		task = new TwitterSend();
		cnt = 10000;
		msg = "Uplaod test.";

		File dir = new File(PATH);
		if (dir.exists()) {
			file = new File(dir.getPath() + "/tmp.png");
		} else {
			System.out.println("無かったので作成");
			dir.mkdir();
		}

		task.execute(cnt, msg, file);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
