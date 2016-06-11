package ddoraemi.joining.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import ddoraemi.start.R;

public class FindPasswordDoneAcitvity extends Activity implements OnClickListener{
	ImageView gotologinbt,back;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findpassworddone);
		init();
	}

	public void init() {
		gotologinbt = (ImageView) findViewById(R.id.FindPasswordDoneActivity_Imggotologin);
		gotologinbt.setOnClickListener(this);
		back=(ImageView)findViewById(R.id.FindPasswordDoneActivity_ImgBack);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		finish();
	}
}
