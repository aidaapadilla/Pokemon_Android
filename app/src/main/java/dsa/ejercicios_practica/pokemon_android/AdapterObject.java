package dsa.ejercicios_practica.pokemon_android;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dsa.models.Objects;
import dsa.services.ObjectService;

public class AdapterObject extends RecyclerView.Adapter<AdapterObject.ViewHolder>{
    private List<Objects> objectList;

    private RecyclerView recyclerView;
    private AdapterObject mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private OnItemClickListener mListener;
    //Through this we get the click and the position to our main activity
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    //for When we call the OnClick Method from main
    public void SetOnItemClickListener(OnItemClickListener listener){
        mListener = listener ;
    }

    //creamos una plantilla de objeto
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNameObject;
        public TextView txtPriceObject;
        public TextView txtDescriptionObject;
        public ImageView imgObject;
        public View layout;

        public ViewHolder(View v,OnItemClickListener listener) {
            super(v);
            layout = v;
            txtNameObject = (TextView) v.findViewById(R.id.nameObject);
            txtDescriptionObject = (TextView) v.findViewById(R.id.descriptionObject);
            txtPriceObject = (TextView) v.findViewById(R.id.priceObject);
            imgObject = (ImageView) v.findViewById(R.id.imgObject);
            String txt =  txtNameObject.toString();

            itemView.findViewById(R.id.buyButtonObject).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    listener.onItemClick(position);
                }
            });
        }
    }

    //rellena la lista de objetos con el AllObjectsActivity
    public void setData(List<Objects> myDataset) {
        objectList = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, Objects item) {
        objectList.add(position, item);
        notifyItemInserted(position);
    }

    /*
    public void sendMessage(int position, View v) {
        Track track = values.get(position);
        Intent intent = new Intent(v.getContext(), TracksActivity.class);
        intent.putExtra("Track title", track.getTitle());
        intent.putExtra("Track singer",track.getSinger());
        intent.putExtra("Track id", track.getId());
        v.getContext().startActivity(intent);
    }

     */

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterObject(List<Objects> myDataset) {
        objectList = myDataset;
    }

    public AdapterObject() {
        objectList = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterObject.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_layout_object, parent, false);
        // set the view's size, margins, paddings and layout parameters
        AdapterObject.ViewHolder vh = new AdapterObject.ViewHolder(v,mListener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)

    public void onBindViewHolder(AdapterObject.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Objects object = objectList.get(position);
        holder.txtNameObject.setText(object.getName());
        holder.txtPriceObject.setText(Double.toString(object.getPrice()));
        holder.txtDescriptionObject.setText(object.getDescription());
        //Bitmap bitmap = StringToBitmap(object.getImage());
        //holder.imgObject.setImageBitmap(bitmap);
        if(object.getName().contains("Potion")){
            holder.imgObject.setImageResource(R.drawable.potion1);
        }else if(object.getName().contains("Superpotion")){
            holder.imgObject.setImageResource(R.drawable.superpotion);
        }
        else if(object.getName().contains("Pokeball")){
            holder.imgObject.setImageResource(R.drawable.pokeball);
        }
        else if(object.getName().contains("Superball")){
            holder.imgObject.setImageResource(R.drawable.superball);
        }
        holder.txtNameObject.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendMessage(holder.getAdapterPosition(),v);
            }
        });

    }
    private Bitmap StringToBitmap (String encodedimage){
        byte[] decoded = Base64.decode(encodedimage,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decoded,0, decoded.length);
        return bitmap;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return objectList.size();
    }
}
