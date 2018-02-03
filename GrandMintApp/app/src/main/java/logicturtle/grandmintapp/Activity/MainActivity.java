package logicturtle.grandmintapp.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import logicturtle.grandmintapp.Fragment.EmailVerifiedFragment;
import logicturtle.grandmintapp.Fragment.UserDetailsFragment;
import logicturtle.grandmintapp.R;
import logicturtle.grandmintapp.Utils.SharedPrefs;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager=getSupportFragmentManager();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user==null) {
            showFragment(manager,UserDetailsFragment.getInstance());
        }
        else if(user!=null) {
            showFragment(manager, EmailVerifiedFragment.getInstance());
        }
    }


    private void showFragment(FragmentManager manager, Fragment fragment){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();
    }





}
