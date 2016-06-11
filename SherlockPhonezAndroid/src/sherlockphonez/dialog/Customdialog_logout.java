package sherlockphonez.dialog;



import com.example.sherlockphonez.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;

public class Customdialog_logout extends Dialog{

	public Button btn_out;
	public Button btn_out_cancel;
	
	public Customdialog_logout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.custom_dialog_logout);
		
		btn_out = (Button)findViewById(R.id.btn_out);
		btn_out_cancel = (Button)findViewById(R.id.btn_out_cancel);
	}
	
	public Button getOkBtn(){
		return btn_out;
	}
	
	public Button getCancelBtn(){
		return btn_out_cancel;
	}

}
