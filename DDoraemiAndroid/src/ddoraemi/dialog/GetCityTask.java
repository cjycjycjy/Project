package ddoraemi.dialog;

import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;

public class GetCityTask extends AsyncTask<Void, Void, Void> {
	Dialog_AddressSelect dialog;
	ArrayList<String> citys;
	public GetCityTask(Dialog_AddressSelect dialog)
	{
		this.dialog=dialog;
		citys=dialog.getCityArray();
	}
	@Override
	protected void onPostExecute(Void s) {
	}

	@Override
	protected Void doInBackground(Void... params) {
		String url = "http://openapi.epost.go.kr/postal/retrieveLotNumberAdressAreaCdService/retrieveLotNumberAdressAreaCdService/getBorodCityList?ServiceKey="
				+ dialog.getKey();
		XmlPullParserFactory factory;
		XmlPullParser parser;
		URL xmlUrl;
		try {
			xmlUrl = new URL(url);
			xmlUrl.openConnection().getInputStream();
			factory = XmlPullParserFactory.newInstance();
			parser = factory.newPullParser();
			String TAG_NAME = "";
			String City="";
			parser.setInput(xmlUrl.openStream(), "utf-8");
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_DOCUMENT) {
				} 
				else if (eventType == XmlPullParser.START_TAG) {
					TAG_NAME = parser.getName();
				} 
				else if (eventType == XmlPullParser.END_TAG) {
					if (parser.getName().equals("brtcNm")) {
						citys.add(City);
						City="";
					} 
					TAG_NAME = "";
				} 
				else if (eventType == XmlPullParser.TEXT) {
					switch (TAG_NAME) {
					case "brtcNm":
						if (parser.getText() != null) {
							City=parser.getText();
						}
					
					break ;
					}
				}
				eventType = parser.next();
			}
			Message msg = dialog.getHandler().obtainMessage();
			Bundle b = new Bundle();
			b.putInt("num", 0);
			msg.setData(b);
			dialog.getHandler().sendMessage(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}