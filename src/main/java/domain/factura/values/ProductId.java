package domain.factura.values;

import co.com.sofka.domain.generic.Identity;

public class ProductId extends Identity {

    private ProductId(String uid){
        super(uid);
    }

    public ProductId(){

    }

    public static ProductId of(String uid){
        return new ProductId(uid);
    }
}
