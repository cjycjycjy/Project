package sherlockphonez.dialog;


import com.example.sherlockphonez.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Customdialog_Email extends Dialog{

	public Button btn_sendpasswd = null;
	public Button btn_send_cancel = null;
	
	public EditText et_phoneNum = null;
	public EditText et_email = null;
	
	public Customdialog_Email(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.custom_dialog_email);
		btn_sendpasswd = (Button)findViewById(R.id.btn_sendpasswd);
		btn_send_cancel = (Button)findViewById(R.id.btn_send_cancel);
		et_phoneNum = (EditText)findViewById(R.id.et_phoneNum_find);
		et_email = (EditText)findViewById(R.id.et_email);
	}
	
	public Button getOkBtn(){
		return btn_sendpasswd;
	}
	
	public Button getCancelBtn(){
		return btn_send_cancel;
	}
	
	public String getPoneNum(){
		return et_phoneNum.getText().toString();
	}
	public String getEmail(){
		return et_email.getText().toString();
	}

}
