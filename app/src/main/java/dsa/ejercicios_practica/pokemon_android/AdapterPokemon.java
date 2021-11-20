package dsa.ejercicios_practica.pokemon_android;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import dsa.models.Attack;
import dsa.models.Pokemon;

public class AdapterPokemon extends RecyclerView.Adapter<AdapterPokemon.ViewHolder> {

    private List<Pokemon> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNamePokemon;
        public TextView txtTypePokemon;
        public TextView txtAttacksPokemon;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtNamePokemon = (TextView) v.findViewById(R.id.namePokemon_line);
            txtTypePokemon = (TextView) v.findViewById(R.id.typePokemon_line);
            txtAttacksPokemon = (TextView) v.findViewById(R.id.attacksPokemon_line);
        }
    }

    public void setData(List<Pokemon> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, Pokemon item) {
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
    public AdapterPokemon(List<Pokemon> myDataset) {
        values = myDataset;
    }

    public AdapterPokemon() {
        values = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterPokemon.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout_pokemon, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Pokemon pokemon = values.get(position);
        final String name = pokemon.getName();
        holder.txtNamePokemon.setText(name);
        holder.txtNamePokemon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendMessage(holder.getAdapterPosition(),v);
            }
        });

        holder.txtTypePokemon.setText(pokemon.getType());
        List<Attack> attackList = pokemon.getAtackList();
        String attackString = attackList.get(0).getName();
        for(Attack a:attackList){
            attackString = (attackString + "," + a.getName());
        }
        holder.txtAttacksPokemon.setText(attackString);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }


}
