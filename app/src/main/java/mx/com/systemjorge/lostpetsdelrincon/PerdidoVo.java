package mx.com.systemjorge.lostpetsdelrincon;


public class PerdidoVo {
    //Create By JorgeRPorras
    //private String key;
    private String nombre;
    private String descripcion;
    private String url;




    public PerdidoVo(){

    }

    public PerdidoVo(String nombre, String descripcion,String url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
        //this.key = key;


    }

    /*public String getKey() { return key ;}

    public void setKey(String key) { this.key = key; }*/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
       return url;
    }

    public void setUrl(String url) {

        this.url = url;

    }


}