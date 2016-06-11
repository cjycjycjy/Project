package ddoraemi.myinfopage.presenter;

import java.util.ArrayList;

import android.content.Intent;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.detailediteminfo.model.AfterwordReply;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.home.model.Group;
import ddoraemi.myinfopage.model.Write_AfterwordInteractor;
import ddoraemi.myinfopage.model.Write_AfterwordInteractorInterface;
import ddoraemi.myinfopage.view.Write_AfterwordView;
import ddoraemi.myinfopage.view.Write_AfterwordViewInterface;

public class Write_AfterwordPresenter implements
		Write_AfterwordPresenterInterface, OnWrite_AfterwordFinishedListner {
	Write_AfterwordView view;

	private Write_AfterwordInteractorInterface Interactor;

	public Write_AfterwordPresenter(Write_AfterwordView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		Interactor = new Write_AfterwordInteractor();
	}

	@Override
	public void validateCredentials(String event) {
		// TODO Auto-generated method stub
		if (event.equals("back")) {
			view.gotoBack();
		}
		else if (event.equals("star1")) {
			view.selectScore(1);
		}
		else if (event.equals("star2")) {
			view.selectScore(2);
		}
		else if (event.equals("star3")) {
			view.selectScore(3);
		}
		else if (event.equals("star4")) {
			view.selectScore(4);
		}
		else if (event.equals("star5")) {
			view.selectScore(5);
		}
		else if(event.equals("pic"))
		{
			view.selctPic();
		}

	}

	@Override
	public void saveAfterword(String result) {
		if(result.equals("200"))
		{
			view.finish();
		}
		else
		{
			Dialog_BaseDialog dialog=new Dialog_BaseDialog(view, "후기등록에 실패하였습니다.");
			dialog.show();
		}
		
	}

	@Override
	public void validateCredentials(String event, String u_id, int g_id,
			String content, float grade, String[] photo) {
		if(event.equals("save"))
		{
			Interactor.saveAfterword(view, this, u_id,g_id,content,grade,photo);
		}
		
	}	
}
