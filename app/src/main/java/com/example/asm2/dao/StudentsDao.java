package com.example.asm2.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm2.database.DbHelper;
import com.example.asm2.helper.DateTimeHelper;
import com.example.asm2.model.Students;

import java.util.ArrayList;
import java.util.List;

public class StudentsDao {
	private final SQLiteDatabase db;

	public StudentsDao(Context context) {
		db = new DbHelper(context).getWritableDatabase();
	}

	public void insert(Students emp) {
		ContentValues values = new ContentValues();
		values.put("id", emp.getId());
		values.put("name", emp.getName());
		values.put("dob", DateTimeHelper.toString(emp.getDob()));
		values.put("classid", emp.getClassId());
		db.insert("students", null, values);
	}

	@SuppressLint("Range")
	public List<Students> get(String sql, String ... selectionArgs) {
		List<Students> list = new ArrayList<>();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		while(cursor.moveToNext()) {
			Students students = new Students();
			students.setId(cursor.getString(cursor.getColumnIndex("id")));
			students.setName(cursor.getString(cursor.getColumnIndex("name")));
			students.setDob(DateTimeHelper.toDate(cursor.getString(cursor.getColumnIndex("dob"))));
			students.setClassId(cursor.getInt(cursor.getColumnIndex("classid")));
			list.add(students);
		}
		cursor.close();
		return list;
	}

	public List<Students> getAll() {
		return get("SELECT * FROM students");
	}

	public void update(Students emp) {
		ContentValues values = new ContentValues();
		values.put("name", emp.getName());
		values.put("dob", DateTimeHelper.toString(emp.getDob()));
		values.put("classid", emp.getClassId());
		db.update("students", values, "id = ?", new String[] { String.valueOf(emp.getId())});
	}

	public List<Students> findById(String id) {
		List<Students> list = get("SELECT * FROM students WHERE id = ?", id);
		if(list.size() > 0) {
			Students students = list.get(0);
			students.setId(id);
			update(students);
		} else {
			Students students = new Students();
			students.setId(id);
			insert(students);
		}

		return list;
	}

	public List<Students> getAllByClass(Integer classId) {
		return get("SELECT * FROM students WHERE classid = ?", String.valueOf(classId));
	}

	public void delete(String id) {
		db.delete("students", "id = ?", new String[]{id});
	}

}

