package ddoraemi.creategroup.presenter;

import com.google.gson.JsonObject;

public interface OnCreateGroup_FinishenListener {
	public void onCreateGroupSuccess(JsonObject result);
	public void onfailure();
}
