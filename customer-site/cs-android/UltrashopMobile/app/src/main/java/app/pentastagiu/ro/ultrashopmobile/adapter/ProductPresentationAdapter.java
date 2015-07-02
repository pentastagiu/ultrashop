package app.pentastagiu.ro.ultrashopmobile.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

import app.pentastagiu.ro.ultrashopmobile.asyncTask.DownloadImageTask;
import app.pentastagiu.ro.ultrashopmobile.ProductPresentation;
import app.pentastagiu.ro.ultrashopmobile.fragment.ProductInfo;
import app.pentastagiu.ro.ultrashopmobile.R;
import app.pentastagiu.ro.ultrashopmobile.model.ProductDescription;

/**
 * Created by Razvan on 30/06/2015.
 * Class for adapting the product presentation view.
 */
public class ProductPresentationAdapter extends ArrayAdapter<ProductPresentation> {

    private List<ProductPresentation> productPresentations;
    private Activity context;
    private static Toast mToast;
    private Integer pImageCount;
    private ProductDescription productDescription;
    Integer pId;

    public ProductPresentationAdapter(List<ProductPresentation> productPresentations, Integer pImageCount, ProductDescription productDescription, Integer pId, Activity context) {
        super(context, R.layout.row_layout_list, productPresentations);
        this.productPresentations = productPresentations;
        this.context = context;
        this.pImageCount = pImageCount;
        this.productDescription = productDescription;
        this.pId = pId;
    }

    public int getSize() {
        return productPresentations.size();
    }

    public ProductPresentation getProductPresentation(int position) {
        return productPresentations.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position > 0) {
            return 1;
        }
        return 0;
    }


    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view = convertView;
        ProductHolder holder = new ProductHolder();
        int type = getItemViewType(position);
        //first verify if convertView is not null
        // if (convertView == null) {
        //this is a new view we inflate the new layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (type == 1) {
            view = inflater.inflate(R.layout.product_info_item2, null);
            // now we can fill the layout with the right values
            TextView presentationTitle = (TextView) view.findViewById(R.id.ppTitle);
            TextView presentationDescription = (TextView) view.findViewById(R.id.ppDescription);
            ImageView imageView = (ImageView) view.findViewById(R.id.imgProduct);
            holder.title = presentationTitle;
            holder.description = presentationDescription;
            holder.imageView = imageView;
            ProductPresentation productPresentation = productPresentations.get(position);

            holder.title.setText(productPresentation.getTitle());
            holder.description.setText(productPresentation.getDescription());
            holder.imageSrc = productPresentation.getImageSrc();
            new DownloadImageTask(holder.imageView).execute("http://192.168.108.131:90/images/" + productPresentation.getProduct().getId() + "/" + holder.imageSrc);
            view.setTag(holder);
        } else {
            view = inflater.inflate(R.layout.product_info_item1, null);
            LinearLayout layout = (LinearLayout) view.findViewById(R.id.pInfoImageList);
            for (int i = 1; i <= pImageCount; i++) {
                ImageView imageView = new ImageView(context);
                imageView.setId(i);
                imageView.setPadding(4, 2, 4, 2);
                String urldisplay = "http://192.168.108.218:90/images/" + pId + "/" + i + ".jpg";
                    /*Bitmap productImage = null;
                    try {
                        InputStream in = new java.net.URL(urldisplay).openStream();
                        productImage = BitmapFactory.decodeStream(in);
                    } catch (Exception e) {
                        Log.e("imgError", "Error downloading image: ", e);
                    }
                    imageView.setImageBitmap(productImage);*/
                new DownloadImageTask(imageView).execute(urldisplay);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setAdjustViewBounds(true);
                //addLayoutImageView(layout, imageView);
                layout.addView(imageView);
            }
            TextView productInfoView = (TextView) view.findViewById(R.id.pInfo);
            TextView productDescriptionView = (TextView) view.findViewById(R.id.pInfoDescription);
            productInfoView.setText(productDescription.getProduct().getName() + "\n" + productDescription.getProduct().getPrice());
            productDescriptionView.setText("Description:\n" + productDescription.getDescription());
            productInfoView.setVisibility(View.VISIBLE);
            view.findViewById(R.id.pInfoBtnAddToCart).setVisibility(View.VISIBLE);
            productDescriptionView.setVisibility(View.VISIBLE);
            view.findViewById(R.id.pInfoImageList).setVisibility(View.VISIBLE);
            holder.title = productInfoView;
            holder.description = productDescriptionView;
            holder.btn = (Button) view.findViewById(R.id.pInfoBtnAddToCart);
            holder.layout = layout;
            view.setTag(holder);
        }
        //   } else {
        //        holder = (ProductHolder) view.getTag();
        //  }

        return view;
    }

 /*   private void addLayoutImageView(final LinearLayout layout, final ImageView imageView) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                layout.addView(imageView);
            }
        });*/

    public void swapProductPresentationList(List<ProductPresentation> products) {
        clear();

        for (ProductPresentation object : products) {
            add(object);
        }

        notifyDataSetChanged();
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    /* *********************************
     * We use the holder pattern
	 * It makes the view faster and avoid finding the component
	 * **********************************/
    protected static class ProductHolder {
        public Button btn;
        public ImageView imageView;
        public TextView title;
        public TextView description;
        public String imageSrc;
        public LinearLayout layout;
    }
}