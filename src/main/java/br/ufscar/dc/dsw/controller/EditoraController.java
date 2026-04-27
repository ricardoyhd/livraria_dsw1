package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.EditoraDAO;
import br.ufscar.dc.dsw.dao.LivroDAO;
import br.ufscar.dc.dsw.domain.Editora;
import br.ufscar.dc.dsw.domain.Livro;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(urlPatterns = "/editoras/*")
public class EditoraController extends HttpServlet{

        private static final long serialVersionUID = 1L;

        private EditoraDAO dao;

        @Override
        public void init() {
            dao = new EditoraDAO();
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException {
            doGet(request, response);
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException {

            String action = request.getPathInfo();
            if (action == null) {
                action = "";
            }

            try {
                switch (action) {
                    case "/cadastro":
                        apresentaFormCadastro(request, response);
                        break;
                    case "/insercao":
                        insere(request, response);
                        break;
                    case "/remocao":
                        remove(request, response);
                        break;
                    case "/edicao":
                        apresentaFormEdicao(request, response);
                        break;
                    case "/atualizacao":
                        atualize(request, response);
                        break;
                    default:
                        lista(request, response);
                        break;
                }
            } catch (RuntimeException | IOException | ServletException e) {
                throw new ServletException(e);
            }
        }

        private void lista(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            List<Editora> listaEditoras = dao.getAll();
            request.setAttribute("listaEditoras", listaEditoras);
            request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/editora/lista.jsp");
            dispatcher.forward(request, response);
        }


        private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            RequestDispatcher dispatcher = request.getRequestDispatcher("/editora/formulario.jsp");
            dispatcher.forward(request, response);
        }

        private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            Long id = Long.parseLong(request.getParameter("id"));
            Editora editora = dao.get(id);
            request.setAttribute("editora", editora);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/editora/formulario.jsp");
            dispatcher.forward(request, response);
        }

        private void insere(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");

            String cnpj = request.getParameter("cnpj");
            String nome = request.getParameter("nome");


            Editora editora = new Editora(cnpj, nome);
            dao.insert(editora);
            response.sendRedirect("lista");
        }

        private void atualize(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            request.setCharacterEncoding("UTF-8");
            String cnpj = request.getParameter("cnpj");
            String nome = request.getParameter("nome");
            Long id = Long.parseLong(request.getParameter("id"));


            Editora editora = new Editora(id,cnpj, nome);
            dao.update(editora);
            response.sendRedirect("lista");
        }

        private void remove(HttpServletRequest request, HttpServletResponse response)
                throws IOException {
            Long id = Long.parseLong(request.getParameter("id"));

            Editora editora = new Editora(id);
            dao.delete(editora);
            response.sendRedirect("lista");
        }








}
