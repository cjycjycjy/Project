package ddoraemi.myinfopage.presenter;

import android.view.View;
import ddoraemi.myinfopage.view.CustomGallery_View;

public class CustomGallery_Presenter implements CustomGallery_PresenterInterface{
	CustomGallery_View view;
	public CustomGallery_Presenter(CustomGallery_View view) {
		// TODO Auto-generated constructor stub
		this.view = view;
	}
	@Override
	public void validateCredentials(String event) {
		// TODO Auto-generated method stub
		if(event.equals("ok"))
		{
			view.clickBtnOK();
		}
		
	}
	@Override
	public void validateCredentials(String event, int position,View v) {
		if(event.equals("gridclick"))
		{
			view.clickGrid(position,v);
		}
		
	}

}
