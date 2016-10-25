package com.example.alianza.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.alianza.R;
import com.example.alianza.adapters.MainAdapter;
import com.example.alianza.firebase.FireBaseInsert;
import com.example.alianza.fragments.MatchFragment;
import com.example.alianza.fragments.NewsFragment;
import com.example.alianza.fragments.PlayerFragment;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    final int newsTabs = 0;
    final int teamTabs = 1;
    final int matchTabs = 2;
    private int position;
    private DrawerLayout mDrawerLayout;


    public OnClickSearchPlayer onClickSearchPlayer;
    public OnClickSearchNews onClickSearchNews;
    public OnClickSearchMatch onClickSearchMatch;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    CircularImageView circularImageView;
    TextView profileName;
    public static boolean isAdmin = false;
    public static boolean isRequestAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG);
            Log.d("Firebase", FirebaseDatabase.getInstance().toString());
        } catch (Exception e) {
            Log.w("Firebase", "SetPresistenceEnabled:Fail" + FirebaseDatabase.getInstance().toString());
            e.printStackTrace();
        }


        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        // Create Navigation drawer and inlfate layout
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        View view = navigationView.getHeaderView(0);

        circularImageView = (CircularImageView) view.findViewById(R.id.profile_picture);

        Picasso.with(this).load(firebaseUser.getPhotoUrl()).into(circularImageView);

        profileName = (TextView) view.findViewById(R.id.profile_name);
        profileName.setText(firebaseUser.getDisplayName());

        //FireBaseInsert fireBaseInsert= new FireBaseInsert();
        //fireBaseInsert.insertAdmin(firebaseUser.getUid(), firebaseUser.getDisplayName());

        databaseReference = FirebaseDatabase.getInstance().getReference("admin");

        databaseReference.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (firebaseUser.getUid().equals(dataSnapshot.getValue().toString().substring(1, 29))) {

                    isAdmin = true;

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            /*VectorDrawableCompat indicator =
                    VectorDrawableCompat.create(getResources(), R.drawable.ic_menu_black_24dp, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.colorWhite, getTheme()));*/
            supportActionBar.setHomeAsUpIndicator(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_menu_white_24dp, getTheme()));
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }


        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Set item in checked state

                        menuItem.setCheckable(false);


                        if (menuItem.getTitle().equals(getResources().getText(R.string.action_admin))) {


                            if (!isAdmin) {


                                databaseReference = FirebaseDatabase.getInstance().getReference("request admin");

                                databaseReference.orderByKey().addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        if (firebaseUser.getUid().equals(dataSnapshot.getValue().toString().substring(1, 29))) {

                                            isRequestAdmin = true;

                                        }

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                                if (!isRequestAdmin) {

                                    FireBaseInsert fireBaseInsert = new FireBaseInsert();
                                    fireBaseInsert.insertAdmin(firebaseUser.getUid(), firebaseUser.getDisplayName());
                                    mDrawerLayout.closeDrawer(GravityCompat.START);
                                    Snackbar.make(findViewById(R.id.main_content), getResources().getString(R.string.send_request_permission), Snackbar.LENGTH_LONG)
                                            .show();

                                } else {

                                    mDrawerLayout.closeDrawer(GravityCompat.START);
                                    Snackbar.make(findViewById(R.id.main_content), getResources().getString(R.string.request_permission), Snackbar.LENGTH_LONG)
                                            .show();

                                }


                            } else {
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                Snackbar.make(findViewById(R.id.main_content), getResources().getString(R.string.admin_permission), Snackbar.LENGTH_LONG)
                                        .show();

                            }


                        } else if (menuItem.getTitle().equals(getResources().getText(R.string.action_logout))) {

                            FirebaseAuth.getInstance().signOut();


                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();

                        } else if (menuItem.getTitle().equals(getResources().getText(R.string.action_add_admin))) {

                            if (isAdmin) {

                                Intent intent = new Intent(MainActivity.this, AddAdministrator.class);
                                intent.putExtra(AddAdministrator.ID_USER, firebaseUser.getUid());
                                intent.putExtra(AddAdministrator.NAME_USER, firebaseUser.getDisplayName());
                                startActivity(intent);

                            } else {

                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                Snackbar.make(findViewById(R.id.main_content), getResources().getString(R.string.not_permission), Snackbar.LENGTH_LONG)
                                        .show();
                            }


                        }

                        return true;
                    }
                });


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isAdmin) {

                    if (getPosition() == newsTabs) {

                        Intent intent = new Intent(MainActivity.this, RegisterCreateNewsActivity.class);
                        startActivity(intent);


                    } else if (getPosition() == teamTabs) {

                        Intent intent = new Intent(MainActivity.this, RegisterCreatePlayerActivity.class);
                        startActivity(intent);

                    } else {

                        Intent intent = new Intent(MainActivity.this, RegisterCreateMatchActivity.class);
                        startActivity(intent);

                    }

                } else {


                    Snackbar.make(findViewById(R.id.main_content), getResources().getString(R.string.not_permission), Snackbar.LENGTH_LONG)
                            .show();

                }


            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case newsTabs:
                        setPosition(newsTabs);
                        break;

                    case teamTabs:
                        setPosition(teamTabs);
                        break;

                    case matchTabs:
                        setPosition(matchTabs);
                        ;
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast like print

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);


                if (getPosition() == newsTabs) {

                    onClickSearchNews.onItemClickSearch(s);

                } else if (getPosition() == teamTabs) {

                    onClickSearchPlayer.onItemClickSearch(s);

                } else {

                    onClickSearchMatch.onItemClickSearch(s);

                }


                return false;
            }
        });


        return true;
    }


    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {

        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        adapter.addFragment(new NewsFragment(), getResources().getString(R.string.tabs_name_news));
        adapter.addFragment(new PlayerFragment(), getResources().getString(R.string.tabs_name_team));
        adapter.addFragment(new MatchFragment(), getResources().getString(R.string.tabs_name_match));
        viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {

            mDrawerLayout.openDrawer(GravityCompat.START);
        }


        return super.onOptionsItemSelected(item);
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public void setOnClickSearchPlayer(OnClickSearchPlayer onClickSearchPlayer) {
        this.onClickSearchPlayer = onClickSearchPlayer;
    }

    public void setOnClickSearchNews(OnClickSearchNews onClickSearchNews) {
        this.onClickSearchNews = onClickSearchNews;
    }

    public void setOnClickSearchMatch(OnClickSearchMatch onClickSearchMatch) {
        this.onClickSearchMatch = onClickSearchMatch;
    }

    public interface OnClickSearchPlayer {
        void onItemClickSearch(String query);
    }

    public interface OnClickSearchNews {
        void onItemClickSearch(String query);
    }

    public interface OnClickSearchMatch {
        void onItemClickSearch(String query);
    }


}
