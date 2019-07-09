/*
 * Black_Dragon.cpp
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */


#include "Black_Dragon.h"

Black_Dragon::Black_Dragon() {

	this->attackPower = 9;
	this->defensePower = 10;
	this->price = 200;
	this->count = 0;
	this->totalAttack = count * attackPower;
	this->totalDefense = count * defensePower;
	this->name = "Black_Dragon";

}

Black_Dragon::~Black_Dragon() {

}

bool Black_Dragon::attack(Creature& opCreature) {

	if (opCreature.getName() == "Wizard") {

		if (count * attackPower - (opCreature.getDefensePower())*2 >= 0) {
			opCreature.setCount(0);
		}

		else {

			int deadCreaturesNum = (count * attackPower) / (opCreature.getDefensePower())*2;

			if (opCreature.getCount() - deadCreaturesNum < 0)
				opCreature.setCount(0);
			else
				opCreature.setCount(opCreature.getCount()-deadCreaturesNum);
		}
	}
	else
	{
		if (count * attackPower - opCreature.getDefensePower() >= 0) {
			opCreature.setCount(0);
		}

		else {

			int deadCreaturesNum = (count * attackPower) / opCreature.getDefensePower();

			if (opCreature.getCount() - deadCreaturesNum < 0)
				opCreature.setCount(0);
			else
				opCreature.setCount(opCreature.getCount()-deadCreaturesNum);
		}
	}

	return true;
}

