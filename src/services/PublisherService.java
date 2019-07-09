package services;

import java.util.ArrayList;

import dao.BookDao;
import dao.PublisherDao;
import model.Publisher;

public class PublisherService {
	PublisherDao publisherDao = new PublisherDao();

	public Publisher getPublisherById(int publisherID) {
		return publisherDao.getPublisherByID(publisherID);
	}

	public void addPublisher(String publisherName, String publisherAddress, String publisherPhone) {
		publisherDao.addPublisher(publisherName, publisherAddress, publisherPhone);
	}
	public void removePublisherByID(int publisherID) {
		publisherDao.removeByID(publisherID);
	}
	public void updatePublisherAllByID(int publisherID, String publisherName, String publisherAddress, String publisherPhone) {
		publisherDao.updateAll(publisherID, publisherName, publisherAddress, publisherPhone);
	}
	public ArrayList<Publisher> getList(){
		return publisherDao.getPubList();
	}

}
