/*
 * Warrior.cpp
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#include "Warrior.h"



Warrior::~Warrior(){

	for (int i=0 ; i< 5; i++){
		delete creaturesList[i];
	}
	delete [] creaturesList;
}

bool Warrior::specialAbility() {

	if (this->goldAmount + 50 <= 2500)
		this->goldAmount = this->goldAmount + 50;
	else
		this->goldAmount = 2500;

	return true;

}


bool Warrior::specialAbility(Hero& other){
	return false;
}
