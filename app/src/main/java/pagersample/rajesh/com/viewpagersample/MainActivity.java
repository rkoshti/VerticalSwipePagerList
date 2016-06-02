package pagersample.rajesh.com.viewpagersample;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PagerAdapter adapter;
    private VerticalViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeData();
    }

    private void initializeData() {

        viewPager = (VerticalViewPager) findViewById(R.id.pager);

        getData();
    }

    private void getData() {

        Ion.with(this)
                .load("http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error

                        DashboardListResponse obj = new Gson().fromJson(result, DashboardListResponse.class);

                        ArrayList<DashboardResponse> list = obj.getActors();

                        adapter = new PagerAdapter(MainActivity.this,
                                list);

                        viewPager.setAdapter(adapter);

                    }
                });


    }

}
