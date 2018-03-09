package com.sapientnl.android.oview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;
//The activity that hosts our Poll fragments
public class PollActivity extends AppCompatActivity implements FragmentChangeListener {
    private PollGenerator pollGenerator = PollGenerator.get(this);
    private List<Poll> polls = pollGenerator.getPolls(); //Get the list created on PollGenerator
    private UUID pollId; //poll's id
    private int position = 0; //Position of poll in the list
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);//Connect activity with a layout

        FragmentManager fm = getSupportFragmentManager(); //Get the support fragment manager for this activity
        Fragment fragment = fm.findFragmentById(R.id.fragment_container); //Give fragment a space inside the activity
        if(fragment == null) {
            fragment = createFragment();
			//Show newly created fragment to the screen
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.fragment_container,fragment);
            transaction.commit();
        }

    }
	//Create a new fragment by calling the newInstance method from PollFragment
    public PollFragment createFragment() {
        changePoll(position);//Keep track of the position of the poll so that you can show the next one
        return PollFragment.newInstance(pollId);
    }
	
	//Get the id of the poll in this position and then go to the next poll
    public void changePoll(int pos) {
        pollId = (UUID) polls.get(pos).getId();
        position++;
    }

    @Override
    public void replaceFragment() {
        Fragment fragment;
		//If position>=3 that means that our list has reached to an end. Obviously as you add more polls to the "database" you need to change this statement
        if(position>=3){
           fragment = new EndPage();
        }
        else{
			//Otherwise call createFragment to create a new poll
            fragment = createFragment();
        }
		//Show on screen the new fragment that is going to replace the previous one
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
