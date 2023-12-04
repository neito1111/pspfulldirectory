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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "teacher", value = "/teacher")
public class TeacherServlet extends HttpServlet {



    private List<String> subj;
    private List<Teacher> teachers;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        teachers = session.createQuery("FROM Teacher order by id", Teacher.class).list;
        subj = session.createQuery("select t.subj from Teacher t group by t.subj").list();
        transaction.commit();
        session.close();

        request.setAttribute("subj", subj);
        request.setAttribute("teachers", teachers);
        getServletContext().getRequestDispatcher("/teacher.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String selectedSubj = request.getParameter("selectedsubj");
        if(selectedCountry!=null){

            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            teachers = session
                    .createQuery("FROM Teacher t  where t.subj = :subj order by t.id", Teacher.class)
                    .setParameter("subj", selectedSubj)
                    .list();
            transaction.commit();
            session.close();
        }


        request.setAttribute("subj", subj);
        request.setAttribute("teacher", teachers);
        getServletContext().getRequestDispatcher("/teacher.jsp").forward(request, response);

    }

    @Override
    public void init() throws ServletException {
        super.init();


        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        teachers = session.createQuery("FROM Teacher order by id", Teacher.class).list();
        countries = session.createQuery("select t.country from Teacher t group by t.subj").list();
        transaction.commit();
        session.close();
    }

}
