package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RecipeTest {

    private static Recipe recipe;

    @BeforeEach
    public void setUp() {
        recipe = new Recipe();
        recipe.setName("Coffee");
        recipe.setPrice(50);
        recipe.setAmtCoffee(3);
        recipe.setAmtMilk(1);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(0);
    }

    @AfterAll
    public static void tearDown() {
        recipe = null;
    }


    @Test
    public void testNewRecipeHasNullNameAndZeroValues() {
        // arrange
        Recipe empty = new Recipe();

        // act & assert
        assertNull(empty.getName());
        assertEquals(0, empty.getPrice());
        assertEquals(0, empty.getAmtCoffee());
        assertEquals(0, empty.getAmtMilk());
        assertEquals(0, empty.getAmtSugar());
        assertEquals(0, empty.getAmtChocolate());
    }

    @Test
    public void testGettersReturnValuesFromSetUp() {
        // act & assert
        assertEquals("Coffee", recipe.getName());
        assertEquals(50, recipe.getPrice());
        assertEquals(3, recipe.getAmtCoffee());
        assertEquals(1, recipe.getAmtMilk());
        assertEquals(1, recipe.getAmtSugar());
        assertEquals(0, recipe.getAmtChocolate());
    }

    @Test
    public void testSetNameChangesName() {
        // act
        recipe.setName("Mocha");

        // assert
        assertEquals("Mocha", recipe.getName());
    }

    @Test
    public void testToStringReturnsName() {
        // act
        String result = recipe.toString();

        // assert
        assertEquals("Coffee", result);
    }

    // boundary tests

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 100 })
    public void testSetPriceWithValidValues(int price) {
        // act
        recipe.setPrice(price);

        // assert
        assertEquals(price, recipe.getPrice());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 15 })
    public void testSetIngredientsWithValidValues(int amount) {
        // act
        recipe.setAmtCoffee(amount);
        recipe.setAmtMilk(amount);
        recipe.setAmtSugar(amount);
        recipe.setAmtChocolate(amount);

        // assert
        assertEquals(amount, recipe.getAmtCoffee());
        assertEquals(amount, recipe.getAmtMilk());
        assertEquals(amount, recipe.getAmtSugar());
        assertEquals(amount, recipe.getAmtChocolate());
    }

    // invalid tests

    @ParameterizedTest
    @ValueSource(ints = { -1, -50 })
    public void testSetPriceWithNegativeValueDefaultsToZero(int price) {
        // act
        recipe.setPrice(price);

        // assert
        assertEquals(0, recipe.getPrice());
    }

    @Test
    public void testSetAmtCoffeeWithNegativeValueDefaultsToZero() {
        // act
        recipe.setAmtCoffee(-1);

        // assert
        assertEquals(0, recipe.getAmtCoffee());
    }

    @Test
    public void testSetAmtMilkWithNegativeValueDefaultsToZero() {
        // act
        recipe.setAmtMilk(-1);

        // assert
        assertEquals(0, recipe.getAmtMilk());
    }

    @Test
    public void testSetAmtSugarWithNegativeValueDefaultsToZero() {
        // act
        recipe.setAmtSugar(-1);

        // assert
        assertEquals(0, recipe.getAmtSugar());
    }

    @Test
    public void testSetAmtChocolateWithNegativeValueDefaultsToZero() {
        // act
        recipe.setAmtChocolate(-1);

        // assert
        assertEquals(0, recipe.getAmtChocolate());
    }

    // equals tests

    @Test
    public void testEqualsWithSameNameReturnsTrue() {
        // arrange
        Recipe other = new Recipe();
        other.setName("Coffee");

        // act / assert
        assertTrue(recipe.equals(other));
    }

    @Test
    public void testEqualsWithDifferentNameReturnsFalse() {
        // arrange
        Recipe other = new Recipe();
        other.setName("Mocha");

        // act / assert
        assertFalse(recipe.equals(other));
    }

    @Test
    public void testEqualsWithOtherNameNullReturnsFalse() {
        // arrange
        Recipe other = new Recipe();

        // act / assert
        assertFalse(recipe.equals(other));
    }

    @Test
    public void testEqualsWithThisNameNullReturnsFalse() {
        // arrange
        Recipe empty = new Recipe();

        // act / assert
        assertFalse(empty.equals(recipe));
    }

    
}