package sg.edu.rp.c346.id20007649.fyp;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> implements Filterable {

    ArrayList<Video> favList;
    ArrayList<Video> filteredFavList;
    Context context;
    FavFilter filterFav;





    // creating a constructor
    FavAdapter(Context context, ArrayList<Video> favList) {

        this.context = context;
        this.favList = favList;
        this.filteredFavList = favList;


    }


    @NonNull
    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // passing our layout file for displaying our card item

        return new FavAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.row, parent, false));

    }




    @Override
    public void onBindViewHolder(@NonNull FavAdapter.ViewHolder holder, int position) {


        // getting data from array list in our modal.
        Video modal = favList.get(position);

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


        holder.videoName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                AlertDialog.Builder myBuilder = new AlertDialog.Builder(v.getRootView().getContext());

                myBuilder.setTitle("Delete favourite keyword sign");
                myBuilder.setMessage("Do you want to delete this keyword sign? ");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FavDB favDB = new FavDB(context);

                        long deleteFav = favDB.deleteKeywordSign(modal.getId());
                        favList.remove(modal);

                        if (deleteFav != -1) {
                            Toast.makeText(context, "Favourite Deleted", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            notifyDataSetChanged();

                        }


                    }
                });


                myBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });



                AlertDialog myDialog = myBuilder.create();

                myDialog.show();

                return false;


            }

        });

    }





    @Override
    public int getItemCount() {

        return favList.size();


    }

    @Override
    public Filter getFilter() {


        if (filterFav == null){
            filterFav = new FavFilter(filteredFavList,this );

        }

        return filterFav;
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
