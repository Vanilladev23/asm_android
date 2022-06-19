package com.example.asm2.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asm2.R;
import com.example.asm2.adapters.ClassesAdapter;
import com.example.asm2.adapters.StudentsAdapter;
import com.example.asm2.dao.ClassesDao;
import com.example.asm2.dao.StudentsDao;
import com.example.asm2.dialog.NewDiaLogFind;
import com.example.asm2.helper.DateTimeHelper;
import com.example.asm2.model.Classes;
import com.example.asm2.model.Students;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ManageStudentActivity extends AppCompatActivity implements View.OnClickListener {
	private EditText edtStudentId, edtName, edtDob;
	private Spinner spnClasses;
	private List<Classes> listClasses;
	private List<Students> listStudents;
	private ListView lvStudents;
	private boolean isEdit = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_student);

		edtStudentId = findViewById(R.id.etStudentId);
		edtName = findViewById(R.id.etName);
		edtDob = findViewById(R.id.etDob);
		spnClasses = findViewById(R.id.spClasses);

		lvStudents = findViewById(R.id.lvStudents);
		lvStudents.setOnItemClickListener((parent, view, position, id) -> {
			Students students = listStudents.get(position);
			edtStudentId.setText(String.valueOf(students.getId()));
			edtName.setText(students.getName());
			edtDob.setText(DateTimeHelper.toString(students.getDob()));
			isEdit = true;
		});

		fillClassesToSpinner();

		findViewById(R.id.btnSave).setOnClickListener(this);
		findViewById(R.id.btnDelete).setOnClickListener(this);
		findViewById(R.id.btnFind).setOnClickListener(this);
	}

	private void fillStudentsToListView() {
		StudentsDao studentsDao = new StudentsDao(this);
		try {
			Classes classes = (Classes) spnClasses.getSelectedItem();
			listStudents = studentsDao.getAllByClass(classes.getId());

			StudentsAdapter studentsAdapter = new StudentsAdapter(this, listStudents);
			lvStudents.setAdapter(studentsAdapter);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
		}

	}

	private void fillClassesToSpinner() {
		ClassesDao classesDao = new ClassesDao(this);
		listClasses = classesDao.getAll();
		ClassesAdapter classesAdapter = new ClassesAdapter(this, listClasses);
		spnClasses.setAdapter(classesAdapter);

		spnClasses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				fillStudentsToListView();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	@SuppressLint("NonConstantResourceId")
	@Override
	public void onClick(View v) {
		StudentsDao studentsDao = new StudentsDao(this);
		switch (v.getId()) {
			case R.id.btnSave:
				try {
					Students students = new Students();
					students.setId(edtStudentId.getText().toString());
					students.setName(edtName.getText().toString());
					students.setDob(DateTimeHelper.toDate(edtDob.getText().toString()));
					students.setClassId(listClasses.get(spnClasses.getSelectedItemPosition()).getId());
					String message;
					if (!isEdit) {
						studentsDao.insert(students);
						message = "Thêm thành công";
					} else {
						studentsDao.update(students);
						message = "Cập nhật thành công";
					}

					Snackbar.make(v, message, Snackbar.LENGTH_LONG).show();
					clearInputFields();
					isEdit = false;
					fillStudentsToListView();
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
				}
				break;
			case R.id.btnDelete:
				if (isEdit && !edtStudentId.getText().toString().equals("")) {
					String id = edtStudentId.getText().toString();
					studentsDao.delete(id);
					fillStudentsToListView();
					Snackbar.make(v, "Xóa thành công", Snackbar.LENGTH_LONG).show();
					clearInputFields();
					isEdit = false;
				} else {
					Snackbar.make(v, "Chưa chọn sinh viên", Snackbar.LENGTH_LONG).show();
				}
				break;
			case R.id.btnFind:
				NewDiaLogFind newDiaLogFind = new NewDiaLogFind(this);
				newDiaLogFind.show();
		}
	}

	private void clearInputFields() {
		edtStudentId.setText("");
		edtName.setText("");
		edtDob.setText("");
	}
}