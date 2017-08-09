package com.scrumtrek.simplestore.demo;

import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.Rental;
import org.junit.Ignore;
import org.junit.Test;

import static com.scrumtrek.simplestore.PriceCodes.Childrens;
import static com.scrumtrek.simplestore.PriceCodes.NewRelease;
import static com.scrumtrek.simplestore.PriceCodes.Regular;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerTest {
    @Test
    public void shouldHaveNameWhenCreated(){
        //region Fixture | Arrange | Given
        //endregion

        //region Act | When
        Customer uut = new Customer("John");
        //endregion

        //region Assert | Then
        assertEquals("John", uut.getName());
        //endregion
    }

    @Test
    public void shouldGenerateStatementWhenSingleMovieOneDayRental(){
        //region Fixture | Arrange | Given
        Customer uut = new Customer("John");
        Movie kingKong = new Movie("King Kong", Regular);
        Rental kingKongRental = new Rental( kingKong, 1);

        uut.addRental(kingKongRental);
        //endregion

        //region Act | When
        String statement = uut.Statement();
        //endregion

        String expected = "Rental record for John\n" +
                "\tKing Kong\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1.0 frequent renter points.";

        //region Assert | Then
        assertEquals(expected, statement);
        //endregion
    }

    @Test
    public void shouldGenerateStatementWhenSingleNewMovieOneDayRental(){
        //region Fixture | Arrange | Given
        Customer uut = new Customer("John");
        Movie movie = new Movie("Spider Man", NewRelease);
        Rental rental = new Rental( movie, 1);
        uut.addRental(rental);
        //endregion

        //region Act | When
        String statement = uut.Statement();
        //endregion

        String expected = "Rental record for John\n" +
                "\tSpider Man\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1.0 frequent renter points.";

        //region Assert | Then
        assertEquals(expected, statement);
        //endregion
    }

    @Test
    public void shouldGenerateStatementWhenSingleMovieTwoDayRental(){
        //region Fixture | Arrange | Given
        Customer uut = new Customer("John");
        //Movie kingKong = new Movie("King Kong", Regular);
        Movie stub = mock(Movie.class);
        when(stub.getPriceCode()).thenReturn(NewRelease);
        when(stub.getTitle()).thenReturn("Mock Movie");

        Rental stubRental = new Rental( stub, 2);

        uut.addRental(stubRental);
        //endregion

        //region Act | When
        String statement = uut.Statement();
        //endregion

        String expected = "Rental record for John\n" +
                "\tMock Movie\t6.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 2.0 frequent renter points.";

        //region Assert | Then
        assertEquals(expected, statement);
        //endregion
    }

    @Test
    public void shouldGenerateStatementWhenCoupleMovieMultiDayRental(){
        //region Fixture | Arrange | Given
        Customer uut = new Customer("John");

        Movie stubA = mock(Movie.class);
        when(stubA.getPriceCode()).thenReturn(NewRelease);
        when(stubA.getTitle()).thenReturn("Rock Movie");

        Rental stubARental = new Rental( stubA, 2);

        uut.addRental(stubARental);

        Movie stubB = mock(Movie.class);
        when(stubB.getPriceCode()).thenReturn(Childrens);
        when(stubB.getTitle()).thenReturn("Mock Movie");

        Rental stubBRental = new Rental( stubB, 3);

        uut.addRental(stubBRental);
        //endregion

        //region Act | When
        String statement = uut.Statement();
        //endregion

        String expected = "Rental record for John\n" +
                "\tRock Movie\t6.0\n" +
                "\tMock Movie\t1.5\n" +
                "Amount owed is 7.5\n" +
                "You earned 3.0 frequent renter points.";

        //region Assert | Then
        assertEquals(expected, statement);

        verify(stubA, times(2)).getPriceCode();
        verify(stubB, times(1)).getTitle();
        //endregion
    }

    @Test
    public void shouldGenerateBonusStatementWhenRegularAndChildrenMovieMultiDayRental(){
        //region Fixture | Arrange | Given
        Customer uut = new Customer("John");

        Movie stubA = mock(Movie.class);
        when(stubA.getPriceCode()).thenReturn(Regular);
        when(stubA.getTitle()).thenReturn("Rock Movie");

        Rental stubARental = new Rental( stubA, 5);

        uut.addRental(stubARental);

        Movie stubB = mock(Movie.class);
        when(stubB.getPriceCode()).thenReturn(Childrens);
        when(stubB.getTitle()).thenReturn("Mock Movie");

        Rental stubBRental = new Rental( stubB, 7);

        uut.addRental(stubBRental);
        //endregion

        //region Act | When
        String statement = uut.Statement();
        //endregion

        String expected = "Rental record for John\n" +
                "\tRock Movie\t6.5\n" +
                "\tMock Movie\t7.5\n" +
                "Amount owed is 14.0\n" +
                "You earned 2.0 frequent renter points.";

        //region Assert | Then
        assertEquals(expected, statement);
        //endregion
    }
}
