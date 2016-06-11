package ddoraemi.home.view;

import java.util.ArrayList;

import ddoraemi.home.model.Group;
import ddoraemi.home.model.ProgramData;
import android.view.View;

public interface Home_MainViewInterface {
	public void setViewPager(View v, ArrayList<ProgramData> data, ArrayList<Group> group);
	public void setProgramList(ArrayList<ProgramData> data , ArrayList<Group> group);
}
