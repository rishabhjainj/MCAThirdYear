package com.AbhiDev.edurecomm.yantra_bot;

import android.os.Bundle;

import java.util.List;

public interface BaseHandler {
    List<String> getQuickResponseList();
    Bundle getBundledParameters();
}
