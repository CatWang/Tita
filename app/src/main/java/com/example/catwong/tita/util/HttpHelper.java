package com.example.catwong.tita.util;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.Excluder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by CuiH on 2017/9/24.
 */

public class HttpHelper {

	public final static int MSG_FAIL = 0;
	public final static int MSG_SUCCESS = 1;

	public final static String LOG_IN_URL = "user/log_in";
	public final static String EVENT_DATE_URL = "event/date";

	private final static String BASE_URL = "http://13.65.194.122:3000/api/";

	private static JsonParser parser = new JsonParser();

	private static String token;


	public static void setToken(String token) {
		HttpHelper.token = token;
	}

	public static void post(final Handler handler, final String subUrl,
							final String body, final boolean needToken) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					URL url = new URL(BASE_URL + subUrl);

					connection = (HttpURLConnection) url.openConnection();

					connection.setRequestMethod("POST");
					connection.setRequestProperty("Connection", "Keep-Alive");
					connection.setRequestProperty("Content-Type",
							"application/x-www-form-urlencoded");

					if (needToken) connection.setRequestProperty("x-access-token", token);

					connection.setConnectTimeout(50000);
					connection.setReadTimeout(50000);

					DataOutputStream out = new DataOutputStream(connection.getOutputStream());
					out.writeBytes(body);

					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));

					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null)
						response.append(line);

					JsonObject json = (JsonObject) parser.parse(response.toString());
					String result = json.get("result").getAsString();

					Message message = new Message();
					if (result.equals("success")) {
						message.what = MSG_SUCCESS;
						message.obj = json.get("data").getAsJsonObject();
					} else {
						message.what = MSG_FAIL;
					}

					handler.sendMessage(message);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (connection != null) connection.disconnect();
				}
			}
		}).start();
	}

	public static void get(final Handler handler, final String subUrl, final boolean needToken) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					URL url = new URL(BASE_URL + subUrl);

					connection = (HttpURLConnection) url.openConnection();

					connection.setRequestMethod("GET");
					connection.setRequestProperty("Connection", "Keep-Alive");

					if (needToken) connection.setRequestProperty("x-access-token", token);

					connection.setConnectTimeout(50000);
					connection.setReadTimeout(50000);

					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));

					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null)
						response.append(line);

					JsonObject json = (JsonObject) parser.parse(response.toString());
					String result = json.get("result").getAsString();

					Message message = new Message();
					if (result.equals("success")) {
						message.what = MSG_SUCCESS;
						message.obj = json.get("data").getAsJsonObject();
					} else {
						message.what = MSG_FAIL;
					}

					handler.sendMessage(message);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (connection != null) connection.disconnect();
				}
			}
		}).start();
	}

	public static String getDateString(Calendar c) {
		int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DATE);

		return year + "-" + month + "-" + day;
	}

	public static String getDateString(Date d) {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		return s.format(d);
	}

	public static Date getDate(String str) {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date d = null;
		try {
			d = s.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return d;
	}

	public static Date getTime(String str) {
		SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");

		Date d = null;
		try {
			d = s.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return d;
	}

//	public static String getDateTimeString(Date d) {
//		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//		int year = c.get(Calendar.YEAR);
//		int month = c.get(Calendar.MONTH)+1;
//		int day = c.get(Calendar.DATE);
//
//		return year+"-"+month+"-"+day;
//	}

}
