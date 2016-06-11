package ddoraemi.programlist.view;

import java.util.ArrayList;

import ddoraemi.home.model.ProgramData;
import ddoraemi.programlist.presenter.Programlist_location_presenter;
import ddoraemi.programlist.presenter.Programlist_location_presenter_Interface;
import ddoraemi.programlist.presenter.Programlist_type_presenter;
import ddoraemi.start.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Programlist_location_View extends Fragment implements Programlist_location_View_Interface,OnClickListener{

	Button btn_seoul;
	Button btn_gangwon;
	Button btn_chungcheong;
	Button btn_jeonla;
	Button btn_jeju;
	Button btn_kyungsang;

	Programlist_location_presenter_Interface presenter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_programlist_location, null);
		init(v);
		return v;
	}
	public void init(View v){
		btn_chungcheong = (Button)v.findViewById(R.id.btn_chungcheong);
		btn_gangwon = (Button)v.findViewById(R.id.btn_gangwon);
		btn_jeju = (Button)v.findViewById(R.id.btn_jeju);
		btn_jeonla = (Button)v.findViewById(R.id.btn_jeonla);
		btn_kyungsang = (Button)v.findViewById(R.id.btn_kyungsang);
		btn_seoul = (Button)v.findViewById(R.id.btn_seoul);

		btn_chungcheong.setOnClickListener(this);
		btn_gangwon.setOnClickListener(this);
		btn_jeju.setOnClickListener(this);
		btn_jeonla.setOnClickListener(this);
		btn_kyungsang.setOnClickListener(this);
		btn_seoul.setOnClickListener(this);

		presenter = new Programlist_location_presenter(this);
	}
	@Override
	public void goToList(ArrayList<ProgramData> list, String location) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getActivity(),Programlist_location_list_view.class);
		intent.putExtra("list",list);
		intent.putExtra("location", location);
		getActivity().startActivityForResult(intent, 50);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_chungcheong){
			presenter.validatecredential("충청도");
		}
		if(v.getId()==R.id.btn_gangwon){
			presenter.validatecredential("강원도");
		}
		if(v.getId()==R.id.btn_jeju){
			presenter.validatecredential("제주도");
		}
		if(v.getId()==R.id.btn_jeonla){
			presenter.validatecredential("전라도");
		}
		if(v.getId()==R.id.btn_kyungsang){
			presenter.validatecredential("경상도");
		}
		if(v.getId()==R.id.btn_seoul){
			presenter.validatecredential("경기도");
		}

	}
	
	

}