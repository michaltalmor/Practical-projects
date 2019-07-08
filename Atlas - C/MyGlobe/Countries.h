/*
 * Countries.h
 *
 *  Created on: Nov 16, 2018
 *      Author: ise
 */

#ifndef COUNTRIES_H_
#define COUNTRIES_H_


#include "Defs.h"


typedef struct Country_s* Country;
typedef struct City_s* City;
typedef struct Coordinate_s* Coordinate;
typedef struct Area_s* Area;

Country addCountry(char*, Area);
status addCity(Country, City);
status deleteCity(Country, char*);
bool isCordInArea(Coordinate, Country);
status freeCountry(Country);
Country deepCopy(Country);
status printCountry(Country);
status freeCity(City);
status freeArea(Area);

char* getCountryName(Country);						// Getter of the country's name

Coordinate createCoord(int, int); 					// Creating a coordinate

Area createArea(Coordinate, Coordinate); 			// Creating an area

City createCity(char*, char*, int); 				// Creating a city

bool isCityExist(Country, char*);					// Checking if city exists in a country

bool isCountryExist(Country [], char*, int, int*); 	// Checking if country exists, by country's name.
													// Puts in the forth parameter (int pointer) the index of the country.

#endif /* COUNTRIES_H_ */
