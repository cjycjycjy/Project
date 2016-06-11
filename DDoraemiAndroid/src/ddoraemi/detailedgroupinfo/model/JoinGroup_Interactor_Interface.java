package ddoraemi.detailedgroupinfo.model;

import android.content.Context;
import ddoraemi.detailedgroupinfo.presenter.OnJoinGroup_FinishedListener;

public interface JoinGroup_Interactor_Interface {
	public void joinGroup(final Context context, final int g_id, final String u_id,
			final int hopeperson,final OnJoinGroup_FinishedListener listener);
}
