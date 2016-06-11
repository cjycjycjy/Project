package ddoraemi.start;

import ddoraemi.home.view.HomeView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity{
	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_splash);
	       
	        Handler handler = new Handler();
	        handler.postDelayed(new Runnable() {
	            public void run() {
	                Intent intent = new Intent(SplashActivity.this, HomeView.class);
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
