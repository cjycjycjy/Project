package ddoraemi.detailedgroupinfo.presenter;

import ddoraemi.home.model.Group;

public interface DetailedGroupInfo_InfoView_presenter_Interface {
	public void validatecredential(String event, Group group);
	public void validatecredential(String event, Group group, boolean ismaster);
	public void validatecredential(String event, int p_id);
}
