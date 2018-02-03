package logicturtle.internshalaapp.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import logicturtle.internshalaapp.Event.DataSetNotify;
import logicturtle.internshalaapp.R;
import logicturtle.internshalaapp.Utils.SharedPrefs;
import logicturtle.internshalaapp.Utils.Toasty;

/**
 * Created by user on 26/01/17.
 */

abstract public class InternshalaFragment extends Fragment {

    protected Toolbar toolbar;
    private static final String DIALOG = "dialog";
    private FragmentManager manager;

    public InternshalaFragment() {

    }

    @LayoutRes
    abstract protected int getLayoutResId();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = (ImageView) getAccountIcon();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedPrefs.getLoginId() == SharedPrefs.LOGIN_TOKEN)
                    setShowDialog();
                else {
                    SharedPrefs.clearLoggedInUser();
                    Toasty.show("Logged out Successfully");
                    EventBus.getDefault().post(new DataSetNotify());
                }
            }
        });

    }

    protected final String getStringFromEditText(EditText editText) {
        return editText.getText().toString().trim();
    }


    protected final void setToolBarTitle(String title) {
        Toolbar toolbar = (Toolbar) getView().getRootView().findViewById(R.id.toolbar);
        View view;
        RelativeLayout relativeLayout = (RelativeLayout) toolbar.getChildAt(0);
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            view = relativeLayout.getChildAt(i);
            if (view instanceof TextView && view.getId() == R.id.toolbar_title) {
                ((TextView) view).setText(title);
                break;
            }
        }
    }


    protected final View getAccountIcon() {
        Toolbar toolbar = (Toolbar) getView().getRootView().findViewById(R.id.toolbar);
        View view;
        ImageView imageView = null;
        RelativeLayout relativeLayout = (RelativeLayout) toolbar.getChildAt(0);
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            view = relativeLayout.getChildAt(i);
            if (view instanceof ImageView && view.getId() == R.id.user_icon) {
                imageView = (ImageView) view;
                break;
            }
        }
        return imageView;
    }

    protected void setShowDialog() {
        showDialog();
    }

    protected final void replaceFragment(Fragment launchingFragment, int containerId, Fragment currentFragmentObject) {
        manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(currentFragmentObject);
        transaction.add(containerId, launchingFragment);
        transaction.commit();
    }

    protected final int getFragmentContainer() {
        int containerID = ((ViewGroup) getView().getParent()).getId();
        return containerID;
    }


    protected void showDialog() {
        LoginDialogFragment dialog = new LoginDialogFragment();
        dialog.show(getChildFragmentManager(), DIALOG);
    }



}
