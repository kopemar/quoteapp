package com.example.quoteforthisday;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class IndividualActivity extends AppCompatActivity {
    final static String QUOTE_OF_THE_DAY_HASHTAG = " #QuoteForToday";
    final static String WHO_GENERATED_IT = " Generated by the world's worst quote app.";
    String extraText;
    String author;
    String shareAuthor;
    String cat;

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getActionBar() != null) getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual);
        TextView showQuote = findViewById(R.id.tv_individual_activity);
        TextView showAuthor = findViewById(R.id.tv_author);
        TextView showCat = findViewById(R.id.tv_cat);
        final String dash = "– ";
        Intent intentThatStartedThis = getIntent();

        if (intentThatStartedThis.hasExtra("AUTHOR")) {
            author = intentThatStartedThis.getStringExtra("AUTHOR");
            String myAuthor = dash + author;
            showAuthor.setText(myAuthor);
        }
        if (intentThatStartedThis.hasExtra("QUOTE")) {
            extraText = intentThatStartedThis.getStringExtra("QUOTE");
            showQuote.setText(extraText);
        }
        if (intentThatStartedThis.hasExtra("CAT")) {
            cat = intentThatStartedThis.getStringExtra("CAT");
            showCat.setText(cat);
        }
    }

    private Intent shareQuote() {
        shareAuthor = " (by " + author + ")";
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setChooserTitle("Sharing options")
                .setText(extraText + shareAuthor + QUOTE_OF_THE_DAY_HASHTAG)
                .getIntent();
        return shareIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_individual, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(shareQuote());
        return true;
    }
}