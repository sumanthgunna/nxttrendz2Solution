/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.nxttrendz2.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.example.nxttrendz2.repository.ProductRepository;
import com.example.nxttrendz2.repository.ProductJpaRepository;
import com.example.nxttrendz2.repository.CategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.nxttrendz2.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductJpaService implements ProductRepository {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Autowired
    private CategoryJpaRepository categoryJpaRepository;

    @Override
    public ArrayList<Product> getProducts() {
        List<Product> productList = productJpaRepository.findAll();
        ArrayList<Product> products = new ArrayList<>(productList);
        return products;
    }

    @Override
    public Product getProductById(int productId) {
        try {
            Product product = productJpaRepository.findById(productId).get();
            return product;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Product addProduct(Product product) {
        Category category = product.getCategory();
        int categoryId = category.getId();

        try {
            Category completeCategory = categoryJpaRepository.findById(categoryId).get();
            product.setCategory(completeCategory);
            productJpaRepository.save(product);
            return product;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Product updateProduct(int productId, Product product) {
        try {
            Product newProduct = productJpaRepository.findById(productId).get();
            if (product.getName() != null) {
                newProduct.setName(product.getName());
            }
            if (product.getDescription() != null) {
                newProduct.setDescription(product.getDescription());
            }
            if (product.getCategory() != null) {
                int categoryId = product.getCategory().getId();
                Category newCategory = categoryJpaRepository.findById(categoryId).get();
                product.setCategory(newCategory);
            }
            if (product.getPrice() != 0) {
                newProduct.setPrice(product.getPrice());
            }
            productJpaRepository.save(newProduct);
            return newProduct;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteProduct(int productId) {
        try {
            productJpaRepository.deleteById(productId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Category getProductCategory(int productId) {
        try {
            Product product = productJpaRepository.findById(productId).get();
            Category category = product.getCategory();
            return category;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}