package com.example.android.tourfc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.tourfc.model.AttractionCollection;
import com.example.android.tourfc.model.AttractionItem;
import com.example.android.tourfc.model.AttractionRepository;
import com.example.android.tourfc.model.AttractionResponse;

import org.json.JSONException;

import java.util.List;

public class MainActivity extends AppCompatActivity {
	Context context = this;
	SQliteHelper db = new SQliteHelper(context);
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GetItemsTask task = new GetItemsTask();
		task.execute(this);


	}

	public class GetItemsTask extends AsyncTask<Object, Void, List<AttractionItem>> {
		ProgressDialog progress;

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected List<AttractionItem> doInBackground(Object... params) {
			List<AttractionItem> rubpl = null;
			try {
				rubpl = Utils.getItems(params);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return rubpl;
		}

		@Override
		protected void onPostExecute(List<AttractionItem> list) {
			try {
				//List<AttractionItem> list = rubpl.AttractionItems;
				db.onUpgrade(db.getWritableDatabase(),1,2);
				for (int i = 0; i < list.size(); i++) {
					db.addAttraction(new AttractionItem(list.get(i).no,list.get(i).pledged,list.get(i).blurb,list.get(i).by,list.get(i).country,list.get(i).currency,list.get(i).endTime,list.get(i).location,list.get(i).funded,list.get(i).backers,list.get(i).state,list.get(i).title,list.get(i).type,list.get(i).url));
				}

				// Initialize list to store collection of attractions
				AttractionRepository repository = AttractionRepository.getInstance(MainActivity.this);
				List<AttractionCollection> collections = repository.getCollections();

				// Hook the recycler view
				RecyclerView recyclerView = findViewById(R.id.main_recycler_view);

				// Set fixed size true and optimize recycler view performance
				// The data container has fixed number of attractions and not infinite list
				recyclerView.setHasFixedSize(true);

				// Connect the RecyclerView widget to the vertical linear layout
				// (not reverse layout - hence false)
				recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,
						LinearLayoutManager.VERTICAL, false));

				// Attach adapter to the RecyclerView widget which is connected to a layout manager
				MasterAdapter collectionAdapter = new MasterAdapter(MainActivity.this, collections);
				recyclerView.setAdapter(collectionAdapter);
			} catch (Exception ex) {
				ex.printStackTrace();

			}

		}
	}


}
