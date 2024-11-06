package com.example.travel_app.Activity;  // Yeh line batati hai ke yeh file "Activity" folder ke andar hai, jo code ko organize karta hai.


// Zaroori classes ko import karte hain:
// Bundle - activity ke data ko save aur reload karne ke liye.
// Window aur WindowManager - UI layout aur settings ke liye.
// FirebaseApp aur FirebaseDatabase - Firebase ko initialize aur database ko access karne ke liye.
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;


/**
 * BaseActivity class "AppCompatActivity" se inherit karti hai, jo basic Android features aur lifecycle methods ko allow karta hai.
 * Is class ka use common settings ya Firebase configuration ko sabhi activities mein share karne ke liye kiya ja sakta hai.
 */
public class BaseActivity extends AppCompatActivity {

    // FirebaseDatabase ka instance, jo database access karne mein help karega.
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Activity ko shuru karte waqt super class ka onCreate method call ho raha hai.

        // Firebase ko initialize kar rahe hain, taake app mein Firebase ke features ka use ho sake.
        FirebaseApp.initializeApp(this);

        // Firebase Realtime Database ka instance le rahe hain, jo database ke data ko read aur write karne ke liye kaam aayega.
        database = FirebaseDatabase.getInstance();

        // Window ka instance le rahe hain, jo screen aur layout settings ko control karne mein madad karega.
        Window w = getWindow();

        // Yeh line screen ko fullscreen banati hai taake status bar aur navigation bar ke area mein bhi app ka layout dikhaye.
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Future mein agar:
        // - Sab activities mein fullscreen ya immersive experience dena ho toh yeh setup kaam aayega.
        // - Firebase ke aur services jese ke Authentication, Crashlytics ya Analytics ko add karna ho toh yeh BaseActivity helpful hai.
    }
}
