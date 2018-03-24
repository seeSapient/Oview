package com.sapientnl.android.oview;

import java.net.URL;
import java.util.List;
import java.util.UUID;
/*The structure of a single poll. It is comprised of a title, an image, the sources where the user
* can get information on the subject and the user's view, if there is one.*/
public class Poll {

    private UUID mId; //random id for the poll
    private String mTitle; //title of the poll
    private boolean mAnsweredAgree; //the opinion of the user,if given any
    private int mImageID; //an image related to the poll
    private List<String> mSources; //list of urls related to the poll
    //getters and setters for the aforementioned variables
    public Poll() {
        mId = UUID.randomUUID();
    }
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean getAnsweredAgree() {
        return mAnsweredAgree;
    }
    public void setAnsweredAgree(boolean answeredAgree) {
        mAnsweredAgree = answeredAgree;
    }

    public int getImage() {
        return mImageID;
    }

    public void setImage(int image) {
        mImageID = image;
    }

    public List<String> getSources() {
        return mSources;
    }

    public void setSources(List<String> sources) {
        mSources = sources;
    }
}
