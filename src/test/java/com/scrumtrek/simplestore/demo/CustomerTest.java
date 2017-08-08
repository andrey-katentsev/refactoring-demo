package com.scrumtrek.simplestore.demo;

import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.Rental;
import org.junit.Ignore;
import org.junit.Test;

import static com.scrumtrek.simplestore.PriceCodes.NewRelease;
import static com.scrumtrek.simplestore.PriceCodes.Regular;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
                "You earned 1 frequent renter points.";

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
                "You earned 2 frequent renter points.";

        //region Assert | Then
        assertEquals(expected, statement);
        //endregion
    }
}
