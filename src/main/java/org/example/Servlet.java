package org.example;
import jakarta.json.Json;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "HelloServlet", urlPatterns = {"/hello", "/addCar"})
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<h1>Здарова, КЛОУН</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if ("/addCar".equals(path)) {
            ServletInputStream inputStream = req.getInputStream();
            JsonReader jsonReader = Json.createReader(inputStream);
            JsonObject jsonObject = jsonReader.readObject();


            String carMarkName = jsonObject.getString("carMarkName");
            String carModelName = jsonObject.getString("carModelName");
            String year = jsonObject.getString("year");
            int price = jsonObject.getInt("price");

            try {
                DB.openConnection(getServletContext());
                DB.addCar(carMarkName,carModelName,year,price);
                DB.closeConnection();
                resp.getWriter().println("Car added successfully!");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            PrintWriter out = resp.getWriter();
            out.println("<h1>KLOUN!</h1>");
        }
    }
}
