package ddoraemi.detailedgroupinfo.presenter;

import ddoraemi.home.model.Group;
import ddoraemi.start.User;

public interface JoinGroup_presenter_Interface {
	public void validatecredential(String event, Group group, User user, int hopeperson);
	public void validatecredential(String event);
}
