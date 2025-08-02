package modules;

public class Cliente {
    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String provincia;
    private int compra_anual;

    public Cliente(int id, String cedula, String nombre, String apellido, String direccion, String provincia, int compra_anual) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.provincia = provincia;
        this.compra_anual = compra_anual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getCompra_anual() {
        return compra_anual;
    }

    public void setCompra_anual(int compra_anual) {
        this.compra_anual = compra_anual;
    }
}
