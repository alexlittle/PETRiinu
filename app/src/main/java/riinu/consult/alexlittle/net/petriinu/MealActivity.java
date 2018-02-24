package riinu.consult.alexlittle.net.petriinu;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Locale;

/**
 * Created by alex on 24.2.2018.
 */

public class MealActivity extends AppCompatActivity {

    private DailySchedule ds;
    public TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            ds = (DailySchedule) bundle.getSerializable(DailySchedule.TAG);
        }

    }


    @Override
    public void onStart() {
        super.onStart();

        tts = new TextToSpeech(MealActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(Locale.UK);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                    else{
                        tts.speak(ds.scheduleQuestion, TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        });

    }
}
