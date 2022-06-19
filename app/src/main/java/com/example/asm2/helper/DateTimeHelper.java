package com.example.asm2.helper;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHelper {
	private static final String Pattern = "dd/MM/yyyy";

	public static Date toDate(String date) {
		@SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(Pattern);
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static String toString(Date date) {
		@SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(Pattern);
		return sdf.format(date);
	}
}
