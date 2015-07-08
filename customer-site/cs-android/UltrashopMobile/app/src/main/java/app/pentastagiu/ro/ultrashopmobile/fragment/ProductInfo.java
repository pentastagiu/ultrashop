package app.pentastagiu.ro.ultrashopmobile.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.widget.Button;
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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import app.pentastagiu.ro.ultrashopmobile.ProductPresentation;
import app.pentastagiu.ro.ultrashopmobile.adapter.ProductPresentationAdapter;
import app.pentastagiu.ro.ultrashopmobile.R;
import app.pentastagiu.ro.ultrashopmobile.model.Product;
import app.pentastagiu.ro.ultrashopmobile.model.ProductDescription;

/**
 * Created by deni on 6/30/2015.
 */
public class ProductInfo extends Fragment {
    private ProductPresentationAdapter productAdapter;
    private ProgressDialog pDialog;
    private ProductDescription productDescription;
    private Integer pImageCount;
    private Integer pId;
    String productId;
    private List<ProductPresentation> productPresentations;

    public ProductInfo() {
    }

    public ProductInfo(String id) {
        productId = id;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = layoutInflater.inflate(R.layout.fragment_product_info, container, false);
        new GetProductDescription(getActivity()).execute(getString(R.string.product_description_address) + productId,
                getString(R.string.product_images_address) + productId,
                getString(R.string.product_presentation_address) + productId);
        return rootView;
    }

    /**
     * Async task class to get json objects by making HTTP calls
     */
    private class GetProductDescription extends AsyncTask<String, Void, Void> {
        Activity context;

        public GetProductDescription(Activity context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Showing progress dialog
            pDialog = new ProgressDialog(context);
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
                        pId = Integer.parseInt(productId);
                        productDescription = parseDescriptionFromJson(urlProductDescription);
                        pImageCount = parseImageCountFromJson(urlImgCount);
                        productPresentations = parsePresentationsFromJson(urlProductPresentations);
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

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            if (!productDescription.getDescription().equals("No description available.")) {
                ListView listView = (ListView) getActivity().findViewById(R.id.pInfoListView);
                productAdapter = new ProductPresentationAdapter(productPresentations, pImageCount, productDescription, pId, getActivity());
                listView.setAdapter(productAdapter);
                listView.setVisibility(View.VISIBLE);
            } else {

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

            for (int i = 0; i < jsonObjects.length(); i++) {
                JSONObject jsonProductPresentaion = jsonObjects.getJSONObject(i);

                Integer id =jsonProductPresentaion.getInt("id");
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