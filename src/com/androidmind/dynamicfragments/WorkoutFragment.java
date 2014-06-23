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
import android.widget.LinearLayout;
import android.widget.TextView;

public class WorkoutFragment extends Fragment {
	public static int exerciseInt = 0;
	final static List<ExerciseFragment> exerciseArray = new ArrayList<ExerciseFragment>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		
		View v = inflater.inflate(R.layout.workout_layout, null);
		TextView displayTextView = (TextView) v.findViewById(R.id.textWo);
		
		if(MainActivity.workoutNeedsRestore == 1){//restore
			displayTextView.setText(Integer.toString(MainActivity.workoutRestoreInt - 1)); 
			MainActivity.workoutNeedsRestore = 0;
		}else{
		displayTextView.setText(Integer.toString(MainActivity.workoutInt - 1));
		}
		// Button btnNew = new Button(getActivity()); //programatic button
		// btnNew.setText("container2 " +
		// Integer.toString(MainActivity.workoutInt -1));
		// btnNew.setId((MainActivity.workoutInt-1));
		// ((ViewGroup) v).addView(btnNew);

		LinearLayout exerciseLayout = new LinearLayout(getActivity());
		exerciseLayout.setOrientation(LinearLayout.VERTICAL);
		// exerciseLayout.setId((MainActivity.workoutInt));
		String a = (String) displayTextView.getText();
		final int uniqueExerciseId = Integer.parseInt(a)+1;
		exerciseLayout.setId(uniqueExerciseId);
		((ViewGroup) v).addView(exerciseLayout);

		// final LinearLayout exLayout = (LinearLayout)
		// v.findViewById(R.id.workout_fragment_container2);
		// exLayout.setTag(displayTextView.getText());//set container id

		Button btnAddWorkout = (Button) v.findViewById(R.id.woAdd);
		btnAddWorkout.setTag(displayTextView.getText());
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) { // add exercise
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				ExerciseFragment exerciseFrag = new ExerciseFragment();
				exerciseArray.add(exerciseFrag);

				fragmentTransaction.setCustomAnimations(R.anim.fadein,
						R.anim.slide_out_left, R.anim.slide_out_left,
						R.anim.slide_in_right);
				// System.out.println("Layout id" + exLayout.getTag() + " " +
				// exLayout.getParent());

				String exerciseTag = ("e")
						.concat(Integer.toString(exerciseInt));
				System.out.println("tag adding exercise: " + v.getTag());

				fragmentTransaction.add(uniqueExerciseId, exerciseFrag,
						exerciseTag);
				System.out.println("Add an exercise " + exerciseTag);
				//fragmentTransaction.addToBackStack("myFrag2");
				fragmentTransaction.commit();
				exerciseInt++;
			}
		};
		btnAddWorkout.setOnClickListener(listener);
		Button btnDelete = (Button) v.findViewById(R.id.woDelete);
		btnDelete.setTag(displayTextView.getText());
		OnClickListener listenerDelete = new OnClickListener() {

			@Override
			public void onClick(View v) { // delete workout
				if ((MainActivity.workoutArray.size() - 1) >= 0) {
					FragmentManager fragmentManager = getFragmentManager();
					// Fragment fragment =
					// getFragmentManager().findFragmentById(
					// R.id.workout_fragment_container);
					// String tag = (String) fragment.getTag();
					// System.out.println("Delete a workout" + tag +
					// " I am returning: " + fragment);
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
					System.out.println("tag of the button" + v.getTag());
					fragmentTransaction.remove(
							fragmentManager.findFragmentByTag((String) v
									.getTag())).commit();
				}
			}
		};
		btnDelete.setOnClickListener(listenerDelete);
		return v;
	}
	
	
}
