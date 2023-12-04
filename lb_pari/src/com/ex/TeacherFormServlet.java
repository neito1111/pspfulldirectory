package com.ex;

import com.ex.model.HibernateUtil;
import com.ex.model.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

@WebServlet(name = "TeacherFormServlet", value = "/editT")
public class TeacherFormServlet extends HttpServlet {

    private Long id;
    private Teacher t;
    private String idParam;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        idParam = request.getParameter("id");
        if (idParam != null) {
            id = Long.parseLong(idParam);

            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            t = session.get(Teacher.class, id);
            transaction.commit();
            session.close();

            request.setAttribute("name", t.getName());
            request.setAttribute("Subject", t.getSubjects());
            request.setAttribute("weeklyClasses", t.getWeeklyClasses());
            request.setAttribute("studentsPerClass", t.getStudentsPerClass());

        } else {
            request.setAttribute("title", "Добавление");
        }


        request.getRequestDispatcher("/TForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(idParam!=null){
            t.setName(request.getParameter("name"));
            t.setSubjects(request.getParameter("subject"));
            t.setWeeklyClasses(request.getParameter("weeklyClasses"));
            t.setStudentsPerClass(request.getParameter("studentsPerClass"));
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
                session.update(t);
            transaction.commit();
            session.close();
        } else {
            System.out.println('d');
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(new Manufacturer(request.getParameter("name"), request.getParameter("subject"), request.getParameter("weeklyClasses"), request.getParameter("studentsPerClass")));
            transaction.commit();
            session.close();
        }
        response.sendRedirect("/teacher");

    }
}