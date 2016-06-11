package ddoraemi.dialog;

import ddoraemi.home.model.ProgramData;
import ddoraemi.start.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;
import android.widget.TextView;

public class Dialog_KakaoLink extends Dialog{
	TextView tv_applyrequest;
	ImageView btn_ok;
	public Dialog_KakaoLink(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setContentView(R.layout.dialog_basedialog);
		getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		this.setCanceledOnTouchOutside(false);      // ���̾˷α� �ٱ����� ��ġ��, ���̾˷α� ������ �ʱ�
		this.setCancelable(true); // ��Ű�� ���̾˷α� �ݱ�
		tv_applyrequest = (TextView)findViewById(R.id.dialog_basedialog_text);
		btn_ok = (ImageView)findViewById(R.id.dialog_basedialog_btnok);
		tv_applyrequest.setText("카카오톡 초대 메세지를 보내시겠습니까?");
		
	}
	public ImageView getBtnOk(){
		return btn_ok;
	}
	
	
}
