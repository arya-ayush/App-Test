package logicturtle.internshalaapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import logicturtle.internshalaapp.Database.DatabaseHolder.AppDatabase;
import logicturtle.internshalaapp.Database.DatabaseInitialiser.RegisterDatabaseInitialiser;
import logicturtle.internshalaapp.Database.Model.Event;
import logicturtle.internshalaapp.Database.Model.RegisterEvent;
import logicturtle.internshalaapp.R;
import logicturtle.internshalaapp.Utils.SharedPrefs;

/**
 * Created by user on 27-Jan-18.
 */

public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.DashboardViewHolder> {

    List<Event> eventList;

    public DashBoardAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public DashboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dashboard_viewholder, parent, false);
        return new DashboardViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final DashboardViewHolder holder, int position) {
        final Event event = eventList.get(position);
        holder.date.setText(event.getDate());
        holder.eventName.setText(event.getName());
        holder.venue.setText(event.getVenue());
        final AppDatabase db=AppDatabase.getAppDatabase(holder.itemView.getContext());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterEvent registerEvent=new RegisterEvent();
                registerEvent.setUserId(SharedPrefs.getLoginId());
                registerEvent.setEventId(event.getEid());
                RegisterDatabaseInitialiser.populateSync(db,registerEvent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.venue)
        TextView venue;

        @BindView(R.id.event_name)
        TextView eventName;

        public DashboardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
