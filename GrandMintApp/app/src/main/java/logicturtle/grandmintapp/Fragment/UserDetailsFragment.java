package logicturtle.grandmintapp.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import logicturtle.grandmintapp.R;
import logicturtle.grandmintapp.Utils.SharedPrefs;
import logicturtle.grandmintapp.Utils.Toasty;
import logicturtle.grandmintapp.Utils.ValidationListener;

/**
 * Created by user on 03-Feb-18.
 */

public class UserDetailsFragment extends GrandMintFragment {

    private FirebaseAuth mAuth;
    private Validator validator;
    private ProgressDialog progressDialog;

    @NotEmpty
    @BindView(R.id.name)
    MaterialEditText name;

    @NotEmpty
    @Email
    @BindView(R.id.email)
    MaterialEditText email;

    @NotEmpty
    @Password
    @BindView(R.id.password)
    MaterialEditText password;

    public UserDetailsFragment() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_user_detail;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setToolBarTitleOnFragment("My Details");
        progressDialog=new ProgressDialog(this.getContext());
        mAuth = FirebaseAuth.getInstance();
        validator = new Validator(this);
        validator.setValidationListener(new ValidationListener() {
            @Override
            public Context getContext() {
                return UserDetailsFragment.this.getContext();
            }

            @Override
            public void onValidationSucceeded() {
                signupUser(getStringFromEditText(email), getStringFromEditText(password));
            }
        });
    }


    public static Fragment getInstance() {
        Fragment fragment = new UserDetailsFragment();
        return fragment;
    }


    private void emailVerification(final FirebaseUser user) {
        user.sendEmailVerification()
                .addOnCompleteListener(UserDetailsFragment.this.getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toasty.show("Registered Successfully");
                            SharedPrefs.setUserNsame(getStringFromEditText(name));
                            progressDialog.dismiss();
                            replaceFragment(EmailVerifiedFragment.getInstance(),R.id.container,UserDetailsFragment.this);
                        }
                        else
                            progressDialog.dismiss();
                    }
                });
    }

    private void signupUser(String email, String password) {
        Log.d("ayush", email + " " + password+" "+mAuth.toString());
        Task<AuthResult> task = mAuth.createUserWithEmailAndPassword(email, password);
        Log.d("ayush", email + " " + password);
        progressDialog.show();
        task.addOnCompleteListener(UserDetailsFragment.this.getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    emailVerification(user);
                }
                else
                    progressDialog.dismiss();
            }
        });
    }

    @OnClick(R.id.register)
    void register() {
        validator.validate();

    }


}
