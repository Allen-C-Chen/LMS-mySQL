package services;

import java.util.ArrayList;

import dao.BorrowerDao;
import model.Borrower;

public class BorrowerService {
	BorrowerDao borrowerDao = new BorrowerDao();

	public Borrower checkID(int borrowerID) {
		Borrower borrower = borrowerDao.getBorrowerByID(borrowerID);
		if(borrower == null) {
			System.out.println("Card not found");
			return null;
		}
		System.out.println(borrower); //remove
		return borrower;
		
	}
	public Borrower getBorrowerById(int borrowerID) {
		Borrower borrower = borrowerDao.getBorrowerByID(borrowerID);
		if(borrower.getName() != null) {
			return borrower;
		}
		return null;
	}
	public void addBorrower(String borrowerName, String borrowerAddress, String borrowerPhone) {
		// TODO Auto-generated method stub
		borrowerDao.addBorrower(borrowerName, borrowerAddress, borrowerPhone);
	}
	public void deleteBorrowerByID(int borrowerID) {
		borrowerDao.removeByID(borrowerID);
	}
	public void updateAll(int borrowerID, String borrowerName, String borrowerAddress, String borrowerPhone) {
		// TODO Auto-generated method stub
		System.out.println("HIH");

		borrowerDao.updateAll(borrowerID, borrowerName, borrowerAddress,borrowerPhone);
		System.out.println("HIH");
	}
	public ArrayList<Borrower> getAllList(){
		return borrowerDao.getAllList();
	}
	
}
