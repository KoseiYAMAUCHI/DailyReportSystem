package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report;
import models.Yoine;
import models.YoineLogic;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	 // 初回起動を判定するための処理
        // アプリケーションスコープから値を取得
        ServletContext sc = this.getServletContext();
        Yoine y = (Yoine) sc.getAttribute("yoine");

        // 初回起動判定の続き
        // アプリケーションスコープに値がなければnewする

        if(null == y) {
            y = new Yoine();
            sc.setAttribute("yoine", y);
        }

        // リクエストパラメーターの取得
            request.setCharacterEncoding("UTF-8");
            String yoine = request.getParameter("action");


        // いいねボタン押されたら
        if (yoine != null) {

            // YoineLogicでいいねを加算
            YoineLogic yl = new YoineLogic();
            yl.yoinePlus(y);

            // いいねの数をアプリケーションスコープに保存
            sc.setAttribute("yoine", y);
        }


        EntityManager em = DBUtil.createEntityManager();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("report", r);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
        rd.forward(request, response);

    }

}

