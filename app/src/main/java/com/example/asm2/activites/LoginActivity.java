package com.example.asm2.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm2.R;

public class LoginActivity extends AppCompatActivity {
	EditText edtUsername, edtPassword;
	Button btnUsername, btnLamLai;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		edtUsername = findViewById(R.id.editTextUsername);
		edtPassword = findViewById(R.id.editTextPassword);
		btnUsername = findViewById(R.id.buttonLogin);
		btnLamLai   = findViewById(R.id.buttonLamlai);

		btnUsername.setOnClickListener(v -> {
			String username = edtUsername.getText().toString();
			String password = edtPassword.getText().toString();
			if (username.equals("admin") && password.equals("admin")) {
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
				Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
			}
		});

		btnLamLai.setOnClickListener(v -> {
			edtUsername.setText("");
			edtPassword.setText("");
		});
	}
}