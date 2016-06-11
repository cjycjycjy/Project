package sherlockphonez.main.controller;

public interface OnMainFinishedListener {
	public void setStartFailedError();
	public void setStopFailedError();
	public void onNetworkError();
	public void startService();
	public void stopService();
}
