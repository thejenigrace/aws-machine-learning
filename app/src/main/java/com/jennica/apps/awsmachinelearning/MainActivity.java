package com.jennica.apps.awsmachinelearning;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;

import java.util.Map;


public class MainActivity extends Activity {

    private EditText etAge;
    private EditText etMarital;
    private EditText etEducation;
    private EditText etJob;

    private Button btnPredict;

    private AndroidRealtimePrediction arp;

    private Map<String, String> mRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Initialize the Amazon Cognito credentials provider
//        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
//                this.getApplicationContext(), // Context
//                "us-east-1:70bb34f9-7d88-41ff-85ea-125d10231516", // Identity Pool ID
//                Regions.US_EAST_1 // Region
//        );

//        AndroidRealtimePrediction arp = new AndroidRealtimePrediction("ml-Bhm53788P6A", credentialsProvider.getCredentials());

        this.etAge = (EditText) findViewById(R.id.editTextAge);
        this.etMarital = (EditText) findViewById(R.id.editTextMarital);
        this.etEducation = (EditText) findViewById(R.id.editTextEducation);
        this.etJob = (EditText) findViewById(R.id.editTextJob);

        this.btnPredict = (Button) findViewById(R.id.buttonPredict);
        this.btnPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConnectToAWS().execute();

                Toast.makeText(getApplicationContext(), "Execute", Toast.LENGTH_SHORT).show();

//                mRecord = new HashMap<String, String>();
//                mRecord.put("age", etAge.getText().toString());
//                mRecord.put("marital", etMarital.getText().toString());
//                mRecord.put("education", etEducation.getText().toString());
//                mRecord.put("job", etJob.getText().toString());

//                Toast.makeText(getBaseContext(), arp.predict(mRecord).getPrediction().toString(), Toast.LENGTH_LONG).show();
//                Log.v("predict", arp.predict(mRecord).getPrediction().toString());
            }
        });
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

    private class ConnectToAWS extends AsyncTask<Object, Void, CognitoCachingCredentialsProvider> {

        @Override
        protected CognitoCachingCredentialsProvider doInBackground(Object... params) {

            // Initialize the Amazon Cognito credentials provider
            CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                    getBaseContext(), // Context
                    "us-east-1:70bb34f9-7d88-41ff-85ea-125d10231516", // Identity Pool ID
                    Regions.US_EAST_1 // Region
            );

            return credentialsProvider;
        }

        @Override
        protected void onPostExecute(CognitoCachingCredentialsProvider result) {

            arp = new AndroidRealtimePrediction("ml-Bhm53788P6A", result.getCredentials());
        }
    }
}
