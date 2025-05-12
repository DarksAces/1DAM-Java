package monlau;

import java.util.List;
import java.util.Scanner;
import monlau.dao.ProductoDAO;
import monlau.dao.ProductoDAOImpl;
import monlau.model.Producto;

/**
 * Clase principal para probar las operaciones CRUD con la base de datos
 */
public class ProductoManager {
    
    private static ProductoDAO productoDAO = new ProductoDAOImpl();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean salir = false;
        
        while (!salir) {
            mostrarMenu();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    insertarProducto();
                    break;
                case 2:
                    buscarProductoPorId();
                    break;
                case 3:
                    actualizarProducto();
                    break;
                case 4:
                    eliminarProducto();
                    break;
                case 5:
                    listarTodosProductos();
                    break;
                case 6:
                    buscarProductoPorNombre();
                    break;
                case 7:
                    buscarProductoPorRangoPrecio();
                    break;
                case 8:
                    ejecutarTestAutomatico();
                    break;
                case 0:
                    salir = true;
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
            
            if (!salir) {
                System.out.println("\nPulse ENTER para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n===== GESTOR DE PRODUCTOS =====");
        System.out.println("1. Insertar nuevo producto");
        System.out.println("2. Buscar producto por ID");
        System.out.println("3. Actualizar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Listar todos los productos");
        System.out.println("6. Buscar productos por nombre");
        System.out.println("7. Buscar productos por rango de precio");
        System.out.println("8. Ejecutar test automático CRUD");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    private static int leerOpcion() {
        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            return opcion;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void insertarProducto() {
        System.out.println("\n--- INSERTAR NUEVO PRODUCTO ---");
        
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());
        
        Producto producto = new Producto(id, nombre, precio);
        
        if (productoDAO.insert(producto)) {
            System.out.println("Producto insertado correctamente!");
        } else {
            System.out.println("No se pudo insertar el producto");
        }
    }
    
    private static void buscarProductoPorId() {
        System.out.println("\n--- BUSCAR PRODUCTO POR ID ---");
        
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Producto producto = productoDAO.read(id);
        
        if (producto != null) {
            System.out.println("Producto encontrado:");
            System.out.println(producto);
        } else {
            System.out.println("No se encontró ningún producto con ID: " + id);
        }
    }
    
    private static void actualizarProducto() {
        System.out.println("\n--- ACTUALIZAR PRODUCTO ---");
        
        System.out.print("ID del producto a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Producto producto = productoDAO.read(id);
        
        if (producto != null) {
            System.out.println("Producto actual: " + producto);
            
            System.out.print("Nuevo nombre (dejar en blanco para mantener '" + producto.getNombre() + "'): ");
            String nombre = scanner.nextLine();
            if (!nombre.trim().isEmpty()) {
                producto.setNombre(nombre);
            }
            
            System.out.print("Nuevo precio (dejar en blanco para mantener '" + producto.getPrecio() + "'): ");
            String precioStr = scanner.nextLine();
            if (!precioStr.trim().isEmpty()) {
                double precio = Double.parseDouble(precioStr);
                producto.setPrecio(precio);
            }
            
            if (productoDAO.update(producto)) {
                System.out.println("Producto actualizado correctamente!");
            } else {
                System.out.println("No se pudo actualizar el producto");
            }
        } else {
            System.out.println("No se encontró ningún producto con ID: " + id);
        }
    }
    
    private static void eliminarProducto() {
        System.out.println("\n--- ELIMINAR PRODUCTO ---");
        
        System.out.print("ID del producto a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Producto producto = productoDAO.read(id);
        
        if (producto != null) {
            System.out.println("Producto a eliminar: " + producto);
            
            System.out.print("¿Está seguro de que desea eliminar este producto? (S/N): ");
            String confirmacion = scanner.nextLine();
            
            if (confirmacion.equalsIgnoreCase("S")) {
                if (productoDAO.delete(producto)) {
                    System.out.println("Producto eliminado correctamente!");
                } else {
                    System.out.println("No se pudo eliminar el producto");
                }
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.out.println("No se encontró ningún producto con ID: " + id);
        }
    }
    
    private static void listarTodosProductos() {
        System.out.println("\n--- LISTA DE TODOS LOS PRODUCTOS ---");
        
        List<Producto> productos = productoDAO.getAll();
        
        if (productos.isEmpty()) {
            System.out.println("No hay productos en la base de datos");
        } else {
            System.out.println("Total de productos: " + productos.size());
            System.out.println("---------------------------------");
            for (Producto p : productos) {
                System.out.println(p);
            }
        }
    }
    
    private static void buscarProductoPorNombre() {
        System.out.println("\n--- BUSCAR PRODUCTOS POR NOMBRE ---");
        
        System.out.print("Introduzca parte del nombre: ");
        String nombre = scanner.nextLine();
        
        List<Producto> productos = productoDAO.findByName(nombre);
        
        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos que contengan '" + nombre + "' en su nombre");
        } else {
            System.out.println("Productos encontrados: " + productos.size());
            System.out.println("---------------------------------");
            for (Producto p : productos) {
                System.out.println(p);
            }
        }
    }
    
    private static void buscarProductoPorRangoPrecio() {
        System.out.println("\n--- BUSCAR PRODUCTOS POR RANGO DE PRECIO ---");
        
        System.out.print("Precio mínimo: ");
        double min = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Precio máximo: ");
        double max = Double.parseDouble(scanner.nextLine());
        
        List<Producto> productos = productoDAO.findByPriceRange(min, max);
        
        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos en el rango de precio " + min + " - " + max);
        } else {
            System.out.println("Productos encontrados: " + productos.size());
            System.out.println("---------------------------------");
            for (Producto p : productos) {
                System.out.println(p);
            }
        }
    }
    
    private static void ejecutarTestAutomatico() {
        System.out.println("\n===== TEST AUTOMÁTICO CRUD =====");
        
        // Generar un ID único basado en timestamp
        int testId = (int) (System.currentTimeMillis() % 10000);
        
        try {
            // 1. CREATE - Insertar un nuevo producto
            System.out.println("\n----- CREATE (INSERT) -----");
            Producto nuevoProducto = new Producto(testId, "Producto Test", 99.99);
            boolean insertOk = productoDAO.insert(nuevoProducto);
            System.out.println("Inserción exitosa: " + insertOk);
            
            // 2. READ - Leer el producto insertado
            System.out.println("\n----- READ -----");
            Producto productoLeido = productoDAO.read(testId);
            if (productoLeido != null) {
                System.out.println("Producto leído: " + productoLeido);
            } else {
                throw new RuntimeException("Error: No se pudo leer el producto insertado");
            }
            
            // 3. UPDATE - Actualizar el producto
            System.out.println("\n----- UPDATE -----");
            productoLeido.setNombre("Producto Actualizado");
            productoLeido.setPrecio(149.99);
            boolean updateOk = productoDAO.update(productoLeido);
            System.out.println("Actualización exitosa: " + updateOk);
            
            // Verificar la actualización
            Producto productoActualizado = productoDAO.read(testId);
            if (productoActualizado != null) {
                System.out.println("Producto actualizado: " + productoActualizado);
                if (!productoActualizado.getNombre().equals("Producto Actualizado")) {
                    throw new RuntimeException("Error: El nombre no se actualizó correctamente");
                }
            } else {
                throw new RuntimeException("Error: No se pudo leer el producto actualizado");
            }
            
            // 4. DELETE - Eliminar el producto
            System.out.println("\n----- DELETE -----");
            boolean deleteOk = productoDAO.delete(productoActualizado);
            System.out.println("Eliminación exitosa: " + deleteOk);
            
            // Verificar la eliminación
            Producto productoEliminado = productoDAO.read(testId);
            if (productoEliminado == null) {
                System.out.println("El producto fue eliminado correctamente");
            } else {
                throw new RuntimeException("Error: El producto no se eliminó correctamente");
            }
            
            System.out.println("\n¡Test CRUD completado con éxito!");
            
        } catch (Exception e) {
            System.err.println("Error durante el test CRUD: " + e.getMessage());
            e.printStackTrace();
            
            // Intentar limpiar el producto de prueba si existe
            Producto producto = productoDAO.read(testId);
            if (producto != null) {
                productoDAO.delete(producto);
                System.out.println("Producto de prueba eliminado durante la limpieza");
            }
        }
    }
}
