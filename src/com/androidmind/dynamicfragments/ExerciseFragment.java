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
		View v = inflater.inflate(R.layout.exercise_layout, null);
		TextView displayTextView = (TextView) v.findViewById(R.id.text1);
		displayTextView.setText("e"
				+ Integer.toString(WorkoutFragment.exerciseInt-1));
		
		// LinearLayout exLinearLayout = (LinearLayout) v
		// .findViewById(R.id.exLinearLayout);
		// EditText exTitle = (EditText) v.findViewById(R.id.exTitle);
		// EditText exKg = (EditText) v.findViewById(R.id.exKg);
		// EditText exSets = (EditText) v.findViewById(R.id.exSets);
		// EditText exReps = (EditText) v.findViewById(R.id.exReps);
		
		Button btnDeleteExercise = (Button) v.findViewById(R.id.exDelete);
		btnDeleteExercise.setTag(displayTextView.getText());
		OnClickListener listener2 = new OnClickListener() {

			@Override
			public void onClick(View v) {//delete exercise
				if ((WorkoutFragment.exerciseArray.size() - 1) >= 0) {
					FragmentManager fragmentManager = getFragmentManager();
					//Fragment fragment2 = getFragmentManager().findFragmentById(
					//		R.id.workout_fragment_container2);
					//String tag = (String) fragment2.getTag();
					//System.out.println("Delete an exercise " + tag);
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					fragmentTransaction.addToBackStack("myFrag3");
					System.out.println("tag of the button " + v.getTag());
					fragmentTransaction.remove(
							fragmentManager.findFragmentByTag((String) v.getTag())).commit();
				}
			}
		};
		btnDeleteExercise.setOnClickListener(listener2);
		return v;

	}
}
