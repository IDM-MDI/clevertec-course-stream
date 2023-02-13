package by.ishangulyev.validator;

import by.ishangulyev.model.Flower;

import java.util.List;
import java.util.Objects;

import static by.ishangulyev.model.ModelFieldValue.ALUMINUM;
import static by.ishangulyev.model.ModelFieldValue.GLASS;
import static by.ishangulyev.model.ModelFieldValue.STEEL;

public class FlowerValidator {
    private static final List<String> SHADE_PREFERRED_VASE_MATERIAL = List.of(GLASS,ALUMINUM,STEEL);
    private FlowerValidator() {}
    public static boolean isFlowerNameValid(Flower flower) {
        return Objects.nonNull(flower) && isFlowerFirstSymbolValid(flower.getCommonName().toLowerCase().charAt(0));
    }
    public static boolean isShadePreferredFlowerValid(Flower flower) {
        return Objects.nonNull(flower)
                &&
                flower.isShadePreferred()
                &&
                flower.getFlowerVaseMaterial().stream().anyMatch(SHADE_PREFERRED_VASE_MATERIAL::contains);
    }
    private static boolean isFlowerFirstSymbolValid(char symbol) {
        return symbol >= 99 && symbol <= 115;
    }
}
