package ddoraemi.detailediteminfo.view;

import java.util.ArrayList;

import android.content.Intent;
import ddoraemi.detailediteminfo.model.AfterwordReply;

public interface DetailedAfterword_reply_view_Interface {
	public void notifyListview(ArrayList<AfterwordReply> result);
	public void showPopMenu();
	public void modifyAfterword(Intent intent);
}
