package com.grapefruit.appjam.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grapefruit.appjam.R;
import com.grapefruit.appjam.databinding.FragmentNewestBinding;

public class NewestFragment extends Fragment {

    private String parameter;

    public NewestFragment() {

    }

    public static NewestFragment newInstance(String parameter) {
        NewestFragment fragment = new NewestFragment();
        Bundle args = new Bundle();
        args.putString("parameter", parameter);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentNewestBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_newest, container, false);
        return binding.getRoot();
    }
}
