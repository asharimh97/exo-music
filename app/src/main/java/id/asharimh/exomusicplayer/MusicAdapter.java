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
 * Created by ASHARI on 05/17/2017.
 */

public class MusicAdapter extends ArrayAdapter<MusicData> {
    private final Context context ;
    private final ArrayList<MusicData> values ;

    public MusicAdapter(Context context, ArrayList<MusicData> values){
        super(context, R.layout.layout_grid_view, values);
        this.context = context ;
        this.values = values ;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.layout_grid_view, parent, false);

        TextView judul = (TextView) v.findViewById(R.id.judulLaguGrid);
        ImageView img = (ImageView) v.findViewById(R.id.imageViewAlbumGrid);

        judul.setText(values.get(position).judul);
        img.setImageResource(R.drawable.cover);

        return v ;
    }
}
