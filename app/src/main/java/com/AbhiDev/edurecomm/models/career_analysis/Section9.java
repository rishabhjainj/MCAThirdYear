package com.AbhiDev.edurecomm.models.career_analysis;

import com.wireout.apiservices.Repository;
import com.wireout.common.EntityLoader;
import com.wireout.listeners.OnEntityReceivedListener;

/** All fields are compulsory **/
public class Section9 extends AbstractSection<Section9>{
    String mood;
    String booleanResponses; //binary string denoting agree (1) / disagree (0)
    String mcq; //comma separated integers for responses to the only mcq in this section
    Integer familyTime; //% family time

    public Section9(int numScreens) {
        super(numScreens);
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getBooleanResponses() {
        return booleanResponses;
    }

    public void setBooleanResponses(String booleanResponses) {
        this.booleanResponses = booleanResponses;
    }

    public String getMcq() {
        return mcq;
    }

    public void setMcq(String mcq) {
        this.mcq = mcq;
    }

    public Integer getFamilyTime() {
        return familyTime;
    }

    public void setFamilyTime(Integer familyTime) {
        this.familyTime = familyTime;
    }

    @Override
    boolean isScreenValidated(int screenId) {
        return true;
    }

    @Override
    public void submit(OnEntityReceivedListener<Section9> listener) {
        if(!isAllValidated()){
            //listener.showMessage("Section not validated!");
            return;
        }

        Repository repository = new Repository();
        repository.postAnalysisSection9(this, new EntityLoader(listener));
    }
}
