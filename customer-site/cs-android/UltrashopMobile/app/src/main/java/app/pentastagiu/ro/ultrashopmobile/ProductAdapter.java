package app.pentastagiu.ro.ultrashopmobile;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Razvan on 19/06/2015.
 * Class for adapting the list view.
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    private List<Product> productList;
    private Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        super(context, R.layout.row_layout, productList);
        this.productList = productList;
        this.context = context;
    }

    public int getSize() {
        return productList.size();
    }

    public Product getProduct(int position) {
        return productList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        try {
            ProductHolder holder = new ProductHolder();

            //first verify if convertView is not null
            if (convertView == null) {
                //this is a new view we inflate the new layout
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.row_layout, null);
                // now we can fill the layout with the right values
                TextView productNameView = (TextView) view.findViewById(R.id.name);
                TextView productPriceView = (TextView) view.findViewById(R.id.price);

                holder.productNameView = productNameView;
                holder.priceView = productPriceView;

                view.setTag(holder);
            } else {
                holder = (ProductHolder) view.getTag();
            }

            Product product = productList.get(position);
            holder.productNameView.setText(product.getName());
            holder.priceView.setText("" + product.getPrice());
        } catch (Exception e) {
            Log.e("PADPT", "Error: " + e);
        }
        return view;
    }

    /* *********************************
     * We use the holder pattern
	 * It makes the view faster and avoid finding the component
	 * **********************************/
    private static class ProductHolder {
        public ImageView imageView;
        public TextView productNameView;
        public TextView priceView;
    }
}
