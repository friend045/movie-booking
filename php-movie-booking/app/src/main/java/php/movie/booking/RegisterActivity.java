package php.movie.booking;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
     EditText editRegisterName;
     EditText editRegisterUsername;
     EditText editRegisterPassword;
     EditText editRegisterEmail;

     AppCompatButton buttonRegister;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_register);
        editRegisterName= (EditText) findViewById(R.id.editRegisterName);
        editRegisterUsername= (EditText) findViewById(R.id.editRegisterUsername);
        editRegisterPassword= (EditText) findViewById(R.id.editRegisterPassword);
        editRegisterEmail= (EditText) findViewById(R.id.editRegisterEmail);

        buttonRegister= (AppCompatButton) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v == buttonRegister){
            registerUser();

        }
    }

    private void registerUser() {
        String name = editRegisterName.getText().toString().trim().toLowerCase();
        String username = editRegisterUsername.getText().toString().trim().toLowerCase();
        String password = editRegisterPassword.getText().toString().trim().toLowerCase();
        String email = editRegisterEmail.getText().toString().trim().toLowerCase();

        register(name,username,password,email);
    }

    private void register(String name, String username, String password, String email) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            private ProgressDialog loading;
            private RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterActivity.this, "上傳中","努力加載...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<>();
                data.put("name",params[0]);
                data.put("username",params[1]);
                data.put("password",params[2]);
                data.put("email",params[3]);

                String result = ruc.sendPostRequest(php.movie.booking.Config.REGISTER_URL,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name,username,password,email);
    }
}
