package sherlockphonez.dialog;

import com.example.sherlockphonez.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Customdialog_Base extends Dialog implements OnClickListener{
	private TextView tv_text;
	private String text;	
	private Context context;
	
	public Customdialog_Base(Context context, String text) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.text=text;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.customdialog_base);
		findViewById(R.id.btn_ok_dialog).setOnClickListener(this);
		tv_text=(TextView)findViewById(R.id.tv_dialog);
		tv_text.setText(text);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_ok_dialog){
			dismiss();
		}
	}
}
