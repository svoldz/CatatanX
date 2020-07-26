package com.catatan.x;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.annotation.SuppressLint;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private TextView tvEmpty;
    private FloatingActionButton fabAdd;

	private NavigationView nv;
	private DrawerLayout dl;
    private Database db;
    private CatatanAdapter adapter;
    private List<CatatanModel> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		toolbar.setTitle(R.string.app_name);
		toolbar.setNavigationIcon(R.drawable.ic_menu);
		dl = (DrawerLayout) findViewById(R.id.drawer_layout);
		dl.setDrawerListener(new ActionBarDrawerToggle(this, dl, toolbar, R.string.drawer_open, R.string.drawer_close));
		nv = (NavigationView) findViewById(R.id.navigation);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
				@Override
				public boolean onNavigationItemSelected(MenuItem menuItem) {
					
					if(menuItem.isChecked())
						menuItem.setChecked(false);
					else
						menuItem.setChecked(true);

			
					dl.closeDrawers();

					switch (menuItem.getItemId()){
							
						case R.id.about:
							startActivity(new Intent(MainActivity.this, About.class));
							Toast.makeText(MainActivity.this, "Tentang Catatan X", Toast.LENGTH_SHORT).show();
							return true;
						case R.id.jadwal:
							startActivity(new Intent(MainActivity.this, Add.class));
							Toast.makeText(MainActivity.this, "Tambah Catatan", Toast.LENGTH_SHORT).show();
							return true;

					}
					return true;
				}
			});	

        db = new Database(this);

        rvList =(RecyclerView) findViewById(R.id.rv_list);
        tvEmpty =(TextView) findViewById(R.id.tv_empty);
        fabAdd =(FloatingActionButton) findViewById(R.id.fab_add);

        adapter = new CatatanAdapter(this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);

        fabAdd.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(new Intent(MainActivity.this, Add.class));
				}
			});
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        MenuItem searchIem = menu.findItem(R.id.action_search);
		final SearchView searchView = (SearchView) searchIem.getActionView();
		searchView.setQueryHint("Cari Berdasarkan Judul");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
				@SuppressLint("SetTextI18n")
				@Override
				public boolean onQueryTextSubmit(String query) {
					return false;
				}

				@Override
				public boolean onQueryTextChange(String nextText) {
					nextText = nextText.toLowerCase();
					ArrayList<CatatanModel> dataFilter = new ArrayList<>();
					for(CatatanModel data : notes){
						String nama = data.getNoteTitle().toLowerCase();
						if(nama.contains(nextText)){
							dataFilter.add(data);
						}
					}
					adapter.setFilter(dataFilter);
					return true;
				}
			});
        return true;
    }
	

    @Override
    protected void onResume() {
        super.onResume();
        getNotes();
    }

    private void getNotes() {
        notes = db.getNotes();
        adapter.setNotes(notes);

        if (notes.size() != 0) {
            tvEmpty.setVisibility(View.GONE);
            rvList.setVisibility(View.VISIBLE);
        } else {
            tvEmpty.setVisibility(View.VISIBLE);
            rvList.setVisibility(View.GONE);
        }
    }
}
