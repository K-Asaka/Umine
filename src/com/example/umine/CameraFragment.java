package com.example.umine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class CameraFragment extends Fragment {

	private static final String TAG = "CameraFragment";
	/*
	 * 保存フォルダ名、ファイル名
	 */
	public static final String DIRPATH ="/Umine/";
	public static final String PICPATH ="uminebg.jpg";

	private boolean hasSurface=false;
	private Camera camera;
	/*
	 * SurfaceCallback
	 */
	private SurfaceHolder.Callback surfaceListener = new SurfaceHolder.Callback() {
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			try {
				hasSurface=true;
				if(camera==null)camera = Camera.open();
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
            parameters.setPreviewSize(640, 480);            // 使用できるサイズはカメラごとに決まっている
            parameters.setRotation(90);

            // パラメーターセット
            camera.setParameters(parameters);
            // プレビュー開始
            camera.startPreview();
        }

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			hasSurface=false;
			onPause();
		}

	};
	/*
	 *ShutterCallback
	 */
	private Camera.ShutterCallback shutterListener=new Camera.ShutterCallback() {
		@Override
		public void onShutter() {
		}
	};
	/*
	 * pictureCallback
	 */
	private Camera.PictureCallback pictrueListener = new Camera.PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			if(data!=null){
				String saveDir=Environment.getExternalStorageDirectory().getPath()+DIRPATH;

				File file= new File(saveDir);
					if(file.mkdir())System.out.println("ディレクトリ作成");
				String imgPath=saveDir+PICPATH;
				System.out.println(imgPath);
				FileOutputStream fos;
				try{
					fos=new FileOutputStream(imgPath);
					fos.write(data);
					fos.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			camera.startPreview();
		}
	};
	/*
	 * onclicklistener
	 */
	OnClickListener onclickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			camera.takePicture(shutterListener, null, pictrueListener);
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v(TAG, "onCreateView()が呼ばれました。");
		View view = inflater.inflate(R.layout.camera_fragment, container, false);
		SurfaceView camSurfaceView=(SurfaceView)view.findViewById(R.id.surface_view);
		SurfaceHolder holder=camSurfaceView.getHolder();
		holder.addCallback(surfaceListener);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		view.setOnClickListener(onclickListener);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.v(TAG, "onActivityCreated()が呼ばれました。");
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
		if(camera==null)camera=Camera.open();
		Log.v(TAG, "onResume()が呼ばれました。");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(camera!=null){
			camera.release();
			camera=null;
		}
		Log.v(TAG, "onPause()が呼ばれました。");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.v(TAG, "onStop()が呼ばれました。");
	}
}

