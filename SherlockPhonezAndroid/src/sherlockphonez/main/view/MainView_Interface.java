package sherlockphonez.main.view;

public interface MainView_Interface {
	public void showProgress();
	public void hideProgress();
	public void setStartFailedError();
	public void setStopFailedError();
	public void setNetworkError();
	public void	setServiceStart();
	public void setServiceStop();
	public void showServiceOnLogo();
	public void showServiceOffLogo();
	public void showServiceOnButton();
	public void hideServiceOnButton();
	public void showServiceOffButton();
	public void hideServiceOffButton();
	public void nevigateToJoin();
	public void nevigateToChangeInfo();
	public void nevigateToOtherPhoneLogin();
	public void nevagateToWirelessControl();
}
