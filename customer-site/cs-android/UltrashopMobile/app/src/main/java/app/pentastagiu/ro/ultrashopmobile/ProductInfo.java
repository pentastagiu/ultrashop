package app.pentastagiu.ro.ultrashopmobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ProductInfo extends ActionBarActivity {

    private ProgressDialog pDialog;
    private ProductDescription productDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        Intent intent = getIntent();
        String tag = intent.getStringExtra("id");
        /*TextView text = (TextView) findViewById(R.id.pInfo);
        text.setText("Product id is and is and is and is and is :" + tag);*/
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        new GetProductDescription().execute("http://192.168.108.218:8080/ultrashop/ws/products/descriptions/product/" + tag);
        LinearLayout layout = (LinearLayout) findViewById(R.id.pInfoImageList);
        for (int i = 0; i < 10; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setPadding(0, 2, 0, 2);
            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            layout.addView(imageView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Async task class to get json objects by making HTTP calls
     */
    private class GetProductDescription extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Showing progress dialog
            pDialog = new ProgressDialog(ProductInfo.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(String... args) {
            try {
                JSONObject url = getJSONfromURL(args[0]);
                if (url != null) {
                    if (!url.toString().isEmpty()) {
                        productDescription = parseFromJson(url);
                    } else {
                        productDescription = new ProductDescription();
                        productDescription.setDescription("No description available.");
                    }
                } else {
                    productDescription = new ProductDescription();
                    productDescription.setDescription("No description available.");
                }
            } catch (JSONException e) {
                Log.e("JSONError", "Error parsing json fromurl: ", e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            TextView productInfoView = (TextView) ProductInfo.this.findViewById(R.id.pInfo);
            TextView productDescriptionView = (TextView) ProductInfo.this.findViewById(R.id.pInfoDescription);
            if (!productDescription.getDescription().equals("No description available.")) {
                productInfoView.setText(productDescription.getProduct().getName() + "\n" + productDescription.getProduct().getPrice());
                productDescriptionView.setText("Description:\n" + productDescription.getDescription());
                productInfoView.setVisibility(View.VISIBLE);
                ProductInfo.this.findViewById(R.id.pInfoBtnAddToCart).setVisibility(View.VISIBLE);
                productDescriptionView.setVisibility(View.VISIBLE);
            } else {
                productDescriptionView.setVisibility(View.VISIBLE);
                productDescriptionView.setText(productDescription.getDescription());
            }
        }

        public JSONObject getJSONfromURL(String url) {
            InputStream is = null;
            String result = "";
            JSONObject jsonObject = null;
            if (isOnline()) {
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpGet httppget = new HttpGet(url);
                    //httppost.addHeader(new BasicHeader("Content-Type", "application/json"));
                    //httppost.addHeader(new BasicHeader("Accept", "application/json"));
                    httppget.setHeader("Content-Type", "application/JSON");
                    httppget.setHeader("Accept", "application/JSON");
                    HttpResponse response = httpclient.execute(httppget);
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        is = entity.getContent();
                    }
                } catch (Exception e) {
                    Log.e("httpget", "Error in http connection: ", e);
                }
                //convert response to string
                if (is != null) {
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        is.close();
                        result = sb.toString();
                    } catch (Exception e) {
                        Log.e("ErrorConvertString", "Error while converting result: ", e);
                    }
                }
                try {
                    if (result != null && !result.equals(""))
                        jsonObject = new JSONObject(result);
                } catch (JSONException e) {
                    Log.e("jsonObj", "Error converting to jsonObject: ", e);
                }
            }
            return jsonObject;
        }

        private ProductDescription parseFromJson(JSONObject jsonObject) throws JSONException {
            ProductDescription productDescription = new ProductDescription();
            //JSONArray jsonObjects = jsonObject.getJSONArray("products");

            Integer id = jsonObject.getInt("id");
            String description = jsonObject.getString("description");
            JSONObject jsonProduct = jsonObject.getJSONObject("product");
            Integer productId = jsonProduct.getInt("id");
            String productName = jsonProduct.getString("name");
            Double productPrice = jsonProduct.getDouble("price");

            Product product = new Product(productId, productName, productPrice);
            productDescription.setId(id);
            productDescription.setDescription(description);
            productDescription.setProduct(product);
            return productDescription;
        }

        // check internet connection
        public Boolean isOnline() {
            try {
                Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.ro");
                int returnVal = p1.waitFor();
                return (returnVal == 0);
            } catch (Exception e) {
                Log.e("Internet", "Internet connection is down: ", e);
            }
            return false;
        }
    }
}
