import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerTest {

	static final Movie m_avatar   = new Movie("Avatar",         Movie.REGULAR);
	static final Movie m_avengers = new Movie("The Avengers",   Movie.NEW_RELEASE);
	static final Movie m_titanic  = new Movie("Titanic",        Movie.REGULAR);
	static final Movie m_starwars = new Movie("Star Wars",      Movie.REGULAR);
	static final Movie m_jurassic = new Movie("Jurassic World", Movie.NEW_RELEASE);
	static final Movie m_lionking = new Movie("The Lion King",  Movie.CHILDRENS);
	static final Movie m_frozen   = new Movie("Frozen",         Movie.CHILDRENS);
	static final Movie m_harry    = new Movie("Harry Potter",   Movie.CHILDRENS);

	@Test
	void testStatement_1regular_1day() {
		Customer customer = new Customer("Alice");
		customer.addRental(new Rental(m_avatar, 1));
		String result = customer.statement();
		String expected = "Rental Record for Alice\n"
				+ "\tAvatar\t2.0\n"
				+ "Amount owed is 2.0\n"
				+ "You earned 1 frequent renter points";
		assertEquals(expected, result);
	}

	@Test
	void testStatement_1regular_3days() {
		Customer customer = new Customer("Alice");
		customer.addRental(new Rental(m_avatar, 3));
		String result = customer.statement();
		String expected = "Rental Record for Alice\n"
				+ "\tAvatar\t3.5\n"
				+ "Amount owed is 3.5\n"
				+ "You earned 1 frequent renter points";
		assertEquals(expected, result);
	}

	@Test
	void testStatement_1childrens_1day() {
		Customer customer = new Customer("Bob");
		customer.addRental(new Rental(m_lionking, 1));
		String result = customer.statement();
		String expected = "Rental Record for Bob\n"
				+ "\tThe Lion King\t1.5\n"
				+ "Amount owed is 1.5\n"
				+ "You earned 1 frequent renter points";
		assertEquals(expected, result);
	}

	@Test
	void testStatement_1childrens_4days() {
		Customer customer = new Customer("Bob");
		customer.addRental(new Rental(m_lionking, 4));
		String result = customer.statement();
		String expected = "Rental Record for Bob\n"
				+ "\tThe Lion King\t3.0\n"
				+ "Amount owed is 3.0\n"
				+ "You earned 1 frequent renter points";
		assertEquals(expected, result);
	}

	@Test
	void testStatement_1new_1day() {
		Customer customer = new Customer("Kent");
		customer.addRental(new Rental(m_avengers, 1));
		String result = customer.statement();
		String expected = "Rental Record for Kent\n"
				+ "\tThe Avengers\t3.0\n"
				+ "Amount owed is 3.0\n"
				+ "You earned 1 frequent renter points";
		assertEquals(expected, result);
	}

	@Test
	void testStatement_1new_2days() {
		Customer customer = new Customer("Kent");
		customer.addRental(new Rental(m_avengers, 2));
		String result = customer.statement();
		String expected = "Rental Record for Kent\n"
				+ "\tThe Avengers\t6.0\n"
				+ "Amount owed is 6.0\n"
				+ "You earned 2 frequent renter points";
		assertEquals(expected, result);
	}

	@Test
	void testStatement_0movie() {
		Customer customer = new Customer("Alice");
		String result = customer.statement();
		String expected = "Rental Record for Alice\n"
				+ "Amount owed is 0.0\n"
				+ "You earned 0 frequent renter points";
		assertEquals(expected, result);
	}

	@Test
	void testStatement_4movies() {
		Customer customer = new Customer("Bob");
		customer.addRental(new Rental(m_titanic, 4));
		customer.addRental(new Rental(m_frozen, 3));
		customer.addRental(new Rental(m_starwars, 2));
		customer.addRental(new Rental(m_jurassic, 5));
		customer.addRental(new Rental(m_harry, 1));
		String result = customer.statement();
		String expected = "Rental Record for Bob\n"
				+ "\tTitanic\t5.0\n"
				+ "\tFrozen\t1.5\n"
				+ "\tStar Wars\t2.0\n"
				+ "\tJurassic World\t15.0\n"
				+ "\tHarry Potter\t1.5\n"
				+ "Amount owed is 25.0\n"
				+ "You earned 6 frequent renter points";
		assertEquals(expected, result);
	}

	@Test
	void testHtmlStatement_5movies() {
		Customer customer = new Customer("Bob");
		customer.addRental(new Rental(m_titanic, 4));
		customer.addRental(new Rental(m_frozen, 3));
		customer.addRental(new Rental(m_starwars, 2));
		customer.addRental(new Rental(m_jurassic, 5));
		customer.addRental(new Rental(m_harry, 1));
		String result = customer.htmlStatement();
		String expected = "<H1>Rentals for <EM>Bob</EM></H1><P>\n"
				+ "Titanic: 5.0<BR>\n"
				+ "Frozen: 1.5<BR>\n"
				+ "Star Wars: 2.0<BR>\n"
				+ "Jurassic World: 15.0<BR>\n"
				+ "Harry Potter: 1.5<BR>\n"
				+ "<P>You owe <EM>25.0</EM><P>\n"
				+ "On this rental you earned <EM>6</EM> frequent renter points<P>";
		assertEquals(expected, result);
	}

}
