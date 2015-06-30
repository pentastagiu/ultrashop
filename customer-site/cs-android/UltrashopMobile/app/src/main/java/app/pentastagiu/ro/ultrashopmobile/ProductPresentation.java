package app.pentastagiu.ro.ultrashopmobile;

/**
 * Created by Razvan on 30/06/2015.
 * The model for the product presentation wrapper..
 */
public class ProductPresentation {
    private Integer id;
    private String description;
    private String title;
    private Integer section;
    private String imageSrc;
    private Product product;

    private ProductPresentation(ProductPresentationBuilder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.title = builder.title;
        this.section = builder.section;
        this.imageSrc = builder.imageSrc;
        this.product = builder.product;
    }

    public ProductPresentation() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public static class ProductPresentationBuilder {
        private Integer id;
        private String description;
        private String title;
        private Integer section;
        private String imageSrc;
        private Product product;

        public ProductPresentationBuilder() {
        }

        public ProductPresentationBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public ProductPresentationBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductPresentationBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ProductPresentationBuilder section(Integer section) {
            this.section = section;
            return this;
        }

        public ProductPresentationBuilder imageSrc(String imageSrc) {
            this.imageSrc = imageSrc;
            return this;
        }

        public ProductPresentationBuilder product(Product product) {
            this.product = product;
            return this;
        }

        public ProductPresentation build() {
            return new ProductPresentation(this);
        }
    }

}
