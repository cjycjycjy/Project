package ddoraemi.detailedgroupinfo.model;

import android.content.Context;
import ddoraemi.detailediteminfo.presenter.OnGetPicture_FinishedListener;

public interface DetailedGroupInfo_Picture_Interactor_Interface {
	public void getPicture(final int p_id, final Context context, final OnGetPicture_FinishedListener listener);
}
