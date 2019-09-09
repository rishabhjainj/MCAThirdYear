package com.AbhiDev.edurecomm.fourpicsoneword;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sharda on 24/07/17.
 */

public class CharactersTextView extends android.support.v7.widget.AppCompatTextView {

    Context context;

    public CharactersTextView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public  CharactersTextView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        init();
    }

    void init(){
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibility(View.GONE);
                if(((VerbalAbilityGame) context).presenter != null)
                    ((VerbalAbilityGame) context).presenter.characterSelectEventHandler(getText().toString());
            }

        });
    }


}
