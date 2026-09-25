package co.edu.uniquindio.poo.parcial.model;

/** Factory Method: cada fábrica decide qué tipo de plan crear. */
public abstract class PlanFactory {
    public abstract PlanEntrenamiento crearPlan(DatosPlan datos);
}
