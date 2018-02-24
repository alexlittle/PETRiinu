package riinu.consult.alexlittle.net.petriinu;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by alex on 24.2.2018.
 */

public class DailySchedule implements Serializable {

    public static final String TAG = DailySchedule.class.getSimpleName();
    public DateTime scheduleDateTime;
    public String scheduleQuestion = "";
    public String scheduleResponse = "";
    public String scheduleTitle = "";
}
