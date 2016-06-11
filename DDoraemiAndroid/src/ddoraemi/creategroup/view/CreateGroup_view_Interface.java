package ddoraemi.creategroup.view;

import java.util.ArrayList;

import ddoraemi.home.model.Group;

public interface CreateGroup_view_Interface {
	public void showDatePopUp();
	public void showOverlapDialog();
	public void clickMinusBtn();
	public void clickPlusBtn();
	public void setDate(int g_open_year, int g_open_month, int g_open_day,
			int g_close_year, int g_close_monte, int g_close_day,
			int g_start_year, int g_start_month, int g_start_day,
			int g_start_hour, int g_start_minute, int g_end_hour,
			int g_end_minute);
	public void getDateInfo(Group temp);
	public void createfinish(ArrayList<Group> group);
}
