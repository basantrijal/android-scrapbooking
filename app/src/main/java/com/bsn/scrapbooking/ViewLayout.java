package com.bsn.scrapbooking;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by basant on 5/19/17.
 */

public class ViewLayout {

    private Context context;

    private LinearLayout baseLL;

    private String listSelected;

    private Image[] mImageBank = new Image[]{
            new Image("bhaktapur", "Bhaktapur", "Old city of Nepal", "NEPAL"),
            new Image("gokyo_ri", "Gokyo Ri", "Sagarmatha National Park", "NEPAL"),
            new Image("swayambhunath", "Swayambhunath", "Stupa in Kathmandu", "NEPAL"),
            new Image("phewa_lake", "Phewa Lake", "Phewa lake in Pokhara", "NEPAL"),

            new Image("lady_liberty", "Lady Liberty", "Statue of lady liberty", "AMERICA"),
            new Image("brooklyn_bridge", "Brooklyn Bridge", "Brooklyn Bridge in New York", "AMERICA"),
            new Image("golden_gate", "Golden Gate", "Golden Gate in San Fransisco", "AMERICA"),
            new Image("white_house", "White House", "White House in Washington DC", "AMERICA"),


            new Image("rocky_mountain", "Rocky Mountain", "Rocky Mountain in Canada", "CANADA"),
            new Image("banff_np", "Banff National Park", "Banff National Park in Canada", "CANADA"),
            new Image("canadian_rockies", "Canadian Rockies", "Canadian Rockies in Canada", "CANADA"),
            new Image("cn_tower", "CN Tower", "CN Tower in Canada", "CANADA"),
    };

    ViewLayout(Context context, LinearLayout baseLL, String listSelected) {
        this.baseLL = baseLL;
        this.context = context;
        this.listSelected = listSelected;
        LinearLayout[] imageContainer = new LinearLayout[mImageBank.length];

        int count = 0;
        LinearLayout ll = this.setLinearLayout();
        for (int i = 0; i < mImageBank.length; i++) {
            if (mImageBank[i].getCountry() == this.listSelected) {
                if (count == 2) {
                    ll = this.setLinearLayout();
                    count = 0;
                }
                ImageView resultView = this.setImageView(i);
                TextView resultText = this.setTextView(i);
                RelativeLayout resultRelative = this.setRelativeLayout();
                resultRelative.addView(resultView);
                resultRelative.addView(resultText);
                ll.addView(resultRelative);
                imageContainer[i] = ll;
                count++;
            }
        }
        for (int j = 0; j < imageContainer.length; j++) {
            if (imageContainer[j] != null && imageContainer[j].getParent() == null) {
                this.baseLL.addView(imageContainer[j]);
            }
        }
    }

    public LinearLayout setLinearLayout() {
        LinearLayout linearL = new LinearLayout(this.context);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 375
        );
        linearParams.setMargins(0, 60, 0, 0);
        linearL.setLayoutParams(linearParams);
        linearL.setPadding(0, 5, 0, 5);
        linearL.setBaselineAligned(false);
        linearL.setOrientation(LinearLayout.HORIZONTAL);
        return linearL;
    }

    public RelativeLayout setRelativeLayout() {
        RelativeLayout relativeL = new RelativeLayout(this.context);
        LinearLayout.LayoutParams relativeParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1
        );
        relativeParams.setMargins(2, 1, 2, 1);
        relativeL.setLayoutParams(relativeParams);
        relativeL.setFocusable(true);
        return relativeL;
    }

    public ImageView setImageView(Integer i) {
        ImageView imageView = new ImageView(this.context);
        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        imageParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        imageView.setLayoutParams(imageParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setAdjustViewBounds(true);
        String mDrawableName = mImageBank[i].getImage();
        int resID = this.context.getResources().getIdentifier(mDrawableName, "drawable", this.context.getPackageName());
        imageView.setImageResource(resID);
        imageView.setContentDescription(mImageBank[i].getContentDescription());
        return imageView;

    }

    public TextView setTextView(Integer i) {
        TextView textView = new TextView(this.context);
        RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        textViewParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        textViewParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        textViewParams.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
        textViewParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        textViewParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        textView.setLayoutParams(textViewParams);
        textView.setTextSize(15);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(Color.parseColor("#3F51B5"));
        textView.setPadding(10, 10, 10, 10);
        textView.setGravity(Gravity.CENTER);
        textView.setText(mImageBank[i].getText());
        textView.setContentDescription(mImageBank[i].getText());

        return textView;
    }
}
