package com.example.asm2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "asm2.db";
	public static final int DB_VERSION = 1;

	public DbHelper(@Nullable Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String classesSql = "CREATE TABLE classes (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"name TEXT NOT NULL)";
		String studentsSql = "CREATE TABLE students (id TEXT PRIMARY KEY, " +
				"name TEXT NOT NULL, dob TEXT NOT NULL, classid INTEGER NOT NULL, " +
				"FOREIGN KEY (classid) REFERENCES classes(id))";
		db.execSQL(classesSql);
		db.execSQL(studentsSql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String classesSql = "DROP TABLE IF EXISTS classes";
		String studentsSql = "DROP TABLE IF EXISTS students";
		db.execSQL(studentsSql);
		db.execSQL(classesSql);
		onCreate(db);
	}

}
