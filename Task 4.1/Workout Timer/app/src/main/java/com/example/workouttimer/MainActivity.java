package com.example.workouttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView text_view_1;
    private Chronometer chrono_meter;
    private TextView text_view_2;
    private long pauseOffset;
    private boolean running=false;
    private long chrono_time;
    private SharedPreferences sp;
    public String workoutTime;
    public String workoutType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("SharedPrefs", MODE_PRIVATE);

        chrono_meter = findViewById(R.id.timer);
        text_view_1 = findViewById(R.id.Output1);
        text_view_2 = findViewById(R.id.Input1);

        updateWorkoutDetails();

        if(savedInstanceState!=null){

            chrono_meter.setBase(SystemClock.elapsedRealtime() - savedInstanceState.getLong("chrono_time"));
            pauseOffset = savedInstanceState.getLong("PauseOffset");
            running = savedInstanceState.getBoolean("running");
            chrono_time = SystemClock.elapsedRealtime() - chrono_meter.getBase();;

            if(running){
                chrono_meter.start();
            }
        }

        chrono_meter.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener()
        {
            @Override
            public void onChronometerTick(Chronometer chrono_meter)
            {
                chrono_time = SystemClock.elapsedRealtime() - chrono_meter.getBase();
            }
        });
    }

    public void updateWorkoutDetails(){
        System.out.println("Update function is running");

        workoutTime = sp.getString("workoutTime", "");
        workoutType = sp.getString("workoutType", "");

        System.out.println(workoutTime);
        System.out.println(workoutType);
        if(!workoutTime.equals("")) {
            System.out.println("Loop 1");
            if(!workoutType.equals("")){
                System.out.println("Loop 2");
                text_view_1.setText("You spent " + workoutTime + " on " + workoutType + " last time.");
            }
            else{
                text_view_1.setText("You spent " + workoutTime + " on your workout last time.");
            }
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        chrono_meter.stop();
        savedInstanceState.putLong("chrono_time", chrono_time);
        savedInstanceState.putLong("PauseOffset", pauseOffset);
        savedInstanceState.putBoolean("running", running);
    }

    public void startTimer(View view){
        if(!running){
            chrono_meter.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chrono_meter.start();
            running = true;
        }
    }

    public void pauseTimer(View view){
        if(running){
            chrono_meter.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chrono_meter.getBase();
            running = false;
        }
    }

    public void stopTimer(View view){
        chrono_meter.stop();

        int mins = (int)(chrono_time/1000/60);
        int secs = (int)((chrono_time/1000)-(mins*60));
        String minsStr = String.valueOf(mins);
        String secsStr = String.valueOf(secs);

        if(mins<10){
            minsStr = ("0"+minsStr);
        }
        if(secs<10){
            secsStr = ("0"+secsStr);
        }

        String timeStr = (minsStr+":"+secsStr);
        System.out.println(timeStr);
        System.out.println(chrono_time);

        if(chrono_time>0){
            SharedPreferences.Editor workoutEdit = sp.edit();
            workoutEdit.putString("workoutTime", timeStr);
            workoutEdit.putString("workoutType", text_view_2.getText().toString());

            workoutEdit.commit();
            updateWorkoutDetails();
        }

        chrono_meter.setBase(SystemClock.elapsedRealtime());

        pauseOffset = 0;
        chrono_time = 0;
        running = false;

        Toast.makeText(getApplicationContext(), "Stopwatch has been reset!", Toast.LENGTH_SHORT).show();
    }
}