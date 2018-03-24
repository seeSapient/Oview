package com.sapientnl.android.oview;

import android.content.Context;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/*Since there is no database, this is a temporary way of creating polls*/
public class PollGenerator {
    private static PollGenerator sPollGenerator;
    private List<Poll> mPolls; //List where polls are saved
    List<String> mSources; //List of all the titles of the sources for the polls
	//PollGenerator is a singleton
    private PollGenerator(Context context) throws MalformedURLException {
		//Start creating those polls
        mPolls = new ArrayList<>();
        Poll poll = new Poll();
        mSources = new ArrayList<>();
        mSources.add(new String("Why companies are abandoning the NRA"));
        mSources.add(new String("United and Delta cut ties to NRA"));
        mSources.add(new String("FedEx stands by NRA"));
        //for 2nd poll
        mSources.add(new String("Trumps calls for arming teachers raising gun purchase age to stop savage shooters"));
        mSources.add(new String("President Trump Attempts to Clarify His Suggestion That We Should Arm Teachers to Prevent School Shootings"));
        //for 3rd poll
        mSources.add(new String("Video Games and Violence"));
        mSources.add(new String("Video games and violence are linked but not the way Donald Trump thinks"));
		//Complete first poll
        poll.setTitle("Do you think the boycotts are a good way and a step forward to change the gun policy in the USA?");
        poll.setSources(mSources.subList(0,2));
        mPolls.add(poll);
        //Create second poll
        poll = new Poll();
        poll.setTitle("Do you agree with giving guns to teachers in schools?");
        poll.setSources(mSources.subList(3,4));
        mPolls.add(poll);
		//Create third poll
        poll = new Poll();
        poll.setTitle("Do you believe that violent video games, movies, songs have an impact to how violent an individual may be?");
        poll.setSources(mSources.subList(5,6));
        mPolls.add(poll);
    }
	//Create a PollGenerator just once, through PollActivity
    public static PollGenerator get(Context context) {
        if(sPollGenerator == null) {
            try {
                sPollGenerator = new PollGenerator(context);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return sPollGenerator;
    }
	//Get the list of polls through this method
    public List<Poll> getPolls(){
        return mPolls;
    }
	//Get the poll with a specific id
    public Poll getPoll(UUID id) {
        for(Poll poll : mPolls) {
            if(poll.getId().equals(id)) {
                return poll;
            }
        }
        return null;
    }
}
