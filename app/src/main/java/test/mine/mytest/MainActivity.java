package test.mine.mytest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.hal.Jackson2HalModule;


import org.springframework.hateoas.hal.HalLinkDiscoverer;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import test.mine.mytest.R;
import test.mine.mytest.model.Place;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        */
    }

    @Override
    protected void onStart() {
        super.onStart();
        new HttpRequestTask().execute();
        /*try {
            traverse();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public Object traverse() throws URISyntaxException {
        String url = "http://203.151.92.199:8888";
        ParameterizedTypeReference<Resource<Place>> resourceParameterizedTypeReference =
                new ParameterizedTypeReference<Resource<Place>>() {
                };
        Traverson traverson = new Traverson(new URI(url), MediaTypes.HAL_JSON);
        Resource<Place> placeResource = traverson.follow("$._embedded.place[0]._links.self.href")
        .toObject(resourceParameterizedTypeReference);
        Toast toast =  Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_SHORT);
        toast.show();
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
          //new HttpRequestTask().execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }



    private class HttpRequestTask extends AsyncTask<Void,Void,Object> {
        @Override
        protected Object doInBackground(Void... params) {
            try{
                String url = "http://203.151.92.199:8888/place";
                Traverson traverson = new Traverson(new URI(url), MediaTypes.HAL_JSON);
                ParameterizedTypeReference<Resource<Place>> resourceParameterizedTypeReference = new
                        ParameterizedTypeReference<Resource<Place>>() {};
                // stuck at this

            }
            catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object obj) {
            //Something here
        }

        public void traverse() throws URISyntaxException {


        }
    }

}