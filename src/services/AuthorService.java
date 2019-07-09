package services;

import java.util.ArrayList;

import dao.AuthorDao;
import model.Author;

public class AuthorService {
	private AuthorDao authorDao = new AuthorDao();

	public Author getAuthorById(int authorID) {
		return authorDao.getAuthorByID(authorID);
	}
	public void addAuthorByName(String authorName) {
		 authorDao.addByName(authorName);
	}
	public void removeAuthorByName(String authorName) { //return the removed object
		authorDao.removeByName(authorName);
	}
	public void removeAuthorByAuthorID(int authorID) {
		authorDao.removeByID(authorID);
	}
	public void updateByAuthorID(int authorID, String newAuthorName) { //return the update object
		// TODO Auto-generated method stub
		authorDao.updateByAuthorID(authorID, newAuthorName); 
	}
	public ArrayList<Author> getAllAuthors(){
		return authorDao.geAllAuthor();
	}

}
