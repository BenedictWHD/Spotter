package com.androidmind.dynamicfragments;

import com.androidmind.dynamicfragments.R;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

public class ExerciseFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
	
		/** Inflating the layout for this fragment **/
		View v = inflater.inflate(R.layout.exercise_layout, null);
		TextView displayTextView = (TextView) v.findViewById(R.id.text1);
		displayTextView.setText("E"
				+ Integer.toString(WorkoutFragment.exerciseInt));

	
		// LinearLayout exLinearLayout = (LinearLayout) v
		// .findViewById(R.id.exLinearLayout);
		// EditText exTitle = (EditText) v.findViewById(R.id.exTitle);
		// EditText exKg = (EditText) v.findViewById(R.id.exKg);
		// EditText exSets = (EditText) v.findViewById(R.id.exSets);
		// EditText exReps = (EditText) v.findViewById(R.id.exReps);
		
		Button btnDeleteWorkout = (Button) v.findViewById(R.id.exDelete);
		OnClickListener listener2 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				if ((WorkoutFragment.exerciseArray.size() - 1) >= 0) {
					FragmentManager fragmentManager = getFragmentManager();
					// id = fragment.getTag();


					Fragment fragment2 = getFragmentManager().findFragmentById(
							R.id.workout_fragment_container2);
					String tag = (String) fragment2.getTag();
					System.out.println(tag + "exerciseTag");
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					fragmentTransaction.addToBackStack("myFrag3");
					fragmentTransaction.remove(
							fragmentManager.findFragmentByTag(tag)).commit();
				}
			}
		};

		btnDeleteWorkout.setOnClickListener(listener2);
		// LinearLayout exLinearLayout = (LinearLayout) v
		// .findViewById(R.id.exLinearLayout);
		// EditText exTitle = (EditText) v.findViewById(R.id.exTitle);
		// EditText exKg = (EditText) v.findViewById(R.id.exKg);
		// EditText exSets = (EditText) v.findViewById(R.id.exSets);
		// EditText exReps = (EditText) v.findViewById(R.id.exReps);

		return v;

	}
}
