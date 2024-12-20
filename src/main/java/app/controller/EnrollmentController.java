package app.controller;

import app.dao.*;
import app.model.*;



import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/Enrollment")
public class EnrollmentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EnrollmentDAO enrollmentDAO;

    public EnrollmentController() {
        super();
        enrollmentDAO = new EnrollmentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listEnrollments(request, response);
                break;
            case "showAddForm":
                showAddForm(request, response);
                break;
            case "add":
                addEnrollment(request, response);
                break;
            case "showEditForm":
                showEditForm(request, response);
                break;
            case "update":
                updateEnrollment(request, response);
                break;
            case "delete":
                deleteEnrollment(request, response);
                break;
            default:
                showErrorPage(request, response, "Invalid Action");
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listEnrollments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Enrollment> enrollments = enrollmentDAO.getAll();
        request.setAttribute("enrollments", enrollments);
        request.getRequestDispatcher("list-enrollments.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("add-enrollment.jsp").forward(request, response);
    }

    private void addEnrollment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        int courseId = Integer.parseInt(request.getParameter("course_id"));
        float mark = Float.parseFloat(request.getParameter("mark"));

        Enrollment newEnrollment = new Enrollment(0,studentId, courseId, mark);
        Enrollment enrollment = enrollmentDAO.create(newEnrollment);
        if (enrollment != null) {
            response.sendRedirect(request.getContextPath() + "/enrollment?action=list");
        } else {
            showErrorPage(request, response, "Error adding enrollment");
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int enrollmentId = Integer.parseInt(request.getParameter("enrollment_id"));
        Enrollment enrollment = enrollmentDAO.read(enrollmentId);
        request.setAttribute("enrollment", enrollment);
        request.getRequestDispatcher("edit-enrollment.jsp").forward(request, response);
    }

    private void updateEnrollment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int enrollmentId = Integer.parseInt(request.getParameter("enrollment_id"));
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        int courseId = Integer.parseInt(request.getParameter("course_id"));
        float mark = Float.parseFloat(request.getParameter("mark"));

        Enrollment newEnrollment = new Enrollment(enrollmentId, studentId, courseId, mark);
        Enrollment enrollment = enrollmentDAO.update(newEnrollment);
        if (enrollment != null) {
            response.sendRedirect(request.getContextPath() + "/enrollment?action=list");
        } else {
            showErrorPage(request, response, "Error updating enrollment");
        }
    }
    private void deleteEnrollment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int enrollmentId = Integer.parseInt(request.getParameter("enrollment_id"));
        boolean success = enrollmentDAO.delete(enrollmentId);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/enrollment?action=list");
        } else {
            showErrorPage(request, response, "Error deleting enrollment");
        }
    }
    private void showErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }

}
