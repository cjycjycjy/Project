package ddoraemi.detailedgroupinfo.view;

import ddoraemi.home.model.Group;

public interface DetailedGroupInfo_InfoView_Interface {
	public void showMap();
	public void showDetailedChallengeInfo();
	public void goToJoinGroup(Group group);
	public void cancelGroup(int minus);
	public void showCancelDialog();
	public void dismissCancelDialog();
	public void goToTmap();
}
