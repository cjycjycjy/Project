package ddoraemi.adminmodehome.presenter;

import com.google.gson.JsonArray;

public interface OnAdminHome_FinishenListener {

	public void onSuccess(JsonArray result);
	public void onFail();
}
