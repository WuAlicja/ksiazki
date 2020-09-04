package com.alicjawaclawek.ksiazki.controller;

import com.alicjawaclawek.ksiazki.model.Book;
import com.alicjawaclawek.ksiazki.model.Category;
import com.alicjawaclawek.ksiazki.repository.BookRepository;
import com.alicjawaclawek.ksiazki.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class AllDataController {

//@Autowired
//private AllData allData;
//public AllDataController(AllData allData){this.allData=allData;}
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookRepository bookRepository;

    public AllDataController(BookRepository bookRepository,CategoryRepository categoryRepository){
        this.bookRepository=bookRepository;
        this.categoryRepository=categoryRepository;
    }

    @PostMapping("books2")
    public Map createBookWithCategory(@RequestParam String categoryName, @RequestParam String title, @RequestParam String author) {
        Category category=new Category();
        category.setCategoryName(categoryName);
        categoryRepository.save(category).getCategoryId();
        Book book=new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setCategoryId(categoryRepository.save(category).getCategoryId());
        Map<String, Object> map=new HashMap<>();
        map.put("book",categoryRepository.save(category));
        map.put("category",bookRepository.save(book));
        return map;
    }


}
