package com.AbhiDev.edurecomm.models.career_analysis;

import com.wireout.apiservices.Repository;
import com.wireout.common.EntityLoader;
import com.wireout.listeners.OnEntityReceivedListener;

import java.util.List;

public class BooleanSectionResponse {
    int section; //Section's database ID. Provide the value of the 'id' of section retrieved from backend.
    List<BooleanQuestionResponse> answers;  //list of answers to questions in this section

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public List<BooleanQuestionResponse> getAnswers() {
        return answers;
    }

    public void setAnswers(List<BooleanQuestionResponse> answers) {
        this.answers = answers;
    }

    public void submit(OnEntityReceivedListener<BooleanSectionResponse> listener){
        Repository repository = new Repository();
        repository.postBooleanQuestionResponse(this,new EntityLoader(listener));
    }
}
