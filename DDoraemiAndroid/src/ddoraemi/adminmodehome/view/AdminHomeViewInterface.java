package ddoraemi.adminmodehome.view;

import java.util.ArrayList;

import ddoraemi.home.model.ProgramData;

public interface AdminHomeViewInterface {
	public void showFailureDialog();
	public void setSideMenu(ArrayList<ProgramData> array);
	public void showSideMenu();
	public void selectProgram(int p_id);
	public void goToUserMode();
}
