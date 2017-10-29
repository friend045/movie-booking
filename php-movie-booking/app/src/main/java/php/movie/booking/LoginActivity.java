package php.movie.booking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import php.movie.booking.ProfileActivity;
import php.movie.booking.RegisterActivity;
import php.movie.booking.SettingActivity;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //定義視圖
    EditText editTextUsername;
    EditText editTextPassword;
    AppCompatButton buttonLogin;
    TextView linkSignup;
    //boolean 值來檢查用戶是否登錄
    //最初它是false
    boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化視圖
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (AppCompatButton) findViewById(R.id.buttonLogin);

        linkSignup = (TextView) findViewById(R.id.linkSignup);

        //添加點擊監聽器
        buttonLogin.setOnClickListener(this);
        linkSignup.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //從sharedpreferences獲取在onresume值
        SharedPreferences sharedPreferences = getSharedPreferences(php.movie.booking.Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //從sharedpreferences獲取boolean值
        loggedIn = sharedPreferences.getBoolean(php.movie.booking.Config.LOGGEDIN_SHARED_PREF, false);

        //如果等於true
        if (loggedIn) {
            //將使用 Profile Activity
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
    }

    private void login() {
        //取得 EditText 值
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        //請求創建字串
        StringRequest stringRequest = new StringRequest(Request.Method.POST, php.movie.booking.Config.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //如果從Server獲得成功
                        if (response.replaceAll("\\s+", "").equals(php.movie.booking.Config.LOGIN_SUCCESS)) {
//                        if (response.equalsIgnoreCase(Config.LOGIN_SUCCESS)) {
                            //創建 shared preference
                            SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(php.movie.booking.Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //創建編輯器以將值存儲到 shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //向編輯器中添加值
                            editor.putBoolean(php.movie.booking.Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(php.movie.booking.Config.USERNAME_SHARED_PREF, username);

                            //將值保存到編輯器
                            editor.apply();

                            //啟用profile activity(跳轉)
                            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        } else {
                            //如果向Server請求失敗
                            // 在Toast上顯示錯誤消息
                            Toast.makeText(LoginActivity.this, "帳號或密碼有錯", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //你可以在這里處理錯誤
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //添加參數以請求
                params.put(php.movie.booking.Config.KEY_USERNAME, username);
                params.put(php.movie.booking.Config.KEY_PASSWORD, password);

                //返回參數
                return params;
            }
        };

        //將字串添加列隊
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        //調用登錄函數
        if (v == buttonLogin) {
            login();
        }
        if(v==linkSignup){
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu person) {
        getMenuInflater().inflate(R.menu.person, person);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuPerson) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
