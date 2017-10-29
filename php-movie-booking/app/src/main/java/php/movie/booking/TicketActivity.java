package php.movie.booking;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;

import static php.movie.booking.R.layout.spinner;


public class TicketActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener, DatePickerDialog.OnClickListener, View.OnClickListener, View.OnTouchListener {
    Spinner spinnerMovieName;
    Spinner spinnerEvent;
    RadioGroup RgCategory;
    RadioButton RbCategory;
    RadioGroup RgSheets;
    RadioButton RbSheets;
    TextView textReleaseDate;
    Button buttonTicketBuy;

    String spMovieName[] = {"請選擇電影", "星際過客", "決戰異世界", "終極追殺令"};
    String spEvent[] = {"請選擇場次", "第一場", "第二場", "第三場"};
    String dpReleaseDate = "";
    int RbC, RbS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        php.movie.booking.Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_ticket);
        spinnerMovieName = (Spinner) findViewById(R.id.spinnerMovieName);
        spinnerEvent = (Spinner) findViewById(R.id.spinnerEvent);
        RgCategory = (RadioGroup) findViewById(R.id.RgCategory);
        RgSheets = (RadioGroup) findViewById(R.id.RgSheets);
        textReleaseDate = (TextView) findViewById(R.id.textReleasedate);
        buttonTicketBuy = (Button) findViewById(R.id.buttonTicketBuy);

        ArrayAdapter<String> adapterMovieName = new ArrayAdapter<>(this, spinner, spMovieName);
        adapterMovieName.setDropDownViewResource(spinner);
        spinnerMovieName.setAdapter(adapterMovieName);
        spinnerMovieName.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapterEvent = new ArrayAdapter<>(this, spinner, spEvent);
        adapterEvent.setDropDownViewResource(spinner);
        spinnerEvent.setAdapter(adapterEvent);


        textReleaseDate.setOnClickListener(this);
        buttonTicketBuy.setOnClickListener(this);
        buttonTicketBuy.setOnTouchListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position == 1) {
            new AlertDialog.Builder(TicketActivity.this)
                    .setTitle("電影介紹")
                    .setMessage(php.movie.booking.Config.MOVIE_1_INTRODUCTION)
                    .setNegativeButton("確定", TicketActivity.this).show();

        }
        if (position == 2) {
            new AlertDialog.Builder(TicketActivity.this)
                    .setTitle("電影介紹")
                    .setMessage(php.movie.booking.Config.MOVIE_2_INTRODUCTION)
                    .setNegativeButton("確定", TicketActivity.this).show();

        }
        if (position == 3) {
            new AlertDialog.Builder(TicketActivity.this)
                    .setTitle("電影介紹")
                    .setMessage(php.movie.booking.Config.MOVIE_3_INTRODUCTION)
                    .setNegativeButton("確定", TicketActivity.this).show();

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        dpReleaseDate = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
        textReleaseDate.setText(dpReleaseDate);

    }

    private void orderTicket() {
        Intent intent = this.getIntent();
        String TempName = intent.getStringExtra("1");
        String username = TempName.trim().toLowerCase();
        String moviename = spinnerMovieName.getSelectedItem().toString().trim().toLowerCase();
        String releasedate = dpReleaseDate.trim().toLowerCase();
        String event = spinnerEvent.getSelectedItem().toString().trim().toLowerCase();
        String category = RbCategory.getText().toString().trim().toLowerCase();
        String sheets = RbSheets.getText().toString().trim().toLowerCase();
        order(username, moviename, releasedate, event, category, sheets);
    }

    private void order(String username, String moviename, String releasedate, String event, String category, String sheets) {
        class OrderTicket extends AsyncTask<String, Void, String> {
            private ProgressDialog loading;
            private php.movie.booking.RegisterUserClass ruc = new php.movie.booking.RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TicketActivity.this, "上傳中", "努力加載...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<>();
                data.put("username", params[0]);
                data.put("moviename", params[1]);
                data.put("releasedate", params[2]);
                data.put("event", params[3]);
                data.put("category", params[4]);
                data.put("sheets", params[5]);
                String result = ruc.sendPostRequest(php.movie.booking.Config.INSERT_TICKET_URL, data);

                return result;
            }
        }

        OrderTicket ru = new OrderTicket();
        ru.execute(username, moviename, releasedate, event, category, sheets);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] temp = {0, 100, 2000, 2};
        vibrator.vibrate(temp, 2);
        return false;
    }

    Calendar cDate = Calendar.getInstance();
    int year = cDate.get(Calendar.YEAR);
    int month = cDate.get(Calendar.MONTH);
    int day = cDate.get(Calendar.DAY_OF_MONTH);

    @Override
    public void onClick(View v) {
        if (v == textReleaseDate) {
            new DatePickerDialog(TicketActivity.this, R.style.DialogTheme, TicketActivity.this, year, month, day).show();
        }
        if (v == buttonTicketBuy) {
            if (spinnerMovieName.getSelectedItem().toString().equals(spMovieName[0]) || spinnerEvent.getSelectedItem().toString().equals(spEvent[0]) ){
                new AlertDialog.Builder(TicketActivity.this)
                        .setTitle("警告")
                        .setMessage("請填寫完整資訊")
                        .setNegativeButton("確定", TicketActivity.this)
                        .show();
            }else {
                RbC = RgCategory.getCheckedRadioButtonId();
                RbS = RgSheets.getCheckedRadioButtonId();
                RbCategory = (RadioButton) findViewById(RbC);
                RbSheets = (RadioButton) findViewById(RbS);
                Intent intent = this.getIntent();
                String TempName = intent.getStringExtra("1");
                new AlertDialog.Builder(TicketActivity.this)
                        .setTitle("確認訂票")
                        .setMessage("訂票資訊\n" + "帳號: " + TempName +
                                "\n電影: " + spinnerMovieName.getSelectedItem().toString() +
                                "\n日期: " + dpReleaseDate + "\n場次: " + spinnerEvent.getSelectedItem().toString() +
                                "\n票別: " + RbCategory.getText().toString() +
                                "\n張數: " + RbSheets.getText().toString())
                        .setPositiveButton("確定", TicketActivity.this)
                        .setNegativeButton("取消", TicketActivity.this)
                        .show();
            }
        }

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            orderTicket();/*買票資訊送出*/
        }
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

}
