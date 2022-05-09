

package com.example.photoapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageView avatartv;
    TextView nam, email;
    RecyclerView postrecycle;
    FloatingActionButton fab;
    ProgressDialog pd;
    ArrayList<ModelPosts> posts;
    AdapterPosts adapterPosts;
    String uid;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // creating a  view to inflate the layout
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        firebaseAuth = FirebaseAuth.getInstance();

        // getting current user data
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        // Initialising the text view and imageview
        avatartv = view.findViewById(R.id.avatartv);
        nam = view.findViewById(R.id.nametv);
        email = view.findViewById(R.id.emailtv);
        uid = FirebaseAuth.getInstance().getUid();
        fab = view.findViewById(R.id.fab);
        postrecycle = view.findViewById(R.id.recyclerposts);
        posts = new ArrayList<>();
        pd = new ProgressDialog(getActivity());
        loadMyPosts();
        pd.setCanceledOnTouchOutside(false);
        String uid = firebaseUser.getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    // Retrieving Data from firebase
                    if (dataSnapshot1.child("uid").getValue().equals(uid)){
                        String name = "" + dataSnapshot1.child("name").getValue().toString();
                        String em = "" + dataSnapshot1.child("email").getValue().toString();
                        String image = "" + dataSnapshot1.child("image").getValue().toString();

                        nam.setText(name);
                        email.setText(em);
                        try {
                            Glide.with(getActivity()).load(image).into(avatartv);
                        } catch (Exception e) {

                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        // On click we will open EditProfileActiity
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditProfilePage.class));
            }
        });
        return view;
    }

    private void loadMyPosts() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        postrecycle.setLayoutManager(layoutManager);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Posts");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                posts.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    for (DataSnapshot childsnapshot: dataSnapshot1.getChildren()) {
                        if (childsnapshot.child("uid").getValue().equals(uid)) {
                            ModelPosts modelPost = childsnapshot.getValue(ModelPosts.class);
                            posts.add(modelPost);
                        }

                    }
                }
                adapterPosts = new AdapterPosts(getActivity(), posts);
                postrecycle.setAdapter(adapterPosts);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
}
