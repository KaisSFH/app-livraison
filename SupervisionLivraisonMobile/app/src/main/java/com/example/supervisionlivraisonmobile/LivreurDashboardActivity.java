package com.example.supervisionlivraisonmobile;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LivreurDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livreur_dashboard);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // Charger le fragment Tournée par défaut
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new TourneeFragment())
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.nav_tournee) {
                selectedFragment = new TourneeFragment();
            } else if (id == R.id.nav_historique) {
                selectedFragment = new HistoriqueFragment();
            } else if (id == R.id.nav_messages) {
                // selectedFragment = new ChatFragment(); // Keep existing if available or use a generic one
            } else if (id == R.id.nav_profil) {
                selectedFragment = new ProfilFragment();
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
