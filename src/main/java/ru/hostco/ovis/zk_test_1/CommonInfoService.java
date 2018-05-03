package ru.hostco.ovis.zk_test_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonInfoService {
	
	static List<String> countryList;
	static {
		countryList = new ArrayList<String>();
		countryList.add("Россия");
		countryList.add("Португалия");
		countryList.add("Франция");
		countryList.add("Испания");
		countryList.add("Бразилия");
		countryList.add("Германия");
		countryList.add("Нидерланды");
		
		countryList = Collections.unmodifiableList(countryList);
	}
	
	public static List<String> getCountryList() {
		return countryList;
	}

}
