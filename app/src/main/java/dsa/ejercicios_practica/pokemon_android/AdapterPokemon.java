package dsa.ejercicios_practica.pokemon_android;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dsa.models.Basepokemon;

public class AdapterPokemon extends RecyclerView.Adapter<AdapterPokemon.ViewHolder> {

    private List<Basepokemon> pokemonList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNamePokemon;
        public TextView txtMaxHealthPokemon;
        public TextView txtTypePokemon;
        public ImageView imgPokemon;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtNamePokemon = (TextView) v.findViewById(R.id.namePokemon);
            txtMaxHealthPokemon = (TextView) v.findViewById(R.id.healthPokemon);
            txtTypePokemon = (TextView) v.findViewById(R.id.typePokemon);
            imgPokemon = (ImageView) v.findViewById(R.id.imgPokemon);
        }
    }

    public void setData(List<Basepokemon> myDataset) {
        pokemonList = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, Basepokemon item) {
        pokemonList.add(position, item);
        notifyItemInserted(position);
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterPokemon(List<Basepokemon> myDataset) {
        pokemonList = myDataset;
    }

    public AdapterPokemon() {
        pokemonList = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterPokemon.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout_pokemon, parent, false);
        // set the view's size, margins, paddings and layout parameters
        AdapterPokemon.ViewHolder vh =new AdapterPokemon.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Basepokemon pokemon = pokemonList.get(position);
        holder.txtNamePokemon.setText(pokemon.getName());
        holder.txtMaxHealthPokemon.setText(Double.toString(pokemon.getMaxhealth()));

        holder.txtTypePokemon.setText(pokemon.getType());

        if(pokemon.getName().equals("Charmander")){
            holder.imgPokemon.setImageResource(R.drawable.charmander);
        }
        else if(pokemon.getName().equals("Squirtle")){
            holder.imgPokemon.setImageResource(R.drawable.squirtle);
        }
        else if(pokemon.getName().equals("Bulbasaur")){
            holder.imgPokemon.setImageResource(R.drawable.bulbasaur);
        }
        else if(pokemon.getName().equals("Cubchoo")){
            holder.imgPokemon.setImageResource(R.drawable.cubchoo);
        }
        else if(pokemon.getName().equals("Geodude")){
            holder.imgPokemon.setImageResource(R.drawable.geodude);
        }

    }

    private Bitmap StringToBitmap (String encodedimage){
        byte[] decoded = Base64.decode(encodedimage,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decoded,0, decoded.length);
        return bitmap;
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return pokemonList.size();
    }


}
