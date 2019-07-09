/*
 * Wizard.h
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#ifndef WIZARD_H_
#define WIZARD_H_

#include <string>
#include "Creature.h"
using namespace std;

class Wizard : public Creature {

public:
	Wizard();
	~Wizard();
	bool attack(Creature& opCreature);

};


#endif /* WIZARD_H_ */
