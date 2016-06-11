package ddoraemi.detailedgroupinfo.presenter;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ddoraemi.detailedgroupinfo.model.JoinPersonInfo_Interactor;
import ddoraemi.detailedgroupinfo.model.JoinPersonInfo_Interactor_Interface;
import ddoraemi.detailedgroupinfo.view.JoinPersonListView;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.start.LoginActivity;
import ddoraemi.start.SplashActivity;
import ddoraemi.start.User;

public class JoinPersonInfo_presenter implements JoinPersoninfo_presenter_interface{

	JoinPersonListView view;
	JoinPersonInfo_Interactor_Interface interactor;
	ArrayList<User> user;

	public JoinPersonInfo_presenter(JoinPersonListView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		interactor = new JoinPersonInfo_Interactor();
		this.user = new ArrayList<>();
	}

	@Override
	public void validatecredential(String event, ArrayList<User> user) {
		// TODO Auto-generated method stub
		view.sendsms(user);
	}



}
