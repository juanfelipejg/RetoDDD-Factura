package domain.factura.values;

import co.com.sofka.domain.generic.Identity;

public class SucursalId extends Identity {

    private SucursalId (String uid){
        super(uid);
    }

    public SucursalId(){

    }

    public static SucursalId of(String uid){
        return new SucursalId(uid);
    }

}
