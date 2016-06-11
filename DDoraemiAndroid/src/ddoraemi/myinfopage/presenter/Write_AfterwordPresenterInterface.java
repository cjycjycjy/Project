package ddoraemi.myinfopage.presenter;

import com.google.gson.JsonArray;

public interface Write_AfterwordPresenterInterface {
	public void validateCredentials(String event) ;
	public void validateCredentials(String result,String u_id,int g_id,String content,float grade,String[] photo);
}
