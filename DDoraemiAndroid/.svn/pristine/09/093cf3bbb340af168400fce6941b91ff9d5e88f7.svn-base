package ddoraemi.joining.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import ddoraemi.dialog.Dialog_AddressSelect;
import ddoraemi.dialog.Dialog_BaseDialog;
import ddoraemi.start.R;

public class Fragment_JoinTwo extends Fragment implements OnClickListener,
		OnCheckedChangeListener {
	TextView next;
	ImageView plus, minus;
	int age;
	String sex;
	RadioGroup radiogroup;
	TextView agetv;
	RadioButton man, woman;
	boolean isSexSelected;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_jointwo, null);
		init(v);
		return v;
	}

	public void init(View v) {
		age = 10;
		radiogroup = (RadioGroup) v
				.findViewById(R.id.join_fragmenttwo_radiogroup);
		radiogroup.setOnCheckedChangeListener(this);
		plus = (ImageView) v.findViewById(R.id.join_fragmenttwo_PlusBt);
		minus = (ImageView) v.findViewById(R.id.join_fragmenttwo_MinusBt);
		next = (TextView) v.findViewById(R.id.join_fragmenttwo_ButtonNext);
		agetv = (TextView) v.findViewById(R.id.join_fragmenttwo_agetv);
		next.setOnClickListener(this);
		plus.setOnClickListener(this);
		minus.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.join_fragmenttwo_ButtonNext) {
			if (ConfirmForNext()) {
				setActivityValue();
				setDefaultValue();
				((JoinActivity) getActivity()).setTitleThree();
				FragmentTransaction ft = getActivity()
						.getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.joinFragment, new Fragment_JoinThree());
				ft.setTransition(FragmentTransaction.TRANSIT_NONE);
				ft.addToBackStack(null);
				ft.commit();
			} else {
				Dialog_BaseDialog dialog = new Dialog_BaseDialog(getActivity(),
						"모든 항목에 응답하셔야\n다음 단계 진행이 가능합니다.");
				dialog.show();
			}

		} else if (v.getId() == R.id.join_fragmenttwo_MinusBt) {
			age--;
			if (age < 0) {
				age = 0;
			}
			agetv.setTextColor(Color.parseColor("#000000"));
			agetv.setText(String.valueOf(age) + "세");
		} else if (v.getId() == R.id.join_fragmenttwo_PlusBt) {
			age++;
			if (age > 100) {
				age = 100;
			}
			agetv.setTextColor(Color.parseColor("#000000"));
			agetv.setText(String.valueOf(age) + "세");
		} 
	}



	public boolean ConfirmForNext() {
		if (isSexSelected) {
			return true;
		}
		return false;
	}

	public void setDefaultValue() {
		radiogroup.clearCheck();
		isSexSelected = false;
		age = 10;
		agetv.setTextColor(Color.parseColor("#868585"));
		agetv.setText(String.valueOf(age) + "세");

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (checkedId == R.id.join_fragmenttwo_RadioMan) {
			sex = "M";
			isSexSelected = true;
		} else if (checkedId == R.id.join_fragmenttwo_RadioWoman) {
			sex = "F";
			isSexSelected = true;
		}

	}

	public void setActivityValue() {
		
		((JoinActivity) getActivity()).setChildage(age);
		((JoinActivity) getActivity()).setChildsex(sex);
	}

}
