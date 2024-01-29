package ba.sum.fpmoz.estore;



import android.annotation.SuppressLint;
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
    TextView vin;

    TextView make;

    TextView manufacturer;

    TextView manufactured_in;

    TextView model_year;

    private final OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();


    public void getData(String vin_number,Button sb, ProgressBar pb, TextView Avin, TextView Amake, TextView Amanufacturer, TextView Amanufactured_in, TextView Amodel_year) {
        Log.d("RESPONSE", "getData: ");
        submit_button = sb;
        progress_bar = pb;
        this.vin = Avin;
        this.make = Amake;
        this.manufacturer = Amanufacturer;
        this.manufactured_in = Amanufactured_in;
        this.model_year = Amodel_year;
        submit_button.setEnabled(false);
        progress_bar.setVisibility(ProgressBar.VISIBLE);
        String url = "https://vin-decoder-api-europe.p.rapidapi.com/vin?vin=" + vin_number;
        // create get request
        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-RapidAPI-Key", "7cfcf9a02emshf2bb227dbff7966p10630cjsnec10b86140de")
                .addHeader("X-RapidAPI-Host", "vin-decoder-api-europe.p.rapidapi.com")
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
                VinResponse vinResponse = gson.fromJson(responseBody, VinResponse.class);
                Log.d("RESPONSE", "onResponse: ");
                // Do something with the response
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        // Update UI on the main thread
                        // Get progress_bar from fragment_search.xml
                        progress_bar.setVisibility(ProgressBar.INVISIBLE);
                        submit_button.setEnabled(true);
                        vin.setText("VIN broj: "  + vinResponse.getVin());
                        make.setText("Marka: " + vinResponse.getMake());
                        manufacturer.setText("Proizvođač: " + vinResponse.getManufacturer());
                        manufactured_in.setText("Proizvedeno: " + vinResponse.getManufacturedIn());
                        model_year.setText("Godina proizvodnje: " + vinResponse.getModelYear());

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
