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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            new HttpRequestTask().execute();
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



    private class HttpRequestTask extends AsyncTask<Void, Void, Object> {
        @Override
        protected Object doInBackground(Void... params) {
            try{
                String url = "http://203.151.92.199:8888/place";

                ObjectMapper mapper = new ObjectMapper();

                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        mapper.registerModule(new Jackson2HalModule());

                        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
                        converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
                        converter.setObjectMapper(mapper);

                RestTemplate template = new RestTemplate(Collections.<HttpMessageConverter<?>> singletonList(converter));
                // Create a new RestTemplate instance

                
                return places;
            }
            catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object obj) {
            //if(obj.getClass()==Place.class) {
            //JSONObject jsonObject = (JSONObject) obj;
            //ObjContainer objs = (ObjContainer)obj;
            Place[] places = (Place[]) obj;
            //TextView greetingIdText = (TextView) findViewById(R.id.id_value);
            //TextView greetingContentText = (TextView) findViewById(R.id.content_value);
            //System.out.println("?|?|?| "+obj.toString());
            //greetingIdText.setText(places[0].getName());
            // greetingContentText.setText(place.getName());
            //}
        }

    }

}