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
    List<URL> mSources; //List of all the url sources for the polls
	//PollGenerator is a singleton
    private PollGenerator(Context context) throws MalformedURLException {
		//Start creating those polls
        mPolls = new ArrayList<>();
        Poll poll = new Poll();
        mSources = new ArrayList<>();
        mSources.add(new URL("http://money.cnn.com/2018/02/25/news/companies/boycott-nra-companies/index.html"));
        mSources.add(new URL("https://www.washingtonpost.com/news/morning-mix/wp/2018/02/24/united-and-delta-cut-ties-to-nra-as-boycott-movement-spreads-to-global-corporations/?utm_term=.1fd02ec9a1cc"));
        mSources.add(new URL("https://www.huffingtonpost.com/entry/fedex-stands-by-nra-discounts_us_5a9486a8e4b01f65f5995fc8"));
        //for 2nd poll
        mSources.add(new URL("http://www.foxnews.com/politics/2018/02/22/trump-calls-for-arming-teachers-raising-gun-purchase-age-to-stop-savage-sicko-shooters.html"));
        mSources.add(new URL("http://time.com/5170304/trump-teachers-armed-guns-florida-school-shooting/"));
        //for 3rd poll
        mSources.add(new URL("https://edition.cnn.com/2016/07/25/health/video-games-and-violence/index.html"));
        mSources.add(new URL(("https://www.theguardian.com/games/2018/feb/28/video-games-and-violence-are-linked-but-not-the-way-donald-trump-thinks")));
		//Complete first poll
        poll.setTitle("Do you think the boycotts are a good way and a step forward to change the gun policy in the USA?");
        poll.setImage(R.drawable.poll1);
        poll.setSources(mSources.subList(0,2));
        mPolls.add(poll);
        //Create second poll
        poll = new Poll();
        poll.setTitle("Do you agree with giving guns to teachers in schools?");
        poll.setImage(R.drawable.poll2);
        poll.setSources(mSources.subList(3,4));
        mPolls.add(poll);
		//Create third poll
        poll = new Poll();
        poll.setTitle("Do you believe that violent video games, movies, songs have an impact to how violent an individual may be?");
        poll.setImage(R.drawable.poll3);
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
