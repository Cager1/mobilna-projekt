package ba.sum.fpmoz.estore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Settings extends Fragment {
    private FirebaseAuth auth;
    EditText editTextOld;
    EditText editTextNew;
    EditText editTextRepeat;
    Button btnChange;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        editTextOld = view.findViewById(R.id.editTextOld);
        editTextNew = view.findViewById(R.id.editTextNew);
        editTextRepeat = view.findViewById(R.id.editTextRepeat);
        btnChange = view.findViewById(R.id.changePasswordBtn);

        auth = FirebaseAuth.getInstance();

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

        return view;
    }

    private void changePassword(){
        String oldPassword = editTextOld.getText().toString().trim();
        String newPassword = editTextNew.getText().toString().trim();
        String repeatPassword = editTextRepeat.getText().toString().trim();

        if (TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(repeatPassword)) {
            // Handle empty fields
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(repeatPassword)) {
            // Handle password mismatch
            Toast.makeText(getContext(), "New passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), oldPassword);

            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Password changed successfully
                                    Toast.makeText(getContext(), "Password changed successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Handle password change failure
                                    Toast.makeText(getContext(), "Failed to change password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        // Handle reauthentication failure
                        Toast.makeText(getContext(), "Reauthentication failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            // User is not logged in
            Toast.makeText(getContext(), "User not logged in", Toast.LENGTH_SHORT).show();
        }
    }
}