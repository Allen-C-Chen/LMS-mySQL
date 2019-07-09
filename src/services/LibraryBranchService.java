package services;

import java.util.ArrayList;

import dao.LibraryBranchDao;
import model.Author;
import model.Book;
import model.LibraryBranch;

public class LibraryBranchService {
	static LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
	
	
	public void addLibraryBranch(String libraryName, String libraryBranchAddress) {
		// TODO Auto-generated method stub
		libraryBranchDao.addByNameAndAddress(libraryName,libraryBranchAddress );
	}
	public LibraryBranch getLibraryBranchById(int libraryBranchID) {
		//return LibraryBranchDao.getLibraryBranchByID(libraryBranchID);
		return libraryBranchDao.getLibraryBranchByID(libraryBranchID);
	}
	public void removeLibraryID(int libraryBranchID) {
		libraryBranchDao.removeByID(libraryBranchID);
	}
	
	
	public void updateLibraryBranchNameByID(int libraryBranchID, String newlibraryName) { //return the update object
		// TODO Auto-generated method stub
		libraryBranchDao.updateLibraryBranchNameByID(libraryBranchID, newlibraryName); 
	}
	public void updateLibraryBranchAddressByID(int libraryBranchID, String newLibraryAddress) { //return the update object
		// TODO Auto-generated method stub
		libraryBranchDao.updateLibraryBranchAddressByID(libraryBranchID, newLibraryAddress); 
	}

	
	
	public ArrayList<LibraryBranch> displayAllLibraryBranchByNameAndAddress() {
		return libraryBranchDao.displayAllLibraryBranchByNameAndAddress();

	}

	public ArrayList<Book> displayAllBooksAndAuthorInABranchID(int i) {
		// TODO Auto-generated method stub
		return libraryBranchDao.displayAllBooksAndAuthorInABranchID(i);
	}


}
