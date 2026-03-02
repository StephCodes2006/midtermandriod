// Stephen Akanniolu n01725208
package stephen.midterm;
public class N01725208Fragment extends androidx.fragment.app.Fragment {

    @Override
    public android.view.View onCreateView(android.view.LayoutInflater inflater,
                                          android.view.ViewGroup container, android.os.Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_n01725208, container, false);

        android.widget.TextView tvDisplay = view.findViewById(R.id.steTVDisplay);
        android.widget.RatingBar ratingBar = view.findViewById(R.id.steRatingBar);
        android.widget.Button btnRating = view.findViewById(R.id.steBtnRating);

        // Retrieve the index saved in AkaFragment [cite: 110, 111]
        android.content.SharedPreferences pref = getActivity().getPreferences(android.content.Context.MODE_PRIVATE);
        int index = pref.getInt("selectedIndex", -1);

        if (index == -1) {
            tvDisplay.setText("NO DATA PASSED"); // Requirement #54c [cite: 117]
            tvDisplay.setTypeface(null, android.graphics.Typeface.ITALIC); [cite: 117]
        } else {
            String[] cities = getResources().getStringArray(R.array.stephen); [cite: 79]
            String[] countries = getResources().getStringArray(R.array.akanniolu); [cite: 81]

            // Requirement #54c: Display City, Country, and Index
            String result = "Capital: " + cities[index] +
                    ", Country: " + countries[index] +
                    ", Index: " + (index + 1); // Index 1 is first element [cite: 120]
            tvDisplay.setText(result);
        }

        // Requirement #54f: Show stars in a Snackbar [cite: 122]
        btnRating.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            com.google.android.material.snackbar.Snackbar.make(view,
                    "Stars selected: " + rating,
                    com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).show(); [cite: 123]
        });

        return view;
    }
}