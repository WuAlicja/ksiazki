package com.alicjawaclawek.ksiazki.controller;

import com.alicjawaclawek.ksiazki.model.Book;
import com.alicjawaclawek.ksiazki.model.Category;
import com.alicjawaclawek.ksiazki.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("categories")
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @GetMapping("categories/{id}")

    public ResponseEntity<Category> showOne(@PathVariable Integer id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("categories/{id}/books")
    public ResponseEntity<List<Book>> showBooksForCategory(@PathVariable Integer id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            return new ResponseEntity<>(category.getBooks(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("categories")
    public Category createCategory(@RequestParam String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);

        return categoryRepository.save(category);
    }

    @PutMapping("categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestParam String categoryName) {

        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setCategoryName(categoryName);
            return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("categories/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer id) {

        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

