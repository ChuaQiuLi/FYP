package sg.edu.rp.c346.id20007649.fyp;

import android.widget.Filter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomFilter extends Filter {

    CustomAdapter adapter;
    ArrayList<Video> filterList;

    public CustomFilter (ArrayList<Video> filterList, CustomAdapter adapter  ){

        this.adapter = adapter;
        this.filterList = filterList;

    }


    @Override
    protected FilterResults performFiltering(CharSequence constraint) {

        FilterResults results = new FilterResults();

        //check constraints is valid
        if(constraint != null && constraint.length()>0){
            //change to upper case
            constraint = constraint.toString().toUpperCase();


            // store it into the filtered model
            ArrayList<Video> filteredVideo = new ArrayList<>();
            for (int i =0; i<filterList.size(); i++){
                if(filterList.get(i).getName().toUpperCase().contains(constraint)){
                    filteredVideo.add(filterList.get(i));
                }
            }

            results.count = filteredVideo.size();
            results.values = filteredVideo;

        }

        else{

            results.count = filterList.size();
            results.values = filterList;

        }


        return results;

    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.videoList = (ArrayList<Video>) results.values;
        adapter.notifyDataSetChanged();

    }
}
