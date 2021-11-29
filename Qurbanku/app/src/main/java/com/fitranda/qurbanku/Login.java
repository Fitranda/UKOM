package com.fitranda.qurbanku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.lib.recaptcha.ReCaptcha;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fitranda.qurbanku.Helper.API;
import com.fitranda.qurbanku.Model.PelM;
import com.fitranda.qurbanku.Model.PelangganM;
import com.fitranda.qurbanku.databinding.ActivityLoginBinding;
import com.fitranda.qurbanku.databinding.FragmentDashboardBinding;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.jakewharton.processphoenix.ProcessPhoenix;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hivatec.ir.easywebservice.Callback;
import hivatec.ir.easywebservice.EasyWebservice;
import hivatec.ir.easywebservice.Method;
import retrofit2.Call;
import retrofit2.Response;

public class Login extends AppCompatActivity   {
    ActivityLoginBinding binding;
    FragmentDashboardBinding bind;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    String SiteKey = "6LeMa2AdAAAAAJewSEOEn6SO_WjeScwYgMb7o0Ms";
    String SecretKey = "6LeMa2AdAAAAAPgiz-tgH3dgmQwolLXOY5O81j_F";
    RequestQueue queue;
    public String userResponseToken;

    private void verifyGoogleReCAPTCHA() {

        // below line is use for getting our safety
        // net client and verify with reCAPTCHA
        SafetyNet.getClient(this).verifyWithRecaptcha(SiteKey)
                // after getting our client we have
                // to add on success listener.
                .addOnSuccessListener(this, new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                        // in below line we are checking the response token.
                        if (!response.getTokenResult().isEmpty()) {
                            // if the response token is not empty then we
                            // are calling our verification method.
                            handleVerification(response.getTokenResult());
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // this method is called when we get any error.
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            // below line is use to display an error message which we get.
                            Log.d("TAG", "Error message: " +
                                    CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()));
                        } else {
                            // below line is use to display a toast message for any error.
                            Toast.makeText(Login.this, "Error found is : " + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

//    private ReCaptcha   reCaptcha;
//    private EditText answer;
//
//    private static final String PUBLIC_KEY  = "6LcPWugSAAAAAC-MP5sg6wp_CQiyxHvPvkQvVlVf";
//    private static final String PRIVATE_KEY = "6LcPWugSAAAAALWMp-gg9QkykQQyO6ePBSUk-Hjg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String TAG = "Logins";
        SafetyNetClient myCLient = SafetyNet.getClient(Login.this);

        binding.areYouHumanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SafetyNet.getClient(Login.this).verifyWithRecaptcha(SiteKey)
                        .addOnSuccessListener(Login.this,
                                new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                                    @Override
                                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                                        // Indicates communication with reCAPTCHA service was
                                        // successful.
                                        String userResponseToken = response.getTokenResult();
                                        if (!userResponseToken.isEmpty()) {
                                            // Validate the user response token using the
                                            // reCAPTCHA siteverify API.
//                                            handleVerification(userResponseToken);
                                            Log.d("token",userResponseToken);
                                            new EasyWebservice("https://www.google.com/recaptcha/api/siteverify")
                                                    .method(Method.POST)
                                                    .addParam("secret",SecretKey)
                                                    .addParam("response",userResponseToken)
                                                    .call(new Callback.A<Boolean>("success") {
                                                        @Override
                                                        public void onSuccess(Boolean aBoolean) {
                                                            binding.cirloginbutton.setVisibility(View.VISIBLE);
                                                            if (aBoolean){
                                                                Toast.makeText(Login.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }

                                                        @Override
                                                        public void onError(String s) {
                                                            Toast.makeText(Login.this, s, Toast.LENGTH_SHORT).show();
                                                            binding.cirloginbutton.setVisibility(View.GONE);
                                                        }
                                                    });
                                        }
                                    }
                                })
                        .addOnFailureListener(Login.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                binding.cirloginbutton.setVisibility(View.GONE);
                                if (e instanceof ApiException) {
                                    // An error occurred when communicating with the
                                    // reCAPTCHA service. Refer to the status code to
                                    // handle the error appropriately.
                                    ApiException apiException = (ApiException) e;
                                    int statusCode = apiException.getStatusCode();
                                    Log.d(TAG, "Error: " + CommonStatusCodes
                                            .getStatusCodeString(statusCode));
                                } else {
                                    // A different, unknown type of error occurred.
                                    Log.d(TAG, "Error: " + e.getMessage());
                                }
                            }
                        });
            }
        });

        binding.btnLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,ForgetPassword.class));
            }
        });


        binding.cirloginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = "";
                String password = "";

                if (!binding.txtEmail.getText().toString().matches("") && !binding.txtPassword.getText().toString().matches("")){
                    String emails = binding.txtEmail.getText().toString();
                    password = binding.txtPassword.getText().toString();
                    email = emails.replace('.',',');

                    String regex = "^(.+)@(.+)$";
                    Pattern pattern = Pattern.compile(regex);

                    Matcher matcher = pattern.matcher(emails);

                    if (matcher.matches()){
                        ProgressDialog dialog = new ProgressDialog(Login.this);
                        dialog.setMessage("Loading...");
                        dialog.setCancelable(false);
                        dialog.show();

                        new EasyWebservice(API.url + "loginpelanggan")
                                .method(Method.POST)
                                .addParam("email",emails)
                                .addParam("password",password)
                                .call(new Callback.AB<String, PelM>("pesan","data") {
                                    @Override
                                    public void onSuccess(String s, PelM pelM) {
                                        if (pelM == null){
                                            Toast.makeText(Login.this, s, Toast.LENGTH_SHORT).show();
                                            dialog.cancel();
                                        }else {
                                            dialog.cancel();
                                            pref = getSharedPreferences(session,MODE_PRIVATE);
                                            editor = pref.edit();
                                            editor.putBoolean("logins",true);
                                            Log.d("tes",pelM.getIdpelanggan().toString());
                                            editor.putInt("idpelanggan",pelM.getIdpelanggan().intValue());
                                            editor.putString("email",pelM.getEmail().toString());
                                            editor.putString("telp",pelM.getTelp().toString());
                                            editor.putString("gambar",pelM.getGambar().toString());
                                            editor.putString("pelanggan",pelM.getPelanggan().toString());
                                            editor.apply();
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onError(String s) {
                                        dialog.cancel();
                                        Toast.makeText(Login.this, "Password harus minimal 8 huruf dan kombinasi", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }else {
                        Toast.makeText(Login.this, "Email harus Valid", Toast.LENGTH_SHORT).show();
                    }



//                    Call<PelangganM> call = API.getService().getloginp(email,password);
//                    Log.d("url",call.request().url().toString());
//                    call.enqueue(new Callback<PelangganM>() {
//                        @Override
//                        public void onResponse(Call<PelangganM> call, Response<PelangganM> response) {
//                            if (response.isSuccessful()){
////                                Toast.makeText(Login.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
//                                dialog.cancel();
//                                pref = getSharedPreferences(session,MODE_PRIVATE);
//                                editor = pref.edit();
//                                editor.putBoolean("logins",true);
//                                editor.putInt("idpelanggan",response.body().getIdpelanggan().intValue());
//                                editor.putString("email",response.body().getEmail().toString());
//                                editor.putString("telp",response.body().getTelp().toString());
//                                editor.putString("gambar",response.body().getGambar().toString());
//                                editor.putString("pelanggan",response.body().getPelanggan().toString());
//                                editor.apply();
//
//                                bind = FragmentDashboardBinding.inflate(getLayoutInflater());
//                                Intent i = new Intent();
//                                setResult(RESULT_OK, i);
//                                finish();
//                            }else{
//                                Log.d("aaaaaaaaaaa",response.message());
//                                dialog.cancel();
//                            }
////                            Toast.makeText(Login.this, "berhasil", Toast.LENGTH_SHORT).show();
//                            dialog.cancel();
//                        }
//
//                        @Override
//                        public void onFailure(Call<PelangganM> call, Throwable t) {
//                            Log.d("error",t.getMessage());
//                            Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                            dialog.cancel();
//                        }
//                    });


                }else{
                    Toast.makeText(Login.this, "isi semua", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public void daftar(View view) {
        startActivity(new Intent(this, Register.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Toast.makeText(Login.this, "Back", Toast.LENGTH_SHORT).show();
        ProcessPhoenix.triggerRebirth(Login.this);
    }
//
//    @Override
//    public void onChallengeShown(boolean shown) {
//        if (shown) {
//            // If a CAPTCHA is shown successfully, displays it for the user to enter the words
//            this.reCaptcha.setVisibility(View.VISIBLE);
//        } else {
//            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void onAnswerVerified(boolean success) {
//        if (success) {
//            Toast.makeText(this, "berhasil", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "gagal", Toast.LENGTH_SHORT).show();
//        }
//
//        // (Optional) Shows the next CAPTCHA
//        this.showChallenge();
//    }
//
//    @Override
//    public void onClick(View v) {
//
////        switch (this.getId()) {
////            case R.id.veri:
////                this.verifyAnswer();
////
////                break;
////
////            case R.id.reload:
////                this.showChallenge();
////
////                break;
////        }
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
//
//    private void showChallenge() {
//        // Displays a progress bar while downloading CAPTCHA
//        this.reCaptcha.setVisibility(View.GONE);
//
//        this.reCaptcha.setLanguageCode("en");
//        this.reCaptcha.showChallengeAsync(Login.PUBLIC_KEY, this);
//    }
//
//    private void verifyAnswer() {
//        if (this.answer.getText().toString().isEmpty()) {
//            Toast.makeText(this, "harap isi semua", Toast.LENGTH_SHORT).show();
//        } else {
//            // Displays a progress bar while submitting the answer for verification
//            this.reCaptcha.verifyAnswerAsync(Login.PRIVATE_KEY, this.answer.getText().toString(), this);
//        }
//    }
protected void handleVerification(final String responseToken) {
    // inside handle verification method we are
    // verifying our user with response token.
    // url to sen our site key and secret key
    // to below url using POST method.
    String url = "https://www.google.com/recaptcha/api/siteverify";

    // in this we are making a string request and
    // using a post method to pass the data.
    StringRequest request = new StringRequest(Request.Method.POST, url,
            new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // inside on response method we are checking if the
                    // response is successful or not.
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getBoolean("success")) {
                            // if the response is successful then we are
                            // showing below toast message.
                            Toast.makeText(Login.this, "User verified with reCAPTCHA", Toast.LENGTH_SHORT).show();
                        } else {
                            // if the response if failure we are displaying
                            // a below toast message.
                            Toast.makeText(getApplicationContext(), String.valueOf(jsonObject.getString("error-codes")), Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ex) {
                        // if we get any exception then we are
                        // displaying an error message in logcat.
                        Log.d("TAG", "JSON exception: " + ex.getMessage());
                    }
                }
            },
            new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // inside error response we are displaying
                    // a log message in our logcat.
                    Log.d("TAG", "Error message: " + error.getMessage());
                }
            }) {
        // below is the getParamns method in which we will
        // be passing our response token and secret key to the above url.
        @Override
        protected Map<String, String> getParams() {
            // we are passing data using hashmap
            // key and value pair.
            Map<String, String> params = new HashMap<>();
            params.put("secret", SecretKey);
            params.put("response", responseToken);
            return params;
        }
    };
    // below line of code is use to set retry
    // policy if the api fails in one try.
    request.setRetryPolicy(new DefaultRetryPolicy(
            // we are setting time for retry is 5 seconds.
            50000,

            // below line is to perform maximum retries.
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    // at last we are adding our request to queue.
    queue.add(request);
}
}

//class Check extends AsyncTask<String, Void, String> {
//
//    ProgressDialog progressDialog;
//    String userResponseToken;
//
//    public Check(String userResponseToken) {
//        this.userResponseToken = userResponseToken;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        progressDialog = new ProgressDialog(progressDialog.getContext());
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Please Wait ");
//        progressDialog.show();
//    }
//
//    @Override
//    protected String doInBackground(String... strings) {
//        String isSuccess="";
//        InputStream is = null;
//        String API="https://www.google.com/recaptcha/api/siteverify?";
//        String newAPI=API+"secret="+"6LdLYVIdAAAAAPABffbeIHxG2oj4eQfXS3Dpwkcn"+"&response="+userResponseToken;
//        Log.d("TAG"," API  " +newAPI);
//        try {
//            URL url = new URL(newAPI);
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setReadTimeout(8000 /* milliseconds */);
//            httpURLConnection.setConnectTimeout(4000 /* milliseconds */);
//            httpURLConnection.setRequestMethod("GET");
//            httpURLConnection.setDoInput(true);
//            // Starts the query
//            httpURLConnection.connect();
//            int response = httpURLConnection.getResponseCode();
//            progressDialog.dismiss();
//            System.out.println(response);
//            is = httpURLConnection.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
//            StringBuilder stringBuilder = new StringBuilder();
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                stringBuilder.append(line).append("\n");
//            }
//            String result = stringBuilder.toString();
//            Log.d("Api", result);
//
//            try {
//                JSONObject jsonObject = new JSONObject(result);
//                System.out.println("Result Object :  " + jsonObject);
//                isSuccess = jsonObject.getString("success");
//                System.out.println("obj "+isSuccess);
//
//            } catch (Exception e) {
//                Log.d("Exception: ", e.getMessage());
//                e.printStackTrace();
//                progressDialog.dismiss();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            progressDialog.dismiss();
//        }
//        return isSuccess;
//    }
//
//    @Override
//    protected void onPostExecute(String s) {
//        super.onPostExecute(s);
//        try {
//            if (s != null) {
//                switch (s) {
//                    case "true":
//                        return ;
//                    case "socketexception":
//                        return;
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//}
