package com.yse.dev.bookmanagement.book.controller;

import com.yse.dev.bookmanagement.book.dto.*;
import com.yse.dev.bookmanagement.book.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;

@Controller// 이 어노테이션에 있는 클래스는 스프링 부트가 브라우저의 요청을 받아들이는 컨트롤러라고 인지해서
// 자바 빈으로 등록해서 관리하게 됩니다. 즉, 프레임워크에서 관리하는 클래스가 됩니다.
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/create")
    public String create(){
        // create 메소드는 브라우저에서 book/create 주소가 HTTP GET 방식으로 입력되었을 때
        // book/create 경로의 뷰를 보여주는 컨트롤러 메소드입니다.
        return "/book/create";
    }

    @PostMapping("/create")
    public String insert(@Valid @ModelAttribute BookCreateDTO bookCreateDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        // Valid 어노테이션을 추가하여 입력 데이터의 유효성을 검사합니다.
        if (bindingResult.hasErrors()) {
            return "book/create";
        }
        Integer bookId = bookService.insert(bookCreateDTO);
        return "redirect:/book/read/" + bookId;
    }

    @GetMapping("/read/{bookId}")
    public String read(@PathVariable Integer bookId, Model model) {
        try {
            BookEditResponseDTO readResponseDto = bookService.read(bookId);
            model.addAttribute("bookReadResponseDTO", readResponseDto);
            return "book/read";
        } catch (NoSuchElementException e) {
            return "redirect:/book/list";
        }
    }

    @GetMapping("/edit/{bookId}")
    public String edit(@PathVariable Integer bookId, Model model) {
        try {
            BookEditResponseDTO editResponseDto = bookService.edit(bookId);
            model.addAttribute("bookEditResponseDTO", editResponseDto);
            return "book/edit";
        } catch (NoSuchElementException e) {
            return "redirect:/book/list";
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("message", e.getMessage());
        model.addAttribute("location", "/book/list");
        return "common/error/422";
    }

    @PostMapping("/edit/{bookId}")
    public String update(@Valid @ModelAttribute BookEditDto bookEditDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookEditResponseDTO", bindingResult);
            redirectAttributes.addFlashAttribute("bookEditDto", bookEditDto);

            return "redirect:/book/edit/" + bookEditDto.getBookId();
        }

        bookService.update(bookEditDto);
        return "redirect:/book/read/" + bookEditDto.getBookId();
    }

}