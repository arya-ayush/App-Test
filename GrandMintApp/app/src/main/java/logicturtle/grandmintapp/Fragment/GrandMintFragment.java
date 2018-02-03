package logicturtle.grandmintapp.Fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import logicturtle.grandmintapp.R;

/**
 */

abstract public class GrandMintFragment extends Fragment {

    protected Toolbar toolbar;

    public GrandMintFragment() {

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

    protected final String getStringFromEditText(EditText editText) {
        return editText.getText().toString().trim();
    }


    protected final void setToolBarTitleOnFragment(String title) {
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

    protected final void replaceFragment(Fragment launchingFragment, int containerId, Fragment currentFragmentObject) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(currentFragmentObject);
        transaction.add(containerId, launchingFragment);
        transaction.commit();
    }

    protected final int getFragmentContainer() {
        int containerID = ((ViewGroup) getView().getParent()).getId();
        return containerID;
    }


}
