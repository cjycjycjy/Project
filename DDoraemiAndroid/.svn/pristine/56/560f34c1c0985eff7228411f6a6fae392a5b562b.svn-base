package ddoraemi.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import ddoraemi.start.R;

public class Dialog_ConfirmPersonal extends Dialog implements android.view.View.OnClickListener
{
    private Context mContext = null;
    private ImageView mBtn_ok = null;
     
     
    public Dialog_ConfirmPersonal(Context context)
    {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_confirmpersonal);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setCanceledOnTouchOutside(false);      // 다이알로그 바깥영역 터치시, 다이알로그 닫히지 않기
        this.setCancelable(true); // 백키로 다이알로그 닫기
         
        mContext = context;
        initComponent();
    }
     
    private void initComponent(){//
        mBtn_ok = (ImageView)findViewById(R.id.dialog_confirmpersonal_BtnOk);
        mBtn_ok.setOnClickListener(this); // 클릭 이벤트 등록
    }
    
    @Override
    public void show()
    {
        super.show();
    }
 
    @Override
    public void dismiss()
    {
        super.dismiss();
    }
     
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.dialog_confirmpersonal_BtnOk)
		{
			this.dismiss();
		}
		
	}   
}
