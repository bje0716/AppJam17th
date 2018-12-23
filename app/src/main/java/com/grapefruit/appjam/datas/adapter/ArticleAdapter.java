package com.grapefruit.appjam.datas.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grapefruit.appjam.R;
import com.grapefruit.appjam.databinding.ViewArticleDataBinding;
import com.grapefruit.appjam.datas.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<Article> articles;

    public ArticleAdapter(List<Article> as) {
        this.articles = as;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_article_data, viewGroup, false);
        ArticleViewHolder holder = new ArticleViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder articleViewHolder, int i) {
        final Article article = articles.get(i);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        private ViewArticleDataBinding binding;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ViewArticleDataBinding getBinding() {
            return binding;
        }
    }
}
