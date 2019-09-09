package com.AbhiDev.edurecomm.viewactions;

import com.wireout.models.TedYouTube;

import java.util.List;

public interface TedViewAction extends BaseViewAction {
    void addToRecyclerView(List<TedYouTube> list);
}
