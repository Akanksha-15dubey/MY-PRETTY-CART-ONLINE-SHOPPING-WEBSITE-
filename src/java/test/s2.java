/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LENOVO
 */
public class s2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
       try
       {
            Connection conn =DB.createConn();
            Statement st=conn.createStatement();
            String un=request.getParameter("t1");
            String up=request.getParameter("t2");
            String sql="select * from SHOP.Login";
             ResultSet rs=st.executeQuery(sql);
             int f=0;
             String mail="";
            while(rs.next())
            {
                    if((un.equals(rs.getString("uname")))&&(up.equals(rs.getString("upass"))))
                    {
                        mail=rs.getString("umail");
                        f=1;
                        break;
                    }
                    else
                    {
                         f=0;

                    }
            }
            if(f==1)
            {
               HttpSession session=request.getSession();
               session.setAttribute("smail",mail);
               response.sendRedirect("mens.jsp");
            }
           else
            {
                        out.print("<script>");
                         out.print("alert('Invalid Username or Email')");
                         out.print("</script>");
                         response.sendRedirect("index.html");
            }
        }
       catch(Exception e)
       {
           out.print(e);
       }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
