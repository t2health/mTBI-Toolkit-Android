package com.t2.mtbi;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPref {
	public static boolean getSendAnnonData(SharedPreferences sharedPref) {
		return sharedPref.getBoolean("send_anon_data", true);
	}
}
