package br.com.ufcg.hojeTem;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import br.com.ufcg.hojeTem.service.EventFacade;
import br.com.ufcg.hojeTem.slideMenu.NavDrawerItem;
import br.com.ufcg.hojeTem.slideMenu.NavDrawerListAdapter;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Projeto: Hoje Tem!
 * 
 * Activity principal para prover recursos e fornecer visualização do mapa de
 * eventos.
 * 
 */
public class MapActivity extends FragmentActivity {

    private static final int MY_EVENTS = 0;
    private static final int PESQUISAR = 1;
    private static final int FILTRAR = 2;
    private static final int VISUALIZAR = 3;
    /** Nosso mapa do Google Maps */
    private GoogleMap eventsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	if (isGooglePlayAvailable()) {
	    setContentView(R.layout.activity_map);
	    setAtualLocation();
	}

	mTitle = mDrawerTitle = getTitle();

	// load slide menu items
	navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

	// nav drawer icons from resources
	navMenuIcons = getResources()
		.obtainTypedArray(R.array.nav_drawer_icons);

	mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

	navDrawerItems = new ArrayList<NavDrawerItem>();

	// adding nav drawer items to array
	// Home
	navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons
		.getResourceId(MY_EVENTS, -1)));
	// Find People
	navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
		.getResourceId(PESQUISAR, -1)));
	// Photos
	navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
		.getResourceId(FILTRAR, -1)));
	// Communities, Will add a counter here
	navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
		.getResourceId(VISUALIZAR, -1)));

	// Recycle the typed array
	navMenuIcons.recycle();

	mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

	// setting the nav drawer list adapter
	adapter = new NavDrawerListAdapter(getApplicationContext(),
		navDrawerItems);
	mDrawerList.setAdapter(adapter);

	// enabling action bar app icon and behaving it as toggle button
	getActionBar().setDisplayHomeAsUpEnabled(true);
	getActionBar().setHomeButtonEnabled(true);

	mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
		R.drawable.ic_drawer, // nav menu toggle icon
		R.string.app_name, // nav drawer open - description for
				   // accessibility
		R.string.app_name // nav drawer close - description for
				  // accessibility
	) {
	    public void onDrawerClosed(View view) {
		getActionBar().setTitle(mTitle);
		// calling onPrepareOptionsMenu() to show action bar icons
		invalidateOptionsMenu();
	    }

	    public void onDrawerOpened(View drawerView) {
		getActionBar().setTitle(mDrawerTitle);
		// calling onPrepareOptionsMenu() to hide action bar icons
		invalidateOptionsMenu();
	    }
	};
	mDrawerLayout.setDrawerListener(mDrawerToggle);

	if (savedInstanceState == null) {
	    // on first time display view for first nav item
	    // displayView(0);
	}
    }

    /**
     * Método para direcionar o mapa para o local atual.
     */
    private void setAtualLocation() {
	eventsMap = ((SupportMapFragment) getSupportFragmentManager()
		.findFragmentById(R.id.map)).getMap();

	eventsMap.setMyLocationEnabled(true);

	LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

	String provider = LocationManager.GPS_PROVIDER;

	Location location = locationManager.getLastKnownLocation(provider);

	// caso o GPS tenha falhado, pega da rede
	if (location == null) {
	    provider = LocationManager.NETWORK_PROVIDER;
	    location = locationManager.getLastKnownLocation(provider);
	}

	if (location != null) {
	    onLocationChanged(location);
	}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	this.getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	if (mDrawerToggle.onOptionsItemSelected(item)) {
	    return true;
	}

	if (item.getItemId() == R.id.action_logout) {
	    this.finish();
	}
	return super.onOptionsItemSelected(item);
    }

    /**
     * Método que acessa os serviços da Google Play e verifica se este serviço
     * está disponível.
     * 
     * @return se o serviço da Google Play está disponível.
     */
    private boolean isGooglePlayAvailable() {
	int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

	boolean available = status == ConnectionResult.SUCCESS;
	if (!available) {
	    Toast.makeText(this, "Google Play not available",
		    Toast.LENGTH_SHORT).show();
	}
	return available;
    }

    /**
     * Cidade alterada, direcionando para o local.
     * 
     * @param location
     *            {@code Location} contendo dados da localização.
     */
    private void onLocationChanged(Location location) {
	double latitude = location.getLatitude();
	double longitude = location.getLongitude();

	LatLng latLng = new LatLng(latitude, longitude);

	eventsMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
	eventsMap.animateCamera(CameraUpdateFactory.zoomTo(15));
	EventFacade.getInstance().markEventCurrentLocation(this.eventsMap,
		latitude, longitude);
    }

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
	// if nav drawer is opened, hide the action items
	boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	menu.findItem(R.id.action_logout).setVisible(!drawerOpen);
	return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
	mTitle = title;
	getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
	super.onPostCreate(savedInstanceState);
	// Sync the toggle state after onRestoreInstanceState has occurred.
	mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
	super.onConfigurationChanged(newConfig);
	// Pass any configuration change to the drawer toggls
	mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
	    ListView.OnItemClickListener {
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
		long id) {
	    // display view for selected nav drawer item
	    displayView(position);
	}
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
	// update the main content by replacing fragments
	switch (position) {
	case MY_EVENTS:
	    EventFacade.getInstance().getUserEvents();

	    try {
		new Thread().sleep(2000);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    // update selected item and title, then close the drawer
	    mDrawerList.setItemChecked(position, true);
	    mDrawerList.setSelection(position);
	    setTitle(navMenuTitles[position]);
	    mDrawerLayout.closeDrawer(mDrawerList);

	    Intent intentUserEvents = new Intent(getApplicationContext(),
		    ListViewMyEventsActivity.class);
	    startActivity(intentUserEvents);
	    break;
	case PESQUISAR:
	    mDrawerList.setItemChecked(position, true);
	    mDrawerList.setSelection(position);
	    setTitle(navMenuTitles[position]);
	    mDrawerLayout.closeDrawer(mDrawerList);
	    break;
	case VISUALIZAR:
	    mDrawerList.setItemChecked(position, true);
	    mDrawerList.setSelection(position);
	    setTitle(navMenuTitles[position]);
	    mDrawerLayout.closeDrawer(mDrawerList);

	    Intent intent = new Intent(getApplicationContext(),
		    ListViewActivity.class);
	    startActivity(intent);

	    break;
	default:
	    break;
	}
    }

}
