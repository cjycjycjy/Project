package ddoraemi.adminmodehome.presenter;

import com.google.gson.JsonObject;

public interface OnAdminHomeMain_FinishenListener {

	public void onSuccess(JsonObject result);
	public void onRenew(JsonObject result);
	public void onFail();
}
