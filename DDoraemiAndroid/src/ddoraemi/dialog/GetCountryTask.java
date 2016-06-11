package ddoraemi.dialog;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;

public class GetCountryTask extends AsyncTask<Void, Void, Void> {
	Dialog_AddressSelect dialog;
	String citycode;
	ArrayList<String> countrys;

	public GetCountryTask(Dialog_AddressSelect dialog, String citycode) {
		this.dialog = dialog;
		this.citycode = citycode;
		countrys = dialog.getCountryArray();
	}

	@Override
	protected void onPostExecute(Void s) {
	}

	@Override
	protected Void doInBackground(Void... params) {
		String citydata="";
		try {
			citydata = URLEncoder.encode(citycode, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url = "http://openapi.epost.go.kr/postal/retrieveLotNumberAdressAreaCdService/retrieveLotNumberAdressAreaCdService/getSiGunGuList?ServiceKey="
				+ dialog.getKey() + "&brtcCd=" + citydata;
		XmlPullParserFactory factory;
		XmlPullParser parser;
		URL xmlUrl;
		try {
			xmlUrl = new URL(url);
			xmlUrl.openConnection().getInputStream();
			factory = XmlPullParserFactory.newInstance();
			parser = factory.newPullParser();
			String TAG_NAME = "";

			String City = "";

			InputStream is = xmlUrl.openStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[16384];
			while ((nRead = is.read(data)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
			String str = new String(buffer.toByteArray());

			parser.setInput(xmlUrl.openStream(), "utf-8");
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_DOCUMENT) {
				} else if (eventType == XmlPullParser.START_TAG) {
					TAG_NAME = parser.getName();
				} else if (eventType == XmlPullParser.END_TAG) {
					if (parser.getName().equals("signguCd")) {
						if((!City.equals("북제주군")&&!City.equals("남제주군")))
						countrys.add(City);
						City = "";
					}
					TAG_NAME = "";
				} else if (eventType == XmlPullParser.TEXT) {
					switch (TAG_NAME) {
					case "signguCd":
						if (parser.getText() != null) {
							City = parser.getText();
						}

						break;
					}
				}
				eventType = parser.next();
			}
			Message msg = dialog.getHandler().obtainMessage();
			Bundle b = new Bundle();
			b.putInt("num", 1);
			msg.setData(b);
			dialog.getHandler().sendMessage(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}