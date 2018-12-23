package com.grapefruit.appjam.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grapefruit.appjam.R;
import com.grapefruit.appjam.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private String parameter;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String parameter) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("parameter", parameter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            parameter = getArguments().getString("parameter");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
        binding.pager.setAdapter(new PagerAdapter(getFragmentManager()));
        binding.tabs.setupWithViewPager(binding.pager);
        return binding.getRoot();
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {

        private String[] titles = {"추천", "최신"};

        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return RecommendFragment.newInstance(titles[i]);
                case 1:
                    return NewestFragment.newInstance(titles[i]);
            }
            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
