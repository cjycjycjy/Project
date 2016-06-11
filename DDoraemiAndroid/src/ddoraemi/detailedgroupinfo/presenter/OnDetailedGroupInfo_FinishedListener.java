package ddoraemi.detailedgroupinfo.presenter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface OnDetailedGroupInfo_FinishedListener {
	public void onDataGetSuccess(JsonArray data, JsonObject personinfo);
	public void onRenewDataGetSuccess(JsonArray data, JsonObject personinfo);
}
