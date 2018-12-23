package com.grapefruit.appjam.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grapefruit.appjam.R;
import com.grapefruit.appjam.databinding.FragmentRecommendBinding;
import com.grapefruit.appjam.datas.Article;
import com.grapefruit.appjam.datas.adapter.ArticleAdapter;

public class RecommendFragment extends Fragment {

    private String parameter;
    private Article article;

    public RecommendFragment() {

    }

    public static RecommendFragment newInstance(String parameter) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString("parameter", parameter);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentRecommendBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recommend, container, false);

        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        // binding.recycler.setAdapter(new ArticleAdapter());
        return binding.getRoot();
    }
}
