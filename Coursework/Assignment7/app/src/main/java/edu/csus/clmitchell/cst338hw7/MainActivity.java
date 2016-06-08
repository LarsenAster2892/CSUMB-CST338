package edu.csus.clmitchell.cst338hw7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.CheckBox;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        Button sbButton = (Button) findViewById(R.id.btnSubmit);
        sbButton.setOnClickListener(this);

        Button exButton = (Button) findViewById(R.id.btnExit);
        exButton.setOnClickListener(this);
        exButton.setEnabled(false);
        exButton.setVisibility(View.INVISIBLE);

   }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnSubmit:
                //
                //  Make thank you text appear
                TextView sbTextThanks = (TextView) findViewById(R.id.textThanks);
                sbTextThanks.setVisibility(View.VISIBLE);
                //
                // Disable button control
                //(Button) v.setEnabled(false);
                Button sbButton = (Button) findViewById(R.id.btnSubmit);
                sbButton.setEnabled(false);
                //
                // disable checkboxes
                //
                CheckBox cbOption = (CheckBox) findViewById(R.id.cbMath170);
                cbOption.setEnabled(false);

                cbOption =  (CheckBox) findViewById(R.id.cbMath150);
                cbOption.setEnabled(false);

                cbOption =  (CheckBox) findViewById(R.id.cbCST231);
                cbOption.setEnabled(false);

                cbOption =  (CheckBox) findViewById(R.id.cbCST238);
                cbOption.setEnabled(false);

                cbOption =  (CheckBox) findViewById(R.id.cbCST300);
                cbOption.setEnabled(false);

                cbOption =  (CheckBox) findViewById(R.id.cbCST337);
                cbOption.setEnabled(false);

                cbOption =  (CheckBox) findViewById(R.id.cbCST373);
                cbOption.setEnabled(false);

                cbOption =  (CheckBox) findViewById(R.id.cbCST400);
                cbOption.setEnabled(false);

                //
                // disable radiobuttons
                //
                RadioButton rbOption = (RadioButton) findViewById(R.id.rbCST201);
                rbOption.setEnabled(false);

                rbOption = (RadioButton) findViewById(R.id.rbCST205);
                rbOption.setEnabled(false);

                rbOption = (RadioButton) findViewById(R.id.rbCST361s);
                rbOption.setEnabled(false);

                rbOption = (RadioButton) findViewById(R.id.rbCST462s);
                rbOption.setEnabled(false);

                rbOption = (RadioButton) findViewById(R.id.rbMath151);
                rbOption.setEnabled(false);

                rbOption = (RadioButton) findViewById(R.id.rbMath370);
                rbOption.setEnabled(false);

                //
                // enable exit button
                Button exButton = (Button) findViewById(R.id.btnExit);
                exButton.setEnabled(true);
                exButton.setVisibility(View.VISIBLE);


                break;

            case R.id.btnExit:
                finish();
                System.exit(0);

                break;


            default:
                break;
        }

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
}
