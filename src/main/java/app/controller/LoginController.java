package app.controller;

import app.model.LoginForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the login form data from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Create a new login form object and set the data
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername(username);
        loginForm.setPassword(password);

        // Validate the login form data
        boolean valid = validate(loginForm);

        // If the login form data is valid, redirect to the home page
        // Otherwise, forward to the login page with an error message
        if (valid) {
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    private boolean validate(LoginForm loginForm) {

        return true;
    }
}