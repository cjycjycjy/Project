package ddoraemi.detailediteminfo.model;

import ddoraemi.detailediteminfo.presenter.OnDetailedItemInfo_FinishedListener;
import android.support.v4.app.FragmentActivity;

public interface DetailedItemInfo_Interactor_Interface {
	public void getData(final FragmentActivity context, final OnDetailedItemInfo_FinishedListener listener, final int p_id);
	public void renewData(final FragmentActivity context, final OnDetailedItemInfo_FinishedListener listener, final int p_id);
	public void registerbookmark(final FragmentActivity context, final OnDetailedItemInfo_FinishedListener listener,
			final int p_id, final String u_id);
	public void unregisterbookmark(final FragmentActivity context, final OnDetailedItemInfo_FinishedListener listener,
			final int p_id, final String u_id);
}