package sistema.administrativo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ventana extends JFrame {

    usuario usuSistema[] = new usuario[10];
    JPanel panelInicioSesion;
    JPanel panelControl;
    JPanel panelCrearUsuario;
    int control = 1;
    cliente clientes[] = new cliente[100];
    int controlCliente = 0;
    JPanel panelControlClientes;
    producto productos[] = new producto[100];
    int controlProducto = 0;
    JPanel panelControlProductos; 
    int controlClientes = 2;
    int controlProductos = 1;

    public ventana() {
        objetos();
        crearAdmin();
        crearClientes();
        crearProductos();
    }

    public void crearAdmin() {
        usuSistema[0] = new usuario();
        usuSistema[0].nombreUsuario = "admin";
        usuSistema[0].nombre = "administrador";
        usuSistema[0].contra = "123456";
    }

    public void crearClientes() {
        clientes[0] = new cliente();
        clientes[0].nombre = "Cliente 1";
        clientes[0].edad = 22;
        clientes[0].genero = 'M';
        clientes[0].nit = 150;

        clientes[1] = new cliente();
        clientes[1].nombre = "Cliente 2";
        clientes[1].edad = 25;
        clientes[1].genero = 'F';
        clientes[1].nit = 150;
    }

    public void crearProductos() {
        productos[0] = new producto();
        productos[0].nombre = "Producto 1";
        productos[0].cantidad = 6;
        productos[0].precio = 100;
    }

    public void objetos() {
        panelInicioSesion = new JPanel();
        this.getContentPane().add(panelInicioSesion);
        panelInicioSesion.setLayout(null);

        JLabel lblLogin = new JLabel("Login");
        lblLogin.setBounds(20, 7, 100, 15);
        panelInicioSesion.add(lblLogin);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(60, 40, 200, 25);
        panelInicioSesion.add(lblUsuario);

        JLabel lblContra = new JLabel("Contrase??a");
        lblContra.setBounds(60, 80, 200, 25);
        panelInicioSesion.add(lblContra);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 40, 200, 25);
        panelInicioSesion.add(txtUsuario);

        JTextField txtContra = new JTextField();
        txtContra.setBounds(150, 80, 200, 25);
        panelInicioSesion.add(txtContra);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(158, 125, 180, 35);
        panelInicioSesion.add(btnIngresar);
        ActionListener ingresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (txtUsuario.getText().equals("") || txtContra.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
                } else {
                    recorrerUsuarios(txtUsuario.getText(), txtContra.getText());
                }
            }
        };
        btnIngresar.addActionListener(ingresar);

        JButton btnCrearUsuario = new JButton("Registrarse");
        btnCrearUsuario.setBounds(158, 185, 180, 35);
        panelInicioSesion.add(btnCrearUsuario);
        ActionListener crearUsuario = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                crearUsuario();
                panelCrearUsuario.setVisible(true);
            }

        };
        btnCrearUsuario.addActionListener(crearUsuario);
    }

    public void recorrerUsuarios(String nombreUsuario, String contra) {
        boolean encontrado = false;
        for (int i = 0; i < 10; i++) {
            if (usuSistema[i] != null) {
                if (usuSistema[i].nombreUsuario.equals(nombreUsuario) && usuSistema[i].contra.equals(contra)) {
                    JOptionPane.showMessageDialog(null, "Bienvenido al Sistema usuario " + nombreUsuario);
                    panelControl();
                    encontrado = true;
                    break;
                } else {
                    encontrado = false;
                }
            }

        }
        if (encontrado == false) {
            JOptionPane.showMessageDialog(null, "Datos Incorrectos");
        }

    }

    public void panelControl() {
        panelControl = new JPanel();
        this.getContentPane().add(panelControl);
        panelControl.setLayout(null);
        this.setSize(600, 500);
        this.setTitle("Control principal");
        panelInicioSesion.setVisible(false);

        JButton btnAdminClientes = new JButton("Administraci??n de Clientes");
        btnAdminClientes.setBounds(160, 10, 250, 26);
        panelControl.add(btnAdminClientes);
        ActionListener administrarClientes = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControlCli();
                panelControlClientes.setVisible(true);
            }
        };
        btnAdminClientes.addActionListener(administrarClientes);

        JButton btnAdminProductos = new JButton("Administraci??n de Productos");
        btnAdminProductos.setBounds(160, 70, 250, 26);
        panelControl.add(btnAdminProductos);
        ActionListener administrarProductos = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControlPro();
                panelControlProductos.setVisible(true);
            }
        };
        btnAdminProductos.addActionListener(administrarProductos);

    }

    public void crearUsuario() {
        panelCrearUsuario = new JPanel();
        this.getContentPane().add(panelCrearUsuario);
        panelCrearUsuario.setLayout(null);
        this.setSize(500, 450);
        this.setTitle("Registro de Nuevo Usuario");
        panelInicioSesion.setVisible(false);

        JLabel lblRegistro = new JLabel("Registro de Usuario");
        lblRegistro.setBounds(20, 10, 150, 20);
        panelCrearUsuario.add(lblRegistro);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(50, 40, 150, 20);
        panelCrearUsuario.add(lblUsuario);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(50, 80, 150, 20);
        panelCrearUsuario.add(lblNombre);

        JLabel lblContra = new JLabel("Contrase??a");
        lblContra.setBounds(50, 120, 150, 20);
        panelCrearUsuario.add(lblContra);

        JLabel lblConfirmar = new JLabel("Confirmar Contrase??a");
        lblConfirmar.setBounds(50, 160, 150, 20);
        panelCrearUsuario.add(lblConfirmar);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(200, 40, 150, 20);
        panelCrearUsuario.add(txtUsuario);

        JTextField txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBounds(200, 80, 150, 20);
        panelCrearUsuario.add(txtNombreUsuario);

        JTextField txtContra = new JTextField();
        txtContra.setBounds(200, 120, 150, 20);
        panelCrearUsuario.add(txtContra);

        JTextField txtConfContra = new JTextField();
        txtConfContra.setBounds(200, 160, 150, 20);
        panelCrearUsuario.add(txtConfContra);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(150, 240, 200, 25);
        panelCrearUsuario.add(btnRegistrar);
        ActionListener registro = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (txtUsuario.getText().equals("") || txtNombreUsuario.getText().equals("") || txtContra.getText().equals("") || txtConfContra.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe de llenar todos los Campos");
                } else {
                    if (txtContra.getText().equals(txtConfContra.getText())) {
                        guardarUsuario(txtUsuario.getText(), txtNombreUsuario.getText(), txtContra.getText());
                        txtUsuario.setText("");
                        txtNombreUsuario.setText("");
                        txtContra.setText("");
                        txtConfContra.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Las Contrase??as NO Coinciden");
                    }
                }
            }
        };
        btnRegistrar.addActionListener(registro);

        JButton btnVolver = new JButton("Volver al Inicio");
        btnVolver.setBounds(150, 300, 200, 25);
        panelCrearUsuario.add(btnVolver);
        ActionListener volverInicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelInicioSesion.setVisible(true);
                panelCrearUsuario.setVisible(false);
                volverInicio();
            }
        };
        btnVolver.addActionListener(volverInicio);
    }

    public void volverInicio() {
        this.setTitle("Sistema administrativo de clientes y recursos");
        this.setSize(450, 350);
    }

    public void guardarUsuario(String nombre, String nombreUsuario, String contra) {
        int posicion = 0;
        if (control < 10) {
            for (int i = 0; i < 9; i++) {
                if (usuSistema[i] == null) {
                    posicion = i;
                    break;
                }
            }
            usuSistema[posicion] = new usuario();
            usuSistema[posicion].nombreUsuario = nombre;
            usuSistema[posicion].nombre = nombreUsuario;
            usuSistema[posicion].contra = contra;
            control++;
            JOptionPane.showMessageDialog(null, "Usuario Registrado Exitosamente,total de Usuarios " + control);

        } else {
            JOptionPane.showMessageDialog(null, "NO se pueden registrar m??s usuarios");
        }
    }

    public void panelControlCli() {
        panelControlClientes = new JPanel();
        this.getContentPane().add(panelControlClientes);
        panelControlClientes.setLayout(null);
        this.setSize(750, 500);
        this.setTitle("Administraci??n de Clientes");
        panelControl.setVisible(false);

        DefaultTableModel datosTabla = new DefaultTableModel();
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Edad");
        datosTabla.addColumn("G??nero");
        datosTabla.addColumn("Nit");

        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                String fila[] = {clientes[i].nombre, String.valueOf(clientes[i].edad), String.valueOf(clientes[i].genero), String.valueOf(clientes[i].nit)};
                datosTabla.addRow(fila);
            }
        }

        JTable tablaClientes = new JTable(datosTabla);
        JScrollPane barraTablaClientes = new JScrollPane(tablaClientes);
        barraTablaClientes.setBounds(10, 10, 300, 100);
        panelControlClientes.add(barraTablaClientes);

        DefaultPieDataset datos = new DefaultPieDataset();
        datos.setValue("Maculino", totalHombres());
        datos.setValue("Femenino", totalMujeres());

        JFreeChart graficoCircular = ChartFactory.createPieChart("Generos en el Sistema", datos);
        ChartPanel panelCircular = new ChartPanel(graficoCircular);
        panelCircular.setBounds(10, 120, 300, 300);
        panelControlClientes.add(panelCircular);

        DefaultCategoryDataset datos2 = new DefaultCategoryDataset();
        datos2.addValue(rango18a30(), "18-30", "Edad");
        datos2.addValue(rango31a45(), "31-45", "Edad");
        datos2.addValue(rango45mas(), "Mayor a 45", "Edad");
        JFreeChart graficoColumnas = ChartFactory.createBarChart("Rango de Edades", "Edad", "Escala", datos2, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panelColumnas = new ChartPanel(graficoColumnas);
        panelColumnas.setBounds(400, 120, 300, 300);
        panelControlClientes.add(panelColumnas);

        JButton btnCargarArchivo = new JButton("Buscar Archivo CSV");
        btnCargarArchivo.setBounds(312, 10, 200, 25);
        panelControlClientes.add(btnCargarArchivo);
        ActionListener buscarArachivo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                File archivoSeleccionado;
                JFileChooser VentanaSeleccion = new JFileChooser();
                VentanaSeleccion.showOpenDialog(null);
                archivoSeleccionado = VentanaSeleccion.getSelectedFile();
                if (archivoSeleccionado == null) {
                    JOptionPane.showMessageDialog(null, "Cancelado");
                } else {
                    leerArchivoCSV(archivoSeleccionado.getPath());
                    panelControlClientes.setVisible(false);
                    panelControlCli();
                }

            }
        };
        btnCargarArchivo.addActionListener(buscarArachivo);

        JButton btnReporte = new JButton("Crear Reporte HTML");
        btnReporte.setBounds(520, 10, 200, 25);
        panelControlClientes.add(btnReporte);
        ActionListener crearHTML = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                crearReporte();
            }
        };
        btnReporte.addActionListener(crearHTML);

        JButton btnVolver = new JButton("Volver al Men??");
        btnVolver.setBounds(419, 50, 200, 25);
        panelControlClientes.add(btnVolver);
        ActionListener volverInicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControl.setVisible(true);
                panelControlClientes.setVisible(false);
                volverInicio();
            }
        };
        btnVolver.addActionListener(volverInicio);

    }

    public void ordenar() {
        cliente auxiliar;
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                if (clientes[j + 1] == null) {
                    break;
                } else {
                    if (clientes[j].edad > clientes[j + 1].edad) {
                        auxiliar = clientes[j + 1];
                        clientes[j + 1] = clientes[j];
                        clientes[j] = auxiliar;
                    }
                }
            }
        }
    }

    public void crearReporte() {
        try {
            ordenar();
            PrintWriter escribirCSS = new PrintWriter("reportes/estilo.css", "UTF-8");
            escribirCSS.println("html {  font-size: 20px; font-family: 'Open Sans', sans-serif; }");
            escribirCSS.println("h1 {  font-size: 60px; text-align: center; }");
            escribirCSS.println("p, li {  font-size: 16px;  line-height: 2;  letter-spacing: 1px; }");
            escribirCSS.println("table {  table-layout: fixed;  width:250px;}  td{border: 1px solid black; width: 190px; word-wrap: break-word}");
            escribirCSS.println("html {  background-color: #00539F; }");
            escribirCSS.println("body { width: 970px; margin: 0 auto; background-color: #FF9500; padding: 0 20px 20px 20px; border: 5px solid black; } ");
            escribirCSS.println("h1 { margin: 0; padding: 20px 0; color: #00539F; text-shadow: 3px 3px 1px black; }");
            escribirCSS.close();

            PrintWriter escribir = new PrintWriter("reportes/index.html", "UTF-8");
            escribir.println("<!doctybe html>");
            escribir.println("<html>");
            escribir.println("<head>");
            escribir.println("<title>Reporte del sistema </title>");
            escribir.println("<link rel=\"stylesheet\" href=\"estilo.css\">");
            escribir.println("</head>");
            escribir.println("<body>");
            escribir.println("<h1>Listado de Clientes en el Sistema</h1>");
            escribir.println("<br>");

            escribir.println("<table border = 1>");
            escribir.println("<tr>");
            escribir.println("<td>NIT</td> <td>NOMBRE</td>  <td>EDAD</td>  <td>G??NERO</td>");
            escribir.println("</tr>");

            for (int i = 0; i < 99; i++) {
                if (clientes[i] != null) {
                    escribir.println("<tr>");
                    escribir.println("<td>" + clientes[i].nit + "</td><td>" + clientes[i].nombre + "</td><td>" + clientes[i].edad + "</td><td>" + clientes[i].genero + "</td>");
                    escribir.println("</tr>");
                }
            }
            escribir.println("</table>");

            escribir.println("</body>");
            escribir.println("</html>");
            escribir.close();
            JOptionPane.showMessageDialog(null, "Reporte Creado con ??xito, Se encuentra en la Carpeta REPORTES");
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "NO se pudo Cargar el Reporte");
        }
    }

    public int totalHombres() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].genero == 'M') {
                    total++;
                }
            }

        }
        return total;
    }

    public int totalMujeres() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].genero == 'F') {
                    total++;
                }
            }

        }
        return total;
    }

    public int rango18a30() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].edad >= 18 && clientes[i].edad <= 30) {
                    total++;
                }
            }

        }
        return total;
    }

    public int rango31a45() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].edad >= 31 && clientes[i].edad <= 45) {
                    total++;
                }
            }

        }
        return total;
    }

    public int rango45mas() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].edad > 45) {
                    total++;
                }
            }

        }
        return total;
    }

    public void leerArchivoCSV(String ruta) {
        try {
            BufferedReader archivoTemporal = new BufferedReader(new FileReader(ruta));
            String lineaLeida = "";
            while (lineaLeida != null) {
                lineaLeida = archivoTemporal.readLine();
                if (lineaLeida != null) {
                    String datosSeparados[] = lineaLeida.split(",");

                    int posicion = 0;
                    if (controlClientes < 100) {
                        for (int i = 0; i < 99; i++) {
                            if (clientes[i] == null) {
                                posicion = i;
                                break;
                            }
                        }
                        clientes[posicion] = new cliente();
                        clientes[posicion].nombre = datosSeparados[0];
                        clientes[posicion].edad = Integer.parseInt(datosSeparados[1]);
                        clientes[posicion].genero = datosSeparados[2].charAt(0);
                        clientes[posicion].nit = Integer.parseInt(datosSeparados[3]);
                        controlClientes++;
                    } else {
                        JOptionPane.showMessageDialog(null, "NO se pueden registrar m??s Clientes");
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Clientes Registrados Exitosamente,total de Clientes " + controlClientes);
            archivoTemporal.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No se pudo Abrir el Archivo CSV");
        }

    }

    public void panelControlPro() {
        panelControlProductos = new JPanel();
        this.getContentPane().add(panelControlProductos);
        panelControlProductos.setLayout(null);
        this.setSize(850, 500);
        this.setTitle("Administraci??n de Productos");
        panelControl.setVisible(false);

        DefaultTableModel datosTabla = new DefaultTableModel();
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Precio");
        datosTabla.addColumn("Columna");

        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                String fila[] = {productos[i].nombre, String.valueOf(productos[i].precio), String.valueOf(productos[i].cantidad)};
                datosTabla.addRow(fila);
            }

        }
        JTable tablaProductos = new JTable(datosTabla);
        JScrollPane barraTablaProductos = new JScrollPane(tablaProductos);
        barraTablaProductos.setBounds(10, 10, 300, 100);
        panelControlProductos.add(barraTablaProductos);
        
        DefaultCategoryDataset datos3 = new DefaultCategoryDataset();
        datos3.addValue(rango10a100(), "10-100", "Precio");
        datos3.addValue(rango101a300(), "101-300", "Precio");
        datos3.addValue(rango300mas(), "Mayor a 300", "Precio");
        JFreeChart graficoColumnas = ChartFactory.createBarChart("Rango de Precios", "Precio", "Escala", datos3, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panelColumnas = new ChartPanel(graficoColumnas);
        panelColumnas.setBounds(19, 120, 300, 300);
        panelControlProductos.add(panelColumnas);

        JButton btnCargarArchivo2 = new JButton("Buscar Archivo CSV");
        btnCargarArchivo2.setBounds(312, 10, 200, 25);
        panelControlProductos.add(btnCargarArchivo2);
        ActionListener buscarArchivo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                File archivoSeleccionado;
                JFileChooser ventanaSeleccion = new JFileChooser();
                ventanaSeleccion.showOpenDialog(null);
                archivoSeleccionado = ventanaSeleccion.getSelectedFile();
                if (archivoSeleccionado == null) {
                    JOptionPane.showMessageDialog(null, "Cancelado");
                } else {
                    leerArchivoCSV2(archivoSeleccionado.getPath());
                    panelControlProductos.setVisible(false);
                    panelControlPro();
                }
            }
        };
        btnCargarArchivo2.addActionListener(buscarArchivo); 
 
        JButton btnReporte = new JButton("Crear Reporte HTML");
        btnReporte.setBounds(520, 10, 200, 25);
        panelControlProductos.add(btnReporte);
        ActionListener crearHTML = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                crearReporte2();
            }
        };
        btnReporte.addActionListener(crearHTML);
        
        JButton btnVolver = new JButton("Volver al Men??");
        btnVolver.setBounds(312, 50, 200, 25);
        panelControlProductos.add(btnVolver);
        ActionListener volverInicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControl.setVisible(true);
                panelControlProductos.setVisible(false);
                volverInicio();
            }
        };
        btnVolver.addActionListener(volverInicio);
    }
    
public void ordenar2() {
        producto auxiliar;
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                if (productos[j + 1] == null) {
                    break;
                } else {
                    if (productos[j].precio > productos[j + 1].precio) {
                        auxiliar = productos[j + 1];
                        productos[j + 1] = productos[j];
                        productos[j] = auxiliar;
                    }
                }
            }
        }
    }

   public void crearReporte2(){
       try {
            ordenar2();
            PrintWriter escribirCSS = new PrintWriter("reportes2/estilo.css", "UTF-8");
            escribirCSS.println("html {  font-size: 20px; font-family: 'Open Sans', sans-serif; }");
            escribirCSS.println("h1 {  font-size: 60px; text-align: center; }");
            escribirCSS.println("p, li {  font-size: 16px;  line-height: 2;  letter-spacing: 1px; }");
            escribirCSS.println("table {  table-layout: fixed;  width:250px;}  td{border: 1px solid black; width: 190px; word-wrap: break-word}");
            escribirCSS.println("html {  background-color: #00539F; }");
            escribirCSS.println("body { width: 970px; margin: 0 auto; background-color: #FF9500; padding: 0 20px 20px 20px; border: 5px solid black; } ");
            escribirCSS.println("h1 { margin: 0; padding: 20px 0; color: #00539F; text-shadow: 3px 3px 1px black; }");
            escribirCSS.close();

            PrintWriter escribir = new PrintWriter("reportes2/index.html", "UTF-8");
            escribir.println("<!DOCTYPE HTML>");
            escribir.println("<html>");
            escribir.println("<head>");
            escribir.println("<title>Reporte del sistema </title>");
            escribir.println("<link rel=\"stylesheet\" href=\"estilo.css\">");
            escribir.println("</head>");
            escribir.println("<body>");
            escribir.println("<h1>Listado de Productos en el Sistema</h1>");
            escribir.println("<br>");

            escribir.println("<table border = 1>");
            escribir.println("<tr>");
            escribir.println("<td>NIT</td> <td>NOMBRE</td>  <td>PRECIO</td>  <td>CANTIDAD</td>");
            escribir.println("</tr>");

            for (int i = 0; i < 99; i++) {
                if (productos[i] != null) {
                    escribir.println("<tr>");
                    escribir.println("<td>" + productos[i].nombre + "</td><td>" + productos[i].precio + "</td><td>" + productos[i].cantidad + "</td>");
                    escribir.println("</tr>");
                }
            }
            escribir.println("</table>");
            
            escribir.println("</body>");
            escribir.println("</html>");
            escribir.close();
            JOptionPane.showMessageDialog(null, "Reporte Creado con ??xito, Se encuentra en la Carpeta REPORTES2");
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "NO se pudo Cargar el Reporte");
        }
   }
    
    public int rango10a100() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                if (productos[i].precio >= 10 && productos[i].precio <= 100) {
                    total++;
                }
            }

        }
        return total;
    }
    
    public int rango101a300() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                if (productos[i].precio >= 101 && productos[i].precio <= 300) {
                    total++;
                }
            }

        }
        return total;
    }
    
    public int rango300mas() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                if (productos[i].precio > 300) {
                    total++;
                }
            }

        }
        return total;
    }
    
    public void leerArchivoCSV2(String ruta) {
        try {
            BufferedReader archivoTemporal = new BufferedReader(new FileReader(ruta));
            String lineaLeida = "";
            while (lineaLeida != null) {
                lineaLeida = archivoTemporal.readLine();
                if (lineaLeida != null) {
                    String datosSeparados[] = lineaLeida.split(",");
                    
                    int posicion = 0;
                    if (controlProducto < 100) {
                        for (int i = 0; i < 99; i++) {
                            if (productos[i] == null) {
                                posicion = i;
                                break;
                            }
                        }
                        productos[posicion] = new producto();
                        productos[posicion].nombre = datosSeparados[0];
                        productos[posicion].precio = Float.parseFloat(datosSeparados[1]);
                        productos[posicion].cantidad = Integer.parseInt(datosSeparados[2]);
                        controlProducto++;
                    } else {
                        JOptionPane.showMessageDialog(null, "NO se pueden registrar Productos");
                    }
                }
            }
             JOptionPane.showMessageDialog(null, "Producto Registrado Exitosamente,total de Productos " + controlProducto);
            archivoTemporal.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No se pudo Abrir el Arachivo CSV");
        }
    }
}
