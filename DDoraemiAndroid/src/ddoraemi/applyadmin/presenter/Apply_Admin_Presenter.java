package ddoraemi.applyadmin.presenter;

import com.google.gson.JsonObject;

import ddoraemi.applyadmin.model.Apply_Admin_Interactor;
import ddoraemi.applyadmin.model.Apply_Admin_Interactor_Interface;
import ddoraemi.applyadmin.view.Apply_Admin_View;

public class Apply_Admin_Presenter implements Apply_Admin_Presenter_Interface, OnApply_Admin_Request_FinishedListener{
	Apply_Admin_View view;
	Apply_Admin_Interactor_Interface interactor;
	
	public Apply_Admin_Presenter(Apply_Admin_View view) {
		// TODO Auto-generated constructor stub
		this.view = view;
		this.interactor = new Apply_Admin_Interactor();
	}
	@Override
	public void validatecredentail(String event, String u_id,String a_name,
			String a_phonenum, String a_p_phonenum, String a_email,String a_programinfo
			) {
		// TODO Auto-generated method stub
		if(event.equalsIgnoreCase("sendrequest")){
			if(a_name.isEmpty() || a_phonenum.isEmpty() || a_p_phonenum.isEmpty() ||
					a_email.isEmpty() || a_programinfo.isEmpty()){
				view.showExistEmpty();
			}
			else{
				view.showApplyDialog();
				interactor.sendRequest(view, u_id,a_name, a_phonenum, a_p_phonenum, a_email,a_programinfo, this);
			}
			
		}else if(event.equalsIgnoreCase("dismissdialog")){
			view.dismissDialog();
		}
	}
	@Override
	public void onRequestSuccess(String result) {
		// TODO Auto-generated method stub
		view.showApplyDialog();
	}
	@Override
	public void onFailed() {
		// TODO Auto-generated method stub
		
	}

}
