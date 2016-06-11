package ddoraemi.detailediteminfo.model;

import java.io.Serializable;

public class ChallengePicture implements Serializable{
	String a_photo_url;

	public ChallengePicture(String a_photo_url) {
		super();
		this.a_photo_url = a_photo_url;
		
	}
	public String getPhoto_url() {
		return a_photo_url;
	}
	public void setPhoto_url(String a_photo_url) {
		this.a_photo_url = a_photo_url;
	}

}
