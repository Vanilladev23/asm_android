package com.example.asm2.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.asm2.R;
import com.example.asm2.dialog.NewDiaLog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.btnNewClass).setOnClickListener(this);
		findViewById(R.id.btnListClasses).setOnClickListener(this);
		findViewById(R.id.btnManageStudents).setOnClickListener(this);
	}

	@SuppressLint("NonConstantResourceId")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnNewClass:
				NewDiaLog newDiaLog = new NewDiaLog(this);
				newDiaLog.show();
				break;
			case R.id.btnListClasses:
				Intent intent = new Intent(this, ListClassesActivity.class);
				startActivity(intent);
				break;
			case R.id.btnManageStudents:
				Intent mngIntent = new Intent(this, ManageStudentActivity.class);
				startActivity(mngIntent);
				break;
		}
	}
}