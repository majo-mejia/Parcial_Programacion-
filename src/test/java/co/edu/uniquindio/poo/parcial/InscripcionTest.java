package co.edu.uniquindio.poo.parcial;

import co.edu.uniquindio.poo.parcial.model.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InscripcionTest {

    @Test
    public void testAgregarServicioDisponible() {
        Cliente cliente = new Cliente("1001", "Juan Perez", "3111234567", "Calle 10");
        PlanBasico plan = new PlanBasico("P01", "Plan Basico", "Descripcion", 1, 100000.0, TipoEstado.ACTIVO, "Acceso general");
        Inscripcion inscripcion = new Inscripcion(1, cliente, plan, LocalDate.now(), null, 0.0);

        // Se cambió 20000.0 a 20000
        ServicioAdicional servicio = new ServicioAdicional("Masaje", 20000, true);

        inscripcion.agregarServicio(servicio);

        assertEquals(120000.0, inscripcion.calcularTotal());
    }

    @Test
    public void testAgregarServicioNoDisponible() {
        Cliente cliente = new Cliente("1001", "Juan Perez", "3111234567", "Calle 10");
        PlanBasico plan = new PlanBasico("P01", "Plan Basico", "Descripcion", 1, 100000.0, TipoEstado.ACTIVO, "Acceso general");
        Inscripcion inscripcion = new Inscripcion(1, cliente, plan, LocalDate.now(), null, 0.0);

        // Se cambió 15000.0 a 15000
        ServicioAdicional servicioInactivo = new ServicioAdicional("Sauna", 15000, false);

        inscripcion.agregarServicio(servicioInactivo);

        assertEquals(100000.0, inscripcion.calcularTotal());
    }

    @Test
    public void testCalcularTotalConDescuento() {
        Cliente cliente = new Cliente("1001", "Juan Perez", "3111234567", "Calle 10");
        PlanBasico plan = new PlanBasico("P01", "Plan Basico", "Descripcion", 1, 100000.0, TipoEstado.ACTIVO, "Acceso general");
        Inscripcion inscripcion = new Inscripcion(1, cliente, plan, LocalDate.now(), null, 10.0);

        assertEquals(90000.0, inscripcion.calcularTotal());
    }

    @Test
    public void testCalcularTotalPlanPersonalizado() {
        Cliente cliente = new Cliente("1001", "Juan Perez", "3111234567", "Calle 10");
        PlanPersonalizado plan = new PlanPersonalizado("P01", "Plan Personalizado", "Descripcion", 1, 100000.0, TipoEstado.ACTIVO, 5);
        Entrenador entrenador = new Entrenador("12345", "Carlos", "3001234567", TipoEspecialidad.DEFINICION, 20000.0);

        Inscripcion inscripcion = new Inscripcion(1, cliente, plan, LocalDate.now(), entrenador, 0.0);

        assertEquals(200000.0, inscripcion.calcularTotal());
    }

    @Test
    public void testGetDescripcion() {
        Cliente cliente = new Cliente("1001", "Juan Perez", "3111234567", "Calle 10");
        PlanBasico plan = new PlanBasico("P01", "Plan Mensual", "Descripcion", 1, 100000.0, TipoEstado.ACTIVO, "Acceso general");
        LocalDate fecha = LocalDate.of(2026, 3, 30);

        Inscripcion inscripcion = new Inscripcion(101, cliente, plan, fecha, null, 0.0);

        assertEquals("101 - Juan Perez - Plan Mensual - 2026-03-30 - $100000", inscripcion.getDescripcion());
    }
}