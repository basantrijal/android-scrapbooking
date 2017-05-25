package com.bsn.scrapbooking;

/**
 * Created by basant on 5/19/17.
 */

public class Image {

    private String mImage;

    private String mText;

    private String mContentDescription;

    private String mCountry;

    public Image(String mImage, String mText, String mContentDescription, String mCountry) {
        this.mImage = mImage;
        this.mText = mText;
        this.mContentDescription = mContentDescription;
        this.mCountry = mCountry;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String mImage) {
        this.mImage = mImage;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public String getContentDescription() {
        return mContentDescription;
    }

    public void setContentDescription(String mContentDescription) {
        this.mContentDescription = mContentDescription;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String mCountry) {
        this.mCountry = mCountry;
    }
}
