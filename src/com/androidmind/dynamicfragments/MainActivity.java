package com.androidmind.dynamicfragments;

import com.androidmind.dynamicfragments.R;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	static List<WorkoutFragment> workoutArray = new ArrayList<WorkoutFragment>();
	public static int workoutInt = 0;
	public static String hasRan = "0";
	public static int workoutNeedsRestore = 0;
	public static int workoutRestoreInt = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		SharedPreferences savePref = getPreferences(MODE_PRIVATE);
		hasRan = savePref.getString("hasRan", hasRan);
		Log.v("TAG", "hasRan= " + hasRan);

		if (hasRan.equals("1")) { //restore workout
			FragmentManager fragmentManager = getFragmentManager();
			WorkoutFragment workoutFrag = new WorkoutFragment();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			fragmentTransaction.add(R.id.workout_fragment_container,
					workoutFrag, "0");
			workoutNeedsRestore = 1;
			workoutRestoreInt = 1;
			fragmentTransaction.commit();

			// Log.v("TAG", "RestoreInstance "
			// + fragmentManager.getFragment(savedInstanceState, "r0"));
		}

		Button addWorkout = (Button) findViewById(R.id.btn_workout);
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) { // new workout
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				WorkoutFragment workoutFrag = new WorkoutFragment();
				workoutArray.add(workoutFrag);
				fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,
						R.anim.slide_out_left, R.anim.slide_out_left,
						R.anim.slide_in_right);
				fragmentTransaction.add(R.id.workout_fragment_container,
						workoutFrag, Integer.toString(workoutInt));
				System.out.println("Add a workout "
						+ Integer.toString(workoutInt));
				// fragmentTransaction.addToBackStack("myFrag");
				fragmentTransaction.commit();
				workoutInt++;
			}
		};
		addWorkout.setOnClickListener(listener);
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);

		if (hasRan.equals("0")) {
			hasRan = "1";
			String hasRanBefore = String.valueOf(hasRan);

			SharedPreferences savePref = getPreferences(MODE_PRIVATE);
			SharedPreferences.Editor editor = savePref.edit();
			editor.putString("hasRan", hasRanBefore);
			editor.commit();
		}

		FragmentManager fragmentManager = getFragmentManager();
		getFragmentManager().putFragment(savedInstanceState, "0",
				fragmentManager.findFragmentByTag("0"));
	}
}
