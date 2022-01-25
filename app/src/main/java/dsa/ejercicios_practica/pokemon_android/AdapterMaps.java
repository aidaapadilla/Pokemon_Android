package dsa.ejercicios_practica.pokemon_android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dsa.models.Map;

public class AdapterMaps extends RecyclerView.Adapter<AdapterMaps.ViewHolder> {

    private List<Map> mapsList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNameMap;
        public ImageView imgMap;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtNameMap = (TextView) v.findViewById(R.id.nameMap);
            imgMap = (ImageView) v.findViewById(R.id.imgMap);
        }
    }

    public void setData(List<Map> myDataset) {
        mapsList = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, Map item) {
        mapsList.add(position, item);
        notifyItemInserted(position);
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterMaps(List<Map> myDataset) {
        mapsList = myDataset;
    }

    public AdapterMaps() {
        mapsList = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterMaps.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout_map, parent, false);
        // set the view's size, margins, paddings and layout parameters
        AdapterMaps.ViewHolder vh =new AdapterMaps.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AdapterMaps.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Map map = mapsList.get(position);
        holder.txtNameMap.setText(map.getName());

        if(map.getLevel()==1.0){
            holder.imgMap.setImageResource(R.drawable.hometown);
        }
        else if(map.getLevel()==2.0){
            holder.imgMap.setImageResource(R.drawable.route);
        }
        else if(map.getLevel()==3.0){
            holder.imgMap.setImageResource(R.drawable.gym);
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
        return mapsList.size();
    }


}
