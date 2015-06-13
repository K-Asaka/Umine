package com.example.umine;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private Camera cam;
	final private String saveDir=Environment.getExternalStorageDirectory().getPath()+"/test999/";
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		try{
		cam=Camera.open();
		}catch(Exception e){
			this.finish();
		}
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LinearLayout l = new LinearLayout(this);
        l.addView(new CamView(this,cam));
        setContentView(l);
//
//		Camera cam;
//		try {
//			cam=Camera.open();
//		} catch (Exception e) {
//			finish();
//		}
//
//
//
//
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
/*
	@Override
	public void onPause(){
		super.onPause();
		if(cam!=null){
			cam.release();
			cam=null;

		}
	}
	*/
}
