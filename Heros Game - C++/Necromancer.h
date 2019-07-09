/*
 * Nacromancer.h
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#ifndef NECROMANCER_H_
#define NECROMANCER_H_

#include <string>
#include "Hero.h"
using namespace std;

class Necromancer : public Hero {

public:
	Necromancer(string name):Hero(name, "Necromancer"){}
	~Necromancer();
	bool specialAbility();
	bool specialAbility(Hero& other);



};


#endif /* NECROMANCER_H_ */
