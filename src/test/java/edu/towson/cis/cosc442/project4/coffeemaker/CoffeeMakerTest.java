package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CoffeeMakerTest {

    private static CoffeeMaker coffeeMaker;
    private static Recipe recipe1;
    private static Recipe recipe2;
    private static Recipe recipe3;
    private static Recipe recipe4;

    @BeforeEach
    public void setUp() {
        coffeeMaker = new CoffeeMaker();

        recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe1.setPrice(50);
        recipe1.setAmtCoffee(3);
        recipe1.setAmtMilk(1);
        recipe1.setAmtSugar(1);
        recipe1.setAmtChocolate(0);

        recipe2 = new Recipe();
        recipe2.setName("Mocha");
        recipe2.setPrice(75);
        recipe2.setAmtCoffee(3);
        recipe2.setAmtMilk(1);
        recipe2.setAmtSugar(1);
        recipe2.setAmtChocolate(3);

        recipe3 = new Recipe();
        recipe3.setName("Latte");
        recipe3.setPrice(100);
        recipe3.setAmtCoffee(3);
        recipe3.setAmtMilk(3);
        recipe3.setAmtSugar(1);
        recipe3.setAmtChocolate(0);

        recipe4 = new Recipe();
        recipe4.setName("Hot Chocolate");
        recipe4.setPrice(65);
        recipe4.setAmtCoffee(0);
        recipe4.setAmtMilk(1);
        recipe4.setAmtSugar(1);
        recipe4.setAmtChocolate(4);
    }

    @AfterAll
    public static void tearDown() {
        coffeeMaker = null;
        recipe1 = null;
        recipe2 = null;
        recipe3 = null;
        recipe4 = null;
    }

    // add recipe

    @Test
    public void testAddRecipe() {
        // act
        boolean result = coffeeMaker.addRecipe(recipe1);

        // assert
        assertTrue(result);
        assertEquals("Coffee", coffeeMaker.getRecipeForName("Coffee").getName());
    }

    @Test
    public void testAddThreeRecipes() {
        // act / assert
        assertTrue(coffeeMaker.addRecipe(recipe1));
        assertTrue(coffeeMaker.addRecipe(recipe2));
        assertTrue(coffeeMaker.addRecipe(recipe3));
    }



    @Test
    public void testAddDuplicateRecipeFails() {
        // arrange
        coffeeMaker.addRecipe(recipe1);
        Recipe duplicate = new Recipe();
        duplicate.setName("Coffee");

        // act
        boolean result = coffeeMaker.addRecipe(duplicate);

        // assert
        assertFalse(result);
    }

    // delete recipe


    @Test
    public void testDeleteRecipeNotInListFails() {
        // arrange
        coffeeMaker.addRecipe(recipe1);

        // act
        boolean result = coffeeMaker.deleteRecipe(recipe2);

        // assert
        assertFalse(result);
    }

    @Test
    public void testDeleteNullRecipeFails() {
        // act
        boolean result = coffeeMaker.deleteRecipe(null);

        // assert
        assertFalse(result);
    }



    // edit recipe

    @Test
    public void testEditRecipe() {
        // arrange
        coffeeMaker.addRecipe(recipe1);
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Coffee");
        newRecipe.setPrice(60);

        // act
        boolean result = coffeeMaker.editRecipe(recipe1, newRecipe);

        // assert
        assertTrue(result);
        assertEquals(60, coffeeMaker.getRecipeForName("Coffee").getPrice());
    }

    @Test
    public void testEditRecipeNotInListFails() {
        // arrange
        coffeeMaker.addRecipe(recipe1);
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Mocha");

        // act
        boolean result = coffeeMaker.editRecipe(recipe2, newRecipe);

        // assert
        assertFalse(result);
    }

    // add inventory

    @Test
    public void testAddZeroInventory() {
        // act
        boolean result = coffeeMaker.addInventory(0, 0, 0, 0);

        // assert
        assertTrue(result);
        assertEquals(15, coffeeMaker.checkInventory().getCoffee());
    }

    // check inventory

    @Test
    public void testCheckInventory() {
        // act
        Inventory inventory = coffeeMaker.checkInventory();

        // assert
        assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", inventory.toString());
    }

    // make coffee

    @Test
    public void testMakeCoffeeWithExactMoney() {
        // arrange
        coffeeMaker.addRecipe(recipe1);

        // act
        int change = coffeeMaker.makeCoffee(recipe1, 50);

        // assert
        assertEquals(0, change);
    }

    @ParameterizedTest
    @CsvSource({
            "51, 1",
            "75, 25",
            "100, 50"
    })
    public void testMakeCoffeeReturnsCorrectChange(int paid, int expectedChange) {
        // arrange
        coffeeMaker.addRecipe(recipe1);

        // act
        int change = coffeeMaker.makeCoffee(recipe1, paid);

        // assert
        assertEquals(expectedChange, change);
    }


    @Test
    public void testMakeCoffeeWithNotEnoughMoney() {
        // arrange
        coffeeMaker.addRecipe(recipe1);

        // act
        int change = coffeeMaker.makeCoffee(recipe1, 49);

        // assert
        assertEquals(49, change);
        assertEquals(15, coffeeMaker.checkInventory().getCoffee());
    }

    @Test
    public void testMakeCoffeeWithNotEnoughInventory() {
        // arrange
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.checkInventory().setChocolate(2);

        // act
        int change = coffeeMaker.makeCoffee(recipe2, 75);

        // assert
        assertEquals(75, change);
        assertEquals(2, coffeeMaker.checkInventory().getChocolate());
    }

    @Test
    public void testGetRecipeForName() {
        // arrange
        coffeeMaker.addRecipe(recipe1);

        // act
        Recipe result = coffeeMaker.getRecipeForName("Coffee");

        // assert
        assertEquals(recipe1, result);
    }

    @Test
    public void testGetRecipeForNameNotFound() {
        // arrange
        coffeeMaker.addRecipe(recipe1);

        // act
        Recipe result = coffeeMaker.getRecipeForName("Tea");

        // assert
        assertNull(result.getName());
    }

    // task 2, additional tests for survived mutants

    @Test
    public void testDeleteExistingRecipeReturnsTrue() {
        // arrange
        coffeeMaker.addRecipe(recipe1);

        // act
        boolean result = coffeeMaker.deleteRecipe(recipe1);

        // assert
        assertTrue(result);
    }

    @Test
    public void testAddInventoryIncreasesStock() {
        // act
        boolean result = coffeeMaker.addInventory(5, 4, 0, 2);

        // assert
        assertTrue(result);
        assertEquals(20, coffeeMaker.checkInventory().getCoffee());
        assertEquals(19, coffeeMaker.checkInventory().getMilk());
        assertEquals(17, coffeeMaker.checkInventory().getChocolate());
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 0, 0, 0",
            "0, -1, 0, 0",
            "0, 0, 0, -1"
    })
    public void testAddNegativeInventoryIsRejected(int coffee, int milk, int sugar, int chocolate) {
        // act
        boolean result = coffeeMaker.addInventory(coffee, milk, sugar, chocolate);

        // assert
        assertFalse(result);
        assertEquals(15, coffeeMaker.checkInventory().getCoffee());
        assertEquals(15, coffeeMaker.checkInventory().getMilk());
        assertEquals(15, coffeeMaker.checkInventory().getChocolate());
    }

    @Test
    public void testMakeCoffeeUsesMilkSugarAndChocolate() {
        // arrange
        coffeeMaker.addRecipe(recipe2);

        // act
        coffeeMaker.makeCoffee(recipe2, 75);

        // assert
        assertEquals(14, coffeeMaker.checkInventory().getMilk());
        assertEquals(14, coffeeMaker.checkInventory().getSugar());
        assertEquals(12, coffeeMaker.checkInventory().getChocolate());
    }

    @Test
    public void testGetRecipesContainsAddedRecipes() {
        // arrange
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);

        // act
        Recipe[] recipes = coffeeMaker.getRecipes();

        // assert
        assertTrue(Arrays.asList(recipes).contains(recipe1));
        assertTrue(Arrays.asList(recipes).contains(recipe2));
    }
}