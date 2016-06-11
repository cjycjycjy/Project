package sherlockphonez.myphonecontroll.view;

import java.io.Serializable;


public class Photo implements Serializable{
	String camera_time;
	
	String photoUrl;
	String url;
	private boolean isChecked=false;
	
	

	public Photo( String photoUrl,String camera_time) {
		// TODO Auto-generated constructor stub
		
		this.camera_time = camera_time;
		this.photoUrl = photoUrl;
		this.url=photoUrl;
	}
	
	public String getCamera_time() {
		return camera_time;
	}

	public void setCamera_time(String camera_time) {
		this.camera_time = camera_time;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public String getUrl(){
		return url;
	}
	
	public String getPhotoUrl(){
		return photoUrl;
	}
}
