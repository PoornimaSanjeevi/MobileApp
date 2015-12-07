package com.apps.finalproj.mobsec;

/**
 * Created by Poornima on 10/21/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PromptSecActivity extends Activity {
    private static final String TAG = "PromptSecActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Activity act = this;
        super.onCreate(savedInstanceState);
        PromptSecCallback cb = new PromptSecCallback() {
            @Override
            public void call(boolean res) {
                if (res) {
                    Toast.makeText(PromptSecActivity.this, "Authenticated!", Toast.LENGTH_SHORT).show();
                    act.finish();
                    act.onBackPressed();
                } else {
                    Toast.makeText(PromptSecActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                }
            }
        };
        Intent intnt = getIntent();
        final String emailid = intnt.getStringExtra(GoogleLoginActivity.EMAIL);
        PromptSecKey ps = new PromptSecKey(emailid, this, cb);
        ps.authenticate();
    }
}