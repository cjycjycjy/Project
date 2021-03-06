package ddoraemi.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import ddoraemi.joining.view.Fragment_JoinOne;
import ddoraemi.start.R;

public class Dialog_ConfirmUseId extends Dialog implements
		android.view.View.OnClickListener {
	private Context mContext = null;
	private ImageView mBtn_ok, mBtn_cancel = null;
	private Fragment_JoinOne fragmentone;

	public Dialog_ConfirmUseId(Context context, Fragment_JoinOne fragment) {
		super(context);//
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_confirmuseid);
		getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		this.setCanceledOnTouchOutside(false); // 다이알로그 바깥영역 터치시, 다이알로그 닫히지 않기
		this.setCancelable(true); // 백키로 다이알로그 닫기
		this.fragmentone = fragment;
		mContext = context;
		initComponent();
	}

	private void initComponent() {
		mBtn_ok = (ImageView) findViewById(R.id.dialog_confirmuse_btnok);
		mBtn_cancel = (ImageView) findViewById(R.id.dialog_confirmuse_btncancel);
		mBtn_cancel.setOnClickListener(this);
		mBtn_ok.setOnClickListener(this); // 클릭 이벤트 등록
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void dismiss() {
		super.dismiss();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.dialog_confirmuse_btnok) {
			fragmentone.setIsoverlapid(true);
			this.dismiss();
		} else if (v.getId() == R.id.dialog_confirmuse_btncancel) {
			fragmentone.setDefaultEditID();
			this.dismiss();
		}

	}
}
