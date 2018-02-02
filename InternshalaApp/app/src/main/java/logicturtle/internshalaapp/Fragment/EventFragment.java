package logicturtle.internshalaapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import logicturtle.internshalaapp.Adapter.EventAdapter;
import logicturtle.internshalaapp.Database.DatabaseHolder.AppDatabase;
import logicturtle.internshalaapp.Database.DatabaseInitialiser.EventDatabaseInitialiser;
import logicturtle.internshalaapp.Database.Model.Event;
import logicturtle.internshalaapp.R;

/**
 * Created by user on 26-Jan-18.
 */

public class EventFragment extends InternshalaFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    List<Event> eventList = new ArrayList<>();


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_event;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppDatabase db = AppDatabase.getAppDatabase(this.getContext());
        setToolBarTitle("Pick Your Workshop");
        if (db.eventDao().countEvents()==0) {
            addEvent();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new EventAdapter(AppDatabase.getAppDatabase(this.getContext()).eventDao().getAll(),this));
    }

    private List<Event> createDummyEvents() {
        Event event = new Event();
        event.setName("Python Workshop");
        event.setDate("28-Jan-2018");
        event.setVenue("Ajay Kumar Garg Engineering College");
        event.setDescription("Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum");
        eventList.add(event);
        Event event1 = new Event();
        event1.setName("Android Workshop");
        event1.setDate("29-Jan-2018");
        event1.setVenue("Raj Kumar Goel Engineering College");
        event1.setDescription("Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum");
        eventList.add(event1);
        Event event2 = new Event();
        event2.setName("Angular 5 Workshop");
        event2.setDate("30-Jan-2018");
        event2.setVenue("ABES Engineering College");
        event2.setDescription("Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum");
        eventList.add(event2);
        Event event3 = new Event();
        event3.setName("Blockchain Workshop");
        event3.setDate("5-feb-2018");
        event3.setVenue("ABES Engineering College");
        event3.setDescription("Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum");
        eventList.add(event3);
        Event event4 = new Event();
        event4.setName("Cloud Computing Workshop");
        event4.setDate("5-feb-2018");
        event4.setVenue("IMS Engineering College");
        event4.setDescription("Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum");
        eventList.add(event4);
        eventList.add(event3);
        eventList.add(event2);
        eventList.add(event4);
        eventList.add(event);
        eventList.add(event1);
        return eventList;
    }


    private void addEvent() {
        List<Event> eventList = createDummyEvents();
        AppDatabase db = AppDatabase.getAppDatabase(this.getContext());
        for (int i = 0; i < eventList.size(); i++)
            EventDatabaseInitialiser.populateSync(db, eventList.get(i));
    }

}
