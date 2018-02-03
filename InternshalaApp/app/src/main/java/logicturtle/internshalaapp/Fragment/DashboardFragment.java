package logicturtle.internshalaapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import logicturtle.internshalaapp.Adapter.DashBoardAdapter;
import logicturtle.internshalaapp.Database.DatabaseHolder.AppDatabase;
import logicturtle.internshalaapp.Database.Model.Event;
import logicturtle.internshalaapp.Database.Model.RegisterEvent;
import logicturtle.internshalaapp.Event.DataSetNotify;
import logicturtle.internshalaapp.R;
import logicturtle.internshalaapp.Utils.SharedPrefs;

/**
 * Created by user on 26-Jan-18.
 */

public class DashboardFragment extends InternshalaFragment {

    List<Event> list = new ArrayList<>();
    public DashBoardAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    AppDatabase db;
    List<RegisterEvent> registerEventList;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolBarTitle("My Dashboard");
        db = AppDatabase.getAppDatabase(this.getContext());
        getData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new DashBoardAdapter(list);
        recyclerView.setAdapter(adapter);
        EventBus.getDefault().register(this);
    }

    @OnClick(R.id.fab)
    void addEvent() {

        replaceFragment(new EventFragment(), R.id.container, this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(DataSetNotify dataSetNotify){
        registerEventList.clear();
        list.clear();
        getData();
        adapter.notifyDataSetChanged();
    }

    private void getData(){
        registerEventList = db.registerDao().getEvent(SharedPrefs.getLoginId());
        for (int i = 0; i < registerEventList.size(); i++)
            list.add(db.eventDao().getEvent(registerEventList.get(i).getEventId()));
    }

}

