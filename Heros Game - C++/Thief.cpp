/*
 * Thief.cpp
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#include "Thief.h"

Thief::~Thief() {

	for (int i=0 ; i< 5; i++){
		delete creaturesList[i];
	}
	delete [] creaturesList;

}

bool Thief::specialAbility(Hero& other) {

	double amount = 70;

	if (other.getGoldAmount() < 70)
		amount = other.getGoldAmount();

	if (this->goldAmount + amount > 2500)
		amount = 2500 - this->goldAmount;

	this->goldAmount = this->goldAmount + amount;
	other.setGoldAmount(other.getGoldAmount() - amount);

	return true;

}
bool Thief::specialAbility(){
	return false;
}

