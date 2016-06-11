package ddoraemi.dialog;

import ddoraemi.start.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;
import android.widget.TextView;

public class Dialog_ApplyAdmin extends Dialog{

	TextView tv_applyrequest;
	ImageView btn_ok;
	public Dialog_ApplyAdmin(Context context, String text) {
		super(context);
		// TODO Auto-generated constructor stub
		setContentView(R.layout.dialog_basedialog);
		getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		this.setCanceledOnTouchOutside(false);      // ���̾˷α� �ٱ����� ��ġ��, ���̾˷α� ������ �ʱ�
		this.setCancelable(true); // ��Ű�� ���̾˷α� �ݱ�
		tv_applyrequest = (TextView)findViewById(R.id.dialog_basedialog_text);
		btn_ok = (ImageView)findViewById(R.id.dialog_basedialog_btnok);
		tv_applyrequest.setText(text);
		
	}
	
	public ImageView getBtnOk(){
		return btn_ok;
	}

}
