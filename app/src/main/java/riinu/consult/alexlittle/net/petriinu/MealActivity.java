package riinu.consult.alexlittle.net.petriinu;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        TextView questionTv = (TextView) findViewById(R.id.question);
        questionTv.setText(ds.scheduleQuestion);

        Button snackBtn = (Button) findViewById(R.id.snackbutton);

        snackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                tts = new TextToSpeech(MealActivity.this, new TextToSpeech.OnInitListener() {

                    @Override
                    public void onInit(int status) {
                        // TODO Auto-generated method stub
                        if(status == TextToSpeech.SUCCESS){
                            int result=tts.setLanguage(MainActivity.locale);
                            if(result==TextToSpeech.LANG_MISSING_DATA ||
                                    result==TextToSpeech.LANG_NOT_SUPPORTED){
                                Log.e("error", "This Language is not supported");
                                MealActivity.this.finish();
                            }
                            else{
                                tts.speak("thank you", TextToSpeech.QUEUE_ADD, null);
                                //tts.speak(getResources().getString(R.string.app_name), TextToSpeech.QUEUE_ADD, null);


                                try {
                                    Thread.sleep(MainActivity.delay);
                                } catch (InterruptedException ie){

                                }
                                MealActivity.this.finish();

                            }
                        }
                        else
                            Log.e("error", "Initilization Failed!");
                        MealActivity.this.finish();
                    }
                });


            }
        });


        Button sandwichBtn = (Button) findViewById(R.id.sandwichbutton);

        sandwichBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                tts = new TextToSpeech(MealActivity.this, new TextToSpeech.OnInitListener() {

                    @Override
                    public void onInit(int status) {
                        // TODO Auto-generated method stub
                        if(status == TextToSpeech.SUCCESS){
                            int result=tts.setLanguage(MainActivity.locale);
                            if(result==TextToSpeech.LANG_MISSING_DATA ||
                                    result==TextToSpeech.LANG_NOT_SUPPORTED){
                                Log.e("error", "This Language is not supported");
                                MealActivity.this.finish();
                            }
                            else{
                                tts.speak("thank you", TextToSpeech.QUEUE_ADD, null);
                                //tts.speak(getResources().getString(R.string.app_name), TextToSpeech.QUEUE_ADD, null);


                                try {
                                    Thread.sleep(MainActivity.delay);
                                } catch (InterruptedException ie){

                                }
                                MealActivity.this.finish();

                            }
                        }
                        else
                            Log.e("error", "Initilization Failed!");
                        MealActivity.this.finish();
                    }
                });


            }
        });


        Button hotBtn = (Button) findViewById(R.id.hotmealbutton);

        hotBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                tts = new TextToSpeech(MealActivity.this, new TextToSpeech.OnInitListener() {

                    @Override
                    public void onInit(int status) {
                        // TODO Auto-generated method stub
                        if(status == TextToSpeech.SUCCESS){
                            int result=tts.setLanguage(MainActivity.locale);
                            if(result==TextToSpeech.LANG_MISSING_DATA ||
                                    result==TextToSpeech.LANG_NOT_SUPPORTED){
                                Log.e("error", "This Language is not supported");
                                MealActivity.this.finish();
                            }
                            else{
                                tts.speak("thank you", TextToSpeech.QUEUE_ADD, null);
                                //tts.speak(getResources().getString(R.string.app_name), TextToSpeech.QUEUE_ADD, null);


                                try {
                                    Thread.sleep(MainActivity.delay);
                                } catch (InterruptedException ie){

                                }
                                MealActivity.this.finish();

                            }
                        }
                        else
                            Log.e("error", "Initilization Failed!");
                        MealActivity.this.finish();
                    }
                });


            }
        });

        tts = new TextToSpeech(MealActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(MainActivity.locale);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                    else{
                        tts.speak(getResources().getString(R.string.app_name), TextToSpeech.QUEUE_ADD, null);

                        tts.speak(ds.scheduleQuestion, TextToSpeech.QUEUE_ADD, null);
                    }
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        });

    }


    @Override
    public void onDestroy()
    {
        // Don't forget to shutdown!
        if (tts != null)
        {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
