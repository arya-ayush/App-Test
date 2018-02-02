package logicturtle.internshalaapp.Fragment;


import com.mobsandgeeks.saripaar.Validator;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import logicturtle.internshalaapp.Database.DatabaseHolder.AppDatabase;
import logicturtle.internshalaapp.Database.DatabaseInitialiser.UserDatabaseInitialiser;
import logicturtle.internshalaapp.Database.Model.User;
import logicturtle.internshalaapp.Event.DataSetNotify;
import logicturtle.internshalaapp.R;
import logicturtle.internshalaapp.Utils.SharedPrefs;
import logicturtle.internshalaapp.Utils.Toasty;
import logicturtle.internshalaapp.Utils.ValidationListener;

/**
 * Created by user on 26-Jan-18.
 */

public class LoginDialogFragment extends DialogFragment {

    @Email
    @NotEmpty
    @BindView(R.id.email)
    MaterialEditText email;

    @Password
    @NotEmpty
    @BindView(R.id.password)
    MaterialEditText password;

    private Validator validator;


    public LoginDialogFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        validator = new Validator(this);
        validator.setValidationListener(new ValidationListener() {
            @Override
            public Context getContext() {
                return LoginDialogFragment.this.getContext();
            }

            @Override
            public void onValidationSucceeded() {
                AppDatabase appDatabase = AppDatabase.getAppDatabase(LoginDialogFragment.this.getContext());
                boolean user = UserDatabaseInitialiser.loginUser(appDatabase, email.getText().toString(), password.getText().toString());
                if (!user) {
                    Toasty.show("Invalid Credentials");
                } else {
                    User user1 = UserDatabaseInitialiser.getUser(appDatabase, email.getText().toString(), password.getText().toString());
                    User user2 = new User();
                    user2.setEmail(user1.getEmail());
                    user2.setName(user1.getName());
                    user2.setPassword(user1.getPassword());
                    SharedPrefs.setUser(user2,LoginDialogFragment.this.getContext());
                    Toasty.show("Logged In successfully");
                    EventBus.getDefault().post(new DataSetNotify());
                    LoginDialogFragment.this.dismiss();
                }
            }
        });
    }

    @OnClick(R.id.login)
    void doLogin() {
        validator.validate();
    }

    @OnClick(R.id.cancel)
    void cancel() {
        dismiss();
    }

    @OnClick(R.id.signup)
    void signup() {
        SignupDialogFragment signupDialogFragment = new SignupDialogFragment();
        signupDialogFragment.show(getFragmentManager(), "Signup");
        LoginDialogFragment.this.dismiss();
    }


}


