/**
 * Name: Matthias Idemundia
 * Date: 04/16/2025
 * Explanation: HW04 Gymlog Class
 */

package com.example.hw04_gymlog;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hw04_gymlog.database.GymLogRepository;
import com.example.hw04_gymlog.database.entities.GymLog;
import com.example.hw04_gymlog.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private GymLogRepository repository;

    public static final String TAG = "GYMLOG";
    String mExercise = "";
    double mWeight = 0.0;
    int mReps = 0;

    // TODO: Add login information
    int loggedInUserId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = GymLogRepository.getRepository(getApplication());

        binding.logDisplayTextView.setMovementMethod(new ScrollingMovementMethod());
        updateDisplay();

        binding.logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInformationFromDisplay();
                insertGymLogRecord();
                updateDisplay();
            }
        });

        binding.exerciseInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay();
            }
        });
    }

    private void insertGymLogRecord() {
        if(mExercise.isEmpty()) {
            return;
        }
        GymLog log = new GymLog(mExercise, mWeight, mReps, loggedInUserId);
        repository.insertGymLog(log);
    }

    private void updateDisplay() {
        ArrayList<GymLog> allLogs = repository.getAllLogs();
        if(allLogs.isEmpty()) {
            binding.logDisplayTextView.setText(R.string.nothing_to_show_time_to_hit_the_gym);
        }
        StringBuilder sb = new StringBuilder();
        for(GymLog log : allLogs) {
            sb.append(log);
        }
        binding.logDisplayTextView.setText(sb.toString());
    }

    private void getInformationFromDisplay(){
        mExercise = binding.exerciseInputEditText.getText().toString();
        try {
            mWeight = Double.parseDouble(binding.weightInputEditText.getText().toString());
        } catch (NumberFormatException e) {
            Log.d(TAG, "Error while reading weight input text.");
        }

        try {
            mReps = Integer.parseInt(binding.repInputEditText.getText().toString());
        } catch (NumberFormatException e) {
            Log.d(TAG, "Error while reading rep input text.");
        }
    }

}