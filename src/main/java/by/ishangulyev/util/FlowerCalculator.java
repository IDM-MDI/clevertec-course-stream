package by.ishangulyev.util;

import by.ishangulyev.model.Flower;

import java.util.Objects;

import static by.ishangulyev.util.DateCalculator.getDayByYearAgo;

public class FlowerCalculator {
    private FlowerCalculator() {}

    public static double flowerCostCalculator(Flower flower) {
        if(Objects.isNull(flower)) {
            return 0;
        }
        double waterConsumed = flower.getWaterConsumptionPerDay() * getDayByYearAgo(5);
        double waterConsumedInCubMetrics = waterConsumed / 1000;
        return flower.getPrice() + waterConsumedInCubMetrics * 1.39;
    }
}
