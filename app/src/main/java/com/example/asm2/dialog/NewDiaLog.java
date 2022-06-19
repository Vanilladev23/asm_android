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
import com.example.asm2.dao.ClassesDao;
import com.example.asm2.model.Classes;

public class NewDiaLog extends Dialog implements View.OnClickListener {
	private final Context context;
	private EditText etClassId, etClassName;

	public NewDiaLog(@NonNull Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_new_class);

		etClassId = findViewById(R.id.etClassId);
		etClassName = findViewById(R.id.etName);

		findViewById(R.id.btnSave).setOnClickListener(this);
		findViewById(R.id.btnWhitewash).setOnClickListener(this);
	}

	@SuppressLint("NonConstantResourceId")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnSave:
				Classes classes = new Classes();
				classes.setName(etClassName.getText().toString());
				ClassesDao dao = new ClassesDao(context);
				dao.insert(classes);
				Toast.makeText(context, "Thêm lớp thành công", Toast.LENGTH_SHORT).show();
				break;
			case R.id.btnWhitewash:
				etClassId.setText("");
				etClassName.setText("");
				break;
		}
	}
}
