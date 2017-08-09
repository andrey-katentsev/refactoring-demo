package com.scrumtrek.simplestore;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String m_Name;
	private List<Rental> m_Rentals = new ArrayList<Rental>();

	public Customer(String name) {
		m_Name = name;
	}

	public String getName() {
		return m_Name;
	}

	public void addRental(Rental arg){
		m_Rentals.add(arg);
	}

	public String Statement()
	{
		// TODO: ближе к месту использованияя (scope) : 3
		double totalAmount = 0;
		double frequentRenterPoints = 0;

		// TODO: нужно ли менять формат вывода? : ?
		String result = "Rental record for " + m_Name + "\n";
		
		for(Rental each: m_Rentals) {
			double thisAmount = 0;

			// TODO: сгруппировать обработку по PriceCode : 2
			// Determine amounts for each line
			switch(each.getMovie().getPriceCode()) {
				case Regular:
					thisAmount += each.getRegularAmount(each.getDaysRented());
					break;
	
				case NewRelease:
					thisAmount += each.getDaysRented() * 3;
					break;
	
				case Childrens:
					thisAmount += each.getChildrenAmount(each.getDaysRented());
					break;
				case XXX:
					thisAmount += each.getXXXAmount(each.getDaysRented());
					frequentRenterPoints += 0.1;
					break;
			}

			// TODO: весь расчёт ренты должен быть отдельно (в одном классе - Rental?)
			// Add frequent renter points
			frequentRenterPoints++;

			// Add bonus for a two-day new-release rental
			if ((each.getMovie().getPriceCode() == PriceCodes.NewRelease) && (each.getDaysRented() > 1))
			{
				frequentRenterPoints ++;
			}

			// TODO: разделить расчёт и отчёт : 3
			// Будет UI, нужен API и тесты на него.
			// Show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}

		// Add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points.";
		return result;
	}
}
