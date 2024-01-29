package ba.sum.fpmoz.estore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Objects;

import okhttp3.Response;


public class Search extends Fragment {


    Button button_submit;
    ProgressBar progress_bar;
    TextView vin;
    TextView manufacturer;
    TextView make;

    TextView manufactured_in;

    TextView model_year;

    @Override
    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment
        button_submit = view.findViewById(R.id.search_submit);
        progress_bar = view.findViewById(R.id.progress_bar);
        vin = view.findViewById(R.id.vin);
        make = view.findViewById(R.id.make);
        manufacturer = view.findViewById(R.id.manufacturer);
        manufactured_in = view.findViewById(R.id.manufactured_in);
        model_year = view.findViewById(R.id.model_year);
        // create submit on click listener
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get search_text from fragment_search.xml
                EditText search_text = Objects.requireNonNull(requireView().findViewById(R.id.search_input));
                String search_value = search_text.getText().toString();


                new GetWebsiteData().getData(
                        search_value
                        , button_submit, progress_bar, vin, make, manufacturer, manufactured_in, model_year);
            }
        });
        return view;
    }


}