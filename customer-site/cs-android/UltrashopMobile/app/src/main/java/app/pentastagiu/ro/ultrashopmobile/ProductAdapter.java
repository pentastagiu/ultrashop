package app.pentastagiu.ro.ultrashopmobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
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
        ProductHolder holder = new ProductHolder();

        //first verify if convertView is not null
        if (convertView == null) {
            //this is a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_layout, null);
            // now we can fill the layout with the right values
            TextView productNameView = (TextView) view.findViewById(R.id.name);
            TextView productPriceView = (TextView) view.findViewById(R.id.price);
            ImageView imageView = (ImageView) view.findViewById(R.id.imgProduct);

            holder.productNameView = productNameView;
            holder.priceView = productPriceView;
            holder.imageView = imageView;

            view.setTag(holder);
        } else {
            holder = (ProductHolder) view.getTag();
        }

        Product product = productList.get(position);
        holder.productNameView.setText(product.getName());
        holder.priceView.setText("" + product.getPrice());
        new DownloadImageTask(holder.imageView).execute("http://192.168.108.213:90/images/" + product.getId() + "/1.jpg");
        return view;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Bitmap temp = BitmapFactory.decodeResource(null, R.mipmap.ic_launcher);
            bmImage.setImageBitmap(temp);
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap productImage = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                productImage = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("imgError", "Error downloading image: ", e);
            }
            return productImage;
        }

        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            bmImage.setImageBitmap(result);
        }
    }

    public void swapProductList(List<Product> products) {
        clear();

        for (Product object : products) {
            add(object);
        }

        notifyDataSetChanged();
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
