package modules;

public class Cliente {
    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String provincia_codigo;
    private int compra_anual;

    public Cliente(String cedula, String nombre, String apellido, String direccion, String telefono, String provincia, int compra_anual) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.provincia_codigo = provincia;
        this.compra_anual = compra_anual;
    }

    public Cliente(int id, String cedula, String nombre, String apellido, String direccion, String telefono, String provincia_codigo, int compra_anual) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.provincia_codigo = provincia_codigo;
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

    public String getProvincia_codigo() {
        return provincia_codigo;
    }

    public void setProvincia_codigo(String provincia_codigo) {
        this.provincia_codigo = provincia_codigo;
    }

    public int getCompra_anual() {
        return compra_anual;
    }

    public void setCompra_anual(int compra_anual) {
        this.compra_anual = compra_anual;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
