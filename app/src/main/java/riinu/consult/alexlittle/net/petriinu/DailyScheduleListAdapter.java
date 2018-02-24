package riinu.consult.alexlittle.net.petriinu;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alex on 24.2.2018.
 */

public class DailyScheduleListAdapter extends ArrayAdapter<DailySchedule> {

    public static final String TAG = DailyScheduleListAdapter.class.getSimpleName();

    private final Context ctx;
    private final ArrayList<DailySchedule> dailyScheduleList;
    private SharedPreferences prefs;

    public DailyScheduleListAdapter(Activity context, ArrayList<DailySchedule> dailyScheduleList) {
        super(context, R.layout.daily_schedule_row, dailyScheduleList);
        this.ctx = context;
        this.dailyScheduleList = dailyScheduleList;
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.daily_schedule_row, parent, false);
        DailySchedule s = dailyScheduleList.get(position);
        TextView titleView = (TextView) rowView.findViewById(R.id.schedule_title);
        titleView.setText(s.scheduleTitle);
        rowView.setTag(s);
        return rowView;
    }
}
