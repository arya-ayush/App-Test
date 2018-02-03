package logicturtle.grandmintapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import logicturtle.grandmintapp.R;
import logicturtle.grandmintapp.Utils.SharedPrefs;

/**
 * Created by user on 03-Feb-18.
 */

public class EmailVerifiedFragment extends GrandMintFragment {

    @BindView(R.id.message)
    TextView message;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            Task<Void> task = user.reload();

            setToolBarTitleOnFragment("Dashboard");
            Log.d("ayush", "" + user.isEmailVerified());
            if (!user.isEmailVerified())
                message.setText(R.string.email_not_verified);
            else
                message.setText("Hello" + " " + SharedPrefs.getUserName());

        }
    }

    public static Fragment getInstance() {
        Fragment fragment = new EmailVerifiedFragment();
        return fragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_email_verified;
    }
}
