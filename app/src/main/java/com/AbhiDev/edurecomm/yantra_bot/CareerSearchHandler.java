package com.AbhiDev.edurecomm.yantra_bot;

import android.os.Bundle;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ai.api.model.AIOutputContext;
import ai.api.model.AIResponse;

public class CareerSearchHandler implements BaseHandler{

    AIResponse response;
    public static final String ACTION = "career_search";

    private static final String COMPETENCY_DIALOG = "career_search_dialog_params_competency";
    private static final String FEES_DIALOG = "career_search_dialog_params_fees";
    private static final String DURATION_DIALOG = "career_search_dialog_params_duration";
    private static final String QUALIFICATION_DIALOG = "career_search_dialog_params_qualification";

    private static final String[] parameters = {
      "Profession", "Qualification", "Fees", "Duration", "Course", "Competency"
    };

    //career_search_dialog_params_competency
    //Log.d("Contexts", results.getContexts().get(2).getName());

    public CareerSearchHandler(AIResponse response) throws InvalidIntentException{
        if(!response.getResult().getAction().equals(ACTION))
            throw new InvalidIntentException("Required " + ACTION + " but found " + response.getResult().getAction());
        this.response = response;
    }

    private String getParamDialogName(List<AIOutputContext> contexts){
        for(AIOutputContext context : contexts)
            if(context.getName().contains("params"))
                return context.getName();
        return null;
    }

    @Override
    public List<String> getQuickResponseList() {
        List<String> quickResponses = new ArrayList<>();
        String paramDialogName = getParamDialogName(response.getResult().getContexts());
        if(paramDialogName == null)
            return quickResponses;
        switch (paramDialogName){
            case COMPETENCY_DIALOG:
                quickResponses.add("beginner");
                quickResponses.add("intermediate");
                quickResponses.add("expert");
                break;
            case FEES_DIALOG:
                quickResponses.add("free");
                quickResponses.add("paid");
                break;
            case DURATION_DIALOG:
                quickResponses.add("short term");
                quickResponses.add("long term");
                break;
            case QUALIFICATION_DIALOG:
//                quickResponses.add("matriculation");
                quickResponses.add("undergraduate");
                quickResponses.add("graduate");
                quickResponses.add("post graduate");
                quickResponses.add("research scholar");
                quickResponses.add("working professional");
                break;
            default: break;
        }
        return quickResponses;
    }

    @Override
    public Bundle getBundledParameters() {
        final HashMap<String,JsonElement> parametersMap = response.getResult().getParameters();
        Bundle bundle = new Bundle();
        for(String param : parameters){
            if(parametersMap.containsKey(param) && parametersMap.get(param) != null)
                bundle.putString(param,parametersMap.get(param).toString());
        }
        return bundle;
    }
    /** testing **/
    static void main(String[] args){
        try {
            CareerSearchHandler handler = new CareerSearchHandler(new AIResponse());
            handler.getQuickResponseList();
            handler.getBundledParameters();
        } catch (InvalidIntentException e) {
            e.printStackTrace();
        }
    }
}
