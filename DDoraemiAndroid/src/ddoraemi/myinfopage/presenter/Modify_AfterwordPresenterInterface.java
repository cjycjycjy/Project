package ddoraemi.myinfopage.presenter;

import com.google.gson.JsonObject;

public interface Modify_AfterwordPresenterInterface {
	public void validateCredentials(String event);
	public void validateCredentials(String result,int a_id,String content,float grade,String[] photo,boolean isPhotochange);

}
