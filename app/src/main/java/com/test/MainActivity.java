package com.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Activity activity = this;
    Button click;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .setFixAspectRatio(true)
//                        .setAspectRatio(1, 1)
                        .start(activity);
            }
        });
    }

    public String getStringImage() {
        if (bitmap != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            return encodedImage;
        }
        return "";
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {


            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                Uri resultUri = result.getUri();
                Log.e("result uri", resultUri.toString());
                try {
                    this.bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);

//                    circleImageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }
/*
    StringRequest stringRequest = new StringRequest(Request.Method.POST, *//*API*//*, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.e("phd_response", response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                boolean status = jsonObject.optBoolean("status", false);
                String msg = jsonObject.optString("msg");
                if (status) {

                    *//*images.setCertificate(imgstr);*//*


                    *//*images.setAwardDetailsArrayList(awardDetailsArrayList);
                     *//*

                    onBackPressed();


                } else {
                    Toast.makeText(activity, "Something went wrong.", Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(activity, VoleyErrorHelper.getMessage(error, activity), Toast.LENGTH_LONG).show();

        }
    }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            HashMap<String, String> params = new HashMap<>();
            try {



                *//*image.put("certificate", imgstr);*//*

            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }


            return params;
        }
    };

   VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);*/
}








