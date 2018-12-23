package com.grapefruit.appjam.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.grapefruit.appjam.R;
import com.grapefruit.appjam.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment {

    private String parameter;

    public SearchFragment() {

    }

    public static SearchFragment newInstance(String parameter) {
        SearchFragment fragment = new SearchFragment();
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
        final FragmentSearchBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        binding.btnSearch.setOnClickListener(v -> {
            if (binding.content.getText().toString().length() == 0) {
                Toast.makeText(getActivity(), "검색할 단어를 입력하세요", Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }
}
