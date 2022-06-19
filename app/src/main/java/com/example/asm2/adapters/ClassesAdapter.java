package com.example.asm2.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm2.R;
import com.example.asm2.model.Classes;

import java.util.List;

public class ClassesAdapter extends BaseAdapter {
	private final Context context;
	private final List<Classes> listClasses;

	public ClassesAdapter(Context context, List<Classes> listClasses) {
		this.context = context;
		this.listClasses = listClasses;
	}

	@Override
	public int getCount() {
		return listClasses.size();
	}

	@Override
	public Object getItem(int position) {
		return listClasses.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("SetTextI18n")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.layout_classes_item, parent, false);
		}
		TextView tvClassId = convertView.findViewById(R.id.tvClassid);
		TextView tvClassName = convertView.findViewById(R.id.tvName);
		Classes classes = listClasses.get(position);
		tvClassId.setText(classes.getId() + "");
		tvClassName.setText(classes.getName());
		return convertView;
	}
}
