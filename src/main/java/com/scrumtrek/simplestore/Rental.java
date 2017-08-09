package com.scrumtrek.simplestore;

public class Rental {
	private Movie m_Movie;
	private int m_DaysRented;

	public Rental(Movie movie, int daysRented) {
		m_Movie = movie;
		m_DaysRented = daysRented;
	}

	public int getDaysRented() {
		return m_DaysRented;
	}

	public Movie getMovie() {
		return m_Movie;
	}
	public double getRegularAmount(int daysRented)
	{
		double thisAmount = 2;
		if (daysRented > 2)
		{
			thisAmount += (daysRented - 2) * 1.5;
		}
		return thisAmount;
	}

	public double getXXXAmount(int daysRented)
	{
		return getRegularAmount(daysRented) * 0.95;
	}

	public double getChildrenAmount(int daysRented)
	{
		double thisAmount = 1.5;
		if (daysRented > 3)
		{
			thisAmount += (daysRented - 3) * 1.5;
		}
		return thisAmount;
	}
}
