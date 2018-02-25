package riinu.consult.alexlittle.net.petriinu;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    public final static Locale locale = Locale.UK;
    public final static int delay = 2000;

    private TextView mTextMessage;
    private TextView mAskQuestion;

    private ArrayList<DailySchedule> dailyScheduleList = new ArrayList<DailySchedule>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createScheduleData();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        AppWidgetHost appWidgetHost = new AppWidgetHost(this, 301);
        AppWidgetProviderInfo newAppWidgetProviderInfo = new AppWidgetProviderInfo();

        // Get an id
        int appWidgetId = appWidgetHost.allocateAppWidgetId();

// Get the list of installed widgets
        List<AppWidgetProviderInfo> appWidgetInfos = new ArrayList<AppWidgetProviderInfo>();
        appWidgetInfos = appWidgetManager.getInstalledProviders();

        for(int j = 0; j < appWidgetInfos.size(); j++)
        {
            Log.d("widget",appWidgetInfos.get(j).provider.getPackageName() + ":" + appWidgetInfos.get(j).provider.getClassName());
            if (appWidgetInfos.get(j).provider.getPackageName().equals("com.google.android.deskclock") && appWidgetInfos.get(j).provider.getClassName().equals("com.android.alarmclock.AnalogAppWidgetProvider"))
            {
                // Get the full info of the required widget
                newAppWidgetProviderInfo = appWidgetInfos.get(j);
                //break;
            }
        }

// Create Widget
        AppWidgetHostView hostView = appWidgetHost.createView(this, appWidgetId, newAppWidgetProviderInfo);
        hostView.setAppWidget(appWidgetId, newAppWidgetProviderInfo);

// Add it to your layout
        LinearLayout ll = (LinearLayout) findViewById(R.id.date_weather);
        ll.addView(hostView);

        DailyScheduleListAdapter dailyScheduleAdapter = new DailyScheduleListAdapter(this, dailyScheduleList);
        ListView dayScheduleList = (ListView) findViewById(R.id.day_schedule);
        dayScheduleList.setAdapter(dailyScheduleAdapter);

        dayScheduleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DailySchedule selectedSchedule = dailyScheduleList.get(position);
                Intent i;
                if (position == 0 || position == 2 || position ==4) {
                    i = new Intent(MainActivity.this, MedicationActivity.class);
                } else if (position == 1 || position == 3 || position ==5) {
                    i = new Intent(MainActivity.this, MealActivity.class);
                } else {
                    i = new Intent(MainActivity.this, ExerciseActivity.class);
                }
                Bundle tb = new Bundle();
                tb.putSerializable(DailySchedule.TAG, selectedSchedule);
                i.putExtras(tb);
                startActivity(i);
            }
        });
    }

    private void createScheduleData(){
        DailySchedule ds1 = new DailySchedule();
        ds1.scheduleTitle = "Morning medication";
        ds1.scheduleDateTime = new DateTime(2018,02,25,8, 30  );
        ds1.scheduleQuestion = "Did you remember to take your donepezil medication this morning?";
        ds1.scheduleImage = R.mipmap.donepezil;
        dailyScheduleList.add(ds1);

        DailySchedule ds2 = new DailySchedule();
        ds2.scheduleTitle = "Breakfast";
        ds2.scheduleDateTime = new DateTime(2018,02,25,9, 30  );
        ds2.scheduleQuestion = "What did you have for breakfast?";
        dailyScheduleList.add(ds2);

        DailySchedule ds3 = new DailySchedule();
        ds3.scheduleTitle = "Lunchtime medication";
        ds3.scheduleDateTime = new DateTime(2018,02,25,13, 00  );
        ds3.scheduleQuestion = "Did you remember to take your blood pressure medication this lunchtime?";
        ds3.scheduleImage = R.mipmap.blood_pressue;
        dailyScheduleList.add(ds3);

        DailySchedule ds4 = new DailySchedule();
        ds4.scheduleTitle = "Lunch";
        ds4.scheduleDateTime = new DateTime(2018,02,25,14, 00  );
        ds4.scheduleQuestion = "What did you have for lunch?";
        dailyScheduleList.add(ds4);

        DailySchedule ds5 = new DailySchedule();
        ds5.scheduleTitle = "Evening medication";
        ds5.scheduleDateTime = new DateTime(2018,02,25,18, 00  );
        ds5.scheduleQuestion = "Did you remember to take your memory medication this evening?";
        ds5.scheduleImage = R.mipmap.memory;
        dailyScheduleList.add(ds5);

        DailySchedule ds6 = new DailySchedule();
        ds6.scheduleTitle = "Dinner";
        ds6.scheduleDateTime = new DateTime(2018,02,25,20, 00  );
        ds6.scheduleQuestion = "What did you have for dinner?";
        dailyScheduleList.add(ds6);

        DailySchedule ds7 = new DailySchedule();
        ds7.scheduleTitle = "Activity";
        ds7.scheduleDateTime = new DateTime(2018,02,25,21, 00  );
        ds7.scheduleQuestion = "What activities did you do today?";
        dailyScheduleList.add(ds7);
    }
}
