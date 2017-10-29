package php.movie.booking;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import php.movie.booking.RecordActivity;
import php.movie.booking.SettingActivity;
import php.movie.booking.TicketActivity;

public class ProfileActivity extends AppCompatActivity implements View.OnTouchListener {

    //Textview顯示當前登錄的用戶
    TextView Person;
    String TempName = "";
    Button buttonCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        php.movie.booking.Utils.onActivityCreateSetTheme(ProfileActivity.this);
        setContentView(R.layout.activity_profile);

        buttonCall= (Button) findViewById(R.id.buttonCall);
        buttonCall.setOnTouchListener(ProfileActivity.this);

        //正在初始化textview
        Person = (TextView) findViewById(R.id.Person);
        //從shared preferences獲取帳號
        SharedPreferences sharedPreferences = getSharedPreferences(php.movie.booking.Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(php.movie.booking.Config.USERNAME_SHARED_PREF, "無法使用");

        //顯示當前登錄到textview的帳號
        Person.setText("使用者: " + username);
        TempName = username;
    }

    public void ticket(View v) {
        Intent intent = new Intent(this, TicketActivity.class);
        intent.putExtra("1", TempName);
        startActivity(intent);
    }

    public void record(View v) {
        Intent intent = new Intent(this, RecordActivity.class);
        intent.putExtra("1", TempName);

        startActivity(intent);
    }

    public void call(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:0800123123"));
        startActivity(intent);
    }

    public void personalize(View v) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    //登出函數
    private void logout() {
        //創建警報對話框以確認註銷
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("你確定你要登出嗎?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //退出共享推薦sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(php.movie.booking.Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //獲取編輯器
                        SharedPreferences.Editor editor = preferences.edit();

                        //對於登錄將值設置為false
                        editor.putBoolean(php.movie.booking.Config.LOGGEDIN_SHARED_PREF, false);

                        //將電子郵件設置為空值
                        editor.putString(php.movie.booking.Config.USERNAME_SHARED_PREF, "");

                        //保存sharedpreferences
                        editor.apply();

                        //開始login activity(跳轉)
                        Intent intent = new Intent(ProfileActivity.this, php.movie.booking.LoginActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //顯示警報對話框
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] temp = {0, 100, 2000, 2};
        vibrator.vibrate(temp, 2);
        return false;
    }
}
