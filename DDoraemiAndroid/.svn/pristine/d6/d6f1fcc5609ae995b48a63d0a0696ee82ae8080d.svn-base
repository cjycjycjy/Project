package ddoraemi.myinfopage.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.PauseOnScrollListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.home.model.ProgramData;
import ddoraemi.myinfopage.model.CustomGallery;
import ddoraemi.myinfopage.presenter.CustomGallery_Presenter;
import ddoraemi.myinfopage.presenter.CustomGallery_PresenterInterface;
import ddoraemi.start.R;

public class CustomGallery_View extends Activity implements CustomGallery_View_Interface,View.OnClickListener,AdapterView.OnItemClickListener{

	GridView gridGallery;
	Handler handler;
	Adapter_CustomGallery adapter;
	CustomGallery_PresenterInterface presenter;
	ImageView imgNoMedia;
	Button btnGalleryOk;

	String action;
	private ImageLoader imageLoader;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_customgallery);

		action = getIntent().getAction();
		if (action == null) {
			finish();
		}
		initImageLoader();
		init();
	}

	private void initImageLoader() {
		try {
			String CACHE_DIR = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/.temp_tmp";
			new File(CACHE_DIR).mkdirs();

			File cacheDir = StorageUtils.getOwnCacheDirectory(getBaseContext(),
					CACHE_DIR);

			DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
			.cacheOnDisc(true).imageScaleType(ImageScaleType.EXACTLY)
			.bitmapConfig(Bitmap.Config.RGB_565).build();
			ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
					getBaseContext())
			.defaultDisplayImageOptions(defaultOptions)
			.discCache(new UnlimitedDiscCache(cacheDir))
			.memoryCache(new WeakMemoryCache());

			ImageLoaderConfiguration config = builder.build();
			imageLoader = ImageLoader.getInstance();
			imageLoader.init(config);

		} catch (Exception e) {

		}
	}
	private void init() {
		presenter=new CustomGallery_Presenter(this);
		handler = new Handler();
		gridGallery = (GridView) findViewById(R.id.gridGallery);
		gridGallery.setFastScrollEnabled(true);
		gridGallery.setVerticalScrollBarEnabled(false);
		adapter = new Adapter_CustomGallery(getApplicationContext(), imageLoader,R.layout.item_customgallerygrid);
		PauseOnScrollListener listener = new PauseOnScrollListener(imageLoader,
				true, true);
		gridGallery.setOnScrollListener(listener);
		findViewById(R.id.llBottomContainer).setVisibility(View.VISIBLE);
			adapter.setMultiplePick(true);
		gridGallery.setAdapter(adapter);
		gridGallery.setOnItemClickListener(this);
		imgNoMedia = (ImageView) findViewById(R.id.imgNoMedia);

		btnGalleryOk = (Button) findViewById(R.id.btnGalleryOk);
		btnGalleryOk.setOnClickListener(this);

		new Thread() {

			@Override
			public void run() {
				Looper.prepare();
				handler.post(new Runnable() {

					@Override
					public void run() {
						adapter.addAll(getGalleryPhotos());
						checkImageStatus();
					}
				});
				Looper.loop();
			};

		}.start();

	}
	private void checkImageStatus() {
		if (adapter.isEmpty()) {
			imgNoMedia.setVisibility(View.VISIBLE);
		} else {
			imgNoMedia.setVisibility(View.GONE);
		}
	}
	public void clickBtnOK()
	{
		ArrayList<CustomGallery> selected = adapter.getSelected();
		if(selected.size()>3)
		{
			Dialog_BaseDialog dialog=new Dialog_BaseDialog(CustomGallery_View.this, "사진은 최대 3장 까지 선택가능합니다.");
			dialog.show();
		}
		else if(selected.size()<1)
		{
			Dialog_BaseDialog dialog=new Dialog_BaseDialog(CustomGallery_View.this, "사진은 한장 이상 선택해야합니다.");
			dialog.show();
		}
		else
		{
			String[] allPath = new String[selected.size()];
			for (int i = 0; i < allPath.length; i++) {
				allPath[i] = selected.get(i).sdcardPath;
			}

			Intent data = new Intent().putExtra("all_path", allPath);
			setResult(RESULT_OK, data);
			finish();
		}
	}


	private ArrayList<CustomGallery> getGalleryPhotos() {
		ArrayList<CustomGallery> galleryList = new ArrayList<CustomGallery>();

		try {
			final String[] columns = { MediaStore.Images.Media.DATA,
					MediaStore.Images.Media._ID };
			final String orderBy = MediaStore.Images.Media._ID;

			Cursor imagecursor = managedQuery(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns,
					null, null, orderBy);

			if (imagecursor != null && imagecursor.getCount() > 0) {

				while (imagecursor.moveToNext()) {
					CustomGallery item = new CustomGallery();

					int dataColumnIndex = imagecursor
							.getColumnIndex(MediaStore.Images.Media.DATA);

					item.sdcardPath = imagecursor.getString(dataColumnIndex);

					galleryList.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// show newest photo at beginning of the list
		Collections.reverse(galleryList);
		return galleryList;
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnGalleryOk)
		{
			presenter.validateCredentials("ok");
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		presenter.validateCredentials("gridclick", arg2,arg1);
		
	}

	@Override
	public void clickGrid(int position, View view) {
		// TODO Auto-generated method stub

		adapter.changeSelection(view, position);
	}

}
