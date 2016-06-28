package com.playbasis.android.playbasissdk.api;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Image;
import com.playbasis.android.playbasissdk.model.StoredRequest;
import com.playbasis.android.playbasissdk.secure.RequestStorage;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by TorIsHere on 1/29/2016 AD.
 */
public class FileApi {

    private static int maxBufferSize = 1 * 1024*1024;
    public static final String TAG = "FileApi";
    private static final String crlf = "\r\n";
    private static final String twoHyphens = "--";
    private static final String boundary =  "*****";

    /**
     * Post image to server and get a link
     * @param playbasis Playbasis object.
     * @param listener OnResult listener.
     */
    public static void uploadImage(final Playbasis playbasis, String fileName, Bitmap bitmap, final OnResult<Image> listener) {
        uploadImage(playbasis, fileName, null, bitmap, listener);
    }

    public static void uploadImage(final Playbasis playbasis, String fileName, String playerID, Bitmap bitmap, final OnResult<Image> listener) {
        String uri = playbasis.getUrl() +"/"+"File/upload";
        final String token = playbasis.getAuthenticator().getToken();

        AsyncHttpPost asyncHttpPost = new AsyncHttpPost(bitmap, listener);
        asyncHttpPost.execute(uri, token, fileName, playerID);
    }

    private static class AsyncHttpPost extends AsyncTask<String, String, JSONObject> {
        private Bitmap bitmap = null;// post data
        private OnResult<Image> listener;
        private static boolean isRunning = false;
        /**
         * constructor
         */
        public AsyncHttpPost(Bitmap bitmap, OnResult<Image> listener) {
            this.listener = listener;
            this.bitmap = bitmap;
        }

        /**
         * background
         */
        @Override
        protected JSONObject doInBackground(String... params) {
            String uri = params[0];
            String token = params[1];
            String fileName = params[2];
            String playerID = params[3];

            String response = null;
            HttpsURLConnection httpsURLConnection = null;
            if (isRunning) {
                return null;
            }
            try {
                isRunning = true;
                URL url = new URL(uri);
                httpsURLConnection = (HttpsURLConnection) url.openConnection(); //(HttpURLConnection) url.openConnection();

                httpsURLConnection.setUseCaches(false);
                httpsURLConnection.setDoOutput(true);

                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpsURLConnection.setRequestProperty("Cache-Control", "no-cache");
                httpsURLConnection.setRequestProperty(
                        "Content-Type", "multipart/form-data;boundary=" + boundary);
            /*
            * Content wrapper
            * */
                DataOutputStream request = new DataOutputStream(
                        httpsURLConnection.getOutputStream());

                // sending token
                request.writeBytes(twoHyphens + boundary + crlf);
                request.writeBytes("Content-Disposition: form-data; name=\"token\"" + crlf);
                request.writeBytes(crlf);
                request.writeBytes(token);
                request.writeBytes(crlf);

                // sending PlayerId
                if (playerID != null) {
                    request.writeBytes(twoHyphens + boundary + crlf);
                    request.writeBytes("Content-Disposition: form-data; name=\"player_id\"" + crlf);
                    request.writeBytes(crlf);
                    request.writeBytes(playerID);
                    request.writeBytes(crlf);
                }

                request.writeBytes(twoHyphens + boundary + crlf);
                request.writeBytes("Content-Disposition: form-data; name=\"" + fileName + "\";filename=\"" + fileName + ".png" + "\"" + crlf);
                request.writeBytes("Content-Type: application/octet-stream" + crlf);

                request.writeBytes(crlf);

            /*
            * Convert Bitmap to ByteBuffer
            * */

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream); //compress to which format you want.
                byte [] byte_arr = stream.toByteArray();
                request.write(byte_arr);

            /*
            * End content wrapper
            * */
                request.writeBytes(crlf);
                request.writeBytes(twoHyphens + boundary + twoHyphens + crlf);

                request.flush();
                request.close();

                InputStream responseStream = new
                        BufferedInputStream(httpsURLConnection.getInputStream());

                BufferedReader responseStreamReader =
                        new BufferedReader(new InputStreamReader(responseStream));

                String line = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((line = responseStreamReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                responseStreamReader.close();

                response = stringBuilder.toString();

                responseStream.close();

            } catch (MalformedURLException ex) {
                Log.d(TAG, "MalformedURLException");
                ex.printStackTrace();
            } catch (IOException ex) {
                Log.d(TAG, "IOException");
                ex.printStackTrace();
            } catch (Exception ex ) {
                ex.printStackTrace();
            }finally {
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
            }

            JSONObject obj = null;
            try {
                obj = new JSONObject(response);
            } catch (Throwable t) {
                Log.e("My App", "Could not parse malformed JSON");
            }
            isRunning = false;

            return obj;
        }

        /**
         * on getting result
         */
        @Override
        protected void onPostExecute(JSONObject result) {
            // something...
            if(listener!=null && result != null) {
                try {
                    boolean status = result.getBoolean("success");
                    JSONObject response = result.getJSONObject("response");
                    if (status) {
                        Image image = JsonHelper.FromJsonObject(response, Image.class);
                        listener.onSuccess(image);
                    } else {
                        listener.onError(new HttpError("Error"));
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
