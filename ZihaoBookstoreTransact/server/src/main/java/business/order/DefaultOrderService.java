package business.order;

import api.ApiException;
import business.BookstoreDbException;
import business.JdbcUtils;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.cart.ShoppingCartItem;
import business.customer.Customer;
import business.customer.CustomerDao;
import business.customer.CustomerDaoJdbc;
import business.customer.CustomerForm;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class DefaultOrderService implements OrderService {

	private BookDao bookDao;
	private OrderDao orderDao = new OrderDaoJdbc();
	private LineItemDao lineItemDao = new LineItemDaoJdbc();
	private CustomerDao customerDao = new CustomerDaoJdbc();

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void setLineItemDao(LineItemDao lineItemDao) {
		this.lineItemDao = lineItemDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public OrderDetails getOrderDetails(long orderId) {
		Order order = orderDao.findByOrderId(orderId);
		Customer customer = customerDao.findByCustomerId(order.getCustomerId());
		List<LineItem> lineItems = lineItemDao.findByOrderId(orderId);
		List<Book> books = lineItems
				.stream()
				.map(lineItem -> bookDao.findByBookId(lineItem.getBookId()))
				.collect(Collectors.toList());
		return new OrderDetails(order, customer, lineItems, books);
	}

	@Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

		validateCustomer(customerForm);
		validateCart(cart);

		try (Connection connection = JdbcUtils.getConnection()) {
			Date date = getDate(
					customerForm.getCcExpiryMonth(),
					customerForm.getCcExpiryYear());
			return performPlaceOrderTransaction(
					customerForm.getName(),
					customerForm.getAddress(),
					customerForm.getPhone(),
					customerForm.getEmail(),
					customerForm.getCcNumber(),
					date, cart, connection);
		} catch (SQLException e) {
			throw new BookstoreDbException("Error during close connection for customer order", e);
		}
	}

	private long performPlaceOrderTransaction(
			String name, String address, String phone,
			String email, String ccNumber, Date date,
			ShoppingCart cart, Connection connection) {
		try {
			connection.setAutoCommit(false);
			long customerId = customerDao.create(
					connection, name, address, phone, email,
					ccNumber, date);
			long customerOrderId = orderDao.create(
					connection,
					cart.getComputedSubtotal() + cart.getSurcharge(),
					generateConfirmationNumber(), customerId);
			for (ShoppingCartItem item : cart.getItems()) {
				lineItemDao.create(connection, customerOrderId,
						item.getBookId(), item.getQuantity());
			}
			connection.commit();
			return customerOrderId;
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new BookstoreDbException("Failed to roll back transaction", e1);
			}
			return 0;
		}
	}

	private Date getDate(String monthString, String yearString) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.MONTH,  Integer.parseInt(monthString));
		calendar.set(Calendar.YEAR, Integer.parseInt(yearString));
		calendar.set(Calendar.DATE, 10);
		Date date = calendar.getTime();
		return date;
	}

	private void validateCustomer(CustomerForm customerForm) {

    	String name = customerForm.getName();
		if (name == null || name.equals("") || name.length() > 45 || name.length() < 4) {
			throw new ApiException.InvalidParameter("Invalid name field");
		}

		String address = customerForm.getAddress();
		if (address == null || address.equals("") || address.length() > 45 || address.length() < 4) {
			throw new ApiException.InvalidParameter("Invalid address field");
		}

		if (!phoneIsValid(customerForm.getPhone())){
			throw new ApiException.InvalidParameter("Invalid phone field");
		}

		String email = customerForm.getEmail();

		if (email == null || email.equals("") || email.contains(" ")
				|| !email.contains("@") || email.endsWith(".")){
			throw new ApiException.InvalidParameter("Invalid email field: ");
		}

		String ccNumber = customerForm.getCcNumber();
		if (!ccNumberIsValid(ccNumber)){
			throw new ApiException.InvalidParameter("Invalid card number field");
		}

		if (expiryDateIsInvalid(customerForm.getCcExpiryMonth(), customerForm.getCcExpiryYear())) {
			throw new ApiException.InvalidParameter("Invalid expiry date");
		}
	}

	private boolean ccNumberIsValid(String ccNumber){
		if (ccNumber == null) return false;
		if (ccNumber.equals("")) return false;

		ccNumber = ccNumber.replaceAll(" ", "").replaceAll("-", "");
		if (ccNumber.length() > 16 || ccNumber.length() < 14) return false;

		return true;
	}

	private int generateConfirmationNumber(){
		Random random = new Random();
		return random.nextInt(999999999);
	}

	private boolean phoneIsValid(String phone){
		if (phone == null) return false;
		if (phone.equals("")) return false;

		phone = phone.replaceAll(" ", "")
				.replaceAll("-","")
				.replaceAll("\\(", "")
				.replaceAll("\\)", "");

		if (!phone.matches("[\\d]+")) return false;
		if (phone.length() != 10) return false;

		return true;
	}

	private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {

		Integer month =  Integer.parseInt(ccExpiryMonth);
		Integer year = Integer.parseInt(ccExpiryYear);
		if (year < 2021) return true;
		if (year == 2021 && month < 10) return true;

		return false;

	}

	private void validateCart(ShoppingCart cart) {

		if (cart.getItems().size() <= 0) {
			throw new ApiException.InvalidParameter("Cart is empty.");
		}

		cart.getItems().forEach(item-> {
			if (item.getQuantity() < 0 || item.getQuantity() > 99) {
				throw new ApiException.InvalidParameter("Invalid quantity");
			}
			Book databaseBook = bookDao.findByBookId(item.getBookId());
			if (item.getBookForm().getPrice() != databaseBook.getPrice()){
				throw new ApiException.InvalidParameter("Invalid price");
			}
			if (item.getBookForm().getCategoryId() != databaseBook.getCategoryId()){
				throw new ApiException.InvalidParameter("Invalid category");
			}

		});
	}


}
