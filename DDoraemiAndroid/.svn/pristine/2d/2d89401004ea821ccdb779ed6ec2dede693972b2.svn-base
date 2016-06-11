package ddoraemi.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import ddoraemi.start.R;

public class Dialog_ProfileImgDialog extends Dialog 
{
    private Context mContext = null;
    private TextView camera = null;
    private TextView album = null;
     
     
    public Dialog_ProfileImgDialog(Context context)
    {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        setContentView(R.layout.dialog_profileimgdialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setCanceledOnTouchOutside(false);      // ���̾˷α� �ٱ����� ��ġ��, ���̾˷α� ������ �ʱ�
        this.setCancelable(true); // ��Ű�� ���̾˷α� �ݱ�
        mContext = context;
        initComponent();
    }
     
    private void initComponent(){
        camera = (TextView)findViewById(R.id.dialog_profiledialog_camera);
        album = (TextView)findViewById(R.id.dialog_profiledialog_album);
    }
    
    public TextView getCameraBtn(){
		return camera;
	}
	public TextView getAlbumBtn(){
		return album;
	}

}
