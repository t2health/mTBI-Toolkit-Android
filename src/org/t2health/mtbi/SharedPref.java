package org.t2health.mtbi;

import android.content.SharedPreferences;

public class SharedPref {
	public static boolean getSendAnnonData(SharedPreferences sharedPref) {
		return sharedPref.getBoolean("send_anon_data", true);
	}
}
