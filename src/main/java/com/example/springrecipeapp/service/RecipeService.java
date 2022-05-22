package com.example.springrecipeapp.service;

import com.example.springrecipeapp.command.RecipeCommand;
import com.example.springrecipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand findCommandById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long id);
}
