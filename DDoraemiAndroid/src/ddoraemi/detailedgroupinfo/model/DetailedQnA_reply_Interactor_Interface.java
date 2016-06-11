package ddoraemi.detailedgroupinfo.model;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import ddoraemi.detailedgroupinfo.presenter.OnDetailedGroupInputqna_FinishedListener;
import ddoraemi.detailedgroupinfo.presenter.OnDetailedQnA_reply_FinishedListener;
import ddoraemi.detailedgroupinfo.view.DetailedQnA_reply_View;

public interface DetailedQnA_reply_Interactor_Interface {
	public void setReply(final Context context, final int q_id, final String u_id, final String q_r_content,
			final OnDetailedQnA_reply_FinishedListener listener);
	public void modifyReply(final Context context,
			final OnDetailedQnA_reply_FinishedListener listener, final int q_r_id , final int q_id, final String q_content);
	public void deleteReply(final Context context,
			final OnDetailedQnA_reply_FinishedListener listener, final int q_r_id , final int q_id);
	public void deleteQnA(final Context context, final OnDetailedQnA_reply_FinishedListener listener,final int q_id);

}
