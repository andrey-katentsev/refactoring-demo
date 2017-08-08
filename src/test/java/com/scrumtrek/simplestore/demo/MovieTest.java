package com.scrumtrek.simplestore.demo;

import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import org.junit.Test;

import static com.scrumtrek.simplestore.PriceCodes.Childrens;
import static com.scrumtrek.simplestore.PriceCodes.NewRelease;
import static com.scrumtrek.simplestore.PriceCodes.Regular;
import static junit.framework.TestCase.assertEquals;

public class MovieTest {
    @Test
    public void shouldHaveTitleAndPriceCodeWhenCreated(){
        //region Fixture | Arrange | Given
        //endregion

        //region Act | When
        Movie uut = new Movie("Back to the Future", Childrens);
        //endregion

        //region Assert | Then
        assertEquals("Back to the Future", uut.getTitle());
        assertEquals(Childrens, uut.getPriceCode());
        //endregion
    }

    @Test
    public void shouldBeAbleToChangePriceCode(){
        //region Fixture | Arrange | Given
        Movie uut = new Movie("Back to the Future II", NewRelease);
        //endregion

        //region Act | When
        uut.setPriceCode(Regular);
        //endregion

        //region Assert | Then
        assertEquals(Regular, uut.getPriceCode());
        //endregion
    }
}
