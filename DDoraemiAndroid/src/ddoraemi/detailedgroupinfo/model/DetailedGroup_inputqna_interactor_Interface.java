package ddoraemi.detailedgroupinfo.model;

import android.support.v4.app.FragmentActivity;
import ddoraemi.detailedgroupinfo.presenter.OnDetailedGroupInfo_FinishedListener;
import ddoraemi.detailedgroupinfo.presenter.OnDetailedGroupInputqna_FinishedListener;

public interface DetailedGroup_inputqna_interactor_Interface {
	public void setData(final FragmentActivity context,
			final OnDetailedGroupInputqna_FinishedListener listener, final int g_id , final String u_id, final String q_content);
}
