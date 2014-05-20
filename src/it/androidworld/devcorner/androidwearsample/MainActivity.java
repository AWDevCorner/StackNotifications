package it.androidworld.devcorner.androidwearsample;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void mostraNotifica(View view)
	{
		Bitmap bitmapMila = BitmapFactory.decodeResource(getResources(), R.drawable.awlogo);

		// Nuke all previous notifications and generate unique ids
		NotificationManagerCompat.from(this).cancelAll();
		int notificationId = 0;

		// String to represent the group all the notifications will be a part of
		final String GROUP_KEY_MESSAGES = "group_key_messages";

		// Group notification that will be visible on the phone
		NotificationCompat.Builder builderG = new NotificationCompat.Builder(this)
		    .setContentTitle("Notifiche AndroidWearSample")
		    .setContentText("Nuove notifiche dal team di AndroidWorld")
		    .setSmallIcon(R.drawable.ic_launcher)
		    .setLargeIcon(bitmapMila);
		Notification summaryNotification = new WearableNotifications.Builder(builderG)
		    .setGroup(GROUP_KEY_MESSAGES, WearableNotifications.GROUP_ORDER_SUMMARY)
		    .build();

		// Separate notifications that will be visible on the watch
		Intent viewIntent1 = new Intent(this, MainActivity.class);
		PendingIntent viewPendingIntent1 =
		    PendingIntent.getActivity(this, notificationId+1, viewIntent1, 0);
		NotificationCompat.Builder builder1 = new NotificationCompat.Builder(this)
		    .addAction(R.drawable.ic_action_done, "Apri l'app!", viewPendingIntent1)
		    .setContentTitle("AndroidWorld")
		    .setContentText("DevCorner - AndroidWear e le Stacking Notifications")
		    .setSmallIcon(R.drawable.ic_launcher);
		Notification notification1 = new WearableNotifications.Builder(builder1)
		    .setGroup(GROUP_KEY_MESSAGES)
		    .build();

		Intent viewIntent2 = new Intent(this, MainActivity.class);
		PendingIntent viewPendingIntent2 =
		     PendingIntent.getActivity(this, notificationId+2, viewIntent2, 0);
		NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this)
		    .addAction(R.drawable.ic_action_done, "Ok, ma apri l'app!", viewPendingIntent2)
		    .setContentTitle("Ligas?")
		    .setContentText("Nicola è l'Oscuro Signore, ma non ditelo in giro...")
		    .setSmallIcon(R.drawable.ic_launcher);
		Notification notification2 = new WearableNotifications.Builder(builder2)
		    .setGroup(GROUP_KEY_MESSAGES)
		    .build();

		// Issue the group notification
		NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
		notificationManager.notify(notificationId+0, summaryNotification);

		// Issue the separate wear notifications
		notificationManager.notify(notificationId+2, notification2);
		notificationManager.notify(notificationId+1, notification1);	
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
