package Graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 2115. Find All Possible Recipes from Given Supplies: https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/
 * Medium
 *
 * You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
 *
 * You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.
 *
 * Return a list of all the recipes that you can create. You may return the answer in any order.
 *
 * Note that two recipes may contain each other in their ingredients.
 *
 *
 *
 * Example 1:
 *
 * Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
 * Output: ["bread"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * Example 2:
 *
 * Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 * Example 3:
 *
 * Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich","burger"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 * We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".
 *
 *
 * Constraints:
 *
 * n == recipes.length == ingredients.length
 * 1 <= n <= 100
 * 1 <= ingredients[i].length, supplies.length <= 100
 * 1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
 * recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
 * All the values of recipes and supplies combined are unique.
 * Each ingredients[i] does not contain any duplicate values.
 */
public class FindAllPossibleRecipesFromGivenSupplies {
    /**
     * My solution: Build Graph, start from each recipe and recursively find out if it is a working recipes, pruning with a hashset. Return false if loop detected
     */
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> recipesMap = new HashMap<>();
        Set<String> suppliesSet = new HashSet<>();
        for (int i = 0; i < recipes.length; i++) {
            recipesMap.put(recipes[i], ingredients.get(i));
        }

        for (String supply : supplies) {
            suppliesSet.add(supply);
        }

        List<String> res = new ArrayList<>();
        Set<String> workingRecipes = new HashSet<>();
        for (int i = 0; i < recipes.length; i++) {
            if (dfs(recipesMap, suppliesSet, recipes[i], new HashMap<>(), workingRecipes)) {
                res.add(recipes[i]);
            }
        }
        return res;
    }

    // it we could make this dish with provided supplies
    private boolean dfs(Map<String, List<String>> recipes, Set<String> supplies, String cur, Map<String, Integer> visited, Set<String> workingRecipes) {
        if (workingRecipes.contains(cur)) {
            return true;
        }
        Integer state = visited.get(cur);
        if (state != null && state == 1) { // visiting
            return false;
        }

        if (state != null && state == 2) {
            return true;
        }

        visited.put(cur, 1);
        for (String ingredient : recipes.get(cur)) {
            if (recipes.containsKey(ingredient)) { // another recipe
                if (!dfs(recipes, supplies, ingredient, visited, workingRecipes)) {
                    return false;
                }
            } else { // ingredient
                if (!supplies.contains(ingredient)) {
                    return false;
                }
            }
        }
        visited.put(cur, 2);
        workingRecipes.add(cur);
        return true;
    }

    /**
     * Alternatively, use topology sort with in-degree
     */
    public List<String> findAllRecipesInDegree(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        List<String> ans = new ArrayList<>();
        // Put all supplies into HashSet.
        Set<String> available = Stream.of(supplies).collect(Collectors.toCollection(HashSet::new));
        Map<String, Set<String>> ingredientToRecipes = new HashMap<>(); // key - ingredient that is not done yet, value - the dependent recipes
        Map<String, Integer> inDegree = new HashMap<>(); // key - recipe, value - inDegree
        for (int i = 0; i < recipes.length; ++i) {
            int nonAvailable = 0;
            for (String ing : ingredients.get(i)) {
                if (!available.contains(ing)) {
                    ingredientToRecipes.computeIfAbsent(ing, s -> new HashSet<>()).add(recipes[i]);
                    ++nonAvailable;
                }
            }
            if (nonAvailable == 0) {
                ans.add(recipes[i]);
            }else {
                inDegree.put(recipes[i], nonAvailable);
            }
        }
        // Toplogical Sort.
        // It's a little bit trickly since we wanted to do this recursively. However, add new recipe in the answer would lead to another iteration, so this works.
        for (int i = 0; i < ans.size(); ++i) {
            String recipe = ans.get(i);
            if (ingredientToRecipes.containsKey(recipe)) {
                for (String rcp : ingredientToRecipes.remove(recipe)) {
                    if (inDegree.merge(rcp, -1, Integer::sum) == 0) {
                        ans.add(rcp);
                    }
                }
            }
        }
        return ans;
    }
}
