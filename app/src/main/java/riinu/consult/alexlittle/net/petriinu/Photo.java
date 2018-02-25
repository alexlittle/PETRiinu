package riinu.consult.alexlittle.net.petriinu;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alex on 25.2.2018.
 */

public class Photo implements Serializable {

    public static final String TAG = Photo.class.getSimpleName();
    public String photoTitle = "";
    public String photoVoice = "";
    public int photoImage = 0;
}