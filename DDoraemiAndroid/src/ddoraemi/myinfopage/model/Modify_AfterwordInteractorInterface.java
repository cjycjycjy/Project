package ddoraemi.myinfopage.model;

import android.app.Activity;
import ddoraemi.myinfopage.presenter.OnModify_AfterwordFinishedListner;

public interface Modify_AfterwordInteractorInterface {
	public void saveAfterword(final Activity context, final OnModify_AfterwordFinishedListner listener,int a_id,String a_content,float a_grade,String[] all_path,boolean photochange);
}
