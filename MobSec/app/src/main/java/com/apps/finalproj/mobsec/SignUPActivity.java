package com.apps.finalproj.mobsec;

/**
 * Created by Poornima on 10/21/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignUPActivity extends Activity {
    EditText editTextPassword, editTextConfirmPassword, editTextEmergencyContact, editTextLocation, editTextOldPass;
    TextView editTextUserName, email;
    Spinner instApps;
    Button btnCreateAccount;
    private static final String TAG = "SignUPActivity";

    LoginDataBaseAdapter loginDataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        // get Instance  of Database Adapter
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        // Get Refferences of Views
        instApps = (Spinner) findViewById(R.id.installedApps);
        editTextUserName = (TextView) findViewById(R.id.editTextUserName);

        email = (TextView) findViewById(R.id.userEmail);
        Intent intnt = getIntent();
        // Get user name and email from extras passed from GoogleLoginActivity and update UI
        final String userName = intnt.getStringExtra(GoogleLoginActivity.ACCOUNTNAME);
        final String emailid = intnt.getStringExtra(GoogleLoginActivity.EMAIL);
        editTextUserName.setText(userName);
        email.setText(emailid);

        editTextEmergencyContact = (EditText) findViewById(R.id.editTextEmergencyContact);
        editTextLocation = (EditText) findViewById(R.id.Location);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        editTextOldPass = (EditText) findViewById(R.id.editTextOldPassword);
        btnCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
        final UserDBObj user = loginDataBaseAdapter.getSinlgeEntry(emailid);
        // update if user already present if not insert
        final boolean update = user != null;
        if (update) {
            editTextEmergencyContact.setText(user.emergContact);
            editTextLocation.setText(user.location);
            editTextOldPass.setVisibility(View.VISIBLE);
        } else {
            editTextOldPass.setVisibility(View.INVISIBLE);
        }
        btnCreateAccount.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        String emergenCon = editTextEmergencyContact.getText().toString();
                        String location = editTextLocation.getText().toString();
                        String password = editTextPassword.getText().toString();
                        String oldPassword = editTextOldPass.getText().toString();
                        String confirmPassword = editTextConfirmPassword.getText().toString();
                        boolean resetPass = false;
                        if (update && !oldPassword.isEmpty()) {
                            if (!user.password.equals(oldPassword)) {
                                Toast.makeText(getApplicationContext(), "Incorrect old password", Toast.LENGTH_LONG).show();
                                return;
                            }
                            resetPass = true;
                        }
                        if (!update || resetPass) {
                            // check if any of the fields are vaccant
                            if (password.equals("") || confirmPassword.equals("")) {
                                Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                                return;
                            }
                            // check if both password matches
                            if (!password.equals(confirmPassword)) {
                                Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                        if (!update) {
                            // Save the Data in Database
                            loginDataBaseAdapter.insertEntry(new UserDBObj(userName, password, emergenCon, location, emailid));
                            Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                        } else {
                            if (resetPass) {
                                loginDataBaseAdapter.updateEntry(new UserDBObj(userName, password, emergenCon, location, emailid), true);
                                Toast.makeText(getApplicationContext(), "Account Successfully Updated ", Toast.LENGTH_LONG).show();
                            } else {
                                loginDataBaseAdapter.updateEntry(new UserDBObj(userName, password, emergenCon, location, emailid), false);
                                Toast.makeText(getApplicationContext(), "Account Successfully Updated ", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }

        );

        getPackages(instApps);
    }

    class PInfo {
        private String appname = "";
        private Drawable icon;

        @Override
        public String toString() {
            return appname;
        }

    }

    private void getPackages(Spinner spinner2) {
        ArrayList<PInfo> apps = getInstalledApps(false); /* false = no system packages */
        ArrayAdapter<PInfo> dataAdapter = new ArrayAdapter<PInfo>(this,
                android.R.layout.simple_spinner_dropdown_item, apps);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    private ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
        ArrayList<PInfo> res = new ArrayList<PInfo>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                continue;
            }
            PInfo newInfo = new PInfo();
            newInfo.appname = p.applicationInfo.loadLabel(getPackageManager()).toString();
            newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
            res.add(newInfo);
        }
        return res;
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}