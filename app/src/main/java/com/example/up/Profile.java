package com.example.up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    View v;
    List<MaskaProfile> maskaProfiles;
    AdapterProfile pAdapter;
    Connection connection;
    GridView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();

        v = findViewById(com.google.android.material.R.id.ghost_view);



        GetTextFromSQL(v);
    }

    public void SelectList(String Choice)
    {
        maskaProfiles = new ArrayList<MaskaProfile>();
        listView = findViewById(R.id.Profile_ListFoto);
        pAdapter = new AdapterProfile(Profile.this, maskaProfiles);
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if (connection != null) {

                String query = Choice;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    MaskaProfile tempMask = new MaskaProfile
                            (resultSet.getInt("ID"),
                                    resultSet.getString("Image"),
                                    resultSet.getString("Time")
                            );
                    maskaProfiles.add(tempMask);
                    pAdapter.notifyDataSetInvalidated();
                }
                connection.close();
            } else {
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        enterMobile();
    }

    public void enterMobile() {
        pAdapter.notifyDataSetInvalidated();
        listView.setAdapter(pAdapter);
    }

    public void GetTextFromSQL(View v) {
        maskaProfiles = new ArrayList<MaskaProfile>();
        listView = findViewById(R.id.Profile_ListFoto);
        pAdapter = new AdapterProfile(Profile.this, maskaProfiles);
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "Select * From Pictures";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    MaskaProfile tempMask = new MaskaProfile
                            (resultSet.getInt("ID"),
                                    resultSet.getString("Image"),
                                    resultSet.getString("Time")
                            );
                    maskaProfiles.add(tempMask);
                    pAdapter.notifyDataSetInvalidated();
                }
                connection.close();

            } else {
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        enterMobile();
    }

    public void onClickExit(View v) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void onClickMenu(View v) {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}