package com.sapientnl.android.oview;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;

public class PollFragment extends Fragment{
    private static final String ARGS_POLL_ID = "poll_id";//This variable is going to be transfered from the PollActivity
    private Poll mPoll; //An instance of a Question
    private ImageView mPollImageView; //The image from the voting page
    private Button mDisagreeButton; //The button that shows the user doesn't agree with the poll
    private Button mAgreeButton; //The button that shows the user agrees with the poll
    private TextView mTitleTextView; //The title of the poll
    private TextView mSources; //List of sources related to the poll

	//We prefer to have an empty constructor and call this function from PollActivity instead
    public static PollFragment newInstance(UUID pollId) {
        Bundle args = new Bundle();
		//save the poll that Poll activity sent to PollFragment through this function
        args.putSerializable(ARGS_POLL_ID, pollId);
        PollFragment fragment = new PollFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID pollId = (UUID) getArguments().getSerializable(ARGS_POLL_ID);
        mPoll = PollGenerator.get(getActivity()).getPoll(pollId);
    }
	//When created, inflate the fragment on the container located on the path R.layout.fragment_poll and connect every widget of this
	//layout withe the variables we have created at the beginning.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_poll,container,false);

        mTitleTextView = (TextView) v.findViewById(R.id.poll_title);
        mTitleTextView.setText(mPoll.getTitle());

        mDisagreeButton = (Button) v.findViewById(R.id.disagree_button);
		//If this button is clicked, set the variable mAnsweredAgree from the Poll class to false and call the function changeFragment()
        mDisagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPoll.setAnsweredAgree(false);
                changeFragment();
            }
        });

        mAgreeButton = (Button) v.findViewById(R.id.agree_button);
		//If this button is clicked, set the variable mAnsweredAgree from the Poll class to true and call the function changeFragment()
        mAgreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPoll.setAnsweredAgree(true);
                changeFragment();
            }
        });

        mSources = (TextView) v.findViewById(R.id.sources);
        mSources.setText(mPoll.getSources().toString().replaceAll("\\p{P}",""));

        return v;
    }
	
	//This method calls this fragment's parent activity (PollActivity) and saves it in a FragmentChangeListener variable. Then it calls replaceFragment()
	//that belongs to this interface and is implemented inside PollActivity
    private void changeFragment(){
        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
        fc.replaceFragment();
    }
}
