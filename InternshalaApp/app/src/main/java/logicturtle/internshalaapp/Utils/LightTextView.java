package logicturtle.internshalaapp.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by user on 13-Jun-17.
 */

public class LightTextView extends AppCompatTextView {

    //use this view for complete app.........


    private static final String LIGHT_FONT = "fonts/SignikaNegative-Light.ttf";

    public LightTextView(Context context) {
        super(context);
    }

    public LightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LightTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(tf.createFromAsset(getContext().getAssets(), LIGHT_FONT));
    }

}