package ddoraemi.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import ddoraemi.start.R;

public class Dialog_BaseDialog extends Dialog implements android.view.View.OnClickListener
{
    private Context mContext = null;
    private ImageView mBtn_ok = null;
    private TextView text;
    String str_text;
     
     
    public Dialog_BaseDialog(Context context,String str_text)
    {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        setContentView(R.layout.dialog_basedialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setCanceledOnTouchOutside(false);      // ���̾˷α� �ٱ����� ��ġ��, ���̾˷α� ������ �ʱ�
        this.setCancelable(true); // ��Ű�� ���̾˷α� �ݱ�
        this.str_text=str_text;
        mContext = context;
        initComponent();
    }
     
    private void initComponent(){
        mBtn_ok = (ImageView)findViewById(R.id.dialog_basedialog_btnok);
        mBtn_ok.setOnClickListener(this); // Ŭ�� �̺�Ʈ ���
        text =(TextView)findViewById(R.id.dialog_basedialog_text);
        text.setText(str_text);
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
		if(v.getId()==R.id.dialog_basedialog_btnok)
		{
			this.dismiss();
		}
	}   
}
