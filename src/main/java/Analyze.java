import models.Car;
import models.CarMaker;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Analyze {

    private static final String PATH = "src/main/resources/CAR_DATA.csv";
    private static final String DELIMITER = ",";
    public static void main(String[] args) {
        try {
            List<String> rawInfo = analyzer();
            List<Car> toCars = toCar(rawInfo);
            Map<CarMaker,List<Car>> map1 = fornat(toCars, Car::getCarmaker);
            for (var i: map1.values()) {
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


//    static Map<String, List<Car>> toMap(List<Car> cars){
//        Map<String, List<Car>> res = cars.stream().
//                collect(Collectors.groupingBy(Car::getColor));
//        return res;
//    }
//
//    static Map<CarMaker, List<Car>> toCarMaker(List<Car> cars) {
//        Map<CarMaker, List<Car>> res = cars.stream().
//                collect(Collectors.groupingBy(Car::getCarmaker));
//        return res;
//    }

    static <T> Map<T, List<Car>> fornat(List<Car> cars, Function<Car,T> function){
        return cars.stream().
                collect(Collectors.groupingBy(function));
    }
}
