package com.androidmind.dynamicfragments;

import com.androidmind.dynamicfragments.R;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	static List<WorkoutFragment> workoutArray = new ArrayList<WorkoutFragment>();
	/** Called when the activity is first created. */
	public static int workoutInt = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button addWorkout = (Button) findViewById(R.id.btn_workout);
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) { //new workout
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				WorkoutFragment workoutFrag = new WorkoutFragment();
				workoutArray.add(workoutFrag);
				// Integer.toString(fragmentInt)
				fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,
						R.anim.slide_out_left, R.anim.slide_out_left,
						R.anim.slide_in_right);
				fragmentTransaction.add(R.id.workout_fragment_container,
						workoutFrag, Integer.toString(workoutInt));
				System.out.println("Add a workout " + Integer.toString(workoutInt));
				fragmentTransaction.addToBackStack("myFrag");
				// use the unique number Integer.toString(workoutInt)
				
				fragmentTransaction.commit();
				workoutInt++;
			}
		};
		addWorkout.setOnClickListener(listener);
		
		
	}
}