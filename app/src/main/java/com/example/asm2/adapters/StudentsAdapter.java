package com.example.asm2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm2.R;
import com.example.asm2.helper.DateTimeHelper;
import com.example.asm2.model.Students;

import java.util.List;

public class StudentsAdapter extends BaseAdapter {
	private final Context context;
	private final List<Students> listStudents;

	public StudentsAdapter(Context context, List<Students> listStudents) {
		this.context = context;
		this.listStudents = listStudents;
	}

	@Override
	public int getCount() {
		return listStudents.size();
	}

	@Override
	public Object getItem(int position) {
		return listStudents.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.layout_student_item, parent, false);
		}

		TextView tvStudentId = convertView.findViewById(R.id.tvStudentId);
		TextView tvName = convertView.findViewById(R.id.tvName);
		TextView tvDob = convertView.findViewById(R.id.tvDob);

		Students students = listStudents.get(position);
		tvStudentId.setText(students.getId());
		tvName.setText(students.getName());
		tvDob.setText(DateTimeHelper.toString(students.getDob()));

		return convertView;
	}
}
