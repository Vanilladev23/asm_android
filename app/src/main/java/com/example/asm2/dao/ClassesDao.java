package com.example.asm2.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm2.database.DbHelper;
import com.example.asm2.model.Classes;

import java.util.ArrayList;
import java.util.List;

public class ClassesDao {
	private final SQLiteDatabase db;

	public ClassesDao(Context context) {
		db = new DbHelper(context).getWritableDatabase();
	}

	public void insert(Classes emp) {
		ContentValues values = new ContentValues();
		values.put("id", emp.getId());
		values.put("name", emp.getName());
		db.insert("classes", null, values);
	}

	@SuppressLint("Range")
	public List<Classes> get(String sql, String ... selectionArgs) {
		List<Classes> list = new ArrayList<>();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		while(cursor.moveToNext()) {
			Classes classes = new Classes();
			classes.setId(cursor.getInt(cursor.getColumnIndex("id")));
			classes.setName(cursor.getString(cursor.getColumnIndex("name")));
			list.add(classes);
		}
		cursor.close();
		return list;
	}

	public void update(Classes classes) {
		ContentValues values = new ContentValues();
		values.put("id", classes.getId());
		values.put("name", classes.getName());
		db.update("classes", values, "id = ?", new String[] { String.valueOf(classes.getId()) });
	}

	public List<Classes> getAll() {
		return get("SELECT * FROM classes");
	}

	public void delete(int id) {
		db.delete("classes", "id = ?", new String[] { String.valueOf(id) });
	}
}
