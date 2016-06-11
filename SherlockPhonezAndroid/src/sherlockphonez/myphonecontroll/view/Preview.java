package sherlockphonez.myphonecontroll.view;

import java.io.IOException;

import com.example.sherlockphonez.AppController;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class Preview extends SurfaceView implements SurfaceHolder.Callback{
	String TAG = "CAMERA";
	private SurfaceHolder mHolder;
	private Camera mCamera;
	private AppController app;
	private Preview mPreview;
	private PictureCallback mPicture; 

	public Preview(Context context, Camera camera, PictureCallback picture) {
		super(context);
		mCamera = camera;
		app=(AppController)context.getApplicationContext();
		
		mHolder = getHolder();
		mHolder.addCallback(this);
		
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		mPicture=picture;
	}

	public void surfaceCreated(SurfaceHolder holder) {
		
		try {
			Camera.Parameters parameters = mCamera.getParameters();
			if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
				parameters.set("orientation", "portrait");
				mCamera.setDisplayOrientation(90);
				parameters.setRotation(90);
			} else {
				parameters.set("orientation", "landscape");
				mCamera.setDisplayOrientation(0);
				parameters.setRotation(0);
			}
			mCamera.setParameters(parameters);

			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();
			
		} catch (IOException e) {
			Log.d(TAG, "Error setting camera preview: " + e.getMessage());
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		
		if (mCamera != null) {
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}
	}

	private Camera.Size getBestPreviewSize(int width, int height)
	{
		Camera.Size result=null;    
		Camera.Parameters p = mCamera.getParameters();
		for (Camera.Size size : p.getSupportedPreviewSizes()) {
			if (size.width<=width && size.height<=height) {
				if (result==null) {
					result=size;
				} else {
					int resultArea=result.width*result.height;
					int newArea=size.width*size.height;

					if (newArea>resultArea) {
						result=size;
					}
				}
			}
		}
		return result;

	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		

		if (mHolder.getSurface() == null){
			// �����䰡 �������� ������
			return;
		}

		// �켱 ����� 
		try {
			mCamera.stopPreview();
		} catch (Exception e){
			// �����䰡 �������� ���� �ʴ� ���� 
		}


		// ������ ����, ó�� ���� ���⼭ ���ش�.
		Camera.Parameters parameters = mCamera.getParameters();
		//Camera.Size size = getBestPreviewSize(w, h);
		//parameters.setPreviewSize(size.width, size.height);
		mCamera.setParameters(parameters);
		// ���� ����� �������� �����並 ������Ѵ� 
		try {
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();
			mCamera.takePicture(null, null, mPicture);

		} catch (Exception e){
			Log.d(TAG, "Error starting camera preview: " + e.getMessage());
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(1,1);
	}

}

