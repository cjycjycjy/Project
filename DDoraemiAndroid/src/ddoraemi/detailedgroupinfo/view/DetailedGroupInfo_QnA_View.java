package ddoraemi.detailedgroupinfo.view;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailedgroupinfo.model.QnA;
import ddoraemi.detailedgroupinfo.presenter.DetailedGroupInfo_QnA_presenter;
import ddoraemi.detailedgroupinfo.presenter.DetailedGroupInfo_QnA_presenter_Interface;
import ddoraemi.detailediteminfo.view.ScrollTabHolderFragment;
import ddoraemi.start.R;
import ddoraemi.volley.VolleySingleton;

public class DetailedGroupInfo_QnA_View extends ScrollTabHolderFragment implements OnScrollListener,
OnItemClickListener, DetailedGroupInfo_QnA_View_Interface,OnClickListener{

	ImageLoader mImageLoader;
	RequestQueue mRequestQueue;
	ArrayList<QnA> qna;
	ListView listview;
	DetailedGroupInfo_QnAListAdapter adapter;
	DetailedGroupInfo_QnA_presenter_Interface presenter;
	EditText et_inputqna;
	ImageView btn_inputqna;
	AppController app;
	NetworkImageView userimg;
	int g_id;


	public DetailedGroupInfo_QnA_View(ArrayList<QnA> qna, int g_id) {
		// TODO Auto-generated constructor stub

		this.qna = qna;
		this.g_id = g_id;
		

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_qnalist, null);
		View placeHolderView = inflater.inflate(R.layout.view_header_placeholder, listview, false);
		init(v,placeHolderView);
		return v;
	}

	public void init(View v, View placeHolderView){

		mRequestQueue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(getActivity()).getImageLoader();

		this.presenter = new DetailedGroupInfo_QnA_presenter(this);
		app = (AppController)getActivity().getApplicationContext();
		listview = (ListView)v.findViewById(R.id.lv_group_qna);
		listview.addHeaderView(placeHolderView);
		listview.setOnItemClickListener(this);
		adapter=new DetailedGroupInfo_QnAListAdapter(getActivity(), R.layout.item_qnalist, qna);
		listview.setAdapter(adapter);

		listview.setOnScrollListener(this);
		et_inputqna = (EditText)v.findViewById(R.id.et_inputqna);
		btn_inputqna = (ImageView)v.findViewById(R.id.btn_inputqna);
		btn_inputqna.setOnClickListener(this);

		userimg = (NetworkImageView) v.findViewById(R.id.iv_inputqna_user);
		userimg.setDefaultImageResId(R.drawable.usericon);
		userimg.setImageUrl(
				app.getServerIp() + "/user_photo/"
						+ app.getUser().getU_photo_url(), mImageLoader);
		
	}
	@Override
	public void adjustScroll(int scrollHeight) {
		// TODO Auto-generated method stub
		listview.setSelectionFromTop(1, listview.getMeasuredHeight());

	}
	@Override
	public void onScrollViewScroll(int scrollheight, int spageCount) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		if (mScrollTabHolder != null)
			mScrollTabHolder.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount, 1);

	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		if(position!=0)
			presenter.validatecredential("detailedqna",null,null,-1,(QnA)listview.getItemAtPosition(position), position);

	}

	@Override
	public void goToDetailedQnA(QnA qna, int position) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this.getActivity(), DetailedQnA_reply_View.class);
		intent.putExtra("qna", qna);
		intent.putExtra("position", position);
		startActivityForResult(intent, 0);	
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == 100){
			renewQnAList((ArrayList<QnA>)data.getSerializableExtra("qna"));
		}
	}
	
	
	@Override
	public void renewQnAList(ArrayList<QnA> qna){
		this.qna.clear();
		this.qna.addAll(qna);
		adapter.notifyDataSetChanged();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_inputqna){
			presenter.validatecredential("inputqna",app.getId(),et_inputqna.getText().toString(),g_id,null,-1);
			et_inputqna.setText("");
			v.clearFocus();
			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(et_inputqna.getWindowToken(), 0);
		}
	}

}