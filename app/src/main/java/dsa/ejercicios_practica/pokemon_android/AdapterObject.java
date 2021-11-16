package dsa.ejercicios_practica.pokemon_android;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import dsa.models.Attack;
import dsa.models.Object;
import dsa.models.Pokemon;

public class AdapterObject extends RecyclerView.Adapter<AdapterObject.ViewHolder>{
    private List<Object> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNameObject;
        public TextView txtPriceObject;
        public TextView txtTypeObject;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtNameObject = (TextView) v.findViewById(R.id.namePokemon_line);
            txtPriceObject = (TextView) v.findViewById(R.id.typePokemon_line);
            txtTypeObject = (TextView) v.findViewById(R.id.attacksPokemon_line);
        }
    }

    public void setData(List<Object> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, Object item) {
        values.add(position, item);
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
    public AdapterObject(List<Object> myDataset) {
        values = myDataset;
    }

    public AdapterObject() {
        values = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterObject.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout_object, parent, false);
        // set the view's size, margins, paddings and layout parameters
        AdapterObject.ViewHolder vh = new AdapterObject.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)

    public void onBindViewHolder(AdapterObject.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Object object = values.get(position);
        final String name = object.getName();
        holder.txtNameObject.setText(name);
        holder.txtNameObject.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendMessage(holder.getAdapterPosition(),v);
            }
        });

        holder.txtPriceObject.setText(Double.toString(object.getPrice()));
        holder.txtTypeObject.setText(object.getType());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }
}
