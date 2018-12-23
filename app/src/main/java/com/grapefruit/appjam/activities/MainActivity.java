package com.grapefruit.appjam.activities;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.grapefruit.appjam.R;
import com.grapefruit.appjam.databinding.ActivityMainBinding;
import com.grapefruit.appjam.fragments.HomeFragment;
import com.grapefruit.appjam.fragments.LikeFragment;
import com.grapefruit.appjam.fragments.ProfileFragment;
import com.grapefruit.appjam.fragments.SearchFragment;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionListener listener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {

                }

                @Override
                public void onPermissionDenied(List<String> deniedPermissions) {
                    Toast.makeText(MainActivity.this, deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                }
            };

            TedPermission.with(this)
                    .setPermissionListener(listener)
                    .setDeniedMessage("권한이 비허용 되었습니다. 설정에서 권한 전체 허용해주세요")
                    .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                    .check();
        }

        auth = FirebaseAuth.getInstance();
        mAuthListener = (@NonNull FirebaseAuth firebaseAuth) -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user == null) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        };

        binding.uiFragmentContainer.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        binding.uiFragmentContainer.setOffscreenPageLimit(4);

        binding.bottom.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.bottom_home:
                    binding.uiFragmentContainer.setCurrentItem(0, true);
                    return true;
                case R.id.bottom_search:
                    binding.uiFragmentContainer.setCurrentItem(1, true);
                    return true;
                case R.id.bottom_user:
                    binding.uiFragmentContainer.setCurrentItem(2, true);
                    return true;
                case R.id.bottom_like:
                    binding.uiFragmentContainer.setCurrentItem(3, true);
                    return true;
            }
            return false;
        });

        binding.bottom.setOnNavigationItemReselectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    break;
                case R.id.bottom_search:
                    break;
                case R.id.bottom_user:
                    break;
                case R.id.bottom_like:
                    break;
            }
        });
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {

        private String[] titles = {"메인", "검색", "나의 정보", "즐겨찾기"};

        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return HomeFragment.newInstance(titles[i]);
                case 1:
                    return SearchFragment.newInstance(titles[i]);
                case 2:
                    return ProfileFragment.newInstance(titles[i]);
                case 3:
                    return LikeFragment.newInstance(titles[i]);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_write:
                startActivity(new Intent(this, WriteActivity.class));
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            auth.removeAuthStateListener(mAuthListener);
        }
    }
}
