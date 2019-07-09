/*
 * Hero.h
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#ifndef HERO_H_
#define HERO_H_

#include <string>
#include <iostream>
#include "Creature.h"
using namespace std;
#include "Zombie.h"
#include "Black_Dragon.h"
#include "Wizard.h"
#include "Vampire.h"
#include "Archer.h"


class Hero {

protected:
	string name;
	string type;
	double goldAmount;
	Creature** creaturesList;


public:
	Hero(string name, string type);
	virtual ~Hero();
	bool buyCreatures(string type, int amount);
	bool getDailyGold();
	bool attack(Hero& other, string myCreature, string opCreature);
	void printDetails();
	virtual bool specialAbility()=0;
	virtual bool specialAbility(Hero& other)=0;
	void endTurn();
	Creature& getCreatureByName(string creatureName);
	string getName();
	string getType();
	int getTotalCreatures();
	Creature* getCreaturesList(int c);
	double getGoldAmount();
	void setGoldAmount(double c);

};


#endif /* HERO_H_ */
