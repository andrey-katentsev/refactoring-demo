package com.scrumtrek.simplestore.demo;

import com.scrumtrek.simplestore.Rental;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

public class TDDTest {
    @Test
    public void shouldCalculateAmountWhenPriceCodeRegular(){
        //region Fixture | Arrange | Given
        Rental uut = new Rental(null, 0);
        //endregion

        //region Act | When
        double amount = uut.getRegularAmount(2);
        //endregion

        //region Assert | Then
        assertEquals(2.0, amount);
        //endregion
    }

    @Test
    public void shouldCalculateDiscountWhenPriceCodeXXX(){
        //region Fixture | Arrange | Given
        Rental uut = new Rental(null, 0);
        //endregion

        //region Act | When
        double amount = uut.getXXXAmount(2);
        //endregion

        //region Assert | Then
        assertEquals(uut.getRegularAmount(2) * 0.95, amount);
        //endregion
    }

    @Test
    public void shouldCalculateBonusWhenPriceCodeChildrenAnd3MoreDays(){
        //region Fixture | Arrange | Given
        Rental uut = new Rental(null, 0);
        //endregion

        //region Act | When
        double amount = uut.getChildrenAmount(4);
        //endregion

        //region Assert | Then
        assertEquals(3.0, amount);
        //endregion
    }
}
