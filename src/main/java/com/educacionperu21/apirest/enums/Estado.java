package com.educacionperu21.apirest.enums;

public enum Estado {

    ACTIVO("Activo"), INACTIVO("Inactivo"), PENDIENTE("Pendiente"), ANULADO("Anulado"),
    MATRICULADO("Matriculado"), NO_MATRICULADO("No Matriculado"), RETIRADO("Retirado"),
    INSCRIPCION_ABIERTA("INSCRIPCION_ABIERTA"), PAGADO("PAGADO"), VENCIDO("VENCIDO");

    Estado(String nombre) {
        this.nombre = nombre;
    }

    private final String nombre;

    public String getNombre() {
        return nombre;
    }

}
