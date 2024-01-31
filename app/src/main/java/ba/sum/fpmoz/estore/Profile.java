package ba.sum.fpmoz.estore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends Fragment {


    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Get the TextView where you want to display the email
        TextView emailTextView = view.findViewById(R.id.textViewEmail);

        // Fetch the current user from Firebase
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            // User is signed in, update the UI with the user's email
            String userEmail = currentUser.getEmail();
            emailTextView.setText(userEmail);
        } else {
            // User is not signed in, handle this case if needed
            emailTextView.setText("Not signed in");
        }

        return view;
    }
}
