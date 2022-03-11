package algo.topological;

import java.util.*;
import java.util.stream.*;

/**
 * 2115. Find All Possible Recipes from Given Supplies Medium
 * You have information about n different recipes. You are given a string array
 * recipes and a 2D string array ingredients. The ith recipe has the name
 * recipes[i], and you can create it if you have all the needed ingredients from
 * ingredients[i]. Ingredients to a recipe may need to be created from other
 * recipes, i.e., ingredients[i] may contain a string that is in recipes.
 * You are also given a string array supplies containing all the ingredients
 * that you initially have, and you have an infinite supply of all of them.
 * Return a list of all the recipes that you can create. You may return the
 * answer in any order.
 * Note that two recipes may contain each other in their ingredients.
 * Example 1:
 * Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies =
 * ["yeast","flour","corn"] Output: ["bread"] Explanation: We can create "bread"
 * since we have the ingredients "yeast" and "flour".
 * Example 2:
 * Input: recipes = ["bread","sandwich"], ingredients =
 * [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich"] Explanation: We can create "bread" since we have
 * the ingredients "yeast" and "flour". We can create "sandwich" since we have
 * the ingredient "meat" and can create the ingredient "bread".
 * Example 3:
 * Input: recipes = ["bread","sandwich","burger"], ingredients =
 * [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies =
 * ["yeast","flour","meat"] Output: ["bread","sandwich","burger"] Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour". We
 * can create "sandwich" since we have the ingredient "meat" and can create the
 * ingredient "bread". We can create "burger" since we have the ingredient
 * "meat" and can create the ingredients "bread" and "sandwich".
 * Constraints:
 * n == recipes.length == ingredients.length 1 <= n <= 100 1 <=
 * ingredients[i].length, supplies.length <= 100 1 <= recipes[i].length,
 * ingredients[i][j].length, supplies[k].length <= 10 recipes[i],
 * ingredients[i][j], and supplies[k] consist only of lowercase English letters.
 * All the values of recipes and supplies combined are unique. Each
 * ingredients[i] does not contain any duplicate values.
 */
public class FindRecipes {

    public List<String> findAllRecipes(String[] recipes,
            List<List<String>> ingredients, String[] supplies) {

        // Stores result
        final List<String> finalRecipes = new ArrayList<>(
                recipes.length); // can never exceed total recipes to prepare

        // to get supply in O(1)
        final Set<String> suppliesSet = Stream.of(supplies).collect(
                Collectors.toSet());

        // Stores degree of recipe
        final Map<String, Integer> degree = new HashMap<>();

        final Map<String, Set<String>> ingredientToRecepie = new HashMap<>();

        // make dependency graph of topological sort
        for (int i = 0; i < recipes.length; i++) {
            final String recipe = recipes[i];
            final List<String> recipeIngredients = ingredients.get(i);
            int notAvailable = 0;
            for (final String item : recipeIngredients) {
                if (!suppliesSet.contains(item)) {
                    notAvailable++;
                    ingredientToRecepie.computeIfAbsent(item,
                            (s) -> new HashSet<>()).add(recipe);
                }
            }

            if (notAvailable == 0) {
                finalRecipes.add(recipe);
            } else {
                degree.put(recipe, notAvailable);
            }
        }

        for (int i = 0; i < finalRecipes.size(); i++) {
            final String recipe = finalRecipes.get(i);
            if (ingredientToRecepie.containsKey(recipe)) {
                for (final String item : ingredientToRecepie.remove(recipe)) {
                    final int newDegree = degree.merge(item, -1, Integer::sum);
                    if (newDegree == 0) {
                        finalRecipes.add(item);
                    }
                }
            }
        }

        return finalRecipes;
    }

    public static void main(String[] args) {
        final String[] recipes = { "bread", "sandwich", "burger" };
        final List<List<String>> ingredients = new ArrayList<>();

        ingredients.add(Arrays.asList("yeast", "flour"));
        ingredients.add(Arrays.asList("bread", "meat"));
        ingredients.add(Arrays.asList("sandwich", "meat", "bread"));

        final String[] supplies = { "yeast", "flour", "meat" };

        System.out.println(
                new FindRecipes().findAllRecipes(recipes, ingredients,
                        supplies));
    }

}
