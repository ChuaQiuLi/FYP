package sg.edu.rp.c346.id20007649.fyp;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> implements Filterable {

    ArrayList<Video> videoList, filteredList;
    Context context;
    CustomFilter filter;
    private FavDB favDB;


    // creating a constructor
   CustomAdapter(Context context, ArrayList<Video> videoList) {

        this.context = context;
        this.videoList = videoList;
        this.filteredList = videoList;

    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // passing our layout file for displaying our card item
        return new CustomAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.row, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

        // getting data from array list in our modal.
        Video modal = videoList.get(position);

        holder.videoName.setText(modal.getName());


        holder.videoName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are opening a new activity and passing data to it.
                Intent intent = new Intent(context, Information.class);
                intent.putExtra("data", modal);

                // on below line we are starting a new activity,
                context.startActivity(intent);
                notifyDataSetChanged();

            }
        });

    }


    @Override
    public int getItemCount() {

        return videoList.size();

    }

    @Override
    public Filter getFilter() {

       if (filter == null){
           filter = new CustomFilter(filteredList, this);

       }

        return filter;
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {


        // on below line creating a variable
        // for our image view and text view.
        private TextView videoName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our image view and text view.
            videoName = itemView.findViewById(R.id.keyWordSign);


        }
    }
}









