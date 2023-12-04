package com.ex;

import com.ex.model.HibernateUtil;
import com.ex.model.Subject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

@WebServlet(name = "DeleteSServlet", value = "/deleteS")
public class DeleteSServlet extends HttpServlet {

    private Long id;
    private Subject s;
    private String idParam;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        idParam = request.getParameter("id");
        if (idParam != null) {
            id = Long.parseLong(idParam);

            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            s = session.get(Subject.class, id);
            transaction.commit();
            session.close();

            request.setAttribute("id", s.getId());
            request.setAttribute("name", s.getName());
            request.setAttribute("dayOfWeek", s.getDayOfWeek());
            request.setAttribute("Classrooms", s.getClassrooms());


        } else {
            request.setAttribute("title", "Добавление");
        }


        request.getRequestDispatcher("/deleteS.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(s);
        transaction.commit();
        session.close();
        response.sendRedirect("/example");

    }
}