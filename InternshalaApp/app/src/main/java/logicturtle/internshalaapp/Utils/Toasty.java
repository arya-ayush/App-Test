package logicturtle.internshalaapp.Utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import logicturtle.internshalaapp.InternshalaApplication;

/**
 * Created by use on 26-15-2017.
 */

public class Toasty extends Toast {

    public Toasty(Context context) {
        super(context);
    }

    public static void show(String message) {
        Context context = InternshalaApplication.getAppContext();
        if (context == null)
            return;
        makeText(context, message, LENGTH_SHORT).show();
    }

    public static void show(int string) {
        Context context = InternshalaApplication.getAppContext();
        if (context == null)
            return;
        makeText(context, context.getString(string), LENGTH_SHORT).show();
    }

    public static void show(Fragment fragment, int string) {
        if (!fragment.isAdded())
            return;
        show(string);
    }

    public static void show(Fragment fragment, String string) {
        if (!fragment.isAdded())
            return;
        show(string);
    }

}
