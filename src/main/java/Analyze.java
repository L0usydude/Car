import models.Car;
import models.CarMaker;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Analyze {

    private static final String PATH = "src/main/resources/CAR_DATA.csv";
    private static final String DELIMITER = ",";
    public static void main(String[] args) {
        try {
            for (Car i: toCar(analyzer())) {
                System.out.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static List<String> analyzer() throws IOException {
        return Files.lines(Paths.get(PATH)).skip(1).toList();
    }
    static List<Car> toCar(List<String> a)
    {
        List<Car> res = new ArrayList<>();
        a.forEach((String str) -> {
            String[] newone = str.split(DELIMITER);
            int year = Integer.parseInt(newone[2]);
            String name = newone[0];
            String carMakerString = newone[1];
            String color = newone[3];
            CarMaker carMaker = CarMaker.builder()
                    .name(carMakerString)
                    .build();
            Car car = Car.builder()
                    .year(year)
                    .name(name)
                    .carmaker(carMaker)
                    .color(color)
                    .build();
            res.add(car);
        });
        return res;
    }

}
