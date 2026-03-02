// Stephen Akanniolu n01725208
public class SteFragment extends androidx.fragment.app.Fragment {
    private int steCounter = 0;

    @Override
    public android.view.View onCreateView(android.view.LayoutInflater inflater,
                                          android.view.ViewGroup container, android.os.Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ste, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        steCounter++; // Requirement #52g [cite: 92]
        android.widget.Toast.makeText(getContext(),
                "Stephen Akanniolu, Count: " + steCounter,
                android.widget.Toast.LENGTH_SHORT).show(); // [cite: 93]
    }
}