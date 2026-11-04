package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InventoryTest {

    private static Inventory inventory;
    private static Recipe recipe;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();

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
        inventory = null;
        recipe = null;
    }


    @Test
    public void testConstructorSetsFifteenOfEachIngredient() {
        // act / assert
        assertEquals(15, inventory.getCoffee());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getSugar());
        assertEquals(15, inventory.getChocolate());
    }

    @Test
    public void testSetCoffee() {
        // act
        inventory.setCoffee(20);

        // assert
        assertEquals(20, inventory.getCoffee());
    }

    @Test
    public void testSetMilk() {
        // act
        inventory.setMilk(20);

        // assert
        assertEquals(20, inventory.getMilk());
    }

    @Test
    public void testSetSugar() {
        // act
        inventory.setSugar(20);

        // assert
        assertEquals(20, inventory.getSugar());
    }

    @Test
    public void testSetChocolate() {
        // act
        inventory.setChocolate(20);

        // assert
        assertEquals(20, inventory.getChocolate());
    }

    @Test
    public void testToString() {
        // act
        String result = inventory.toString();

        // assert
        assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", result);
    }


    // boundary tests 

    @Test
    public void testSetCoffeeToZero() {
        // act
        inventory.setCoffee(0);

        // assert
        assertEquals(0, inventory.getCoffee());
    }

    // invalid tests

    @ParameterizedTest
    @ValueSource(ints = { -1, -15 })
    public void testSetNegativeAmountsDefaultToZero(int amount) {
        // act
        inventory.setCoffee(amount);
        inventory.setMilk(amount);
        inventory.setSugar(amount);
        inventory.setChocolate(amount);

        // assert
        assertEquals(0, inventory.getCoffee());
        assertEquals(0, inventory.getMilk());
        assertEquals(0, inventory.getSugar());
        assertEquals(0, inventory.getChocolate());
    }

    // enough ingredients

    @Test
    public void testEnoughIngredientsReturnsTrue() {
        // act
        boolean result = inventory.enoughIngredients(recipe);

        // assert
        assertTrue(result);
    }

    @Test
    public void testEnoughIngredientsWhenAmountEqualsInventory() {
        // arrange
        recipe.setAmtCoffee(15);

        // act
        boolean result = inventory.enoughIngredients(recipe);

        // assert
        assertTrue(result);
    }

    @Test
    public void testNotEnoughCoffee() {
        // arrange
        recipe.setAmtCoffee(16);

        // act / assert
        assertFalse(inventory.enoughIngredients(recipe));
    }

    @Test
    public void testNotEnoughMilk() {
        // arrange
        recipe.setAmtMilk(16);

        // act / assert
        assertFalse(inventory.enoughIngredients(recipe));
    }

    @Test
    public void testNotEnoughSugar() {
        // arrange
        recipe.setAmtSugar(16);

        // act / assert
        assertFalse(inventory.enoughIngredients(recipe));
    }

    @Test
    public void testNotEnoughChocolate() {
        // arrange
        recipe.setAmtChocolate(16);

        // act / assert
        assertFalse(inventory.enoughIngredients(recipe));
    }

    // exception tests 

    @Test
    public void testEnoughIngredientsWithNullRecipeThrowsException() {
        // act / assert
        assertThrows(NullPointerException.class, () -> inventory.enoughIngredients(null));
    }

    // task 2, additional tests for survived mutants


    // boundary tests
/*     @Test
    public void testEnoughMilkWhenAmountEqualsInventory() {
        // arrange
        recipe.setAmtMilk(15);

        // act / assert
        assertTrue(inventory.enoughIngredients(recipe));
    } 

    @Test
    public void testEnoughSugarWhenAmountEqualsInventory() {
        // arrange
        recipe.setAmtSugar(15);

        // act / assert
        assertTrue(inventory.enoughIngredients(recipe));
    }

    @Test
    public void testEnoughChocolateWhenAmountEqualsInventory() {
        // arrange
        recipe.setAmtChocolate(15);

        // act / assert
        assertTrue(inventory.enoughIngredients(recipe));
    } */
}

