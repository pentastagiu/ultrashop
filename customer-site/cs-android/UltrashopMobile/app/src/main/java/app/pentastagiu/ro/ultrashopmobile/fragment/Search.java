package app.pentastagiu.ro.ultrashopmobile.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import app.pentastagiu.ro.ultrashopmobile.R;
import app.pentastagiu.ro.ultrashopmobile.adapter.ProductAdapter;
import app.pentastagiu.ro.ultrashopmobile.model.Product;

/**
 * Created by deni on 7/1/2015.
 */
public class Search extends Fragment {
    ProgressDialog pDialog;

    private List<Product> productList = new ArrayList<Product>();
    private ProductAdapter productAdapter;
    // Search EditText
    EditText inputSearch;
    String searchValue = "";

    public Search() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.fragment_search, container, false);

        inputSearch = (EditText) rooView.findViewById(R.id.inputSearch);
        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchValue = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        Button searchButton = (Button) rooView.findViewById(R.id.btnSearch);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(inputSearch.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                new GetProducts(getActivity()).execute("http://192.168.108.131:8080/ultrashop/ws/products/search",
                        searchValue.toString());
            }
        });
        inputSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //IME options are available for all keyboards, except HTC
                //KeyEvents covers the HTC keyboards
                if (actionId == EditorInfo.IME_ACTION_DONE
                        || actionId == EditorInfo.IME_NULL
                        || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(inputSearch.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    new GetProducts(getActivity()).execute("http://192.168.108.131:8080/ultrashop/ws/products/search",
                            searchValue.toString());
                    return true;
                } else {
                    return false;
                }
            }
        });
        return rooView;
    }

    /**
     * Async task class to get json objects by making HTTP calls
     */
    private class GetProducts extends AsyncTask<String, String, Void> {
        Activity thisContext;

        public GetProducts(Activity context) {
            thisContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Showing progress dialog
            pDialog = new ProgressDialog(thisContext);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(String... args) {
            try {
                JSONArray url = getJSONfromURL(args[0], args[1]);
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
            GridView gridView = (GridView) thisContext.findViewById(R.id.gridView);
            productAdapter = new ProductAdapter(productList, thisContext);
            gridView.setAdapter(productAdapter);
        }

        public JSONArray getJSONfromURL(String url, String search) {
            InputStream is = null;
            String result = "";
            JSONArray jArray = null;
            if (isOnline()) {
                try {
                    String stringForJsonObject = "{search:'" + search.toString() + "'}";
                    JSONObject jsonSearch = new JSONObject(stringForJsonObject);
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(url);
                    httpPost.setHeader("Content-Type", "application/JSON");
                    httpPost.setHeader("Accept", "application/JSON");
                    httpPost.setEntity(new StringEntity(search.toString()));
                    ResponseHandler handler = new BasicResponseHandler();
                    String response = httpclient.execute(httpPost, handler).toString();
                    result = response;
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection " + e.getStackTrace().toString());
                    Log.e("exc", "http", e);
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
                Process p1 = Runtime.getRuntime().exec("ping -c 1 www.google.ro");
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
