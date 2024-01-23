package ba.sum.fpmoz.estore;



import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.*;

public class GetWebsiteData {


    ProgressBar progress_bar;
    Button submit_button;
    TextView most_expensive;
    TextView least_expensive;
    TextView average;

    private final OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();


    public void getData(String filters, Button sb, ProgressBar pb, TextView me, TextView le, TextView avg) {
        Log.d("RESPONSE", "getData: ");
        submit_button = sb;
        progress_bar = pb;
        most_expensive = me;
        least_expensive = le;
        average = avg;
        submit_button.setEnabled(false);
        progress_bar.setVisibility(ProgressBar.VISIBLE);
        String url = "https://scraper.mojshop.net/scrape";
        String requestBody = "{\"url\":\"https://olx.ba/pretraga?q=" + filters + "\"}";
        // Create the request body
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody);
        // create get request
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        // Make the asynchronous POST request
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle the error
                Log.d("RESPONSE", "onFailure: ");
                Log.e("RESPONSE", e.getMessage());
                Log.e("RESPONSE", e.toString());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        // Update UI on the main thread
                        // Example: change a view's visibility
                        // get progress_bar from fragment_search.xml
                        progress_bar.setVisibility(ProgressBar.INVISIBLE);
                        submit_button.setEnabled(true);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("RESPONSE", "onResponse: ");
                String responseBody = response.body().string();
                Log.d("RESPONSE", responseBody);
                // Handle the response
                if (!isValidJson(responseBody) || !response.isSuccessful()) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            // Update UI on the main thread
                            // Get progress_bar from fragment_search.xml
                            progress_bar.setVisibility(ProgressBar.INVISIBLE);
                            submit_button.setEnabled(true);
                        }
                    });

                    throw new IOException("Unexpected code " + response);
                };
                Gson gson = new Gson();
                OlxResponse olxResponse = gson.fromJson(responseBody, OlxResponse.class);
                Log.d("RESPONSE", "onResponse: ");
                // Do something with the response
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        // Update UI on the main thread
                        // Get progress_bar from fragment_search.xml
                        progress_bar.setVisibility(ProgressBar.INVISIBLE);
                        submit_button.setEnabled(true);
                        most_expensive.setText(olxResponse.getMostExpensive());
                        least_expensive.setText(olxResponse.getLeastExpensive());
                        average.setText(olxResponse.getAverage());
                    }
                });

                response.body().close();

            }
        });
    }

    private boolean isValidJson(String jsonString) {
        try {
            // use static method instead from gson
            JsonParser.parseString(jsonString);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }

}
