package racingcar.domain;

import racingcar.utils.InputValidation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> carList;
    private int maxPosition = 0;

    public Cars(String[] names) {
        InputValidation.validateCars(names);

        carList = new ArrayList<>();
        this.carList.addAll(Arrays.stream(names)
                .map(name -> new Car(name))
                .collect(Collectors.toList()));
    }

    public List<Car> carList() {
        return Collections.unmodifiableList(carList);
    }

    public void startRace() {
        for (Car car : carList) {
            car.moveByDefaultRule();
            maxPosition = car.aboveMaxPosition(maxPosition);
        }
    }

    public List<String> getStates() {
        return carList.stream()
                .map(car -> car.toString())
                .collect(Collectors.toList());
    }

    public List<String> showWinners() {
        return carList.stream()
                .filter(car -> car.isMaxPosition(maxPosition))
                .map(car -> car.getName().toString())
                .collect(Collectors.toList());
    }
}