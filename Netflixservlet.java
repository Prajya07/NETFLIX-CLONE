import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class NetflixServlet extends HttpServlet {
    private List<String> movies;

    public void init() throws ServletException {
        // Initialize with some dummy data
        movies = new ArrayList<>(Arrays.asList(
            "Stranger Things",
            "The Crown",
            "Bridgerton",
            "Money Heist"
        ));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        String action = request.getParameter("action");
        
        if ("getMovies".equals(action)) {
            out.println(getMoviesJson());
        } else {
            out.println("{\"error\": \"Invalid action\"}");
        }
    }

    private String getMoviesJson() {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < movies.size(); i++) {
            json.append("\"").append(movies.get(i)).append("\"");
            if (i < movies.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }
}