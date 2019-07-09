/*
 * Thief.h
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#ifndef THIEF_H_
#define THIEF_H_

#include <string>
#include "Hero.h"
using namespace std;

class Thief : public Hero {

public:
	Thief(string name):Hero(name, "Thief"){}
	~Thief();
	bool specialAbility(Hero& other);
	bool specialAbility();

};


#endif /* THIEF_H_ */
