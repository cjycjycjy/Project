package ddoraemi.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import ddoraemi.start.R;

public class Dialog_Admin_Selectlocation extends Dialog{

	Button seoul,chungcheong,gangwon,jeonla,kyungsang,jeju;
	public Dialog_Admin_Selectlocation(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);//
		setContentView(R.layout.dialog_admin_select_location);
		getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		this.setCanceledOnTouchOutside(false);      // ���̾˷α� �ٱ����� ��ġ��, ���̾˷α� ������ �ʱ�
		this.setCancelable(true); // ��Ű�� ���̾˷α� �ݱ�
		seoul = (Button)findViewById(R.id.tv_admin_select_seoul);
		chungcheong = (Button)findViewById(R.id.tv_admin_select_chungcheong);
		gangwon = (Button)findViewById(R.id.tv_admin_select_gangwon);
		jeonla = (Button)findViewById(R.id.tv_admin_select_jeonla);
		kyungsang = (Button)findViewById(R.id.tv_admin_select_kyungsang);
		jeju = (Button)findViewById(R.id.tv_admin_select_jeju);
	}
	public Button getSeoul() {
		return seoul;
	}
	public Button getChungcheong() {
		return chungcheong;
	}
	public Button getGangwon() {
		return gangwon;
	}
	public Button getJeonla() {
		return jeonla;
	}
	public Button getKyungsang() {
		return kyungsang;
	}
	public Button getJeju() {
		return jeju;
	}



}
