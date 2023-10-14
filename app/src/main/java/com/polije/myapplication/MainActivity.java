package com.polije.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout emailLayout, passwordLayout;
    private TextInputEditText emailEditText, passwordEditText;
    private Button loginButton;

    private static final String validEmail = "muhyi@gmail.com";
    private static final String validPassword = "admin123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailLayout = findViewById(R.id.il_email);
        passwordLayout = findViewById(R.id.il_password);
        emailEditText = findViewById(R.id.et_email);
        passwordEditText = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.btn_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString();

                if (!isValidEmail(email)) {
                    emailLayout.setError("Email tidak valid");
                    return;
                } else {
                    emailLayout.setError(null);
                }

                if (password.length() < 8) {
                    passwordLayout.setError("Password harus terdiri dari minimal 8 karakter");
                    return;
                } else {
                    passwordLayout.setError(null);
                }

                if (email.equals(validEmail) && password.equals(validPassword)) {
                    loginSuccess();
                } else {
                    Toast.makeText(MainActivity.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    private void loginSuccess() {
        Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();
        // Lanjutkan ke aktivitas Dashboard
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish(); // Tutup aktivitas login agar tidak dapat kembali ke sana
    }
}