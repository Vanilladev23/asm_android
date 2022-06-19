package com.example.asm2.activites;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.asm2.R;
import com.example.asm2.adapters.ClassesAdapter;
import com.example.asm2.dao.ClassesDao;
import com.example.asm2.model.Classes;

import java.util.List;

public class ListClassesActivity extends AppCompatActivity {
	private ListView lvClasses;
	private List<Classes> listClasses;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_classes);

		lvClasses = findViewById(R.id.lvClasses);

		fillClassesListView();

		lvClasses.setOnItemClickListener((parent, view, position, id) -> {
			AlertDialog.Builder builder = new AlertDialog.Builder(ListClassesActivity.this);
			builder.setTitle("Thông báo");
			builder.setMessage("Bạn có muốn xóa lớp này không?");
			builder.setPositiveButton("Có", (dialog, which) -> {
				ClassesDao classesDao = new ClassesDao(this);
				Classes classes = listClasses.get(position);
				classesDao.delete(classes.getId());
				fillClassesListView();
			});
			builder.setNegativeButton("Không", (dialog, which) -> {
				dialog.dismiss();
			});
			builder.show();
		});
	}

	private void fillClassesListView() {
		ClassesDao classesDao = new ClassesDao(this);
		listClasses = classesDao.getAll();
		ClassesAdapter classesAdapter = new ClassesAdapter(this, listClasses);
		lvClasses.setAdapter(classesAdapter);
	}
}