package riinu.consult.alexlittle.net.petriinu;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by alex on 25.2.2018.
 */

public class PhotoActivity  extends AppCompatActivity {

    private ArrayList<Photo> photoList = new ArrayList<Photo>();
    public TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        createPhotoData();

        PhotoListAdapter photoAdapter = new PhotoListAdapter(this, photoList);
        ListView photoShowList = (ListView) findViewById(R.id.photo_list);
        photoShowList.setAdapter(photoAdapter);


    }


    private void createPhotoData() {
        Photo p1 = new Photo();
        p1.photoTitle = "Friend - Jussi";
        p1.photoImage = R.mipmap.friend;
        p1.photoVoice = "This is your friend Jussi, you often go ice fishing with Jussi";
        photoList.add(p1);

        Photo p2 = new Photo();
        p2.photoTitle = "Ex-wife - Riitta";
        p2.photoImage = R.mipmap.wife;
        p2.photoVoice = "This was your wife Riitta";
        photoList.add(p2);

        Photo p3 = new Photo();
        p3.photoTitle = "Son - Jarkko";
        p3.photoImage = R.mipmap.son;
        p3.photoVoice = "This is your son Jarkko, he lives in Helsinki and will come to visit you at the weekend";
        photoList.add(p3);

        Photo p4 = new Photo();
        p4.photoTitle = "Daughter - Kati";
        p4.photoImage = R.mipmap.daughter;
        p4.photoVoice = "This is your daughter Kati, she lives in Espoo with her husband and your two grandchildren";
        photoList.add(p4);

    }
}
