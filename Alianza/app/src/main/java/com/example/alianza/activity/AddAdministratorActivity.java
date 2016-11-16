package com.example.alianza.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.alianza.R;
import com.example.alianza.firebase.FireBaseInsert;
import com.example.alianza.pojo.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddAdministratorActivity extends AppCompatActivity {


    private DatabaseReference databaseReference;
    List<User> users;
    ArrayAdapter<String> adapter;
    List<String> name;
    FireBaseInsert fireBaseInsert;
    ListView listName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_administrator);


        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = new ArrayList<>();
        users = new ArrayList<>();
        listName = (ListView) findViewById(R.id.activity_list_add_admin);
        fireBaseInsert = new FireBaseInsert();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child(FireBaseInsert.REQUEST_ADMIN).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot.getValue() != null) {

                    User user = dataSnapshot.getValue(User.class);

                    setUsers(user);
                    name.add(user.getName());

                }

            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                setAdapter();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        setAdapter();


        listName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(AddAdministratorActivity.this, R.style.AppThemeRegisters));
                builder.setPositiveButton(getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        fireBaseInsert.insertAdmin(users.get(position));

                        databaseReference.child(FireBaseInsert.REQUEST_ADMIN).orderByChild("id").equalTo(users.get(position).getId()).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                dataSnapshot.getRef().setValue(null);

                                removeUser(position);
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }

                })
                        .setNegativeButton(getString(android.R.string.no), null)
                        .setTitle(getString(android.R.string.dialog_alert_title))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage(getString(R.string.add))
                        .show();
            }
        });

        listName.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                return false;
            }
        });
    }

    public void setUsers(User user) {

        users.add(user);

    }

    public void removeUser(int position) {

        users.remove(position);
        name.remove(position);

    }

    public void setAdapter() {

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, name);
        listName.setAdapter(adapter);

    }
}
