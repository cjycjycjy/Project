package ddoraemi.detailedgroupinfo.view;

import java.util.ArrayList;

import ddoraemi.detailedgroupinfo.model.QnA;

public interface DetailedGroupInfo_QnA_View_Interface {
	public void goToDetailedQnA(QnA qna, int position);
	public void renewQnAList(ArrayList<QnA> qna);
}
