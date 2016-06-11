package ddoraemi.applyadmin.presenter;

import com.google.gson.JsonObject;

public interface OnApply_Admin_Request_FinishedListener {
	public void onRequestSuccess(String result);
	public void onFailed();
}
