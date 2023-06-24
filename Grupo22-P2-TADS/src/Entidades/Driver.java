package Entidades;

public class Driver implements Comparable<Driver> {
    private String nombre;
    private int cantidad;

    public Driver(String entrada, Integer integer) {
        this.nombre = entrada;
        this.cantidad = integer;
    }


    @Override
    public int compareTo(Driver o) {
        return o.cantidad - this.cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
