package ddoraemi.dialog;

import ddoraemi.start.R;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Dialog_ChangeFavorite extends Dialog {

	ImageView mBtn_ok;
	ImageView mBtn_cancel;
	final int COUNTINTEREST = 8;
	ImageView traditionalfood;
	ImageView planting;
	ImageView havest;
	ImageView traditionalplay;
	ImageView cooking;
	ImageView craft;
	ImageView culture;
	ImageView nature;

	ToggleButton[] toggle;

	public Dialog_ChangeFavorite(Context context, ArrayList<String> favorite) {
		// TODO Auto-generated constructor stub
		super(context);
		setContentView(R.layout.dialog_changefavorite);
		getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		this.setCanceledOnTouchOutside(false); // ���̾˷α� �ٱ����� ��ġ��, ���̾˷α�
												// ������ �ʱ�
		this.setCancelable(true); // ��Ű�� ���̾˷α� �ݱ�
		mBtn_ok = (ImageView) findViewById(R.id.btn_dialog_confirmcancel_ok);
		mBtn_cancel = (ImageView) findViewById(R.id.btn_dialog_confirmcancel_cancel);

		toggle = new ToggleButton[COUNTINTEREST];
		toggle[0] = (ToggleButton) findViewById(R.id.join_fragmentthree_tranditionalfood);
		toggle[1] = (ToggleButton) findViewById(R.id.join_fragmentthree_planting);
		toggle[2] = (ToggleButton) findViewById(R.id.join_fragmentthree_harvest);
		toggle[3] = (ToggleButton) findViewById(R.id.join_fragmentthree_tranditionalplay);
		toggle[4] = (ToggleButton) findViewById(R.id.join_fragmentthree_cooking);
		toggle[5] = (ToggleButton) findViewById(R.id.join_fragmentthree_craft);
		toggle[6] = (ToggleButton) findViewById(R.id.join_fragmentthree_culture);
		toggle[7] = (ToggleButton) findViewById(R.id.join_fragmentthree_nature);

		for (int i = 0; i < favorite.size(); i++) {
			switch (favorite.get(i).trim()) {
			case "전통음식체험":
				toggle[0].setChecked(true);
				break;
			case "경작체험":
				toggle[1].setChecked(true);
				break;
			case "수확체험":
				toggle[2].setChecked(true);
				break;
			case "전통놀이체험":
				toggle[3].setChecked(true);
				break;
			case "요리체험":
				toggle[4].setChecked(true);
				break;
			case "공예체험":
				toggle[5].setChecked(true);
				break;
			case "문화체험":
				toggle[6].setChecked(true);
				break;
			case "자연체험":
				toggle[7].setChecked(true);
				break;
			}
		}
	}

	public ToggleButton[] getToggle() {
		return toggle;
	}

	public ImageView getOkBtn() {
		return mBtn_ok;
	}

	public ImageView getCancelBtn() {
		return mBtn_cancel;
	}
}
