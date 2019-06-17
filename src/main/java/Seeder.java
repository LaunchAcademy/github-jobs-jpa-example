import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Seeder {
  private static String JOBS_FILE_PATH = "jobs.json";

  public static void main(String[] args) throws IOException {

    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("com.launchacademy.githubJobs");
    EntityManager em = emf.createEntityManager();

    try {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      InputStream stream = classLoader.getResourceAsStream(JOBS_FILE_PATH);

      List<HashMap<String,String>> jsonList = new ArrayList<>();
      ObjectMapper mapper = new ObjectMapper();
      jsonList = mapper.readValue(stream, ArrayList.class);
      for(HashMap<String, String> job : jsonList) {
        Job jerb = new Job();
        jerb.setGithubId(job.get("id"));
        jerb.setPositionType(job.get("type"));
        jerb.setLocation(job.get("location"));
        jerb.setTitle(job.get("title"));
        jerb.setCompanyUrl(job.get("company_url"));

        em.getTransaction().begin();
        em.persist(jerb);
        em.getTransaction().commit();
      }
//      Scanner scanner = new Scanner(stream).useDelimiter("\\z");
//      System.out.println(scanner.next());





    }
    finally {
      em.close();
      emf.close();
    }
  }
}
