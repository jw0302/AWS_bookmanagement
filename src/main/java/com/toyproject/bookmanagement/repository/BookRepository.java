package com.toyproject.bookmanagement.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.toyproject.bookmanagement.entity.Book;
import com.toyproject.bookmanagement.entity.Category;
import com.toyproject.bookmanagement.entity.RentalList;

@Mapper
public interface BookRepository {
	
	public Book getBook(int bookId);

	// 도서 검색 & 전체갯수
	public List<Book> searchBooks(Map<String, Object> map);
	public int getTotalCount(Map<String, Object> map);
	
	// 카테고리 가져오기
	public List<Category> getCategories();
	
	// 좋아요 기능
	public int getLikeCount(int bookId);
	public int getLikeStatus(Map<String, Object> map);
	public int setLike(Map<String, Object> map);
	public int disLike(Map<String, Object> map);
	
	// 대여 기능
	public List<RentalList> getRentalListByBookId(int bookId);
	public int getRentalCount(int bookId);
	public int getRentalStatus(Map<String, Object> map);
	public int rentalBook(Map<String, Object> map);
	public int returnBook(Map<String, Object> map);
}
