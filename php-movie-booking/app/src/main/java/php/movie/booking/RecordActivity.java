package php.movie.booking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonGetRecord;

    ListView TicketList;
    ArrayList<HashMap<String, String>> contactList;

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_record);
        buttonGetRecord = (Button) findViewById(R.id.buttonGetRecord);

        contactList = new ArrayList<>();
        TicketList = (ListView) findViewById(R.id.TicketList);

        buttonGetRecord.setOnClickListener(this);
    }

    private void getData() {
        Intent intent = this.getIntent();
        String TempName = intent.getStringExtra("1");
//        if (TempName.equals("")) {
//            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
//            return;
//        }
        loading = ProgressDialog.show(this, "更新中", "努力加載...", false, false);

        String url = php.movie.booking.Config.DATA_URL + TempName.trim();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RecordActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {

        String moviename = "";
        String releasedate = "";
        String event = "";
        String category = "";
        String sheets = "";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(php.movie.booking.Config.JSON_ARRAY);
            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);

                moviename = collegeData.getString(php.movie.booking.Config.KEY_MOVIENAME);
                releasedate = collegeData.getString(php.movie.booking.Config.KEY_RELEASEDATE);
                event = collegeData.getString(php.movie.booking.Config.KEY_EVENT);
                category = collegeData.getString(php.movie.booking.Config.KEY_CATEGORY);
                sheets = collegeData.getString(php.movie.booking.Config.KEY_SHEETS);


                HashMap<String, String> contact = new HashMap<>();

                contact.put("moviename", moviename);
                contact.put("releasedate", releasedate);
                contact.put("event", event);
                contact.put("category", category);
                contact.put("sheets", sheets);
                contactList.add(contact);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(RecordActivity.this, contactList,
                R.layout.list_item, new String[]{"moviename", "releasedate", "event", "category", "sheets"},
                new int[]{R.id.moviename, R.id.releasedate, R.id.event, R.id.category, R.id.sheets});
        TicketList.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu home) {
        getMenuInflater().inflate(R.menu.home, home);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuHome) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        contactList.clear();
        getData();
    }
}
