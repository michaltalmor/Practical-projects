/*
 * Warrior.h
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#ifndef WARRIOR_H_
#define WARRIOR_H_

#include <string>
#include "Hero.h"
using namespace std;

class Warrior : public Hero {
public:
	Warrior(string name):Hero(name, "Warrior"){}
	~Warrior();
	bool specialAbility();
	bool specialAbility(Hero& other);
};


#endif /* WARRIOR_H_ */
