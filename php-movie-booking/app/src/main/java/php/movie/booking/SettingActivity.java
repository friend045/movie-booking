package php.movie.booking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    Button Color1,Color2,Color3,Color4,Color5,Color6,Color7,Color8,Color9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_setting);
        Color1= (Button) findViewById(R.id.Color1);
        Color1.setOnClickListener(this);
        Color2= (Button) findViewById(R.id.Color2);
        Color2.setOnClickListener(this);
        Color3= (Button) findViewById(R.id.Color3);
        Color3.setOnClickListener(this);
        Color4= (Button) findViewById(R.id.Color4);
        Color4.setOnClickListener(this);
        Color5= (Button) findViewById(R.id.Color5);
        Color5.setOnClickListener(this);
        Color6= (Button) findViewById(R.id.Color6);
        Color6.setOnClickListener(this);
        Color7= (Button) findViewById(R.id.Color7);
        Color7.setOnClickListener(this);
        Color8= (Button) findViewById(R.id.Color8);
        Color8.setOnClickListener(this);
        Color9= (Button) findViewById(R.id.Color9);
        Color9.setOnClickListener(this);


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

        switch (v.getId())
        {

            case R.id.Color1:
                Utils.changeToTheme(this, Utils.COLOR_1);
                break;
            case  R.id.Color2:
                Utils.changeToTheme(this, Utils.COLOR_2);
                break;
            case  R.id.Color3:
                Utils.changeToTheme(this, Utils.COLOR_3);
                break;
            case  R.id.Color4:
                Utils.changeToTheme(this, Utils.COLOR_4);
                break;
            case  R.id.Color5:
                Utils.changeToTheme(this, Utils.COLOR_5);
                break;
            case  R.id.Color6:
                Utils.changeToTheme(this, Utils.COLOR_6);
                break;
            case  R.id.Color7:
                Utils.changeToTheme(this, Utils.COLOR_7);
                break;
            case  R.id.Color8:
                Utils.changeToTheme(this, Utils.COLOR_8);
                break;
            case  R.id.Color9:
                Utils.changeToTheme(this, Utils.COLOR_9);
                break;



        }
    }
}
