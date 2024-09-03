package com.yse.dev.bookmanagement.book.controller;

import com.yse.dev.bookmanagement.book.dto.BookCreateDTO;
import com.yse.dev.bookmanagement.book.dto.BookReadResponseDTO;
import com.yse.dev.bookmanagement.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@Controller// 이 어노테이션에 있는 클래스는 스프링 부트가 브라우저의 요청을 받아들이는 컨트롤러라고 인지해서
// 자바 빈으로 등록해서 관리하게 됩니다. 즉, 프레임워크에서 관리하는 클래스가 됩니다.
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/book/create")
    public String create(){
        // create 메소드는 브라우저에서 book/create 주소가 HTTP GET 방식으로 입력되었을 때
        // book/create 경로의 뷰를 보여주는 컨트롤러 메소드입니다.
        return "/book/create";
    }

    @PostMapping("/book/create")
    public String insert(BookCreateDTO bookCreateDTO){
        Integer bookId = this.bookService.insert(bookCreateDTO);
        return String.format("redirect:/book/read/%s",bookId);
    }

    @GetMapping("/book/read/{bookId}")
    public ModelAndView read(@PathVariable Integer bookId){
        ModelAndView modelAndView = new ModelAndView(); // 뷰와 데이터를 함께 반환하기 위한 객체 생성

        try {
            // bookId를 사용하여 도서 정보를 읽음(서비스 계층 호출)
            BookReadResponseDTO readResponseDto = this.bookService.read(bookId);
            modelAndView.addObject("bookReadResponseDTO", readResponseDto);
            modelAndView.setViewName("book/read");
        }catch (NoSuchElementException e){
            // 도서를 찾을 수 없는 경우 (NoSuchElementException 발생 시 예외 처리)
            modelAndView.setStatus(HttpStatus.UNPROCESSABLE_ENTITY); // HTTP 상태 코드 422 설정
            modelAndView.addObject("message", "Book not found");
            modelAndView.addObject("location","/book"); // 사용자에게 보여줄 리디렉션 위치 정보 추가
            modelAndView.setViewName("common/error/422");
        }

        return modelAndView;
    }

}