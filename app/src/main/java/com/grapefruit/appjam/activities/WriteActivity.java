package com.grapefruit.appjam.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.grapefruit.appjam.R;
import com.grapefruit.appjam.databinding.ActivityWriteBinding;
import com.grapefruit.appjam.utils.VerifyUtil;

public class WriteActivity extends AppCompatActivity {

    private ActivityWriteBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private boolean isMedia = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        View dv = getLayoutInflater().inflate(R.layout.dialog_link, null);

        binding.link.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setView(dv)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            EditText edt = dv.findViewById(R.id.link_data);
                            Toast.makeText(WriteActivity.this, edt.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        });

        binding.picture.setOnClickListener(v -> {
            startActivityForResult(new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT), 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                binding.img.setVisibility(View.VISIBLE);
                binding.img.setImageURI(data.getData());

                binding.upload.setOnClickListener(v -> {

                    if (!VerifyUtil.verifyString(binding.content.getText().toString()) && !isMedia) {
                        Toast.makeText(this, "글 작성 해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        if (user != null && isMedia) {
                            // 등록
                            Toast.makeText(this, "글 등록되었습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
}
