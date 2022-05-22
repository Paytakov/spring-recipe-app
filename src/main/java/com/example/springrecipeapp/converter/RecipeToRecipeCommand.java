package com.example.springrecipeapp.converter;

import com.example.springrecipeapp.command.RecipeCommand;
import com.example.springrecipeapp.model.Category;
import com.example.springrecipeapp.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingredientConverter;
    private final NoteToNoteCommand noteConverter;
    private final CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter,
                                 NoteToNoteCommand noteConverter, CategoryToCategoryCommand categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.noteConverter = noteConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipe.getId());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setPreparationTime(recipe.getPreparationTime());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setNote(noteConverter.convert(recipe.getNote()));
        recipeCommand.setImage(recipe.getImage());

        if (recipe.getCategories() != null && recipe.getCategories().size() > 0) {
            recipe.getCategories()
                    .forEach((Category category) -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
        }

        if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
            recipe.getIngredients()
                    .forEach(ingredient -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipeCommand;
    }
}
