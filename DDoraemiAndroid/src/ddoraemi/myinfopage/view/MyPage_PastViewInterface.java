package ddoraemi.myinfopage.view;

import java.util.ArrayList;

import ddoraemi.detailediteminfo.model.Afterword;
import ddoraemi.home.model.Group;

public interface MyPage_PastViewInterface {
	public void notifyWritenAfterword(ArrayList<Group> items);
	public void goTodetail(Afterword item);
}
