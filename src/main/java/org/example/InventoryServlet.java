
        package org.example;

import java.io.*;
import java.sql.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/InventoryServlet")
public class InventoryServlet extends HttpServlet {

    String url = "jdbc:mysql://localhost:3306/inventory_db";
    String user = "root";
    String password = "1234";

    // Insert item
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException{

        int code = Integer.parseInt(request.getParameter("item_code"));
        String name = request.getParameter("item_name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = con.prepareStatement(
                         "INSERT INTO inventory VALUES (?, ?, ?, ?)")) {

                ps.setInt(1, code);
                ps.setString(2, name);
                ps.setInt(3, quantity);
                ps.setDouble(4, price);

                ps.executeUpdate();
            }

            response.sendRedirect("InventoryServlet");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // Display all items
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> items = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(url, user, password);
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM inventory")) {

                while (rs.next()) {
                    items.add(new String[]{
                            rs.getString("item_code"),
                            rs.getString("item_name"),
                            rs.getString("quantity"),
                            rs.getString("price")
                    });
                }
            }

            request.setAttribute("items", items);

            request.getRequestDispatcher("inventory.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}