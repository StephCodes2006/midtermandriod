// Stephen Akanniolu n01725208
package stephen.midterm;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.snackbar.Snackbar;

public class N01725208Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_n01725208, container, false);

        // Initialize UI components using your required 'ste' prefix
        TextView steTVDisplay = view.findViewById(R.id.steTVDisplay);
        RatingBar steRatingBar = view.findViewById(R.id.steRatingBar);
        Button steBtnRating = view.findViewById(R.id.steBtnRating);

        // Retrieve the index passed from AkaFragment via SharedPreferences
        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        int index = pref.getInt("selectedIndex", -1);

        if (index == -1) {
            // Requirement: If no data, set label to "NO DATA PASSED" in italic
            steTVDisplay.setText(R.string.no_data);
            steTVDisplay.setTypeface(null, Typeface.ITALIC);
        } else {
            // Requirement: If data exists, display City, Country, and Index
            String[] cities = getResources().getStringArray(R.array.stephen);
            String[] countries = getResources().getStringArray(R.array.akanniolu);

            String city = cities[index];
            String country = countries[index];
            int displayIndex = index + 1; // Requirement: Index 1 indicates 1st element

            String resultText = "Capital: " + city + ", Country: " + country + ", Index: " + displayIndex;
            steTVDisplay.setText(resultText);
            steTVDisplay.setTypeface(null, Typeface.NORMAL);
        }

        // Requirement: Rating bar logic and Snackbar display
        steBtnRating.setOnClickListener(v -> {
            float rating = steRatingBar.getRating();
            Snackbar.make(view, "Stars selected: " + rating, Snackbar.LENGTH_SHORT).show();
        });

        return view;
    }
}