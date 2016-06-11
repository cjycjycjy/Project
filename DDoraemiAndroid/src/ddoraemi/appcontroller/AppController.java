package ddoraemi.appcontroller;



import java.util.ArrayList;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import ddoraemi.home.view.HomeView;
import ddoraemi.start.User;

public class AppController extends Application {
	private String ServerIp;
	private String registrationID;
	private String id;
	private User user;
	PackageInfo pi;
	String version;
	HomeView rootActivity;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		this.ServerIp = "http://203.252.166.151:9090/skp";
		try {
			pi = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		version = pi.versionName;
	}

		public String getVersion(){
		return version;
	}
	public void setRootActivity(HomeView view)
	{
		this.rootActivity=view;
	}
	public HomeView getRootActivity()
	{
		return rootActivity;
	}
	public String getRegistrationID() {
		return registrationID;
	}

	public void setRegistrationID(String registrationID) {
		this.registrationID = registrationID;
	}

	public void setbookmark(int p_id){
		this.user.getbookmark().add(p_id);
	}
	public void delbookmark(int p_id){
		this.user.getbookmark().remove((Object)p_id);
	}
	public boolean searchbookmark(int p_id){
		ArrayList<Integer> ar = user.getbookmark();
		if(ar.contains(p_id)){
			
			return true;
		}else
			return false;
		
	}
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String getServerIp() {
		return ServerIp;
	}

	public void setServerIp(String serverIp) {
		ServerIp = serverIp;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}
}
