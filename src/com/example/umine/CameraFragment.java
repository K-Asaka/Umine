package com.example.umine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

public class CameraFragment extends Fragment {

	private static final String TAG = "CameraFragment";
	private Camera camera;
	//Surfaceリスナ
	private SurfaceHolder.Callback surfaceListener = new SurfaceHolder.Callback() {
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			try {
				camera = Camera.open();
				camera.setPreviewDisplay(holder);
				camera.setDisplayOrientation(90);
			} catch(IOException e) {
			}
		}

		public void surfaceChanged(SurfaceHolder holder, int format,    int width, int height) {
            Log.d(TAG, "surfaceChanged width:" + width + " height:" + height);

            Camera.Parameters parameters = camera.getParameters();

            // デバッグ用表示
            android.hardware.Camera.Size size = parameters.getPictureSize();
            Log.d(TAG, "getPictureSize width:" + size.width + " size.height:" + size.height);
            size = parameters.getPreviewSize();
            Log.d(TAG, "getPreviewSize width:" + size.width + " size.height:" + size.height);

            // プレビューのサイズを変更
            // parameters.setPreviewSize(width, height);    // 画面サイズに合わせて変更しようとしたが失敗する
            parameters.setPreviewSize(640, 480);            // 使用できるサイズはカメラごとに決まっている
            parameters.setRotation(90);

            // パラメーターセット
            camera.setParameters(parameters);
            // プレビュー開始
            camera.startPreview();
        }

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			camera.stopPreview();
			camera.release();
		}

	};
	private Camera.PictureCallback pictrueListener = new Camera.PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			if(data!=null){
				String saveDir=Environment.getExternalStorageDirectory().getPath()+"/test9999999/";

				File file= new File(saveDir);
					if(file.mkdir())System.out.println("ディレクトリ作成");
				String imgPath=saveDir+new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())+".jpg";
				System.out.println(imgPath);
				FileOutputStream fos;
				try{
					fos=new FileOutputStream(imgPath,true);
					fos.write(data);
					fos.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			camera.startPreview();
		}
	};
	OnTouchListener ontouchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction()==MotionEvent.ACTION_DOWN)
				if(camera!=null)
					camera.takePicture(null, null, pictrueListener);
			return false;
		}
	};
//	private Camera.PictureCallback pictureListener= new Camera.PictureCallback() {
//
//		@Override
//		public void onPictureTaken(byte[] data, Camera camera) {
//			// TODO 自動生成されたメソッド・スタブ
//
//		}
//	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v(TAG, "onCreateView()が呼ばれました。");
		View view = inflater.inflate(R.layout.camera_fragment, container, false);
		SurfaceView camSurfaceView=(SurfaceView)view.findViewById(R.id.surface_view);
		SurfaceHolder holder=camSurfaceView.getHolder();
		holder.addCallback(surfaceListener);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		view.setOnTouchListener(ontouchListener);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.v(TAG, "onActivityCreated()が呼ばれ	ました。");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.v(TAG, "onStart()が呼ばれました。");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v(TAG, "onResume()が呼ばれました。");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v(TAG, "onPause()が呼ばれました。");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.v(TAG, "onStop()が呼ばれました。");
	}

}
