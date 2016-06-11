package ddoraemi.creategroup.model;

import android.content.Context;
import ddoraemi.creategroup.presenter.OnCreateGroup_FinishenListener;

public interface CreateGroup_Interactor_Inteface {
	public void createGroup(final Context context,final int p_id,
	final String u_id ,
	final String g_name, 
	final int g_want_persons, 
	final String g_info,
	final int g_open_year ,
	final int g_open_month ,
	final int g_open_day,
	final int g_close_year,
	final int g_close_month, 
	final int g_close_day ,
	final int g_start_year,
	final int g_start_month, 
	final int g_start_day ,
	final int g_start_hour ,
	final int g_start_min,
	final int g_end_hour,
	final int g_end_min,
	final OnCreateGroup_FinishenListener listener);
}
