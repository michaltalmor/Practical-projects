/*
 * main.c
 *
 *  Created on: Nov 29, 2018
 *      Author: ise
 */

#include "keyValuePair.h"
#include "LinkedList.h"
#include "Defs.h"
#include "Countries.h"
#include "HashTable.h"

Element copyKey(Element elem)
{
	if (elem==NULL)
		return NULL;
	char* key = (char*)malloc(strlen((char*)elem)+1);
	strcpy(key, (char*)elem);
	return key;

}

status freeKey(Element elem)
{
	free((char*)elem);
	return success;
}

bool equalKeys(Element elem1, Element elem2){
	if (strcmp((char*)elem1, (char*)elem2) == 0)
		return true;
	return false;
}

status printKey(Element elem){
	printf("%s\n",(char*)elem);
	return success;

}


Element copyValue(Element elem)
{

	return deepCopy((Country)elem);

}

status freeValue(Element elem)
{
	if (freeCountry((Country)elem) == success)
		return success;

	return failure;
}


status printVal(Element elem){

	printCountry((Country)elem);
	return success;

}

int keyToInt(Element elem){

	int key = 0;
	char* name = (char*)elem;
	for (int i = 0; i < strlen(name); i++)
		key = key + name[i];

	return key;
}

int main(int argc, char* argv[]) {

	int maxCountries = atoi(argv[2]);
	Country countries[maxCountries];
	FILE *file;

	char countryName[300];
	char cityName[300];
	char favFood[300];
	int population;

	char x1Char[300];
	char x2Char[300];
	char y1Char[300];
	char y2Char[300];
	char populationChar[300];
	int x1;
	int x2;
	int y1;
	int y2;
	int n; // index of the current character in a word
	int c; // a character
	int currentCountry = 0;
	bool maxCountriesFlag = false;
	bool keepRunning = true;

	if (argc > 3)
		file = fopen(argv[3], "r");


	while ((keepRunning == true) && (c = fgetc(file)) != EOF) {
		if (maxCountriesFlag == true)
			break;

		while (c != '\n') {
			if (maxCountriesFlag == true)
				break;
			n = 0;
			strcpy(countryName, "");

			// is Country
			if (c != '\t') {

				if (currentCountry == maxCountries)
					maxCountriesFlag = true;

				if (maxCountriesFlag == true)
					break;

				// reading countryName
				while (c != ',') {
					countryName[n] = c;
					c = fgetc(file);
					n++;
				}
				countryName[n] = '\0';

				c = fgetc(file);
				n = 0;

				// reading x1
				while (c != ',') {
					x1Char[n] = c;
					c = fgetc(file);
					n++;
				}
				x1Char[n] = '\0';
				x1 = atoi(x1Char);

				c = fgetc(file);
				n = 0;

				// reading y1
				while (c != ',') {
					y1Char[n] = c;
					c = fgetc(file);
					n++;
				}
				y1Char[n] = '\0';
				y1 = atoi(y1Char);

				c = fgetc(file);
				n = 0;

				// reading x2
				while (c != ',') {
					x2Char[n] = c;
					c = fgetc(file);
					n++;
				}
				x2Char[n] = '\0';
				x2 = atoi(x2Char);

				c = fgetc(file);
				n = 0;

				// reading y2
				while (c != '\n') {
					y2Char[n] = c;
					c = fgetc(file);
					n++;
				}
				y2Char[n] = '\0';
				y2 = atoi(y2Char);

				Coordinate point1 = createCoord(x1, y1);

				if (point1 == NULL)
					keepRunning = false;

				Coordinate point2 = createCoord(x2, y2);

				if (point2 == NULL) {
					free(point1);
					point1 = NULL;
					keepRunning = false;
				}

				Area area = createArea(point1, point2);

				if (area == NULL) {
					free(point1);
					point1 = NULL;
					free(point2);
					point2 = NULL;
					keepRunning = false;
				}

				countries[currentCountry] = addCountry(countryName, area);

				if (countries[currentCountry] == NULL) {
					free(point1);
					point1 = NULL;
					free(point2);
					point2 = NULL;
					freeArea(area);
					area = NULL;
					keepRunning = false;
				}
				currentCountry++;

				free(point1);
				point1 = NULL;
				free(point2);
				point2 = NULL;
				freeArea(area);
				area = NULL;

			}

			// is City
			else
			{
				// Getting the next character after tab
				c = fgetc(file);

				// reading cityName
				while (c != ',') {
					cityName[n] = c;
					c = fgetc(file);
					n++;
				}
				cityName[n] = '\0';

				c = fgetc(file);
				n = 0;

				// reading favFood
				while (c != ',') {
					favFood[n] = c;
					c = fgetc(file);
					n++;
				}
				favFood[n] = '\0';

				c = fgetc(file);
				n = 0;

				// reading population
				while (c != '\n') {
					populationChar[n] = c;
					c = fgetc(file);
					n++;
				}
				populationChar[n] = '\0';
				population = atoi(populationChar);

				City tmpCity = createCity(cityName, favFood, population);

				if (tmpCity == NULL)
					keepRunning = false;

				if (keepRunning == true && addCity(countries[currentCountry-1], tmpCity) == failure) {
					freeCity(tmpCity);
					tmpCity = NULL;
					keepRunning = false;
				}

				freeCity(tmpCity);
				tmpCity = NULL;

			}

		}

	}

	fclose(file);

	int hashSize= atoi(argv[1]);

	hashTable table = createHashTable(copyKey, freeKey, printKey,copyValue, freeValue, printVal, equalKeys, keyToInt, hashSize);

	if (table == NULL)
		keepRunning = false;

	for (int i = 0 ;i < maxCountries; i ++)
		if (addToHashTable(table, (Element)getCountryName(countries[i]), (Element)countries[i]) == failure)
			keepRunning = false;

	int choice = 0;



	while (choice != 8)

	{
		if (keepRunning == false)
			choice = 8;

		else {

			printf("%s", "please choose one of the following numbers:\n");
			printf("%s", "1 : print countries\n");
			printf("%s", "2 : add country\n");
			printf("%s", "3 : add city to country\n");
			printf("%s", "4 : delete city from country\n");
			printf("%s", "5 : print country by name\n");
			printf("%s", "6 : delete country\n");
			printf("%s", "7 : is country in area\n");
			printf("%s", "8 : exit\n");

			scanf("%d", &choice);
		}

		switch(choice) {

		case 1: // print countries from table

			if (keepRunning == true)
				displayHashElements(table);

			break;

		case 2: // add country
			printf("%s", "please enter a country name\n");
			char countryName[300];
			scanf("%s", countryName);
			bool countryFound;

			if (lookupInHashTable(table, countryName) == NULL)
				countryFound=false;
			else
				countryFound=true;

			if (countryFound == true) {
				printf("%s", "country with this name already exist\n");
			}
			else {

				printf("%s", "please enter two x and y coordinations:x1,y1,x2,y2\n");
				char cordsStr[300];
				char x1Char[300];
				char y1Char[300];
				char x2Char[300];
				char y2Char[300];

				scanf("%s", cordsStr);

				int x1 = 0;
				int y1 = 0;
				int x2 = 0;
				int y2 = 0;
				int i = 0; // index for strings
				int j = 0; // index for strings
				int q = 0; // index for strings
				int p = 0; // index for strings

				int len = strlen(cordsStr);

				while (i < len && cordsStr[i] != ',')
				{
					x1Char[i] = cordsStr[i];
					i++;
				}
				x1Char[i] = '\0';

				i++;

				while (i < len && cordsStr[q] != ',')
				{
					y1Char[q] = cordsStr[i];
					i++;
					q++;
				}
				y1Char[q] = '\0';

				i++;
				while (i < len && cordsStr[p] != ',')
				{
					x2Char[p] = cordsStr[i];
					i++;
					p++;
				}
				x2Char[p] = '\0';

				i++;
				while (i < len)
				{
					y2Char[j] = cordsStr[i];
					i++;
					j++;
				}
				y2Char[j] = '\0';

				x1 = atoi(x1Char);
				y1 = atoi(y1Char);
				x2 = atoi(x2Char);
				y2 = atoi(y2Char);

				Coordinate tmpCoord1 = createCoord(x1,y1);

				if (tmpCoord1 == NULL)
					keepRunning = false;

				Coordinate tmpCoord2 = createCoord(x2,y2);

				if (tmpCoord2 == NULL) {
					free(tmpCoord1);
					tmpCoord1 = NULL;
					keepRunning = false;
				}

				Area area = createArea(tmpCoord1, tmpCoord2);

				if (area == NULL) {
					free(tmpCoord1);
					tmpCoord1 = NULL;
					free(tmpCoord2);
					tmpCoord2 = NULL;
					keepRunning = false;
				}

				Country newCountry = addCountry(countryName, area);

				if (newCountry == NULL) {
					free(tmpCoord1);
					tmpCoord1 = NULL;
					free(tmpCoord2);
					tmpCoord2 = NULL;
					freeArea(area);
					area = NULL;
					keepRunning = false;
				}

				if (addToHashTable(table, getCountryName(newCountry), newCountry) == failure) {
					free(tmpCoord1);
					tmpCoord1 = NULL;
					free(tmpCoord2);
					tmpCoord2 = NULL;
					freeArea(area);
					area = NULL;
					freeCountry(newCountry);
					newCountry = NULL;
					keepRunning = false;
				}

				free(tmpCoord1);
				tmpCoord1 = NULL;
				free(tmpCoord2);
				tmpCoord2 = NULL;
				freeArea(area);
				area = NULL;
				freeCountry(newCountry);
				newCountry = NULL;

			}

			if (keepRunning == false)
				choice = 8;

			break;

		case 3: // add city to country
			printf("%s", "please enter a country name\n");

			scanf("%s", countryName);


			if (lookupInHashTable(table, countryName)==NULL)
				countryFound=false;
			else
				countryFound=true;

			if (countryFound == false) {

				printf("%s", "country not exist\n");

			} else {

				printf("%s", "please enter a city name\n");
				char cityName[300];
				scanf("%s", cityName);
				bool cityFound = isCityExist(lookupInHashTable(table, countryName),cityName);

				if (cityFound == true)
					printf("%s", "this city already exist in this country\n");

				else {
					int population;
					char favFood[300];

					printf("%s", "please enter the city favorite food\n");
					scanf("%s", favFood);

					printf("%s", "please enter number of residents in the city\n");
					scanf("%d", &population);

					City tmpCity = createCity(cityName, favFood, population);

					if (tmpCity == NULL)
						keepRunning = false;

					if (keepRunning == true && 	addCity(lookupInHashTable(table, countryName), tmpCity) == failure) {
						freeCity(tmpCity);
						tmpCity = NULL;
						keepRunning = false;
					}

					freeCity(tmpCity);
					tmpCity = NULL;

				}
			}

			if (keepRunning == false)
				choice = 8;

			break;

		case 4: // delete city from country

			printf("%s", "please enter a country name\n");
			scanf("%s", countryName);

			if (lookupInHashTable(table, countryName)==NULL)
				countryFound=false;
			else
				countryFound=true;

			if (countryFound == false) {

				printf("%s", "country name not exist\n");

			} else {

				printf("%s", "please enter a city name\n");

				scanf("%s", cityName);
				bool cityFound = isCityExist(lookupInHashTable(table, countryName),cityName);
				if (cityFound == false)
					printf("%s", "the city not exist in this country\n");
				else
					deleteCity(lookupInHashTable(table, countryName), cityName);

			}

			break;

		case 5: //print country by name

			printf("%s", "please enter a country name\n");
			scanf("%s", countryName);
			if (lookupInHashTable(table, countryName)==NULL)
				countryFound=false;
			else
				countryFound=true;

			if (countryFound == false)
				printf("%s", "country name not exist\n");
			else
				printCountry(lookupInHashTable(table, countryName));

			break;

		case 6: //delete country
			printf("%s", "please enter a country name\n");
			scanf("%s", countryName);
			if (lookupInHashTable(table, countryName)==NULL)
				countryFound=false;
			else
				countryFound=true;

			if (countryFound == false)

				printf("%s", "can't delete the country\n");

			else {

				removeFromHashTable(table, countryName);
				printf("%s", "country deleted\n");

			}

			break;

		case 7://is country in area
			printf("%s", "please enter a country name\n");
			scanf("%s", countryName);
			if (lookupInHashTable(table, countryName)==NULL)
				countryFound=false;
			else
				countryFound=true;

			if (countryFound == false) {

				printf("%s", "country name not exist\n");

			} else {
				printf("%s", "please enter x and y coordinations:x,y\n");
				char cordsStr[300];
				char xChar[300];
				char yChar[300];

				scanf("%s", cordsStr);

				int x = 0;
				int y = 0;
				int i = 0; // index for strings
				int j = 0; // index for strings

				int len = strlen(cordsStr);

				while (i < len && cordsStr[i] != ',')
				{
					xChar[i] = cordsStr[i];
					i++;
				}
				xChar[i] = '\0';

				i++;

				while (i < len)
				{
					yChar[j] = cordsStr[i];
					i++;
					j++;
				}
				yChar[j] = '\0';

				x = atoi(xChar);
				y = atoi(yChar);


				Coordinate tmpCoord = createCoord(x,y);

				if (tmpCoord == NULL)
					keepRunning = false;

				if (isCordInArea(tmpCoord, lookupInHashTable(table, countryName)) == true){
					printf ("the coordinate in the country\n");
				}
				else
					printf("the coordinate not in the country\n");

				free(tmpCoord);
				tmpCoord=NULL;
			}

			if (keepRunning == false)
				choice = 8;

			break;

		case 8: // exit

			for (int i = 1; i < currentCountry; i++)
				freeCountry(countries[i]);

			freeCountry(countries[0]);

			destroyHashTable(table);
			free(table);
			table=NULL;

			break;

		default:

			printf("%s", "please choose a valid number\n");

			break;

		}
	}
	return 0;
}
