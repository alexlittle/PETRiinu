package riinu.consult.alexlittle.net.petriinu;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;

/**
 * Created by alex on 25.2.2018.
 */

public class PhotoListAdapter extends ArrayAdapter<Photo> {



    private final Context ctx;
    private final ArrayList<Photo> photoList;
    private SharedPreferences prefs;
    public TextToSpeech tts;

    public PhotoListAdapter(Activity context, ArrayList<Photo> photoList) {
        super(context, R.layout.photo_row, photoList);
        this.ctx = context;
        this.photoList = photoList;
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.photo_row, parent, false);


        final Photo p = photoList.get(position);

        Button titleView = (Button) rowView.findViewById(R.id.photo_button);
        titleView.setText(p.photoTitle);
        titleView.setCompoundDrawablesWithIntrinsicBounds( p.photoImage, 0, 0, 0);

        titleView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                tts = new TextToSpeech(ctx, new TextToSpeech.OnInitListener() {

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
                                tts.speak(p.photoVoice, TextToSpeech.QUEUE_ADD, null);
                                //tts.speak(getResources().getString(R.string.app_name), TextToSpeech.QUEUE_ADD, null);


                                try {
                                    Thread.sleep(MainActivity.delay);
                                } catch (InterruptedException ie){

                                }

                            }
                        }
                        else
                            Log.e("error", "Initilization Failed!");
                    }
                });


            }
        });

        return rowView;
    }
}
