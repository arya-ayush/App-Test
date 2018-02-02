package logicturtle.internshalaapp.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import logicturtle.internshalaapp.Database.DatabaseHolder.AppDatabase;
import logicturtle.internshalaapp.Database.DatabaseInitialiser.RegisterDatabaseInitialiser;
import logicturtle.internshalaapp.Database.Model.Event;
import logicturtle.internshalaapp.Database.Model.RegisterEvent;
import logicturtle.internshalaapp.Fragment.LoginDialogFragment;
import logicturtle.internshalaapp.R;
import logicturtle.internshalaapp.Utils.SharedPrefs;
import logicturtle.internshalaapp.Utils.Toasty;

/**
 * Created by user on 27-Jan-18.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    List<Event> eventList;
    private Fragment fragment;

    public EventAdapter(List<Event> eventList, Fragment fragment) {
        this.eventList = eventList;
        this.fragment = fragment;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.event_viewholder_view, parent, false);
        return new EventViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final EventViewHolder holder, int position) {
        final Event event = eventList.get(position);
        holder.date.setText(event.getDate());
        holder.eventName.setText(event.getName());
        holder.venue.setText(event.getVenue());
        final AppDatabase db = AppDatabase.getAppDatabase(holder.itemView.getContext());
        holder.apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SharedPrefs.getLoginId()==SharedPrefs.LOGIN_TOKEN)
                    new LoginDialogFragment().show(fragment.getFragmentManager(),"Login");
                else {
                    RegisterEvent registerEvent = new RegisterEvent();
                    registerEvent.setUserId(SharedPrefs.getLoginId());
                    registerEvent.setEventId(event.getEid());
                    RegisterDatabaseInitialiser.populateSync(db, registerEvent);
                    Toasty.show("Applied");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.venue)
        TextView venue;

        @BindView(R.id.event_name)
        TextView eventName;

        @BindView(R.id.apply)
        Button apply;

        public EventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
