package com.example.springrecipeapp.converter;

import com.example.springrecipeapp.command.CategoryCommand;
import com.example.springrecipeapp.command.IngredientCommand;
import com.example.springrecipeapp.command.NoteCommand;
import com.example.springrecipeapp.command.RecipeCommand;
import com.example.springrecipeapp.model.Difficulty;
import com.example.springrecipeapp.model.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeCommandToRecipeTest {
    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGREDIENT_ID_1 = 3L;
    public static final Long INGREDIENT_ID_2 = 4L;
    public static final Long NOTE_ID = 9L;

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        this.converter = new RecipeCommandToRecipe(new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NoteCommandToNote(), new CategoryCommandToCategory());
    }

    @Test
    public void convert() {
        // given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPreparationTime(PREP_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CAT_ID_1);
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CAT_ID2);

        recipeCommand.getCategories().add(categoryCommand);
        recipeCommand.getCategories().add(categoryCommand2);

        NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(NOTE_ID);

        recipeCommand.setNote(noteCommand);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID_1);

        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID_2);

        recipeCommand.getIngredients().add(ingredientCommand);
        recipeCommand.getIngredients().add(ingredientCommand2);

        // when
        Recipe recipe = converter.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPreparationTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTE_ID, recipe.getNote().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

}