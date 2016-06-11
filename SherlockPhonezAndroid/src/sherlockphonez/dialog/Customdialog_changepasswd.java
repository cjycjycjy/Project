package sherlockphonez.dialog;


import com.example.sherlockphonez.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Customdialog_changepasswd extends Dialog{

	public Button btn_setpasswd = null;
	public Button btn_setpasswd_cancel = null;
	public String passwd=null;
	public EditText et_setpasswd = null;
			
	
	public Customdialog_changepasswd(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.custom_dialog_lock);
		btn_setpasswd = (Button)findViewById(R.id.btn_setpasswd);
		et_setpasswd = (EditText)findViewById(R.id.et_setpasswd);
		btn_setpasswd_cancel = (Button)findViewById(R.id.btn_setpasswd_cancel);
	}
	
	public Button getOkBtn(){
		return btn_setpasswd;
	}
	
	public Button getCancelBtn(){
		return btn_setpasswd_cancel;
	}
	
	public String getPasswd(){
		return et_setpasswd.getText().toString();
	}
	
	public void setPasswd(){
		et_setpasswd.setText("");
	}

}
