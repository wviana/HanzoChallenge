package com.hanzo.wviana.hanzotestapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public static final String EDIT_DATA = "edit_data";
    public static final String EDIT_INDEX = "edit_index";
    @Bind(R.id.user_list)
    ListView userList;
    private ArrayList<HashMap<String,String>> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        updateUserList();
        userList.setOnItemClickListener(this);
    }

    @OnClick(R.id.button_add_user)
    public void addNewUser() {
        Intent intent = new Intent(this, UserFormActivity.class);
        startActivityForResult(intent, UserFormActivity.NEW_USER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(resultCode == Activity.RESULT_OK){
            Bundle bundle = data.getExtras();
            users.add((HashMap<String, String>) bundle.get(UserFormActivity.USER_DATA));
            updateUserList();
        }
    }

    private void updateUserList() {
        userList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getUserStringList()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<String> getUserStringList(){
        ArrayList<String> firstFildValues = new ArrayList<>();
        for(HashMap<String, String> user : users){
            firstFildValues.add(user.values().iterator().next());
        }
        return firstFildValues;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, UserFormActivity.class);
        intent.putExtra(EDIT_DATA, (HashMap<String, String>) users.get(position));
        users.remove(position);
        startActivityForResult(intent, UserFormActivity.EDIT_USER);

    }
}
