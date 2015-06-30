package app.pentastagiu.ro.ultrashopmobile.model;

/**
 * Created by Razvan on 29/06/2015.
 * The model for the product description data.
 */
public class ProductDescription {
    private Integer id;
    private String description;
    private Product product;

    public ProductDescription() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
