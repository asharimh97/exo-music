package id.asharimh.exomusicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ASHARI on 05/11/2017.
 */

public class ItemsAdapter extends ArrayAdapter<ItemData>{
    private final Context context ;
    private final ArrayList<ItemData> values;

    public ItemsAdapter(Context contex, ArrayList<ItemData> values){
        super (contex, R.layout.layout_grid_view, values);
        this.context = contex;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.layout_grid_view, parent, false);

        TextView judul = (TextView) v.findViewById(R.id.judulLaguGrid);
        ImageView img = (ImageView) v.findViewById(R.id.imageViewAlbumGrid);

        judul.setText(values.get(position).judulLagu);
        img.setImageResource(R.drawable.cover);
        //img.setImageResource(values.get(position).albumCoverImg);
        return v ;
    }
}
