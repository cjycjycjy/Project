package ddoraemi.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import ddoraemi.start.R;

public class Dialog_confirm_JoinGroup extends Dialog{
	TextView tv_p_name;
	TextView tv_p_date;
	ImageView btn_ok;
	ImageView btn_cancel;

	public Dialog_confirm_JoinGroup(Context context, String p_name, String date) {
		// TODO Auto-generated constructor stub
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		setContentView(R.layout.dialog_confirm_joingroup);

		tv_p_date = (TextView)findViewById(R.id.tv_joindialog_date);
		tv_p_name = (TextView)findViewById(R.id.tv_joindialog_p_name);
		tv_p_date.setText(date);
		tv_p_name.setText(p_name);
		btn_ok = (ImageView)findViewById(R.id.btn_dialog_confirmjoin_ok);
		btn_cancel = (ImageView)findViewById(R.id.btn_dialog_confirmjoin_cancel);
	}

	public ImageView getOkBtn(){
		return btn_ok;
	}

	public ImageView getCancelBtn(){
		return btn_cancel;
	}
}
