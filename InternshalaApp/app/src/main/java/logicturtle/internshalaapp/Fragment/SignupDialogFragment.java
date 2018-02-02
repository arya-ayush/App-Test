package logicturtle.internshalaapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import logicturtle.internshalaapp.Database.DatabaseHolder.AppDatabase;
import logicturtle.internshalaapp.Database.DatabaseInitialiser.UserDatabaseInitialiser;
import logicturtle.internshalaapp.Database.Model.User;
import logicturtle.internshalaapp.R;
import logicturtle.internshalaapp.Utils.SharedPrefs;
import logicturtle.internshalaapp.Utils.Toasty;
import logicturtle.internshalaapp.Utils.ValidationListener;

/**
 * Created by user on 26-Jan-18.
 */

public class SignupDialogFragment extends DialogFragment {


    @NotEmpty
    @BindView(R.id.name)
    MaterialEditText name;

    @Email
    @NotEmpty
    @BindView(R.id.email)
    MaterialEditText email;

    @Password
    @NotEmpty
    @BindView(R.id.password)
    MaterialEditText password;
    private Validator validator;


    public SignupDialogFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        validator = new Validator(this);
        validator.setValidationListener(new ValidationListener() {
            @Override
            public Context getContext() {
                return SignupDialogFragment.this.getContext();
            }

            @Override
            public void onValidationSucceeded() {
                AppDatabase appDatabase = AppDatabase.getAppDatabase(SignupDialogFragment.this.getContext());
                List<User> users = UserDatabaseInitialiser.getAllUsers(appDatabase);
                if (checkUniqueness(users, email.getText().toString())) {
                    User user = new User();
                    user.setEmail(email.getText().toString());
                    user.setName(name.getText().toString());
                    user.setPassword(password.getText().toString());
                    UserDatabaseInitialiser.populateSync(appDatabase, user);
                    SharedPrefs.setUser(user, SignupDialogFragment.this.getContext());
                    Toasty.show("Signup successfully");
                    SignupDialogFragment.this.dismiss();
                } else {
                    Toasty.show("email is already registered");
                }
            }
        });

        User user = SharedPrefs.getUser();
        if (user != null) {
            Toasty.show("Logged In successfully");
        }

    }


    private boolean checkUniqueness(List<User> users, String email) {
        boolean unique = true;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equalsIgnoreCase(email)) {
                unique = false;
                break;
            }
        }
        return unique;
    }

    @OnClick(R.id.signup)
    void doLogin() {
        validator.validate();
    }

    @OnClick(R.id.cancel)
    void cancel() {
        dismiss();
    }




}
