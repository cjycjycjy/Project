package ddoraemi.myinfopage.presenter;

import com.google.gson.JsonObject;

public interface OnMyPageFinishedListner {
	public void OnGetDataSuccess(JsonObject data);
	public void OnGetDataSuccessForRenew(JsonObject data);

}
