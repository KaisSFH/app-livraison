package com.example.supervisionlivraisonmobile;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ControleurDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controleur_dashboard);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        
        // Charger le premier fragment par défaut
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LivraisonFragment())
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.nav_livraison) {
                selectedFragment = new LivraisonFragment();
            } else if (id == R.id.nav_dashboard) {
                // selectedFragment = new DashboardFragment();
            } else if (id == R.id.nav_localiser) {
                // selectedFragment = new LocaliserFragment();
            } else if (id == R.id.nav_messages) {
                // selectedFragment = new MessagesFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                return true;
            }
            return false;
        });
    }
}
