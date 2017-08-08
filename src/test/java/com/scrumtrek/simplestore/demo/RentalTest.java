package com.scrumtrek.simplestore.demo;

import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import com.scrumtrek.simplestore.Rental;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;

public class RentalTest {
    @Test
    public void shouldHaveMovieAndDaysRentedWhenCreated(){
        //region Fixture | Arrange | Given
        Movie dummy = new Movie("Terminator 2", PriceCodes.Regular);
        //endregion

        //region Act | When
        Rental uut = new Rental(dummy, 0);
        //endregion

        //region Assert | Then
        assertSame(dummy, uut.getMovie());
        assertEquals(dummy, uut.getMovie());
        assertEquals(0, uut.getDaysRented());
        //endregion
    }
}
