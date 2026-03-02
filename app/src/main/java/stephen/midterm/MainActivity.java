// Stephen Akanniolu n01725208
package stephen.midterm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.steTabLayout);
        ViewPager2 viewPager = findViewById(R.id.steViewPager);

        // Adapter to manage the 3 fragments
        viewPager.setAdapter(new androidx.viewpager2.adapter.FragmentStateAdapter(this) {
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

        // Link Tabs and ViewPager
        new com.google.android.material.tabs.TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) tab.setText("STEPHEN");
            else if (position == 1) tab.setText("AKANNIOLU");
            else tab.setText("N01725208");
        }).attach();
    }
}