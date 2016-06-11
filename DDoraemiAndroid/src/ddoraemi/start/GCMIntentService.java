package ddoraemi.start;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

import ddoraemi.appcontroller.AppController;
import ddoraemi.home.model.AlarmListHelper;
import ddoraemi.home.model.PushMessage;

public class GCMIntentService extends GCMBaseIntentService {
	AppController app;
	SQLiteDatabase db;
	AlarmListHelper helper;

	private static void generateNotification(Context context, String message) {

		int icon = R.drawable.logo2;
		long when = System.currentTimeMillis();
		boolean isRunning = false;

		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		Notification notification = new Notification(icon, message, when);
		Intent notificationIntent = new Intent();
		isRunning = isRunningProcess(context, "ddoraemi.start");
		if (!isRunning) {
			notificationIntent = new Intent(context, LoginActivity.class);
		}
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent intent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(context, "", message, intent);

		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(0, notification);

	}

	public static boolean isRunningProcess(Context context, String packageName) {

		boolean isRunning = false;

		ActivityManager actMng = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);

		List<RunningTaskInfo> list = actMng.getRunningTasks(1);
		ComponentName topActivity = list.get(0).topActivity;
		if (topActivity.getPackageName().equalsIgnoreCase(packageName)) {
			isRunning = true;
		}
		return isRunning;
	}

	@Override
	protected void onError(Context arg0, String arg1) {

	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		app = (AppController) getApplicationContext();
		helper = new AlarmListHelper(context);
		try {
			db = helper.getWritableDatabase();
		} catch (SQLException e) {
			db = helper.getReadableDatabase();
		}
		int pk = intent.getIntExtra("pk", 0);
		String type = intent.getStringExtra("type").toString();
		String u_id = intent.getStringExtra("u_id").toString();
		String p_g_name = intent.getStringExtra("p_g_name").toString();
		int year = Integer.parseInt(intent.getStringExtra("year").toString());
		int month = Integer.parseInt(intent.getStringExtra("month").toString());
		int day = Integer.parseInt(intent.getStringExtra("day").toString());
		int hour = Integer.parseInt(intent.getStringExtra("hour").toString());
		int minute = Integer.parseInt(intent.getStringExtra("minute")
				.toString());
		int p_g_id = Integer.parseInt(intent.getStringExtra("p_g_id")
				.toString());
		String img_url = intent.getStringExtra("img_url").toString();
		String message = intent.getStringExtra("message").toString();
		int ischecked = intent.getIntExtra("ischecked", 0);
		String getUserid = intent.getStringExtra("get_u_id");
		PushMessage msg = new PushMessage(pk, type, u_id, p_g_name, year,
				month, day, hour, minute, p_g_id, img_url, message, ischecked);

		if (type.equals("b")) {
			helper.insert(db, msg, getUserid);
		} else {
			helper.insert(db, msg, app.getId());
		}

		if (app.getUser().getU_alarm() == 1) {
			Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			vibrator.vibrate(1000);
			generateNotification(context, message);
			WakeUpScreen.acquire(getApplicationContext());
			WakeUpScreen.release();
		}

		app.getRootActivity().countMyAlarm();

	}

	@Override
	protected void onRegistered(Context context, String reg_id) {
		Log.e("registrationID.(GCM INTENTSERVICE)", reg_id);
		app = (AppController) getApplicationContext();
		app.setRegistrationID(reg_id);
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		Log.e("Ű�� �����մϴ�.(GCM INTENTSERVICE)", "���ŵǾ����ϴ�.");
	}

	public static class WakeUpScreen {

		private static PowerManager.WakeLock wakeLock;

		public static void acquire(Context context, long timeout) {

			PowerManager pm = (PowerManager) context
					.getSystemService(Context.POWER_SERVICE);
			wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP
					| PowerManager.FULL_WAKE_LOCK
					| PowerManager.ON_AFTER_RELEASE, context.getClass()
					.getName());

			if (timeout > 0)
				wakeLock.acquire(timeout);
			else
				wakeLock.acquire();

		}

		public static void acquire(Context context) {
			acquire(context, 0);
		}

		public static void release() {
			if (wakeLock.isHeld())
				wakeLock.release();
		}
	}

}
