package com.androidmind.dynamicfragments;

import com.androidmind.dynamicfragments.R;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WorkoutFragment extends Fragment {
	public static int exerciseInt = 0;
	final static List<ExerciseFragment> exerciseArray = new ArrayList<ExerciseFragment>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		/** Inflating the layout for this fragment **/
		View v = inflater.inflate(R.layout.workout_layout, null);
		TextView displayTextView = (TextView) v.findViewById(R.id.text1);
		displayTextView
				.setText("W" + Integer.toString(MainActivity.workoutInt-1));
		Button btnAddWorkout = (Button) v.findViewById(R.id.woAdd);
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) { //add exercise
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				ExerciseFragment exerciseFrag = new ExerciseFragment();
				exerciseArray.add(exerciseFrag);
				// Integer.toString(fragmentInt)
				fragmentTransaction.setCustomAnimations(R.anim.fadein,
						R.anim.slide_out_left, R.anim.slide_out_left,
						R.anim.slide_in_right);
				String exerciseTag = ("e").concat(Integer
						.toString(exerciseInt));
				fragmentTransaction.add(R.id.workout_fragment_container2,
						exerciseFrag, exerciseTag);
				System.out.println("Add an exercise " + exerciseTag);
				// use the unique number
				fragmentTransaction.addToBackStack("myFrag2");
				
				fragmentTransaction.commit();
				exerciseInt++;
			}
		};
		btnAddWorkout.setOnClickListener(listener);
		Button btnDelete = (Button) v.findViewById(R.id.woDelete);
		OnClickListener listenerDelete = new OnClickListener() {

			@Override
			public void onClick(View v) { //delete workout
				if ((MainActivity.workoutArray.size() - 1) >= 0) {
					FragmentManager fragmentManager = getFragmentManager();
					// id = fragment.getTag();
					Fragment fragment = getFragmentManager().findFragmentById(
							R.id.workout_fragment_container);
					String tag = (String) fragment.getTag();
					System.out.println("Delete a workout" + tag + " I am returning: " + fragment);
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					fragmentTransaction.remove(
							fragmentManager.findFragmentByTag(tag)).commit();
				}
			}
		};
		btnDelete.setOnClickListener(listenerDelete);

	
		return v;
	}
}
