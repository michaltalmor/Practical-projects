/*
 * Archer.cpp
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */


#include "Archer.h"

Archer::Archer() {

	this->attackPower = 5;
	this->defensePower = 4;
	this->price = 90;
	this->count = 0;
	this->totalAttack = count * attackPower;
	this->totalDefense = count * defensePower;
	this->name = "Archer";

}

Archer::~Archer() {

}

bool Archer::attack(Creature& opCreature) {

	if (opCreature.getName() == "Black_Dragon") {

		if ((count * attackPower*1.2) - opCreature.getTotalDefense() >= 0) {
			opCreature.setCount(0);
		}

		else {

			int deadCreaturesNum = (count * attackPower*1.2) / opCreature.getDefensePower();

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

