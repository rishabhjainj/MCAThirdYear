package com.AbhiDev.edurecomm.presenters;

import com.wireout.models.Articles;
import com.wireout.viewactions.BaseViewAction;

import java.util.List;

public interface TrendingArticlesViewAction extends BaseViewAction {
    void addMoreTrendingArticles(List<Articles> articles);
    void addMoreCareerBlogs(List<Articles> articles);
}
