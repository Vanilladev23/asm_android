package com.example.asm2.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.asm2.R;
import com.example.asm2.dao.StudentsDao;
import com.example.asm2.model.Students;

import java.util.List;

public class NewDiaLogFind extends Dialog implements View.OnClickListener {
	private final Context context;
	private EditText etFindId;
	private StudentsDao dao;
	private List<Students> students;
	public NewDiaLogFind(@NonNull Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_new_find_student);

		etFindId = findViewById(R.id.etFind);
		findViewById(R.id.btnFindId).setOnClickListener(this);
	}

	@SuppressLint("NonConstantResourceId")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnFindId:
				// search by student id
				dao = new StudentsDao(context);
				students = dao.findById(etFindId.getText().toString());

				if (students.size() > 0) {
					Toast.makeText(context, "Tìm thấy " + students.size() + " kết quả", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(context, "Không tìm thấy kết quả", Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + v.getId());
		}
	}
}
