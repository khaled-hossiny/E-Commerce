package servlet;

import utility.PropertiesUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;

@WebServlet("/file/*")
public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        File image = new File(URLDecoder.decode(path, "UTF-8"));
        ServletContext sc = req.getServletContext();
        String contentType = sc.getMimeType(image.getName());
        resp.reset();
        resp.setContentType(contentType);
        resp.setHeader("Content-Length", String.valueOf(image.length()));
        Files.copy(image.toPath(), resp.getOutputStream());
    }
}
