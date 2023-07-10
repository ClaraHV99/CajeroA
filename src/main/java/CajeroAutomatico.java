import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CajeroAutomatico extends JFrame {
    private double saldo;
    private String clave;

    private JLabel labelSaldo;
    private JPasswordField fieldClave;
    private JTextField fieldMonto;

    public CajeroAutomatico(double saldoInicial, String clave) {
        this.saldo = saldoInicial;
        this.clave = clave;

        setTitle("Cajero Automático");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 255, 204)); // Color de fondo

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSuperior.setBackground(new Color(153, 204, 255)); // Color de fondo
        labelSaldo = new JLabel("Saldo actual: $" + String.format("%.2f", saldo));
        panelSuperior.add(labelSaldo);
        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCentral.setBackground(new Color(255, 255, 204)); // Color de fondo

        JLabel labelClave = new JLabel("Clave:");
        panelCentral.add(labelClave);

        fieldClave = new JPasswordField(10);
        panelCentral.add(fieldClave);

        JLabel labelMonto = new JLabel("Monto:");
        panelCentral.add(labelMonto);

        fieldMonto = new JTextField(10);
        panelCentral.add(fieldMonto);

        JButton buttonDepositar = new JButton("Depositar");
        buttonDepositar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositar();
            }
        });
        panelCentral.add(buttonDepositar);

        JButton buttonRetirar = new JButton("Retirar");
        buttonRetirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retirar();
            }
        });
        panelCentral.add(buttonRetirar);

        add(panelCentral, BorderLayout.CENTER);

        JButton buttonSalir = new JButton("Salir");
        buttonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(buttonSalir, BorderLayout.SOUTH);
    }

    private void depositar() {
        String inputClave = new String(fieldClave.getPassword());
        double monto = Double.parseDouble(fieldMonto.getText());

        if (inputClave.equals(clave)) {
            saldo += monto;
            labelSaldo.setText("Saldo actual: $" + String.format("%.2f", saldo));
        } else {
            JOptionPane.showMessageDialog(this, "Clave incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void retirar() {
        String inputClave = new String(fieldClave.getPassword());
        double monto = Double.parseDouble(fieldMonto.getText());

        if (inputClave.equals(clave)) {
            if (monto <= saldo) {
                saldo -= monto;
                labelSaldo.setText("Saldo actual: $" + String.format("%.2f", saldo));
            } else {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Clave incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        double saldoInicial = 1000.0;
        String clave = "1234";

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                CajeroAutomatico cajero = new CajeroAutomatico(saldoInicial, clave);
                cajero.setVisible(true);
            }
        });
    }
}
