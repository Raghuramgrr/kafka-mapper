import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

public class mainapp {

    @SpringBootApplication(scanBasePackageClasses = Topology.class)
    public static void main(String[] args){
        System.out.print("Hello world");


    }

}
