package ddoraemi.dialog;

import ddoraemi.start.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;
import android.widget.TextView;

public class Dialog_cancelGroup extends Dialog{

	ImageView mBtn_ok;
	ImageView mBtn_cancel;
	TextView p_name;
	TextView p_date;
	public Dialog_cancelGroup(Context context, String name, String date) {
		// TODO Auto-generated constructor stub
		super(context);
		setContentView(R.layout.dialog_cancelgroup);
		getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		this.setCanceledOnTouchOutside(false);      // ���̾˷α� �ٱ����� ��ġ��, ���̾˷α� ������ �ʱ�
		this.setCancelable(true); // ��Ű�� ���̾˷α� �ݱ�
		mBtn_ok = (ImageView)findViewById(R.id.btn_dialog_confirmcancel_ok);
		mBtn_cancel = (ImageView)findViewById(R.id.btn_dialog_confirmcancel_cancel);
		p_name =(TextView)findViewById(R.id.tv_canceldialog_date);
		p_date = (TextView)findViewById(R.id.tv_canceldialog_date);
		p_name.setText(name);
		p_date.setText(date);
		
		
	}

	public ImageView getOkBtn(){
		return mBtn_ok;
	}
	public ImageView getCancelBtn(){
		return mBtn_cancel;
	}
}
