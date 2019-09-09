package com.AbhiDev.edurecomm.models.career_analysis;

import com.wireout.listeners.OnEntityReceivedListener;

import java.io.Serializable;

abstract class AbstractSection<T> implements Serializable {

    int numScreens;

    public AbstractSection(int numScreens){
        this.numScreens = numScreens;
    }

    abstract boolean isScreenValidated(int screenId);
    abstract public void submit(OnEntityReceivedListener<T> listener);

    boolean isAllValidated(){
        for(int i=1; i<=numScreens; i++)
            if(!isScreenValidated(numScreens))
                return false;
        return true;
    }
}
