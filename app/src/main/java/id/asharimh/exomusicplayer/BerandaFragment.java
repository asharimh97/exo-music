package id.asharimh.exomusicplayer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BerandaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BerandaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BerandaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<MusicData> musicDatas ;
    GridView gridViewPopuler ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BerandaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BerandaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BerandaFragment newInstance(String param1, String param2) {
        BerandaFragment fragment = new BerandaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        final String TAG = "Firebase data " ;
        musicDatas = new ArrayList<MusicData>() ;

        gridViewPopuler = (GridView) view.findViewById(R.id.gridViewPopuler);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),
//                R.layout.layout_grid_view, R.id.judulLaguGrid, populer);
        final MusicAdapter adapter = new MusicAdapter(view.getContext(), BaseClass.listPlaying);

        // Firebase set up
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("musics");
        System.out.println(database + " " + myRef);
        // Read from the database
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "Child music added : " + dataSnapshot.getKey()) ;
                MusicData music = dataSnapshot.getValue(MusicData.class) ;
                BaseClass.listPlaying.add(music) ;
                gridViewPopuler.setAdapter(adapter);
//                System.out.println(dataSnapshot);
//                System.out.println(music);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "Child music changed : " + dataSnapshot.getKey()) ;
                MusicData music = dataSnapshot.getValue(MusicData.class) ;
//                System.out.println(dataSnapshot);
//                System.out.println(music);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Child music removed : " + dataSnapshot.getKey()) ;
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "Child music moved : " + dataSnapshot.getKey()) ;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Child music cancelled") ;
            }
        } ;
        myRef.addChildEventListener(childEventListener) ;
//        gridViewPopuler.setAdapter(adapter);

        gridViewPopuler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MusicData dataMusik = (MusicData) gridViewPopuler.getItemAtPosition(i);
                BaseClass.idx = i ;
                String musicLink = dataMusik.linkLagu ;
                String musicTitle = dataMusik.judul ;
                String musicDaerah = dataMusik.daerah ;

                Intent intent = new Intent(getContext(), PlayerActivity.class) ;
                intent.putExtra(BaseClass.WILL_PLAY, musicLink) ;
                intent.putExtra(BaseClass.WILL_PLAY_DAERAH, musicDaerah) ;
                intent.putExtra(BaseClass.WILL_PLAY_TITLE, musicTitle) ;
                intent.putExtra(BaseClass.WILL_PLAY_IDX, BaseClass.idx);

                startActivity(intent);
            }
        });

        return view ;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString()
            //       + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
