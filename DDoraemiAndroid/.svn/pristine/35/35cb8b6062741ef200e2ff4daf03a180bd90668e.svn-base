package ddoraemi.myinfopage.presenter;

import java.util.ArrayList;

import android.content.Intent;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.myinfopage.model.Modify_AfterwordInteractor;
import ddoraemi.myinfopage.model.Modify_AfterwordInteractorInterface;
import ddoraemi.myinfopage.view.Modify_AfterWordView;

public class Modify_AfterwordPresenter implements
		Modify_AfterwordPresenterInterface, OnModify_AfterwordFinishedListner {
	Modify_AfterWordView view;
	Modify_AfterwordInteractorInterface interactor;

	public Modify_AfterwordPresenter(Modify_AfterWordView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		interactor = new Modify_AfterwordInteractor();
	}

	@Override
	public void validateCredentials(String event) {
		// TODO Auto-generated method stub
		if (event.equals("back")) {
			view.gotoBack();
		} else if (event.equals("star1")) {
			view.selectScore(1);
		} else if (event.equals("star2")) {
			view.selectScore(2);
		} else if (event.equals("star3")) {
			view.selectScore(3);
		} else if (event.equals("star4")) {
			view.selectScore(4);
		} else if (event.equals("star5")) {
			view.selectScore(5);
		} else if (event.equals("pic")) {
			view.selctPic();
		} else if (event.equals("deleteallpic")) {
			view.deleteallpic();
		}
	}

	@Override
	public void validateCredentials(String result, int a_id, String content,
			float grade, String[] photo, boolean isPhotochange) {
		interactor.saveAfterword(view, this, a_id, content, grade, photo,
				isPhotochange);

	}

	@Override
	public void modifyAfterword(JsonObject result) {
		if (result.get("result").getAsString().equals("200")) {
			JsonObject afterwordob = result.getAsJsonObject("afterword");
			Intent intent = new Intent();
			String a_content = afterwordob.get("a_content").getAsString();
			float a_grade = afterwordob.get("a_grade").getAsFloat();
			JsonArray photo = afterwordob.get("a_photo_url").getAsJsonArray();
			ArrayList<String> photoarray = new ArrayList<>();
			for (int i = 0; i < photo.size(); i++) {
				JsonObject obj = (JsonObject) photo.get(i);
				String url = obj.get("a_photo_url").getAsString();
				photoarray.add(url);
			}
			intent.putExtra("content", a_content);
			intent.putExtra("grade", a_grade);
			intent.putExtra("photo", photoarray);
			view.setResult(100, intent);
			view.finish();

		} else {
			Dialog_BaseDialog dialog=new Dialog_BaseDialog(view, "수정에 실패하였습니다.");
			dialog.show();
		}

	}

}
