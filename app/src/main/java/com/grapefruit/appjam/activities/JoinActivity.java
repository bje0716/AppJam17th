package com.grapefruit.appjam.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.grapefruit.appjam.R;
import com.grapefruit.appjam.databinding.ActivityJoinBinding;
import com.grapefruit.appjam.datas.UserData;
import com.grapefruit.appjam.utils.VerifyUtil;

public class JoinActivity extends AppCompatActivity {

    private ActivityJoinBinding binding;
    private FirebaseAuth auth;
    private DatabaseReference database;
    private String value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        binding.signIn.setOnClickListener(v -> {
            final String email = binding.email.getText().toString();
            final String password = binding.password.getText().toString();
            final String name = binding.name.getText().toString();
            final String nickname = binding.nickname.getText().toString();


            if (!VerifyUtil.verifyStrings(email, password, name, nickname)) {
                Toast.makeText(this, "입력창 중 일부가 비었습니다", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(this, "비밀번호는 6자리 이상으로 구성되어야 합니다", Toast.LENGTH_SHORT).show();
                return;
            }

            binding.value.setOnCheckedChangeListener((radioGroup, i) -> {
                switch (i) {
                    case R.id.mento:
                        value = "mento";
                        break;
                    case R.id.menti:
                        value = "menti";
                        break;
                }
            });

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, (Task<AuthResult> task) -> {
                        if (!task.isSuccessful()) {
                            Toast.makeText(this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        final FirebaseUser user = task.getResult().getUser();
                        final UserData userData = new UserData(email, name, nickname, value);
                        database.child("users").child(user.getUid()).setValue(userData);

                        startActivity(new Intent(JoinActivity.this, MainActivity.class));
                        Toast.makeText(this, "회원기입 완료됨", Toast.LENGTH_SHORT).show();
                        finish();
                    });
        });
    }
}
