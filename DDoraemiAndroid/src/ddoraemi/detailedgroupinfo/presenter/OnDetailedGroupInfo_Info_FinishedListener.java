package ddoraemi.detailedgroupinfo.presenter;

import com.google.gson.JsonObject;

public interface OnDetailedGroupInfo_Info_FinishedListener {
	public void onCancelGroupSuccess(int minus);
	public void onDeleteGroupSuccess();
	public void onGoToIteminfo(JsonObject result);
}
