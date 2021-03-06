package com.example.sherlockphonez;

import sherlockphonez.main.view.MainView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				Intent intent = new Intent(MainActivity.this,MainView.class);
				startActivity(intent);
				finish();
			}
		}, 3000);   
	}
	@Override
	public void onBackPressed() {
		//super.onBackPressed();
	}

}
