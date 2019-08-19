package com.example.harsh.fusionflix.Utility;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/*
 * IsNetworkConnection.java is static class to check network connection. return true is yes otherwise false.
 * */
public class IsNetworkConnection {
	/**
	 * Checks if the device has Internet connection.
	 * @return <code>true</code> if the phone is connected to the Internet.
	 */
	public static boolean checkNetworkConnection(Context context) {
		boolean connected = false;
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivityManager != null && connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnected()==true || (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
					connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)) {
                connected = Connection.isConnectedFast(context);
				//we are connected to a network
			//	connected = true;
			} else
				connected = false;
			return connected;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return connected;
	}
}
