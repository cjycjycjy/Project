package ddoraemi.detailediteminfo.presenter;

import com.google.gson.JsonArray;

public interface OnDetailedAfterword_reply_FinishedListener {
	public void onDataSetSuccess(JsonArray result);
	public void onDeleteSuccess(String result);
}
