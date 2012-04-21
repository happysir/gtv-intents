package com.google.android.apps.tvremote;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class IntentActivity extends BaseActivity implements OnClickListener {

    private static final String LOG_TAG = "IntentActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_activity);
        findViewById(R.id.intent_button).setOnClickListener(this);
    }

    private void flingIntent(Intent intent) {
        if (intent != null) {
            if (Intent.ACTION_SEND.equals(intent.getAction())) {
                String text = intent.getStringExtra(Intent.EXTRA_TEXT);
                if (text != null) {
                    Uri uri = Uri.parse(text);
                    if (uri != null) {
                        getCommands().flingUrl(text);
                    } else {
                        Toast.makeText(this, R.string.error_could_not_send_url, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.w(LOG_TAG, "No URI to fling");
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        String myUri = "musicbrainz:///release/98c0716c-42eb-4b7f-8246-94924c1981ad";
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, myUri);
        flingIntent(intent);
    }

}
