package app.pentastagiu.ro.ultrashopmobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
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

    private ProductPresentationAdapter productAdapter;
    private ProgressDialog pDialog;
    private ProductDescription productDescription;
    private Integer pId;
    private Integer pImageCount;
    private List<ProductPresentation> productPresentations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        Intent intent = getIntent();
        String tag = intent.getStringExtra("id");
        pId = Integer.parseInt(tag);
        /*TextView text = (TextView) findViewById(R.id.pInfo);
        text.setText("Product id is: " + tag);*/
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        new GetProductDescription().execute("http://192.168.108.218:8080/ultrashop/ws/products/descriptions/product/" + tag,
                "http://192.168.108.218:8080/ultrashop/ws/products/images/product/" + tag,
                "http://192.168.108.218:8080/ultrashop/ws/products/presentations/product/" + tag);
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
                JSONObject urlProductDescription = getJSONfromURL(args[0]);
                JSONObject urlImgCount = getJSONfromURL(args[1]);
                JSONArray urlProductPresentations = getJSONArrayFromURL(args[2]);
                if (urlProductDescription != null) {
                    if (!urlProductDescription.toString().isEmpty()) {
                        productDescription = parseDescriptionFromJson(urlProductDescription);
                        pImageCount = parseImageCountFromJson(urlImgCount);
                        productPresentations = parsePresentationsFromJson(urlProductPresentations);
                        LinearLayout layout = (LinearLayout) findViewById(R.id.pInfoImageList);
                        for (int i = 1; i <= pImageCount; i++) {
                            ImageView imageView = new ImageView(ProductInfo.this);
                            imageView.setId(i);
                            imageView.setPadding(4, 2, 4, 2);
                            //imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                            //new DownloadImageTask(imageView).execute("http://192.168.108.218:90/images/" + pId + "/" + i + ".jpg");
                            String urldisplay = "http://192.168.108.218:90/images/" + pId + "/" + i + ".jpg";
                            Bitmap productImage = null;
                            try {
                                InputStream in = new java.net.URL(urldisplay).openStream();
                                productImage = BitmapFactory.decodeStream(in);
                            } catch (Exception e) {
                                Log.e("imgError", "Error downloading image: ", e);
                            }
                            imageView.setImageBitmap(productImage);
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            imageView.setAdjustViewBounds(true);
                            addLayoutImageView(layout, imageView);
                        }
                        ListView listView = (ListView) findViewById(R.id.pInfoListView);
                        productAdapter = new ProductPresentationAdapter(productPresentations, ProductInfo.this);
                        addListDescriptionsView(listView, productAdapter);
                    } else {
                        productDescription = new ProductDescription();
                        productDescription.setDescription("No description available.");
                    }
                } else {
                    productDescription = new ProductDescription();
                    productDescription.setDescription("No description available.");
                }
            } catch (JSONException e) {
                Log.e("JSONError", "Error parsing json from url: ", e);
            }
            return null;
        }

        private void addLayoutImageView(final LinearLayout layout, final ImageView imageView) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    layout.addView(imageView);
                }
            });
        }

        private void addListDescriptionsView(final ListView view, final ProductPresentationAdapter adapter) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.setAdapter(adapter);
                    view.setVisibility(View.VISIBLE);
                    //setListViewHeightBasedOnChildren(view);
                    view.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            v.getParent().requestDisallowInterceptTouchEvent(true);
                            return false;
                            //return (event.getAction() == MotionEvent.ACTION_MOVE);
                        }
                    });
                }
            });
        }

        /**
         * Method for Setting the Height of the ListView dynamically.
         * Hack to fix the issue of not showing all the items of the ListView
         * when placed inside a ScrollView
         */
        private void setListViewHeightBasedOnChildren(ListView listView) {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null)
                return;

            int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            int totalHeight = 0;
            View view = null;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                view = listAdapter.getView(i, view, listView);
                if (i == 0)
                    view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

                view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += view.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
            listView.requestLayout();
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
                ProductInfo.this.findViewById(R.id.pInfoImageList).setVisibility(View.VISIBLE);
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

        public JSONArray getJSONArrayFromURL(String url) {
            InputStream is = null;
            String result = "";
            JSONArray jsonArray = null;
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
                        jsonArray = new JSONArray(result);
                } catch (JSONException e) {
                    Log.e("jsonObj", "Error converting to jsonObject: ", e);
                }
            }
            return jsonArray;
        }

        private ProductDescription parseDescriptionFromJson(JSONObject jsonObject) throws JSONException {
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

        private Integer parseImageCountFromJson(JSONObject jsonObject) throws JSONException {
            Integer imageCount = jsonObject.getInt("numberOfImages");

            return imageCount;
        }

        private List<ProductPresentation> parsePresentationsFromJson(JSONArray jsonObjects) throws JSONException {
            ArrayList<ProductPresentation> productPresentations = new ArrayList<ProductPresentation>();
            //JSONArray jsonObjects = jsonObject.getJSONArray("products");

            for (int i = 0; i < jsonObjects.length(); i++) {
                JSONObject jsonProductPresentaion = jsonObjects.getJSONObject(i);

                Integer id = jsonProductPresentaion.getInt("id");
                String description = jsonProductPresentaion.getString("description");
                String title = jsonProductPresentaion.getString("title");
                Integer section = jsonProductPresentaion.getInt("section");
                String imageSrc = jsonProductPresentaion.getString("imageSrc");

                JSONObject jsonProduct = jsonProductPresentaion.getJSONObject("product");
                Integer productId = jsonProduct.getInt("id");
                String productName = jsonProduct.getString("name");
                Double productPrice = jsonProduct.getDouble("price");
                Product product = new Product(productId, productName, productPrice);

                ProductPresentation currentProductPresentation = new ProductPresentation.ProductPresentationBuilder()
                        .id(id).description(description).title(title).section(section).imageSrc(imageSrc).product(product).build();
                productPresentations.add(currentProductPresentation);
            }
            return productPresentations;
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
