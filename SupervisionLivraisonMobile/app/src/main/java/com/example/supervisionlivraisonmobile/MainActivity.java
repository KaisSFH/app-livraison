package com.example.supervisionlivraisonmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.supervisionlivraisonmobile.model.LoginRequest;
import com.example.supervisionlivraisonmobile.model.LoginResponse;
import com.example.supervisionlivraisonmobile.network.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etLogin, etPassword;
    private Button btnLogin;
    private ProgressBar progressBar;
    private TextView tvRegister, tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);
        tvRegister = findViewById(R.id.tvRegister);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        btnLogin.setOnClickListener(v -> handleLogin());

        tvRegister.setOnClickListener(v ->
            startActivity(new Intent(MainActivity.this, RegisterActivity.class))
        );

        tvForgotPassword.setOnClickListener(v ->
            startActivity(new Intent(MainActivity.this, ForgotPasswordActivity.class))
        );
    }

    private void handleLogin() {
        String login = etLogin.getText() != null ? etLogin.getText().toString().trim() : "";
        String password = etPassword.getText() != null ? etPassword.getText().toString().trim() : "";

        if (login.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // --- COMPTE DE TEST SANS BACKEND ---
        if (login.equals("admin") && password.equals("admin")) {
            Intent intent = new Intent(MainActivity.this, ControleurDashboardActivity.class);
            intent.putExtra("nom", "Admin");
            intent.putExtra("role", "Controleur");
            startActivity(intent);
            finish();
            return;
        }
        // ------------------------------------

        progressBar.setVisibility(View.VISIBLE);
        btnLogin.setEnabled(false);

        LoginRequest request = new LoginRequest(login, password);
        RetrofitClient.getApiService().login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressBar.setVisibility(View.GONE);
                btnLogin.setEnabled(true);

                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.isSuccess()) {
                        String role = loginResponse.getNomposte();
                        Toast.makeText(MainActivity.this, "Bienvenue " + loginResponse.getNom(), Toast.LENGTH_SHORT).show();
                        // Redirection selon le rôle
                        Intent intent;
                        if (role != null && role.equalsIgnoreCase("Controleur")) {
                            intent = new Intent(MainActivity.this, ControleurDashboardActivity.class);
                        } else {
                            intent = new Intent(MainActivity.this, LivreurDashboardActivity.class);
                        }
                        intent.putExtra("nom", loginResponse.getNom());
                        intent.putExtra("prenom", loginResponse.getPrenom());
                        intent.putExtra("idpers", loginResponse.getIdpers());
                        intent.putExtra("role", role);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Identifiants incorrects", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Erreur: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                btnLogin.setEnabled(true);
                Toast.makeText(MainActivity.this, "Erreur réseau: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}