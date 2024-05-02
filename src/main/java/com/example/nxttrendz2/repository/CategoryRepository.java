/*
 *
 * You can use the following import statements
 * 
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.nxttrendz2.repository;

import java.util.*;
import com.example.nxttrendz2.model.Category;

public interface CategoryRepository {
    ArrayList<Category> getCategories();

    Category getCategoryById(int categoryId);

    Category addCategory(Category category);

    Category updateCategory(int categoryId, Category category);

    void deleteCategory(int categoryId);
}