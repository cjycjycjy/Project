package ddoraemi.myinfopage.model;

import android.app.Activity;
import ddoraemi.myinfopage.presenter.OnWrite_AfterwordFinishedListner;

public interface Write_AfterwordInteractorInterface {
	public void saveAfterword(final Activity context, final OnWrite_AfterwordFinishedListner listener,String u_id,int g_id,String a_content,float a_grade,String[] all_path);
}
