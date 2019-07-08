/*
 ============================================================================
 Name        : Countries.c
 Author      : MichalAdir
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Countries.h"

typedef struct Country_s {

	int citiesNumber;
	struct Area_s * area;
	char * countryName;
	struct City_s ** cities;
} Country_T;

typedef struct City_s {
	int residents;
	char * cityName;
	char * favoriteFood;
} City_T;

typedef struct Coordinate_s {
	int x;
	int y;
} Coordinate_T;

typedef struct Area_s {
	Coordinate point1;
	Coordinate point2;
} Area_T;


Country addCountry(char* countryName, Area area) {

	if (countryName == NULL)
		return NULL;

	if (area ==  NULL)
		return NULL;

	Country country = (Country)malloc(sizeof(struct Country_s));

	if (country == NULL)
		return NULL;

	country->citiesNumber = 0;

	country->countryName = (char*)malloc(sizeof(strlen(countryName)+1));

	if (country->countryName == NULL) {
		free(country);
		return NULL;
	}

	strcpy(country->countryName, countryName);

	country->area = (Area)malloc(sizeof(struct Area_s));

	if (country->area == NULL) {
		free (countryName);
		free(country->cities);
		free(country);
		return NULL;
	}

	country->area->point1 = (Coordinate)malloc(8);

	if (country->area->point1 == NULL) {
		free (countryName);
		free(country->cities);
		free(country->area);
		free(country);
		return NULL;
	}

	country->area->point2 = (Coordinate)malloc(8);

	if (country->area->point2 == NULL) {
		free (countryName);
		free(country->cities);
		free(country->area->point1);
		free(country->area);
		free(country);
		return NULL;
	}

	country->area->point1->x = area->point1->x;
	country->area->point1->y = area->point1->y;
	country->area->point2->x = area->point2->x;
	country->area->point2->y = area->point2->y;

	return country;
}


status addCity(Country country, City city) {

	if (country == NULL)
		return failure;

	if (city == NULL)
		return failure;

	for (int i = 0; i < country->citiesNumber; i++)
		if(strcmp(country->cities[i]->cityName, city->cityName) == 0)
			return failure;

	//create new city with city details

	City newCity = (City)malloc(sizeof(struct City_s));

	if (newCity == NULL)
		return failure;

	newCity->cityName = (char*)malloc((strlen(city->cityName)+1)*sizeof(char));

	if (newCity->cityName == NULL) {
		free(newCity);
		return failure;
	}

	strcpy(newCity->cityName, city->cityName);
	newCity->favoriteFood = (char*)malloc((strlen(city->favoriteFood)+1)*sizeof(char));

	if (newCity->favoriteFood == NULL) {
		free(newCity->cityName);
		free(newCity);
		return failure;
	}

	strcpy(newCity->favoriteFood, city->favoriteFood);
	newCity->residents=city->residents;


	if (country->citiesNumber == 0) {

		country->cities = (City*)malloc(sizeof(City));
		if (country->cities==NULL)
			return failure;

	}
	else
	{
		country->cities = (City*)realloc(country->cities, sizeof(City)*(country->citiesNumber+1));
		if (country->cities==NULL)
			return failure;
	}

	if (country->cities == NULL) {
		free(newCity->cityName);
		free(newCity->favoriteFood);
		free(newCity);
		return failure;
	}


	country->cities[country->citiesNumber] = newCity;
	country->citiesNumber++;
	return success;

}


status deleteCity(Country country, char* cityName) {

	if (country == NULL)
		return failure;

	if (cityName == NULL)
		return failure;

	for (int i = 0; i < country->citiesNumber; i++) {

		if(strcmp(country->cities[i]->cityName, cityName) == 0)
		{

			free(country->cities[i]->cityName);
			free(country->cities[i]->favoriteFood);

			if (i==0){

				//replace the locations of cities in the array
				City tmpCity = country->cities[0];

				for (int j=i;j<country->citiesNumber-1;j++){
					country->cities[j] = country->cities[j+1];
				}

				free(tmpCity);
				//free after replace to save the first pointer in the array

			}
			else{
				//free the pointer and reduce the space
				free(country->cities[i]);
				for (int j=i;j<country->citiesNumber-1;j++){
					country->cities[j] = country->cities[j+1];
				}
			}


			country->citiesNumber--;


			if (country->citiesNumber == 0)
				free(country->cities);
			else
				country->cities = (City*)realloc(country->cities, sizeof(City)*(country->citiesNumber));

			if (country->cities == NULL)
				return failure;

			return success;
		}
	}

	return failure;

}


bool isCordInArea(Coordinate coordinate, Country country) {

	if ((coordinate->x >= country->area->point1->x) && (coordinate->x <= country->area->point2->x))
		if ((coordinate->y >= country->area->point2->y) && (coordinate->y <= country->area->point1->y))
			return true;

	return false;
}


status freeCountry(Country country) {

	if (country == NULL)
		return failure;

	//free cities


	for (int i=country->citiesNumber-1;i>=0;i--)
		deleteCity(country,country->cities[i]->cityName);

	free(country->countryName);
	free(country->area->point1);
	free(country->area->point2);
	free(country->area);
	free(country);

	return success;
}



Country deepCopy(Country country) {

	Country newCountry = addCountry(country->countryName,country->area);

	newCountry->citiesNumber=country->citiesNumber;

	if (country->citiesNumber != 0) {
		newCountry->cities = (City*)malloc((sizeof(City)*(newCountry->citiesNumber)));

		if (newCountry->cities == NULL) {
			freeCountry(newCountry);
			return NULL;
		}
	}

	//create city struct for each city in country
	for (int i=0;i<country->citiesNumber ; i++){

		City newCity = createCity(country->cities[i]->cityName,country->cities[i]->favoriteFood,country->cities[i]->residents);

		newCountry->cities[i]=newCity;
	}

	return newCountry;
}


status printCountry(Country country) {

	if (country == NULL)
		return failure;

	printf("Country %s coordinates: <%d,%d>,<%d,%d>\n", country->countryName , country->area->point1->x,country->area->point1->y,country->area->point2->x,country->area->point2->y);

	for (int i=0 ; i<country->citiesNumber;i++){
		printf("\t%s includes %d residents and their favorite food is %s.\n",country->cities[i]->cityName,country->cities[i]->residents,country->cities[i]->favoriteFood);
	}
	return success;
}

Coordinate createCoord(int x, int y) {

	Coordinate point = (Coordinate)malloc(sizeof(struct Coordinate_s));

	if (point == NULL)
		return NULL;

	point->x = x;
	point->y = y;

	return point;

}

City createCity(char* cityName, char* favFood, int residents) {

	City city = (City)malloc(sizeof(struct City_s));

	if (city == NULL)
		return NULL;

	city->residents = residents;

	city->cityName = (char*)malloc(strlen(cityName)+1);
	if (city->cityName == NULL) {
		free (city);
		return NULL;
	}

	strcpy(city->cityName, cityName);

	city->favoriteFood = (char*)malloc(strlen(favFood)+1);

	if (city->favoriteFood == NULL) {
		free(city->cityName);
		free(city);
		return NULL;

	}
	strcpy(city->favoriteFood, favFood);

	return city;

}

Area createArea(Coordinate point1, Coordinate point2) {

	if (point1 == NULL)
		return NULL;

	if (point2 == NULL)
		return NULL;

	Area area = (Area)malloc(sizeof(struct Area_s));

	if (area == NULL)
		return NULL;

	area->point1 = createCoord(point1->x, point1->y);

	if (area->point1 == NULL)
		return NULL;

	area->point2 = createCoord(point2->x, point2->y);

	if (area->point2 == NULL) {
		free(point1);
		return NULL;
	}

	return area;

}

bool isCountryExist(Country countries[], char* countryName, int maxCountries, int* countryIndex) {

	for (int i = 0; i < maxCountries; i++, (*countryIndex)++)
		if (strcmp(countries[i]->countryName, countryName) == 0)
			return true;

	return false;

}

bool isCityExist(Country country, char* cityName) {

	for (int i = 0; i < country->citiesNumber; i++)
		if (strcmp(country->cities[i]->cityName, cityName) == 0)
			return true;

	return false;

}

char* getCountryName(Country country) {

	return country->countryName;

}

status freeCity(City city) {

	if (city == NULL)
		return failure;

	free(city->cityName);
	free(city->favoriteFood);
	free(city);

	return success;

}

status freeArea(Area area) {

	if (area == NULL)
		return failure;

	free(area->point1);
	free(area->point2);
	free(area);

	return success;

}
