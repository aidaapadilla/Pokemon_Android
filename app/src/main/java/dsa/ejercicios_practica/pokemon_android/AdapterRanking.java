package dsa.ejercicios_practica.pokemon_android;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dsa.models.Character;

public class AdapterRanking extends RecyclerView.Adapter<AdapterRanking.ViewHolder> {
    private List<Character> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtName;
        public TextView txtPoints;
        public TextView txtMainPokemon;
        public ImageView imgCharacter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtName = (TextView) v.findViewById(R.id.nameCharacterRanking);
            txtPoints = (TextView) v.findViewById(R.id.pointsCharacterRanking);
            txtMainPokemon = (TextView) v.findViewById(R.id.mainPokemonRanking);
            imgCharacter = (ImageView) v.findViewById(R.id.imgCharacter);
        }
    }

    public void setData(List<Character> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, Character item) {
        values.add(position, item);
        notifyItemInserted(position); // Used for informing that the list needs to be changed
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterRanking(List<Character> myDataset) {
        values = myDataset;
    }

    public AdapterRanking() {
        values = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterRanking.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout_ranking, parent, false);
        // set the view's size, margins, paddings and layout parameters
        AdapterRanking.ViewHolder vh = new AdapterRanking.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Character character = values.get(position);
        String name = character.getName();
        holder.txtName.setText(name);
        holder.txtPoints.setText(Double.toString(character.getPoints()));
        holder.txtMainPokemon.setText("Main Pokemon: " + character.getPokemon1name());

        if(character.getAvatar().equals("May") || character.getAvatar().equals("may")){
            holder.imgCharacter.setImageResource(R.drawable.may);
        }
        else if(character.getAvatar().equals("James") || character.getAvatar().equals("james")){
            holder.imgCharacter.setImageResource(R.drawable.james);
        }
        else if(character.getAvatar().equals("Red") || character.getAvatar().equals("red")){
            holder.imgCharacter.setImageResource(R.drawable.red);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}