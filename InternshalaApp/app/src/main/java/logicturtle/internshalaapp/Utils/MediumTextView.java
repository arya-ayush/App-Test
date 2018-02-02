package logicturtle.internshalaapp.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by user on 13-Jun-17.
 */


//use this view for complete app.........

public class MediumTextView extends AppCompatTextView {
    private static final String MEDIUM_FONT="fonts/SignikaNegative-SemiBold.ttf";

    public MediumTextView(Context context) {
        super(context);
    }

    public MediumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MediumTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(tf.createFromAsset(getContext().getAssets(),MEDIUM_FONT));
    }


}
