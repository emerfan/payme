package com.payme.api.utils;

import com.payme.api.model.DateSearch;

public class SearchUtility {

	private SearchUtility() {
		throw new UnsupportedOperationException("Cannot instantiate SearchUtility class");
	}

	public static boolean IsFullSearch(DateSearch dateSearchParams) {
		return dateSearchParams.getPaidStatus() != null && dateSearchParams.getTradeEntityId() != null
				&& dateSearchParams.getDateFrom() != null && dateSearchParams.getDateTo() != null;
	}

	public static boolean IsSearchTimePeriodForStatus(DateSearch dateSearchParams) {
		return dateSearchParams.getPaidStatus() != null && dateSearchParams.getDateFrom() != null
				&& dateSearchParams.getDateTo() != null;
	}

	public static boolean IsSearchTimePeriodForTransaction(DateSearch dateSearchParams) {
		return dateSearchParams.getTradeEntityId() != null && dateSearchParams.getDateFrom() != null
				&& dateSearchParams.getDateTo() != null;
	}

	public static boolean IsSearchTimePeriod(DateSearch dateSearchParams) {
		return dateSearchParams.getDateFrom() != null && dateSearchParams.getDateTo() != null;
	}
}
