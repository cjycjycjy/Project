package ddoraemi.dialog;

import ddoraemi.start.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;
import android.widget.TextView;

public class Dialog_logout extends Dialog{

	ImageView mBtn_ok;
	ImageView mBtn_cancel;

	public Dialog_logout(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		setContentView(R.layout.dialog_logout);
		getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		this.setCanceledOnTouchOutside(false);      // ���̾˷α� �ٱ����� ��ġ��, ���̾˷α� ������ �ʱ�
		this.setCancelable(true); // ��Ű�� ���̾˷α� �ݱ�
		mBtn_ok = (ImageView)findViewById(R.id.btn_dialog_confirmcancel_ok);
		mBtn_cancel = (ImageView)findViewById(R.id.btn_dialog_confirmcancel_cancel);
	}

	public ImageView getOkBtn(){
		return mBtn_ok;
	}
	public ImageView getCancelBtn(){
		return mBtn_cancel;
	}
}
