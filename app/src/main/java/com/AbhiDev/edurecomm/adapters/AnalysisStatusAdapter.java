package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.squareup.picasso.Picasso;
import com.wireout.Activities.Analysis.AbilitySection;
import com.wireout.Activities.Analysis.CommSkillSection;
import com.wireout.Activities.Analysis.EmotionalIntelligenceSection;
import com.wireout.Activities.Analysis.HandwritingSection;
import com.wireout.Activities.Analysis.InterstSection;
import com.wireout.Activities.Analysis.LifeChoicesSection;
import com.wireout.Activities.Analysis.LogicalReasoningSection;
import com.wireout.Activities.Analysis.MeOrNotMeSection;
import com.wireout.Activities.Analysis.MotivationalQuotientSection;
import com.wireout.Activities.Analysis.YourEgogramSection;
import com.wireout.Activities.Analysis.AnalysisBioDataSection;
import com.wireout.Activities.BrainBoosterIntroScreen;
import com.wireout.Activities.SectionInfoActivity;
import com.wireout.common.MyApplication;
import com.wireout.models.career_analysis.CareerAnalysis;
import com.wireout.R;
import com.wireout.fourpicsoneword.VerbalAbilityIntroScreen;
import com.wireout.game2.FlexibilityGameIntro;
import com.wireout.mathGame.GameIntro;
import com.wireout.models.career_analysis.BooleanQuestion;

import java.util.List;

public class AnalysisStatusAdapter extends RecyclerView.Adapter<AnalysisStatusAdapter.MyViewHolder> {
    Context context;
    List<String> sectionNames;
    List<Integer> sectionImages;
    List<String> time;
    Intent i;
    CareerAnalysis status;
    ImagePopup imagePopup;
    List<BooleanQuestion> allQuestions;

    public AnalysisStatusAdapter(Context context, List<String> sectionNames, List<Integer> sectionImages, List<String> time, List<BooleanQuestion> allQuestions, CareerAnalysis status){
        this.sectionImages = sectionImages;
        this.sectionNames = sectionNames;
        this.context = context;
        this.time = time;
        this.status = status;
        this.allQuestions = allQuestions;
    }
    @Override
    public AnalysisStatusAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.analysis_status_item_view,parent,false);
        return new AnalysisStatusAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final AnalysisStatusAdapter.MyViewHolder holder, final int position) {
        Picasso.with(context)
                .load(sectionImages.get(position))
                .into(holder.sectionImage);
        holder.sectionName.setText(sectionNames.get(position));
        switch (position){
            case 0:
                if(MyApplication.getInstance().prefManager.getString(AnalysisBioDataSection.HISTORYNGOALSSECTIONCOMPLETED)!=null) {

                if (MyApplication.getInstance().prefManager.getString(AnalysisBioDataSection.HISTORYNGOALSSECTIONCOMPLETED).equals("true")) {
                    setCompleted(holder);
                }
            }

                break;
            case 1:
                if(status.getSection2()!=null){
                    setCompleted(holder);
                }
//                if(MyApplication.getInstance().prefManager.getString(MeOrNotMeSection.MEORNOTMECOMPLETED)!=null) {
//
//                if (MyApplication.getInstance().prefManager.getString(MeOrNotMeSection.MEORNOTMECOMPLETED).equals("true")) {
//                    setCompleted(holder);
//                }
//            }
                break;
            case 2:
//                if(MyApplication.getInstance().prefManager.getString(AbilitySection.ABILITYCOMPLETED)!=null) {
//
//                if (MyApplication.getInstance().prefManager.getString(AbilitySection.ABILITYCOMPLETED).equals("true")) {
//                    setCompleted(holder);
//                }
//            }
                if(status.getSection3()!=null){
                    setCompleted(holder);
                }
                break;
            case 3:
                if(status.getSection4()!=null){
                    setCompleted(holder);
                }
//                if(MyApplication.getInstance().prefManager.getString(InterstSection.INTERESTCOMPLETED)!=null) {
//
//                if (MyApplication.getInstance().prefManager.getString(InterstSection.INTERESTCOMPLETED).equals("true")) {
//                    setCompleted(holder);
//                }
//            }
                break;



            case 4:
                if(status.getSection5()!=null){
                    setCompleted(holder);
                }
//                if(MyApplication.getInstance().prefManager.getString(YourEgogramSection.EGOGRAMCOMPLETED)!=null){
//                if(MyApplication.getInstance().prefManager.getString(YourEgogramSection.EGOGRAMCOMPLETED).equals("true")){
//                    setCompleted(holder);
//                }
//            }
                break;
            case 5:
                if(status.getSection6()!=null){
                    setCompleted(holder);
                }
//                if(MyApplication.getInstance().prefManager.getString(LifeChoicesSection.LIFECHOICESSECTIONCOMPLETED)!=null){
//                if(MyApplication.getInstance().prefManager.getString(LifeChoicesSection.LIFECHOICESSECTIONCOMPLETED).equals("true")){
//                    setCompleted(holder);
//                }

            break;
            case 6:
                if(status.getSection7()!=null){
                    setCompleted(holder);
                }
//                if(MyApplication.getInstance().prefManager.getString(SectionEndActivity.VERBALABILITYCOMPLETED)!=null){
//                if(MyApplication.getInstance().prefManager.getString(SectionEndActivity.VERBALABILITYCOMPLETED).equals("true")){
//                    setCompleted(holder);
//                }
//            }
                break;
            case 7:
                if(status.getSection8()!=null){
                    setCompleted(holder);
                }
//                if(MyApplication.getInstance().prefManager.getString(SectionEndActivity.BRAINBOOSTERCOMPLETED)!=null) {
//
//                    if (MyApplication.getInstance().prefManager.getString(SectionEndActivity.BRAINBOOSTERCOMPLETED).equals("true")) {
//                       setCompleted(holder);
//                    }
//                }
                break;
            case 8:
                if(status.getSection9()!=null){
                    setCompleted(holder);
                }
//                if(MyApplication.getInstance().prefManager.getString(EmotionalIntelligenceSection.EMOTIONALSECTIONCOMPLETED)!=null) {
//
//                if (MyApplication.getInstance().prefManager.getString(EmotionalIntelligenceSection.EMOTIONALSECTIONCOMPLETED).equals("true")) {
//                    setCompleted(holder);
//                }
//            }
                break;
            case 9:
                if(status.getSection10()!=null){
                    setCompleted(holder);
                }
//                if(MyApplication.getInstance().prefManager.getString(SectionEndActivity.FLEXIBILITYGAMECOMPLETED)!=null) {
//
//                if (MyApplication.getInstance().prefManager.getString(SectionEndActivity.FLEXIBILITYGAMECOMPLETED).equals("true")) {
//                    setCompleted(holder);
//                }
//            }
                break;
            case 10:
                if(status.getSection11()!=null){
                    setCompleted(holder);
                }
//                if(MyApplication.getInstance().prefManager.getString(SectionEndActivity.MATHGAMECOMPLETED)!=null) {
//
//                if (MyApplication.getInstance().prefManager.getString(SectionEndActivity.MATHGAMECOMPLETED).equals("true")) {
//                    setCompleted(holder);
//                }
//            }
                break;
            case 11:
                if(status.getSection12()!=null){
                    setCompleted(holder);
                }
//                if(MyApplication.getInstance().prefManager.getString(MotivationalQuotientSection.MOTIVATIONALCOMPLETED)!=null) {
//
//                if (MyApplication.getInstance().prefManager.getString(MotivationalQuotientSection.MOTIVATIONALCOMPLETED).equals("true")) {
//                    setCompleted(holder);
//                }
//            }
                break;
            case 12:
                if(MyApplication.getInstance().prefManager.getString(CommSkillSection.COMMSKILLSECTIONCOMPLETED)!=null) {

                    if (MyApplication.getInstance().prefManager.getString(CommSkillSection.COMMSKILLSECTIONCOMPLETED).equals("true")) {
                        setCompleted(holder);
                    }
                }
                break;
            case 13:
                if(MyApplication.getInstance().prefManager.getString(LogicalReasoningSection.LOGICALSECTIONCOMPLETED)!=null) {

                    if (MyApplication.getInstance().prefManager.getString(LogicalReasoningSection.LOGICALSECTIONCOMPLETED).equals("true")) {
                        setCompleted(holder);
                    }
                }
                break;
            case 14:
                if(MyApplication.getInstance().prefManager.getString(HandwritingSection.HANDWRITINGSECTIONCOMPLETED)!=null) {

                if (MyApplication.getInstance().prefManager.getString(HandwritingSection.HANDWRITINGSECTIONCOMPLETED).equals("true")) {
                    setCompleted(holder);
                }
            }
                break;
        }
        holder.infoButton.setOnClickListener(new View.OnClickListener() {
            Intent i = new Intent(context, SectionInfoActivity.class);
            @Override
            public void onClick(View view) {
                switch (position) {
                    case 0:
                        i.putExtra("id","0");
                        context.startActivity(i);
                        break;
                    case 1:
                        i.putExtra("id","1");
                        context.startActivity(i);
                        break;
                    case 2:
                        i.putExtra("id","2");
                        context.startActivity(i);
                        break;
                    case 3:
                        i.putExtra("id","3");
                        context.startActivity(i);
                        break;
                    case 4:
                        i.putExtra("id","4");
                        context.startActivity(i);
                        break;
                    case 5:
                        i.putExtra("id","5");
                        context.startActivity(i);
                        break;
                    case 6:
                        i.putExtra("id","6");
                        context.startActivity(i);
                        break;
                    case 7:
                        i.putExtra("id","7");
                        context.startActivity(i);
                        break;
                    case 8:
                        i.putExtra("id","8");
                        context.startActivity(i);
                        break;
                    case 9:
                        i.putExtra("id","9");
                        context.startActivity(i);
                        break;
                    case 10:
                        i.putExtra("id","10");
                        context.startActivity(i);
                        break;
                    case 11:
                        i.putExtra("id","11");
                        context.startActivity(i);
                        break;
                    case 12:
                        i.putExtra("id","12");
                        context.startActivity(i);
                        break;
                    case 13:
                        i.putExtra("id","13");
                        context.startActivity(i);
                        break;
                    case 14:
                        i.putExtra("id","14");
                        context.startActivity(i);
                        break;


                }
            }
        });
        holder.time.setText(time.get(position));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position){
                    case 0: i = new Intent(context, AnalysisBioDataSection.class);
                        for(BooleanQuestion sections: allQuestions){
                            if(sections.getSerialNo()==2){
                                i.putExtra("questionsSection2",sections);
                            }
                            if(sections.getSerialNo()==3){
                                i.putExtra("questionsSection3",sections);
                            }
                            if(sections.getSerialNo()==4){
                                i.putExtra("questionsSection4",sections);
                            }
                            if(sections.getSerialNo()==5){
                                i.putExtra("questionsSection5",sections);
                            }
                            if(sections.getSerialNo()==12)
                                i.putExtra("questions",sections);
                        }
                        context.startActivity(i);
                        break;

                    case 1:
                            i = new Intent(context, MeOrNotMeSection.class);
                            for(BooleanQuestion sections: allQuestions){
                                if(sections.getSerialNo()==2){
                                    i.putExtra("questionsSection2",sections);
                                }
                                if(sections.getSerialNo()==3){
                                    i.putExtra("questionsSection3",sections);
                                }
                                if(sections.getSerialNo()==4){
                                    i.putExtra("questionsSection4",sections);
                                }
                                if(sections.getSerialNo()==5){
                                    i.putExtra("questionsSection5",sections);
                                }
                                if(sections.getSerialNo()==12)
                                    i.putExtra("questions",sections);
                            }
                            context.startActivity(i);
                            break;
                    case 2: i = new Intent(context, AbilitySection.class);
                    for(BooleanQuestion sections : allQuestions){
                        Log.d("sectionNo",allQuestions.get(4).getSerialNo()+"");
                        if(sections.getSerialNo()==2){
                            i.putExtra("questionsSection2",sections);
                        }
                        if(sections.getSerialNo()==3){
                            i.putExtra("questionsSection3",sections);
                        }
                        if(sections.getSerialNo()==4){
                            i.putExtra("questionsSection4",sections);
                        }
                        if(sections.getSerialNo()==5){
                            i.putExtra("questionsSection5",sections);
                        }
                        if(sections.getSerialNo()==12)
                            i.putExtra("questions",sections);

                    }
                    context.startActivity(i);
                    break;
                    case 3: i = new Intent(context, InterstSection.class);
                        for(BooleanQuestion sections : allQuestions){
                            Log.d("sectionNo",allQuestions.get(4).getSerialNo()+"");
                            if(sections.getSerialNo()==2){
                                i.putExtra("questionsSection2",sections);
                            }
                            if(sections.getSerialNo()==3){
                                i.putExtra("questionsSection3",sections);
                            }
                            if(sections.getSerialNo()==4){
                                i.putExtra("questionsSection4",sections);
                            }
                            if(sections.getSerialNo()==5){
                                i.putExtra("questionsSection5",sections);
                            }
                            if(sections.getSerialNo()==12)
                                i.putExtra("questions",sections);
                        }
                        context.startActivity(i);
                        break;
                    case 4: i = new Intent(context, YourEgogramSection.class);
                        for(BooleanQuestion sections : allQuestions){
                            //Log.d("sectionNo",allQuestions.get(5).getSerialNo()+"");
                            if(sections.getSerialNo()==2){
                                i.putExtra("questionsSection2",sections);
                            }
                            if(sections.getSerialNo()==3){
                                i.putExtra("questionsSection3",sections);
                            }
                            if(sections.getSerialNo()==4){
                                i.putExtra("questionsSection4",sections);
                            }
                            if(sections.getSerialNo()==5){
                                i.putExtra("questionsSection5",sections);
                            }
                            if(sections.getSerialNo()==12)
                                i.putExtra("questions",sections);
                        }
                        context.startActivity(i);
                        break;

                    case 5:i = new Intent(context, LifeChoicesSection.class);
                        for(BooleanQuestion sections : allQuestions){
                            if(sections.getSerialNo()==2){
                                i.putExtra("questionsSection2",sections);
                            }
                            if(sections.getSerialNo()==3){
                                i.putExtra("questionsSection3",sections);
                            }
                            if(sections.getSerialNo()==4){
                                i.putExtra("questionsSection4",sections);
                            }
                            if(sections.getSerialNo()==5){
                                i.putExtra("questionsSection5",sections);
                            }
                            if(sections.getSerialNo()==12)
                                i.putExtra("questions",sections);

                        }
                        context.startActivity(i);
                        break;
                    case 6:i = new Intent(context, VerbalAbilityIntroScreen.class);
                        for(BooleanQuestion sections : allQuestions){
                            if(sections.getSerialNo()==2){
                                i.putExtra("questionsSection2",sections);
                            }
                            if(sections.getSerialNo()==3){
                                i.putExtra("questionsSection3",sections);
                            }
                            if(sections.getSerialNo()==4){
                                i.putExtra("questionsSection4",sections);
                            }
                            if(sections.getSerialNo()==5){
                                i.putExtra("questionsSection5",sections);
                            }
                            if(sections.getSerialNo()==12)
                                i.putExtra("questions",sections);

                        }
                        context.startActivity(i);
                        break;
                    case 7:
                        i = new Intent(context, BrainBoosterIntroScreen.class);
                        for(BooleanQuestion sections : allQuestions){
                            if(sections.getSerialNo()==2){
                                i.putExtra("questionsSection2",sections);
                            }
                            if(sections.getSerialNo()==3){
                                i.putExtra("questionsSection3",sections);
                            }
                            if(sections.getSerialNo()==4){
                                i.putExtra("questionsSection4",sections);
                            }
                            if(sections.getSerialNo()==5){
                                i.putExtra("questionsSection5",sections);
                            }
                            if(sections.getSerialNo()==12)
                                i.putExtra("questions",sections);

                            }
                        context.startActivity(i);

                        break;
                    case 8:
                        i = new Intent(context, EmotionalIntelligenceSection.class);
                        for(BooleanQuestion sections : allQuestions){
                            if(sections.getSerialNo()==2){
                                i.putExtra("questionsSection2",sections);
                            }
                            if(sections.getSerialNo()==3){
                                i.putExtra("questionsSection3",sections);
                            }
                            if(sections.getSerialNo()==4){
                                i.putExtra("questionsSection4",sections);
                            }
                            if(sections.getSerialNo()==5){
                                i.putExtra("questionsSection5",sections);
                            }
                            if(sections.getSerialNo()==12)
                                i.putExtra("questions",sections);

                        }
                        context.startActivity(i);
                        break;

                    case 9:
                        i = new Intent(context, FlexibilityGameIntro.class);
                        for(BooleanQuestion sections : allQuestions){
                            if(sections.getSerialNo()==2){
                                i.putExtra("questionsSection2",sections);
                            }
                            if(sections.getSerialNo()==3){
                                i.putExtra("questionsSection3",sections);
                            }
                            if(sections.getSerialNo()==4){
                                i.putExtra("questionsSection4",sections);
                            }
                            if(sections.getSerialNo()==5){
                                i.putExtra("questionsSection5",sections);
                            }
                            if(sections.getSerialNo()==12)
                                i.putExtra("questions",sections);


                        }
                        context.startActivity(i);
                        break;

                    case 10:
                        i = new Intent(context, GameIntro.class);
                        for(BooleanQuestion sections : allQuestions){
                            //Log.d("sectionNo",allQuestions.get(5).getSerialNo()+"");

                            if(sections.getSerialNo()==2){
                                i.putExtra("questionsSection2",sections);
                            }
                            if(sections.getSerialNo()==3){
                                i.putExtra("questionsSection3",sections);
                            }
                            if(sections.getSerialNo()==4){
                                i.putExtra("questionsSection4",sections);
                            }
                            if(sections.getSerialNo()==5){
                                i.putExtra("questionsSection5",sections);
                            }
                            if(sections.getSerialNo()==12)
                                i.putExtra("questions",sections);

                        }
                        context.startActivity(i);
                        break;
                    case 11: i = new Intent(context, MotivationalQuotientSection.class);
                        for(BooleanQuestion sections : allQuestions){
                            //Log.d("sectionNo",allQuestions.get(5).getSerialNo()+"");

                            if(sections.getSerialNo()==2){
                                i.putExtra("questionsSection2",sections);
                            }
                            if(sections.getSerialNo()==3){
                                i.putExtra("questionsSection3",sections);
                            }
                            if(sections.getSerialNo()==4){
                                i.putExtra("questionsSection4",sections);
                            }
                            if(sections.getSerialNo()==5){
                                i.putExtra("questionsSection5",sections);
                            }
                            if(sections.getSerialNo()==12)
                                i.putExtra("questions",sections);
                        }
                        context.startActivity(i);
                        break;

                    case 12:
                        i = new Intent(context, CommSkillSection.class);
                        for(BooleanQuestion sections : allQuestions){
                            //Log.d("sectionNo",allQuestions.get(5).getSerialNo()+"");

                            if(sections.getSerialNo()==2){
                                i.putExtra("questionsSection2",sections);
                            }
                            if(sections.getSerialNo()==3){
                                i.putExtra("questionsSection3",sections);
                            }
                            if(sections.getSerialNo()==4){
                                i.putExtra("questionsSection4",sections);
                            }
                            if(sections.getSerialNo()==5){
                                i.putExtra("questionsSection5",sections);
                            }
                            if(sections.getSerialNo()==12)
                                i.putExtra("questions",sections);
                        }
                        context.startActivity(i);
                        break;
                    case 13:
                        i = new Intent(context, LogicalReasoningSection.class);
                        for(BooleanQuestion sections : allQuestions){
                            //Log.d("sectionNo",allQuestions.get(5).getSerialNo()+"");

                            if(sections.getSerialNo()==2){
                                i.putExtra("questionsSection2",sections);
                            }
                            if(sections.getSerialNo()==3){
                                i.putExtra("questionsSection3",sections);
                            }
                            if(sections.getSerialNo()==4){
                                i.putExtra("questionsSection4",sections);
                            }
                            if(sections.getSerialNo()==5){
                                i.putExtra("questionsSection5",sections);
                            }
                            if(sections.getSerialNo()==12)
                                i.putExtra("questions",sections);
                        }
                        context.startActivity(i);
                        break;
                    case 14:
                        i = new Intent(context, HandwritingSection.class);
                        for(BooleanQuestion sections : allQuestions){
                            //Log.d("sectionNo",allQuestions.get(5).getSerialNo()+"");

                            if(sections.getSerialNo()==2){
                                i.putExtra("questionsSection2",sections);
                            }
                            if(sections.getSerialNo()==3){
                                i.putExtra("questionsSection3",sections);
                            }
                            if(sections.getSerialNo()==4){
                                i.putExtra("questionsSection4",sections);
                            }
                            if(sections.getSerialNo()==5){
                                i.putExtra("questionsSection5",sections);
                            }
                            if(sections.getSerialNo()==12)
                                i.putExtra("questions",sections);
                        }
                        context.startActivity(i);
                        break;

                }
            }
        });
    }
    public void setCompleted(AnalysisStatusAdapter.MyViewHolder holder){
        holder.sectionName.setBackground(context.getResources().getDrawable(R.drawable.rounded_rectangle_green_analysis));
        holder.sectionName.setTextColor(context.getResources().getColor(R.color.white));
    }
    @Override
    public int getItemCount() {
        return sectionNames.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView sectionImage;
        TextView sectionName;
        TextView time;
        ImageView infoButton;
        View parent;
        public MyViewHolder(View itemView) {
            super(itemView);
            infoButton = itemView.findViewById(R.id.info_button);
            sectionImage=itemView.findViewById(R.id.section_image);
            sectionName=itemView.findViewById(R.id.section_name);
            time = itemView.findViewById(R.id.section_time);

            parent=this.itemView;
        }
    }
}
