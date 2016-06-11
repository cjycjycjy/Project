package sherlockphonez.join.controller;

public interface OnJoinFinishedListener {
	public void onExistError();
	public void onNetworkError();
	public void onFailedError();
	public void onSuccess();
	
}
