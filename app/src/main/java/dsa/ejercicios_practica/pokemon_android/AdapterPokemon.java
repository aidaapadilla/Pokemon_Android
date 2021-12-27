package dsa.ejercicios_practica.pokemon_android;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dsa.models.Attack;
import dsa.models.Pokemons;

public class AdapterPokemon extends RecyclerView.Adapter<AdapterPokemon.ViewHolder> {

    private List<Pokemons> pokemonList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNamePokemon;
        public TextView txtTypePokemon;
        public TextView txtAttacksPokemon;
        public ImageView imgPokemon;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtNamePokemon = (TextView) v.findViewById(R.id.namePokemon);
            txtTypePokemon = (TextView) v.findViewById(R.id.typePokemon);
            txtAttacksPokemon = (TextView) v.findViewById(R.id.attackListPokemon);
            imgPokemon = (ImageView) v.findViewById(R.id.imgPokemon);
        }
    }

    public void setData(List<Pokemons> myDataset) {
        pokemonList = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, Pokemons item) {
        pokemonList.add(position, item);
        notifyItemInserted(position);
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterPokemon(List<Pokemons> myDataset) {
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
        Pokemons pokemon = pokemonList.get(position);
        String namelevel = pokemon.getName() + " " + Double.toString(pokemon.getLevel());
        holder.txtNamePokemon.setText(namelevel);
        holder.txtTypePokemon.setText(pokemon.getType());
        String text = null;
        if(pokemon.getAttack1name()!=null){
            text = pokemon.getAttack1name();
        }
        if(pokemon.getAttack2name()!=null){
            if(text!=null) {
                text = text + "," + pokemon.getAttack2name();
            }
            else{
                text = pokemon.getAttack2name();
            }
        }
        holder.txtAttacksPokemon.setText(text);

        if(pokemon.getName().equals("Charmander")){
            holder.imgPokemon.setImageResource(R.drawable.charmander);
        }
        else if(pokemon.getName().equals("Squirtle")){
            holder.imgPokemon.setImageResource(R.drawable.squirtle);
        }
        else if(pokemon.getName().equals("Bulbasaur")){
            holder.imgPokemon.setImageResource(R.drawable.bulbasaur);
        }
        else if(pokemon.getName().equals("Glaceon")){
            holder.imgPokemon.setImageResource(R.drawable.glaceon);
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
