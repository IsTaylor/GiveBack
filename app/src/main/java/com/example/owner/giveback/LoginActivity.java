package com.example.owner.giveback;

import android.app.ProgressDialog;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;


import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email)
    AutoCompleteTextView mEmailView;
    @BindView(R.id.password)
    EditText mPasswordView;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    private View mLoginFormView;
    private View mProgressView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        firebaseAuth = FirebaseAuth.getInstance();

        Button mEmailSignUpButton = (Button) findViewById(R.id.email_sign_up_button);
        Button mEmailLoginButton = (Button) findViewById(R.id.email_login_button);

        mEmailSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignUp();
            }
        });

        mEmailLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

    }


    private void attemptSignUp() {
        // Reset errors
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String userEmail = mEmailView.getText().toString();
        String userPassword = mPasswordView.getText().toString();

        if (!isFormValid(userEmail, userPassword)){
            return;

        } else {
            showProgress(true);
            addToFirebase(userEmail, userPassword);
        }

    }

    private void attemptLogin() {
        // Reset errors
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String userEmail = mEmailView.getText().toString();
        String userPassword = mPasswordView.getText().toString();

        if (!isFormValid(userEmail, userPassword)){
            return;

        } else {
            showProgress(true);
            loginFirebase(userEmail, userPassword);
        }

    }

    private void loginFirebase(String userEmail, String userPassword){
        firebaseAuth.signInWithEmailAndPassword(
                userEmail,
                userPassword
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                showProgress(false);

                if (task.isSuccessful()) {
                    //TODO start new intent
                   /* startActivity(new Intent(LoginActivity.this,
                            PostsActivity.class));
                            */

                } else {
                    Toast.makeText(LoginActivity.this,
                            "Error: "+task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this,
                        "Error: " + e.getMessage(),
                        Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }



    private void addToFirebase(String userEmail, String userPassword){
        firebaseAuth.createUserWithEmailAndPassword(userEmail,
                userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                showProgress(false);
                if (task.isSuccessful()) {
                    FirebaseUser fbUser = task.getResult().getUser();
                    fbUser.updateProfile(new UserProfileChangeRequest.Builder().
                            setDisplayName(usernameFromEmail(fbUser.getEmail())).build());

                    //TODO start new intent
                    /*startActivity(new Intent(LoginActivity.this,
                            PostsActivity.class));
                            */
                    Toast.makeText(LoginActivity.this, "Registration ok", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Error: " + task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private boolean isFormValid(String userEmail, String userPassword) {
        boolean cancel = false;
        View focusView = null;

        //if emil address isn't blank and has a valid address
        if (TextUtils.isEmpty(mEmailView.getText())) {
            mEmailView.setError("Email cannot be empty");
            cancel = true;
            focusView = mEmailView;
        } else if(!isEmailValid(userEmail)){
            mEmailView.setError("Email must be valid");
            cancel = true;
            focusView = mEmailView;
        }

        //if password isn't blank and has a valid address
        if (TextUtils.isEmpty(mPasswordView.getText())) {
            mPasswordView.setError("Password cannot be empty");
            cancel = true;
            focusView = mPasswordView;
        } else if(!isPasswordValid(userPassword)){
            mPasswordView.setError("Password must be longer than 6 characters");
            cancel = true;
            focusView = mPasswordView;

        }

        if (cancel){
            //error in user input, change focus
            focusView.requestFocus();
        }

        return(!cancel);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }



}
