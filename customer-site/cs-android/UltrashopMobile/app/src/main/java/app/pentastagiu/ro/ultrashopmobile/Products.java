package app.pentastagiu.ro.ultrashopmobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Products extends Activity {

    //The data to show
    private List<Product> productList = new ArrayList<Product>();
    private ProductAdapter productAdapter;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        // exit button
        Button btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        // populate the products list using JSON request.
        new GetProducts().execute("http://192.168.108.218:8080/ultrashop/ws/products");
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.abc_fade_out);
    }

    private void initList() {
        for (int i = 0; i < 10; i++) {
            productList.add(new Product(i, "Product " + i, (double) Math.round((Math.random() * 801 + 200) * 100) / 100));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_products, menu);
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

   /* private void readProductListJSON() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONArray url = getJSONfromURL("http://192.168.108.213:8080/ultrashop/ws/products");
                try {
                    List<Product> productList = parse(url);
                    productAdapter.swapProductList(productList);
                } catch (Exception e) {
                    Log.d("jsonError", "Unable to parse objects with error: " + e.getMessage());
                }
            }
        }).start();
    }*/


    /**
     * Async task class to get json objects by making HTTP calls
     */
    private class GetProducts extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Showing progress dialog
            pDialog = new ProgressDialog(Products.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(String... args) {
            try {
                JSONArray url = getJSONfromURL(args[0]);
                if (url != null) {
                    productList = parseFromJson(url);
                }
            } catch (JSONException jsonExc) {
                Log.d("JSONError", "ExceptionJSON: " + jsonExc.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            GridView gridView = (GridView) findViewById(R.id.gridView);
            productAdapter = new ProductAdapter(productList, Products.this);
            gridView.setAdapter(productAdapter);
        }

        public JSONArray getJSONfromURL(String url) {
            InputStream is = null;
            String result = "";
            JSONArray jArray = null;
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
                    is = entity.getContent();
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection " + e.getStackTrace().toString());
                    Log.e("exc", "http", e);
                }
                //convert response to string
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
                    Log.d("ErrorConvert", "Error while converting result: " + e.getMessage());
                }
                try {
                    jArray = new JSONArray(result);
                } catch (JSONException e) {
                    Log.d("jsonObj", "Error parsing data: " + e.getMessage());
                }
            } else {
                productList = new ArrayList<Product>();
                Product product = new Product(0, "No internet connection !", null);
                productList.add(product);
            }
            return jArray;
        }

        private List<Product> parseFromJson(JSONArray jsonObjects) throws JSONException {
            ArrayList<Product> products = new ArrayList<Product>();
            //JSONArray jsonObjects = jsonObject.getJSONArray("products");

            for (int i = 0; i < jsonObjects.length(); i++) {
                JSONObject jsonProduct = jsonObjects.getJSONObject(i);
                Integer id = jsonProduct.getInt("id");
                String name = jsonProduct.getString("name");
                Double price = jsonProduct.getDouble("price");

                Product product = new Product(id, name, price);
                products.add(product);
            }
            return products;
        }

        // check internet connection
        public Boolean isOnline() {
            try {
                Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.ro");
                int returnVal = p1.waitFor();
                boolean reachable = (returnVal == 0);
                return reachable;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return false;
        }
    }
}
