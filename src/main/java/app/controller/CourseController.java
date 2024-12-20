package app.controller;

import app.dao.CourseDAO;
import app.model.Course;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/Courses")
public class CourseController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourseDAO courseDAO;

    public CourseController() {
        super();
        courseDAO = new CourseDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "list":
                    listCourses(request, response);
                    break;
                case "new":
                    showNewCourseForm(request, response);
                    break;
                case "edit":
                    showEditCourseForm(request, response);
                    break;
                case "delete":
                    deleteCourse(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "new":
                    addCourse(request, response);
                    break;
                case "edit":
                    updateCourse(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void listCourses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> courses = courseDAO.getAll();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("list_courses.jsp").forward(request, response);
    }

    private void showNewCourseForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("new_course.jsp").forward(request, response);
    }

    private void showEditCourseForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Course course = courseDAO.read(id);
        if (course != null) {
            request.setAttribute("course", course);
            request.getRequestDispatcher("edit_course.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void addCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("courseName");
        String instructor = request.getParameter("instructor");
        String description = request.getParameter("description");
        Course course = new Course(0,courseName, instructor, description);
        courseDAO.create(course);
        response.sendRedirect(request.getContextPath() + "/course?action=list");
    }

    private void updateCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String courseName = request.getParameter("courseName");
        String instructor = request.getParameter("instructor");
        String description = request.getParameter("description");
        Course course = new Course(id, courseName, instructor, description);
        courseDAO.update(course);
        response.sendRedirect(request.getContextPath() + "/course?action=list");
    }

    private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        courseDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/course?action=list");
    }
}
