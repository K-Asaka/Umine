package com.example.umine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class CamView extends SurfaceView implements Callback ,PictureCallback{

	public CamView(Context context) {
		super(context);
	}

	private Camera camera;
	/* 19:25
	private Context context;
	public CamView(Context context,Camera camera) {
		super(context);
		this.context=context;
		this.camera=camera;
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
*/
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			camera=Camera.open();
			camera.setPreviewDisplay(holder);
		} catch(IOException e) {
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int f, int w, int h) {
		Camera.Parameters p = camera.getParameters();
		p.setPreviewSize(w,h);
		camera.setParameters(p);
		camera.startPreview();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		camera.stopPreview();
		camera.release();
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		if(data==null)return;
		String saveDir=Environment.getExternalStorageDirectory().getPath()+"/test9999999/";

		File file= new File(saveDir);

			file.mkdir();

		String imgPath=saveDir+new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())+".jpg";
		System.out.println(imgPath);
		FileOutputStream fos;
		System.out.println("0");
		try{
			fos=new FileOutputStream(imgPath,true);
			System.out.println("1");
			fos.write(data);
			System.out.println("2");
			fos.close();
			System.out.println("3");

		}catch(Exception e){
			e.printStackTrace();
			System.out.println("x");

		}


		/*	16:55
				if(data!=null&&Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
			FileOutputStream foStream=null;
			File file=new File(Environment.getExternalStorageDirectory().getPath()+"/cmr/");
			if(file.exists())
				file.mkdir();
			String imgName=Environment.getExternalStorageDirectory().getPath()+"/cmr/"+System.currentTimeMillis()+".jpg";
			try{
				foStream=new FileOutputStream(imgName);
				foStream.write(data);
				foStream.close();
				ContentValues values = new ContentValues();
				ContentResolver contentResolver = context.getContentResolver();
				values.put(Images.Media.MIME_TYPE, "image/jpeg");
				values.put("_data", imgName);
				 try {
					 contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
					 }catch(Exception e){
					 Toast.makeText(context, "再起動後に画像が認識されます。", Toast.LENGTH_SHORT).show();
					 e.printStackTrace();
					 }
			}catch(Exception e){
				Toast.makeText(context, "ファイルの保存中にエラーが発生しました。", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
		}
		 */
		camera.startPreview();
	}

	@Override
	public boolean onTouchEvent(MotionEvent me){
		if(me.getAction()==MotionEvent.ACTION_DOWN){
			if(camera!=null)camera.takePicture(null, null, this);
		}
		return true;

	}

}