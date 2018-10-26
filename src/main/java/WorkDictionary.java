
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WorkDictionary {

    public static void main(String[] args) {

        ApplicationContext cpx = new ClassPathXmlApplicationContext("dictionary-project.xml");
        ConsoleApplication application = (ConsoleApplication) cpx.getBean("application");
        application.start();
    }

}
