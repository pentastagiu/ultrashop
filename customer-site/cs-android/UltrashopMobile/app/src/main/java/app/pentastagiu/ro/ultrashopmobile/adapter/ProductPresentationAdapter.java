package app.pentastagiu.ro.ultrashopmobile;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.pentastagiu.ro.ultrashopmobile.fragment.ProductInfo;

/**
 * Created by Razvan on 30/06/2015.
 * Class for adapting the product presentation view.
 */
public class ProductPresentationAdapter extends ArrayAdapter<ProductPresentation> {

    private List<ProductPresentation> productPresentations;
    private Context context;
    private static Toast mToast;

    public ProductPresentationAdapter(List<ProductPresentation> productPresentations, Context context) {
        super(context, R.layout.row_layout_list, productPresentations);
        this.productPresentations = productPresentations;
        this.context = context;
    }

    public int getSize() {
        return productPresentations.size();
    }

    public ProductPresentation getProductPresentation(int position) {
        return productPresentations.get(position);
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view = convertView;
        ProductHolder holder = new ProductHolder();

        //first verify if convertView is not null
        if (convertView == null) {
            //this is a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_layout_list, null);
            // now we can fill the layout with the right values
            TextView presentationTitle = (TextView) view.findViewById(R.id.ppTitle);
            TextView presentationDescription = (TextView) view.findViewById(R.id.ppDescription);
            ImageView imageView = (ImageView) view.findViewById(R.id.imgProduct);
            Button btnAddToCart = (Button) view.findViewById(R.id.btnAddToCart);

            holder.title = presentationTitle;
            holder.description = presentationDescription;
            holder.imageView = imageView;

            view.setTag(holder);
        } else {
            holder = (ProductHolder) view.getTag();
        }

        ProductPresentation productPresentation = productPresentations.get(position);
        // Go to product info activity on image click
        holder.imageView.setTag(productPresentation.getId());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
                Intent intent = new Intent(context, ProductInfo.class);
                intent.putExtra("id", v.getTag().toString());
                context.startActivity(intent);*/

                Fragment fragment = new ProductInfo(v.getTag().toString());
                FragmentManager fragmentManager = ((ActionBarActivity) context).getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment).commit();
                ((ActionBarActivity) context).setTitle("...");
            }
        });

        holder.title.setText(productPresentation.getTitle());
        holder.description.setText(productPresentation.getDescription());
        holder.imageSrc = productPresentation.getImageSrc();
        new DownloadImageTask(holder.imageView).execute("http://192.168.108.131:90/images/" + productPresentation.getProduct().getId() + "/" + holder.imageSrc);
        return view;
    }

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
        public ImageView imageView;
        public TextView title;
        public TextView description;
        public String imageSrc;
    }
}