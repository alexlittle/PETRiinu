package riinu.consult.alexlittle.net.petriinu;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by alex on 24.2.2018.
 */

public class MedicationActivity extends AppCompatActivity {

    private DailySchedule ds;
    public TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

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

        Button btnYes = (Button) findViewById(R.id.yes_button);

        btnYes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                tts = new TextToSpeech(MedicationActivity.this, new TextToSpeech.OnInitListener() {

                    @Override
                    public void onInit(int status) {
                        // TODO Auto-generated method stub
                        if(status == TextToSpeech.SUCCESS){
                            int result=tts.setLanguage(Locale.UK);
                            if(result==TextToSpeech.LANG_MISSING_DATA ||
                                    result==TextToSpeech.LANG_NOT_SUPPORTED){
                                Log.e("error", "This Language is not supported");
                                MedicationActivity.this.finish();
                            }
                            else{
                                tts.speak("thank you", TextToSpeech.QUEUE_ADD, null);
                                //tts.speak(getResources().getString(R.string.app_name), TextToSpeech.QUEUE_ADD, null);


                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException ie){

                                }
                                MedicationActivity.this.finish();

                            }
                        }
                        else
                            Log.e("error", "Initilization Failed!");
                        MedicationActivity.this.finish();
                    }
                });


            }
        });

        Button btnNo = (Button) findViewById(R.id.no_button);

        btnNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tts = new TextToSpeech(MedicationActivity.this, new TextToSpeech.OnInitListener() {

                    @Override
                    public void onInit(int status) {
                        // TODO Auto-generated method stub
                        if(status == TextToSpeech.SUCCESS){
                            int result=tts.setLanguage(Locale.UK);
                            if(result==TextToSpeech.LANG_MISSING_DATA ||
                                    result==TextToSpeech.LANG_NOT_SUPPORTED){
                                Log.e("error", "This Language is not supported");
                                MedicationActivity.this.finish();
                            }
                            else{
                                tts.speak("thank you", TextToSpeech.QUEUE_ADD, null);
                                //tts.speak(getResources().getString(R.string.app_name), TextToSpeech.QUEUE_ADD, null);


                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException ie){

                                }
                                MedicationActivity.this.finish();

                            }
                        }
                        else
                            Log.e("error", "Initilization Failed!");
                        MedicationActivity.this.finish();
                    }
                });
            }
        });

        /*Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play(); */

        tts = new TextToSpeech(MedicationActivity.this, new TextToSpeech.OnInitListener() {

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
