package ddoraemi.detailediteminfo.model;

import ddoraemi.detailediteminfo.presenter.OnDetailedItemInfo_View_FinishedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public interface DetailedItemInfo_View_Interactor_interface {
	public void getData(final Fragment context, OnDetailedItemInfo_View_FinishedListener listener);
}
