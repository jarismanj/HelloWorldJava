package helloworld;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Dev JarismanJ to share scientific calculator code on java

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ScientificCalculator extends JFrame implements ActionListener {

    private JTextPane displayPane;
    private StringBuilder currentInput = new StringBuilder();
    private double num1 = 0;
    private String operator = "";
    private boolean isNewOp = true;
    private final DecimalFormat df = new DecimalFormat("#.######");

    // Skema Warna Paparan Matematik (Monokai / Dark Academic Style)
    private final String COLOR_FUNC = "#E6DB74";   // Kuning keemasan untuk fungsi (sin, cos, log)
    private final String COLOR_OP   = "#F92672";   // Merah jambu cerah untuk simbol operator (+, -, ×, ÷)
    private final String COLOR_NUM  = "#66D9EF";   // Sian terang untuk nilai/angka
    private final String COLOR_EQ   = "#FD971F";   // Jingga untuk tanda sama dengan
    private final String COLOR_RES  = "#A6E22E";   // Hijau zamrud untuk hasil jawapan utama
    private final String COLOR_NOTE = "#75715E";   // Kelabu bagi notasi/unit

    public ScientificCalculator() {
        setTitle("Kalkulator Saintifik Profesional");
        setSize(420, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(24, 24, 27));

        // 1. Skrin Paparan Berformat HTML
        displayPane = new JTextPane();
        displayPane.setContentType("text/html");
        displayPane.setEditable(false);
        displayPane.setBackground(new Color(39, 40, 34));
        displayPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        updateDisplay("", "0", false);

        JScrollPane scrollPane = new JScrollPane(displayPane);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));
        scrollPane.setPreferredSize(new Dimension(380, 130));
        add(scrollPane, BorderLayout.NORTH);

        // 2. Panel Butang Kalkulator
        JPanel keypadPanel = new JPanel();
        keypadPanel.setLayout(new GridLayout(6, 5, 6, 6));
        keypadPanel.setBackground(new Color(24, 24, 27));
        keypadPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

        String[] buttons = {
            "sin",  "cos",  "tan",  "log",  "ln",
            "√",    "x²",   "1/x",  "π",    "C",
            "7",    "8",    "9",    "÷",    "DEL",
            "4",    "5",    "6",    "×",    "( - )",
            "1",    "2",    "3",    "-",    "%",
            "0",    ".",    "=",    "+",    "e"
        };

        for (String text : buttons) {
            JButton btn = createStyledButton(text);
            btn.addActionListener(this);
            keypadPanel.add(btn);
        }

        add(keypadPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);

        // Penggayaan warna butang mengikut jenis fungsi
        if (text.matches("[0-9]|\\.")) {
            btn.setBackground(new Color(45, 45, 50));
            btn.setForeground(Color.WHITE);
        } else if (text.equals("=")) {
            btn.setBackground(new Color(39, 174, 96));
            btn.setForeground(Color.WHITE);
        } else if (text.equals("C") || text.equals("DEL")) {
            btn.setBackground(new Color(192, 57, 43));
            btn.setForeground(Color.WHITE);
        } else if (text.matches("[+\\-×÷%]")) {
            btn.setBackground(new Color(230, 126, 34));
            btn.setForeground(Color.WHITE);
        } else {
            btn.setBackground(new Color(60, 64, 75));
            btn.setForeground(new Color(200, 220, 255));
        }

        return btn;
    }

    // Mengemas kini paparan dengan pewarnaan struktur matematik HTML
    private void updateDisplay(String expressionHtml, String mainValue, boolean isFinalResult) {
        String html;
        if (isFinalResult) {
            html = "<html><body style='font-family: Consolas, monospace; text-align: right; margin: 0;'>"
                    + "<div style='font-size: 14px; margin-bottom: 8px;'>" + expressionHtml + "</div>"
                    + "<div style='font-size: 22px; font-weight: bold;'>"
                    + "<span style='color: " + COLOR_EQ + ";'>= </span>"
                    + "<span style='color: " + COLOR_RES + ";'>" + mainValue + "</span>"
                    + "</div>"
                    + "</body></html>";
        } else {
            html = "<html><body style='font-family: Consolas, monospace; text-align: right; margin: 0;'>"
                    + "<div style='font-size: 13px; color: " + COLOR_NOTE + "; margin-bottom: 8px;'>" 
                    + (expressionHtml.isEmpty() ? "&nbsp;" : expressionHtml) + "</div>"
                    + "<div style='font-size: 24px; color: " + COLOR_NUM + "; font-weight: bold;'>" 
                    + mainValue + "</div>"
                    + "</body></html>";
        }
        displayPane.setText(html);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9]")) {
            if (isNewOp) {
                currentInput.setLength(0);
                isNewOp = false;
            }
            currentInput.append(cmd);
            updateDisplay("", currentInput.toString(), false);
        } else if (cmd.equals(".")) {
            if (isNewOp) {
                currentInput.setLength(0);
                currentInput.append("0");
                isNewOp = false;
            }
            if (!currentInput.toString().contains(".")) {
                currentInput.append(".");
            }
            updateDisplay("", currentInput.toString(), false);
        } else if (cmd.equals("π")) {
            currentInput.setLength(0);
            currentInput.append(Math.PI);
            isNewOp = false;
            updateDisplay("", df.format(Math.PI), false);
        } else if (cmd.equals("e")) {
            currentInput.setLength(0);
            currentInput.append(Math.E);
            isNewOp = false;
            updateDisplay("", df.format(Math.E), false);
        } else if (cmd.equals("C")) {
            currentInput.setLength(0);
            num1 = 0;
            operator = "";
            isNewOp = true;
            updateDisplay("", "0", false);
        } else if (cmd.equals("DEL")) {
            if (currentInput.length() > 0) {
                currentInput.deleteCharAt(currentInput.length() - 1);
                String val = currentInput.length() > 0 ? currentInput.toString() : "0";
                updateDisplay("", val, false);
            }
        } else if (cmd.equals("( - )")) {
            if (currentInput.length() > 0) {
                double val = Double.parseDouble(currentInput.toString()) * -1;
                currentInput.setLength(0);
                currentInput.append(df.format(val));
                updateDisplay("", currentInput.toString(), false);
            }
        } else if (isSingleVariableFunction(cmd)) {
            executeScientificFunction(cmd);
        } else if (cmd.matches("[+\\-×÷%]")) {
            if (currentInput.length() > 0) {
                num1 = Double.parseDouble(currentInput.toString());
                operator = cmd;
                isNewOp = true;
                String expr = "<span style='color: " + COLOR_NUM + ";'>" + df.format(num1) + "</span> "
                            + "<span style='color: " + COLOR_OP + ";'>" + operator + "</span>";
                updateDisplay(expr, df.format(num1), false);
            }
        } else if (cmd.equals("=")) {
            calculateBinaryResult();
        }
    }

    private boolean isSingleVariableFunction(String cmd) {
        return cmd.matches("sin|cos|tan|log|ln|√|x²|1/x");
    }

    private void executeScientificFunction(String func) {
        if (currentInput.length() == 0) return;
        double val = Double.parseDouble(currentInput.toString());
        double result = 0;
        String formattedFormula = "";

        switch (func) {
            case "sin":
                result = Math.sin(Math.toRadians(val));
                formattedFormula = "<span style='color:" + COLOR_FUNC + ";'>sin</span>(" 
                                 + "<span style='color:" + COLOR_NUM + ";'>" + df.format(val) + "°</span>)";
                break;
            case "cos":
                result = Math.cos(Math.toRadians(val));
                formattedFormula = "<span style='color:" + COLOR_FUNC + ";'>cos</span>(" 
                                 + "<span style='color:" + COLOR_NUM + ";'>" + df.format(val) + "°</span>)";
                break;
            case "tan":
                result = Math.tan(Math.toRadians(val));
                formattedFormula = "<span style='color:" + COLOR_FUNC + ";'>tan</span>(" 
                                 + "<span style='color:" + COLOR_NUM + ";'>" + df.format(val) + "°</span>)";
                break;
            case "log":
                result = Math.log10(val);
                formattedFormula = "<span style='color:" + COLOR_FUNC + ";'>log<sub>10</sub></span>(" 
                                 + "<span style='color:" + COLOR_NUM + ";'>" + df.format(val) + "</span>)";
                break;
            case "ln":
                result = Math.log(val);
                formattedFormula = "<span style='color:" + COLOR_FUNC + ";'>ln</span>(" 
                                 + "<span style='color:" + COLOR_NUM + ";'>" + df.format(val) + "</span>)";
                break;
            case "√":
                result = Math.sqrt(val);
                formattedFormula = "<span style='color:" + COLOR_FUNC + ";'>&radic;</span>(" 
                                 + "<span style='color:" + COLOR_NUM + ";'>" + df.format(val) + "</span>)";
                break;
            case "x²":
                result = Math.pow(val, 2);
                formattedFormula = "<span style='color:" + COLOR_NUM + ";'>" + df.format(val) + "</span>" 
                                 + "<sup style='color:" + COLOR_FUNC + ";'>2</sup>";
                break;
            case "1/x":
                result = 1 / val;
                formattedFormula = "1 / (<span style='color:" + COLOR_NUM + ";'>" + df.format(val) + "</span>)";
                break;
        }

        currentInput.setLength(0);
        currentInput.append(df.format(result));
        isNewOp = true;
        updateDisplay(formattedFormula, df.format(result), true);
    }

    private void calculateBinaryResult() {
        if (operator.isEmpty() || currentInput.length() == 0) return;

        double num2 = Double.parseDouble(currentInput.toString());
        double result = 0;

        switch (operator) {
            case "+": result = num1 + num2; break;
            case "-": result = num1 - num2; break;
            case "×": result = num1 * num2; break;
            case "÷":
                if (num2 == 0) {
                    updateDisplay("<span style='color:" + COLOR_OP + ";'>Ralat Pembahagian</span>", "Indeterminate", true);
                    currentInput.setLength(0);
                    operator = "";
                    isNewOp = true;
                    return;
                }
                result = num1 / num2;
                break;
            case "%": result = num1 % num2; break;
        }

        String expressionHtml = "<span style='color:" + COLOR_NUM + ";'>" + df.format(num1) + "</span> "
                              + "<span style='color:" + COLOR_OP + ";'>" + operator + "</span> "
                              + "<span style='color:" + COLOR_NUM + ";'>" + df.format(num2) + "</span>";

        updateDisplay(expressionHtml, df.format(result), true);
        currentInput.setLength(0);
        currentInput.append(df.format(result));
        operator = "";
        isNewOp = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ScientificCalculator().setVisible(true);
        });
    }
}