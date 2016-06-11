package ddoraemi.myinfopage.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailediteminfo.model.Afterword;
import ddoraemi.dialog.Dialog_ApplyAdmin;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.home.model.Group;
import ddoraemi.myinfopage.model.CustomGallery;
import ddoraemi.myinfopage.presenter.Modify_AfterwordPresenter;
import ddoraemi.myinfopage.presenter.Modify_AfterwordPresenterInterface;
import ddoraemi.start.R;

public class Modify_AfterWordView extends Activity implements
		Modify_AfterWordViewInterface, View.OnClickListener {
	ImageView[] score, empty;
	EditText content;
	ImageView save, pic, video, back;
	GridView grid;
	TextView tv_pname;
	TextView tv_gtime;
	TextView tv_paddr;
	TextView tv_piccount;
	String[] all_path;
	TextView deleteallpic;
	boolean isPhotochange;
	Modify_AfterwordPresenterInterface presenter;
	final int MULTIPIC = 200;
	int myscore;
	Afterword afterworditem;
	Adapter_CustomGallery gadapter;
	Adapter_NetworkCustomGallery adapter;
	ImageLoader imageLoader;
	AppController app;
	int position;
	Dialog_ApplyAdmin d;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modifyafterword);
		initImageLoader();
		init();
	}

	private void initImageLoader() {
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
				this).defaultDisplayImageOptions(defaultOptions).memoryCache(
				new WeakMemoryCache());

		ImageLoaderConfiguration config = builder.build();
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(config);
	}

	public void init() {

		app = (AppController) getApplicationContext();
		Intent intent = getIntent();

		presenter = new Modify_AfterwordPresenter(this);
		afterworditem = (Afterword) intent
				.getSerializableExtra("afterworditem");
		position = (int) intent.getIntExtra("position", -1);
		score = new ImageView[5];
		empty = new ImageView[5];
		grid = (GridView) findViewById(R.id.activity_modifyafterword_grid);
		grid = (GridView) findViewById(R.id.activity_modifyafterword_grid);
		grid = (GridView) findViewById(R.id.activity_modifyafterword_grid);
		grid = (GridView) findViewById(R.id.activity_modifyafterword_grid);
		content = (EditText) findViewById(R.id.activity_modifyafterword_edit);
		score[0] = (ImageView) findViewById(R.id.activity_modifyafterword_star1);
		score[1] = (ImageView) findViewById(R.id.activity_modifyafterword_star2);
		score[2] = (ImageView) findViewById(R.id.activity_modifyafterword_star3);
		score[3] = (ImageView) findViewById(R.id.activity_modifyafterword_star4);
		score[4] = (ImageView) findViewById(R.id.activity_modifyafterword_star5);
		empty[0] = (ImageView) findViewById(R.id.activity_modifyafterword_emptystar1);
		empty[1] = (ImageView) findViewById(R.id.activity_modifyafterword_emptystar2);
		empty[2] = (ImageView) findViewById(R.id.activity_modifyafterword_emptystar3);
		empty[3] = (ImageView) findViewById(R.id.activity_modifyafterword_emptystar4);
		empty[4] = (ImageView) findViewById(R.id.activity_modifyafterword_emptystar5);
		save = (ImageView) findViewById(R.id.activity_modifyafterword_modify);
		pic = (ImageView) findViewById(R.id.activity_modifyafterword_pic);
		video = (ImageView) findViewById(R.id.activity_modifyafterword_video);
		back = (ImageView) findViewById(R.id.activity_modifyafterword_back);
		tv_piccount = (TextView) findViewById(R.id.activity_modify_piccount);
		deleteallpic = (TextView) findViewById(R.id.activity_modifyafterword_deleteallpic);
		deleteallpic.setOnClickListener(this);
		empty[0].setOnClickListener(this);
		empty[1].setOnClickListener(this);
		empty[2].setOnClickListener(this);
		empty[3].setOnClickListener(this);
		empty[4].setOnClickListener(this);
		score[0].setOnClickListener(this);
		score[1].setOnClickListener(this);
		score[2].setOnClickListener(this);
		score[3].setOnClickListener(this);
		score[4].setOnClickListener(this);
		pic.setOnClickListener(this);
		video.setOnClickListener(this);
		back.setOnClickListener(this);
		save.setOnClickListener(this);
		adapter = new Adapter_NetworkCustomGallery(this,
				R.layout.item_afterwordpiclist, afterworditem.getA_photo_url());
		grid.setAdapter(adapter);
		selectScore((int)afterworditem.getA_grade());
		content.setText(afterworditem.getA_content());
		content.setSelected(false);
		tv_piccount.setText(afterworditem.getA_photo_url().size()+"");
		d = new Dialog_ApplyAdmin(this, "후기를 등록하시겠습니까?");
		d.getBtnOk().setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.activity_modifyafterword_back) {
			presenter.validateCredentials("back");
		} else if (v.getId() == R.id.activity_modifyafterword_emptystar1
				|| v.getId() == R.id.activity_modifyafterword_star1) {
			presenter.validateCredentials("star1");
		} else if (v.getId() == R.id.activity_modifyafterword_emptystar2
				|| v.getId() == R.id.activity_modifyafterword_star2) {
			presenter.validateCredentials("star2");
		} else if (v.getId() == R.id.activity_modifyafterword_emptystar3
				|| v.getId() == R.id.activity_modifyafterword_star3) {
			presenter.validateCredentials("star3");
		} else if (v.getId() == R.id.activity_modifyafterword_emptystar4
				|| v.getId() == R.id.activity_modifyafterword_star4) {
			presenter.validateCredentials("star4");
		} else if (v.getId() == R.id.activity_modifyafterword_emptystar5
				|| v.getId() == R.id.activity_modifyafterword_star5) {
			presenter.validateCredentials("star5");
		} else if (v.getId() == R.id.activity_modifyafterword_pic) {
			presenter.validateCredentials("pic");
		}else if(v.getId() == R.id.dialog_basedialog_btnok){
			String a_content = content.getText().toString();
			if (all_path == null) {
				all_path = new String[0];
			}
			d.dismiss();
			presenter.validateCredentials("save", afterworditem.getA_id(),
					a_content, (float) myscore, all_path, isPhotochange);
		}
				
		else if (v.getId() == R.id.activity_modifyafterword_modify) {
			String a_content = content.getText().toString();
			if (all_path == null) {
				all_path = new String[0];
			}
			if (myscore != 0 && a_content.length() != 0) {
				d.show();
			} else {
				Dialog_BaseDialog dialog = new Dialog_BaseDialog(
						Modify_AfterWordView.this, "입력되지 않은 항목이 있습니다.");
				dialog.show();
			}
		} else if (v.getId() == R.id.activity_modifyafterword_deleteallpic) {
			presenter.validateCredentials("deleteallpic");
		}

	}

	@Override
	public void gotoBack() {
		finish();
	}

	@Override
	public void modify() {

	}

	@Override
	public void selectScore(int myscore) {
		this.myscore = myscore;
		for (int i = 0; i < myscore; i++) {
			score[i].setVisibility(View.VISIBLE);
		}
		for (int i = myscore; i < 5; i++) {
			score[i].setVisibility(View.GONE);
		}

	}

	@Override
	public void selctPic() {
		// TODO Auto-generated method stub
		Intent i = new Intent(Action.ACTION_MULTIPLE_PICK);
		startActivityForResult(i, MULTIPIC);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == MULTIPIC && resultCode == Activity.RESULT_OK) {
			isPhotochange = true;
			if (gadapter == null) {
				gadapter = new Adapter_CustomGallery(this, imageLoader,
						R.layout.item_writeafterwordgrid);
				gadapter.setMultiplePick(false);
				grid.setAdapter(gadapter);
			}
			isPhotochange = true;
			all_path = data.getStringArrayExtra("all_path");
			tv_piccount.setText(String.valueOf(all_path.length));

			ArrayList<CustomGallery> dataT = new ArrayList<CustomGallery>();

			for (String string : all_path) {
				CustomGallery item = new CustomGallery();
				item.sdcardPath = string;

				dataT.add(item);
			}

			tv_piccount.setText(dataT.size()+"");
			gadapter.addAll(dataT
					);
		}
	}

	@Override
	public void deleteallpic() {
		isPhotochange = true;
		gadapter = new Adapter_CustomGallery(this, imageLoader,
				R.layout.item_writeafterwordgrid);
		gadapter.setMultiplePick(false);
		grid.setAdapter(gadapter);
		tv_piccount.setText("0");
	}
}
