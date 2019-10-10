package by.htp.jd2.command.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import by.htp.jd2.command.Command;

public class UploadFile extends HttpServlet implements Command {

    private static final long serialVersionUID = 3288090851335589999L;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("description " + request.getParameter("description"));
        System.out.println("file " + request.getParameter("file"));
        try {
            Part part = request.getPart("file"); // Retrieves <input type="file" name="file">
            System.out.println("getSubmittedFileName()" + part.getSubmittedFileName());
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

            System.out.println("fileName " + fileName);
            try (InputStream input = part.getInputStream()) {
                System.out.println("input " + input.available());
                String upload = getServletConfig().getInitParameter("upload");
                System.out.println("upload " + upload);
                File tempFile = File.createTempFile("xxx_", "_zzz", new File(upload));
                System.out.println("tempFile: " + tempFile);
                Files.copy(input, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

    }

}
