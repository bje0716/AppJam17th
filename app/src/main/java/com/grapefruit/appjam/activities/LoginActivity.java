package com.grapefruit.appjam.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.grapefruit.appjam.R;
import com.grapefruit.appjam.databinding.ActivityLoginBinding;
import com.grapefruit.appjam.utils.VerifyUtil;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        binding.join.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, JoinActivity.class));
        });

        binding.signIn.setOnClickListener(v -> {
            final String email = binding.email.getText().toString();
            final String password = binding.password.getText().toString();

            if (!VerifyUtil.verifyStrings(email, password)) {
                Toast.makeText(this, "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (!task.isSuccessful()) {
                            Toast.makeText(this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    });

        });
    }
}
