package modules;

public class Vendedor {
    private int id;
    private String codigo;
    private String nombre;
    private String apellido;
    private String departamento_codigo;
    private String cargo;
    private int ventas_mensuales;
    private int ventas_anuales;

    public Vendedor(int id, String codigo, String nombre, String apellido, String departamento_codigo, String cargo, int ventas_mensuales, int ventas_anuales) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.departamento_codigo = departamento_codigo;
        this.cargo = cargo;
        this.ventas_mensuales = ventas_mensuales;
        this.ventas_anuales = ventas_anuales;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getDepartamento_codigo() {
        return departamento_codigo;
    }

    public void setDepartamento_codigo(String departamento_codigo) {
        this.departamento_codigo = departamento_codigo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getVentas_mensuales() {
        return ventas_mensuales;
    }

    public void setVentas_mensuales(int ventas_mensuales) {
        this.ventas_mensuales = ventas_mensuales;
    }

    public int getVentas_anuales() {
        return ventas_anuales;
    }

    public void setVentas_anuales(int ventas_anuales) {
        this.ventas_anuales = ventas_anuales;
    }
}
