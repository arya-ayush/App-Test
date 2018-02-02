package logicturtle.internshalaapp.Activity;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import logicturtle.internshalaapp.Fragment.DashboardFragment;
import logicturtle.internshalaapp.Fragment.EventFragment;
import logicturtle.internshalaapp.R;
import logicturtle.internshalaapp.Utils.SharedPrefs;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (SharedPrefs.getLoginId() == SharedPrefs.LOGIN_TOKEN)
            transaction.add(R.id.container, new EventFragment());
        else
            transaction.add(R.id.container, new DashboardFragment());
        transaction.commit();
    }


}



