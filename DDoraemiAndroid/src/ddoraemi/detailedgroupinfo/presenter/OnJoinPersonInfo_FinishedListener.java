package ddoraemi.detailedgroupinfo.presenter;

import com.google.gson.JsonObject;

public interface OnJoinPersonInfo_FinishedListener {
	public void onDataGetSuccess(JsonObject result);
	public void onDataGetFailed();
}
