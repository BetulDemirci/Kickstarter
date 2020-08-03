package com.example.android.tourfc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.android.tourfc.model.Attraction;
import com.example.android.tourfc.model.AttractionRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@SuppressWarnings("FieldCanBeLocal")
public class AttractionListFragment extends Fragment {
    /* Class Constants */
    private static final String ARG_SECTION_TITLE = "sectionTitle";

    /* Class variables */
    private int sectionTitle;
    private ListView attractionListView;
    private AttractionListAdapter listViewAdapter;
    private EditText theFilter;

    public static AttractionListFragment newInstance(int sectionTitle) {
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_TITLE, sectionTitle);

        AttractionListFragment fragment = new AttractionListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (getArguments() != null) {
            sectionTitle = getArguments().getInt(ARG_SECTION_TITLE);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.show_all_view, container, false);
        setHasOptionsMenu(true);
        AttractionRepository repository = AttractionRepository.getInstance(getActivity());
        List<Attraction> attractions = repository.getCollection(sectionTitle).getAttractions();

        attractionListView = v.findViewById(R.id.show_all_list_view);
        listViewAdapter = new AttractionListAdapter(getActivity(), attractions, sectionTitle);
        attractionListView.setAdapter(listViewAdapter);

        theFilter = v.findViewById(R.id.searchFilter);
        theFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               // listViewAdapter.getFilter().filter(charSequence);

                ArrayList<Attraction> newList = new ArrayList<>();
                for (Attraction a: attractions) {
                    String number = a.getBacker().toLowerCase();
                    String title = a.getTitle().toLowerCase();

                    if(number.contains(charSequence)) newList.add(a);
                    else if(title.contains(charSequence)) newList.add(a);
                }

                listViewAdapter.setFilter(newList);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_att,menu);
        menu.findItem(R.id.action_sort).setVisible(true);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_sort){
            sortArrayList();
        }

        return super.onOptionsItemSelected(item);
    }

    private void sortArrayList(){
        AttractionRepository repository = AttractionRepository.getInstance(getActivity());
        List<Attraction> attractions = repository.getCollection(sectionTitle).getAttractions();
        Collections.sort(attractions, new Comparator<Attraction>() {
            @Override
            public int compare(Attraction lhs, Attraction rhs) {
                return lhs.getTitle().compareTo(rhs.getTitle());
            }
        });
        listViewAdapter = new AttractionListAdapter(getActivity(), attractions, sectionTitle);
        attractionListView.setAdapter(listViewAdapter);
    }

}
