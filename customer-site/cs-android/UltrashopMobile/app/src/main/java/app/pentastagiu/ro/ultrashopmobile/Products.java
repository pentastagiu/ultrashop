package app.pentastagiu.ro.ultrashopmobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Products extends Activity {

    //The data to show
    List<Product> productList = new ArrayList<Product>();
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        initList();

        ListView listView = (ListView) findViewById(R.id.listView);
        productAdapter = new ProductAdapter(productList, this);
        listView.setAdapter(productAdapter);

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
}
