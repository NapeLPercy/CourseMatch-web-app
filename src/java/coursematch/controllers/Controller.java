package coursematch.controllers;

import coursematch.entities.Student;
import coursematch.services.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.json.JSONObject;

public class Controller extends HttpServlet {

    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getHeader("type");
        JSONObject obj = new JSONObject();
        Student one = new Student("ANape", "Lekoloane", 10, "High Certificate", null);
        Student two = new Student("ZSeja", "Lekoloane", 30, "Diploma", null);
        Student three = new Student("KRinah", "Lekoloane", 47, "Barchelor", null);

        ArrayList<Student> stds = new ArrayList<>();
        stds.add(one);
        stds.add(two);
        stds.add(three);

        //int id = Integer.parseInt(request.getSession().getAttribute("student_id_number").toString());
        if (type.equals("name")) {

            Collections.sort(stds, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    if (o1.getName().compareTo(o2.getName()) < 0) {
                        return 1;
                    }

                    return -1;
                }
            });

            obj.put("data", stds);

        } else if (type.equals("endorsement")) {

            Collections.sort(stds, new Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    return compareEndorsements(s1.getEndorsement(), s2.getEndorsement());
                }
            });

            obj.put("data", stds);
        } else if (type.equals("aps")) {

            Collections.sort(stds, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    if (o1.getAPS() < o2.getAPS()) {
                        return 1;
                    }

                    return -1;
                }
            });

            obj.put("data", stds);
        } else if (type.equals("search")) {

            String param = request.getParameter("search");

            for (int i = 0; i < stds.size(); i++) {
                if (!stds.get(i).getName().contains(param)) {
                    stds.remove(i);
                } else {
                    obj.put("data", stds);
                }
            }
        }
        PrintWriter writer = response.getWriter();
        writer.write(obj.toString());
    }

    private int compareEndorsements(String e1, String e2) {
        List<String> endorsementOrder = Arrays.asList("Bachelors", "Diploma", "Higher Certificate");
        return Integer.compare(endorsementOrder.indexOf(e1), endorsementOrder.indexOf(e2));
    }
}
