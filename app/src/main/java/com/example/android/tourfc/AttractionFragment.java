package com.example.android.tourfc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tourfc.model.Attraction;


public class AttractionFragment extends Fragment {
    /* Class Constants */
    private static final String ARG_ATTRACTION = "attraction";

    @SuppressWarnings("FieldCanBeLocal")
    private Button showInMapButton;
    private Attraction attraction;

    public static AttractionFragment newInstance(Attraction attraction) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_ATTRACTION, attraction);

        AttractionFragment fragment = new AttractionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        attraction = getArguments() != null ? getArguments().getParcelable(ARG_ATTRACTION) : null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        setHasOptionsMenu(true);
        // Grab a handle on the image view
        ImageView detailImageView = view.findViewById(R.id.detail_image_view);
        detailImageView.setImageResource(attraction.getImageResourceId());

        // grab a handle on the text view
        TextView textView = view.findViewById(R.id.detail_long_desc_tv);
        textView.setText(attraction.getLongDesc());

        showInMapButton = view.findViewById(R.id.detail_show_in_map_button);
        showInMapButton.setOnClickListener(v -> {

            showWebSite(attraction.getUrl());
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_att,menu);
        menu.findItem(R.id.action_sort).setVisible(false);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
    public void showWebSite(String url) {
        String fullUrl = "https://www.kickstarter.com"+url ;
        Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
        myWebLink.setData(Uri.parse(fullUrl));
        startActivity(myWebLink);
    }
}
