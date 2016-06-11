package ddoraemi.myinfopage.view;

import java.util.ArrayList;
import java.util.Hashtable;

import android.graphics.Bitmap;
import android.view.View;
import ddoraemi.detailediteminfo.model.Afterword;
import ddoraemi.home.model.Group;
import ddoraemi.home.model.ProgramData;

public interface MypageViewInterface {
	public void navigateToNext();

	public void setViewPager(View v, ArrayList<Group> partcipateprogram,
			ArrayList<ProgramData> wishprogram, ArrayList<Group> pastprogram,Hashtable<Integer, Afterword> afterword);

	public void setArrayList(ArrayList<Group> partcipateprogram,
			ArrayList<ProgramData> wishprogram, ArrayList<Group> pastprogram,Hashtable<Integer, Afterword> afterword);

	public void setPage();

	public void renew();
	public void renewprofile(Bitmap photo);
	public void renewViewPager(View v, ArrayList<Group> particiategroup,
			ArrayList<ProgramData> wishprogram, ArrayList<Group> pastprogram,Hashtable<Integer, Afterword> afterword);

	public void renewArrayList(ArrayList<Group> partcipateprogram,
			ArrayList<ProgramData> wishprogram, ArrayList<Group> pastprogram,Hashtable<Integer, Afterword> afterword);
}
