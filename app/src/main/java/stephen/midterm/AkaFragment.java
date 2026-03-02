// Stephen Akanniolu n01725208
package stephen.midterm;
public class AkaFragment extends androidx.fragment.app.Fragment {

    @Override
    public android.view.View onCreateView(android.view.LayoutInflater inflater,
                                          android.view.ViewGroup container, android.os.Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_aka, container, false);

        android.widget.AutoCompleteTextView autoComplete = view.findViewById(R.id.steAC);
        android.widget.Button btn = view.findViewById(R.id.steBtnSubmit);

        // Load the cities from strings.xml [cite: 97]
        String[] cities = getResources().getStringArray(R.array.stephen);
        android.widget.ArrayAdapter<String> adapter = new android.widget.ArrayAdapter<>(
                getContext(), android.R.layout.simple_list_item_1, cities);
        autoComplete.setAdapter(adapter);

        btn.setOnClickListener(v -> {
            String input = autoComplete.getText().toString().trim();

            // 1. Check if empty [cite: 105]
            if (input.isEmpty()) {
                autoComplete.setError("Can not be empty");
                return;
            }

            // 2. Validate against array [cite: 107, 108]
            int index = -1;
            for (int i = 0; i < cities.length; i++) {
                if (cities[i].equalsIgnoreCase(input)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                autoComplete.setError("Stephen Akanniolu, " + input + " is not a valid capital");
            } else {
                // 3. Valid input: Pass index via ViewModel or Shared Preferences [cite: 110]
                // For now, let's use a simple shared preference to communicate between fragments
                android.content.SharedPreferences pref = getActivity().getPreferences(android.content.Context.MODE_PRIVATE);
                pref.edit().putInt("selectedIndex", index).apply();

                autoComplete.setText(""); // Clear input [cite: 112]
                android.widget.Toast.makeText(getContext(), "Data Saved", android.widget.Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}