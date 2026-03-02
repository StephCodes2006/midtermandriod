// Stephen Akanniolu n01725208
package stephen.midterm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 steViewPager; // Using 'ste' prefix as required

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.steTabLayout);
        steViewPager = findViewById(R.id.steViewPager);

        // 1. Adapter to manage the 3 fragments
        steViewPager.setAdapter(new androidx.viewpager2.adapter.FragmentStateAdapter(this) {
            @NonNull
            @Override
            public androidx.fragment.app.Fragment createFragment(int position) {
                switch (position) {
                    case 0: return new SteFragment();
                    case 1: return new AkaFragment();
                    default: return new N01725208Fragment();
                }
            }
            @Override
            public int getItemCount() { return 3; }
        });

        // 2. Link Tabs and ViewPager
        new TabLayoutMediator(tabLayout, steViewPager, (tab, position) -> {
            if (position == 0) tab.setText("STEPHEN");
            else if (position == 1) tab.setText("AKANNIOLU");
            else tab.setText("N01725208");
        }).attach();

        // 3. FIX: Modern Back Key Logic (Replaces the red onBackPressed)
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Stephen Akanniolu")
                        .setMessage("Do you want to leave the app")
                        .setCancelable(false)
                        .setPositiveButton("Yes", (dialog, which) -> {
                            // Opens Android Settings on 'Yes'
                            startActivity(new Intent(Settings.ACTION_SETTINGS));
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            // Moves to the 3rd tab on 'No'
                            steViewPager.setCurrentItem(2);
                        })
                        .show();
            }
        });
    }

    // 4. Menu Logic for Google Maps
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ste_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.steMap) {
            checkLocationPermission();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 5. Permission & Map Logic
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Requirement #58: Request Permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
        } else {
            launchGoogleMaps();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchGoogleMaps();
            } else {
                // Requirement #59: Indefinite Snackbar if denied
                Snackbar.make(findViewById(android.R.id.content),
                                "Stephen Akanniolu Access Denied",
                                Snackbar.LENGTH_INDEFINITE)
                        .setAction("Dismiss", v -> {}).show();
            }
        }
    }

    private void launchGoogleMaps() {
        // Requirement #57: Launch Maps with Coordinates
        double lat = 43.7285;
        double lon = -79.6078;

        Uri gmmIntentUri = Uri.parse("geo:" + lat + "," + lon + "?z=15");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}