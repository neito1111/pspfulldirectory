package com.ex;

import com.ex.model.Subject;
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
import java.util.List;

@WebServlet(name = "example", value = "/example")
public class TextServlet extends HttpServlet {

    private Double date;
    private List<Subject> subjects;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        subjects = session.createQuery("FROM Subject order by id asc", Subject.class).list();
        transaction.commit();
        session.close();
        request.setAttribute("subjects", subjects);
        getServletContext().getRequestDispatcher("/subjects.jsp").forward(request, response); //переадресация на страницу
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String kol = request.getParameter("kol");
        if(kol!=null && !kol.isEmpty()){
            int kol = Integer.valueOf(kol);
            Session session = HibernateUtil.getSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            subjects = session
                    .createQuery("FROM Subject w WHERE s.kol <= :kol order by s.id asc ", Subject.class)
                    .setParameter("kol", kol)
                    .list();
            transaction.commit();
            session.close();
        } else {
            Session session = HibernateUtil.getSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            subjects = session.createQuery("FROM Subject order by id asc ", Subject.class).list();
            transaction.commit();
            session.close();
        }

        request.setAttribute("subjects", subjects);

        request.getRequestDispatcher("/subjects.jsp").forward(request, response);
    }
}