package project.calculator.domain.batch.porfolio;

@FunctionalInterface
public interface UnitPriceCalculator<S> {
    abstract S calculate();
}
