package ddoraemi.detailediteminfo.presenter;

import android.content.Intent;



public interface DetailedItemInfo_Presenter_Interface {
	public void createGroup();
	public void showMap();
	public void validatecredential(String event, int p_id,Intent intent, boolean bookmarked);
	
}
