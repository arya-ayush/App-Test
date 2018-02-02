package logicturtle.internshalaapp.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by user on 13-Jun-17.
 */


//use this view for complete app.........

public class BoldTextView extends AppCompatTextView {
    private static final String BOLD_FONT="fonts/SignikaNegative-Bold.ttf";

    public BoldTextView(Context context) {
        super(context);
    }

    public BoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(tf.createFromAsset(getContext().getAssets(),BOLD_FONT));
    }
}
