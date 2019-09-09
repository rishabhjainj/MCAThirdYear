package com.AbhiDev.edurecomm.Activities;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;


/**
 * Created by Avishi Goyal on 05-08-2017.
 */

public class BackPressed extends AppCompatActivity {

        protected OnBackPressedListener onBackPressedListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public interface OnBackPressedListener {
            void doBack();
        }

        public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
            this.onBackPressedListener = onBackPressedListener;
        }

        @Override
        public void onBackPressed() {
            if (onBackPressedListener != null)
                onBackPressedListener.doBack();
            else
                super.onBackPressed();
        }

        @Override
        protected void onDestroy() {
            onBackPressedListener = null;
            super.onDestroy();
        }
    public class FontChangeCrawler
    {
        private Typeface typeface;

        public FontChangeCrawler(Typeface typeface)
        {
            this.typeface = typeface;
        }

        public FontChangeCrawler(AssetManager assets, String assetsFontFileName)
        {
            typeface = Typeface.createFromAsset(assets, assetsFontFileName);
        }

        public void replaceFonts(ViewGroup viewTree)
        {
            View child;
            for(int i = 0; i < viewTree.getChildCount(); ++i)
            {
                child = viewTree.getChildAt(i);
                if(child instanceof ViewGroup)
                {
                    // recursive call
                    replaceFonts((ViewGroup)child);
                }
                else if(child instanceof TextView)
                {
                    // base case
                    ((TextView) child).setTypeface(typeface);
                }
            }
        }
    }

}
