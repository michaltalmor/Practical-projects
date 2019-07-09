/*
 * Hero.cpp
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#include "Hero.h"


Hero::Hero(string name, string type) {

	this->name = name;
	this->type = type;
	this->goldAmount = 750;
	this->creaturesList = new Creature*[5];
	creaturesList[0] = new Zombie();
	creaturesList[1] = new Vampire();
	creaturesList[2] = new Archer();
	creaturesList[3] = new Wizard();
	creaturesList[4] = new Black_Dragon();

}

Hero::~Hero() {

}

bool Hero::buyCreatures(string type, int amount) {

	bool isValid=false;

	if (type == "Zombie")
	{

			try{
			if (amount * creaturesList[0]->getPrice() <= goldAmount) {
				creaturesList[0]->addCount(amount);
				goldAmount = goldAmount - (amount * creaturesList[0]->getPrice());
				isValid = true;
			}
			else
			{
				throw exception();
			}
				}
			catch (exception &e){
				cout << "You don't have enough money" << endl;
				return false;
			}

	}
	else if (type == "Vampire")
	{
		isValid=false;

			try {



				if (amount * creaturesList[1]->getPrice() <= goldAmount) {
					creaturesList[1]->addCount(amount);
					goldAmount = goldAmount - (amount * creaturesList[1]->getPrice());
					isValid=true;
				}
				else
				{
					throw exception();
				}
			}
			catch (exception &e){
				cout << "You don't have enough money" << endl;
				return false;
			}


	}
	else if (type == "Archer")
	{
		isValid=false;

			try {



				if (amount * creaturesList[2]->getPrice() <= goldAmount) {
					creaturesList[2]->addCount(amount);
					goldAmount = goldAmount - (amount * creaturesList[2]->getPrice());
					isValid=true;
				}
				else
				{
					throw exception();
				}
			}
			catch (exception &e){
				cout << "You don't have enough money" << endl;
				return false;
			}

	}
	else if (type == "Wizard")
	{
		isValid=false;

			try {



				if (amount * creaturesList[3]->getPrice() <= goldAmount) {
					creaturesList[3]->addCount(amount);
					goldAmount = goldAmount - (amount * creaturesList[3]->getPrice());
					isValid=true;
				}
				else
				{
					throw exception();
				}
			}
			catch (exception &e){
				cout << "You don't have enough money" << endl;
				return false;
			}

	}
	else
	{
		isValid=false;

			try {



				if (amount * creaturesList[4]->getPrice() <= goldAmount) {
					creaturesList[4]->addCount(amount);
					goldAmount = goldAmount - (amount * creaturesList[4]->getPrice());
					isValid=true;
				}
				else
				{
					throw exception();
				}
			}
			catch (exception &e){
				cout << "You don't have enough money" << endl;
				return false;
			}

	}

	return true;

}

bool Hero::getDailyGold() {

	if (this->goldAmount + 100 <= 2500)
		this->goldAmount = this->goldAmount + 100;
	else
		this->goldAmount = 2500;

	return true;

}

bool Hero::attack(Hero& other, string myCreature, string opCreature) {

	bool succeed = false;



	succeed = getCreatureByName(myCreature).attack(other.getCreatureByName(opCreature));

	int totalCreaturesOpponent = other.creaturesList[0]->getCount() + other.creaturesList[1]->getCount() + other.creaturesList[2]->getCount() + other.creaturesList[3]->getCount() + other.creaturesList[4]->getCount();

	if (totalCreaturesOpponent == 0) {
		if (this->goldAmount + other.goldAmount <= 2500){
			this->goldAmount = this->goldAmount + other.goldAmount;
		}
		else
			this->goldAmount = 2500;

	other.goldAmount=0;
	}

	return succeed;

}

void Hero::printDetails() {


	cout << this->name << " " << this->type << ":" << "\n"
	<< this->goldAmount << " gold" << endl;

	if (this->creaturesList[4]->getCount() > 0){
		cout << this->creaturesList[4]->getCount() << " Black_Dragon";
	if (this->creaturesList[3]->getCount() > 0 || this->creaturesList[2]->getCount() > 0 || this->creaturesList[1]->getCount() > 0 || this->creaturesList[0]->getCount() > 0)
		cout << " ";
	}

	if (this->creaturesList[3]->getCount() > 0){
		cout << this->creaturesList[3]->getCount() << " Wizards";
	if (this->creaturesList[2]->getCount() > 0 || this->creaturesList[1]->getCount() > 0 || this->creaturesList[0]->getCount() > 0)
		cout << " ";
	}

	if (this->creaturesList[2]->getCount() > 0){
		cout << this->creaturesList[2]->getCount() << " Archers";
	if (this->creaturesList[1]->getCount() > 0 || this->creaturesList[0]->getCount() > 0)
		cout << " ";
	}

	if (this->creaturesList[1]->getCount() > 0){
		cout << this->creaturesList[1]->getCount() << " Vampires";
	if (this->creaturesList[0]->getCount() > 0)
		cout << " ";
	}

	if (this->creaturesList[0]->getCount() > 0)
		cout << this->creaturesList[0]->getCount() << " Zombies";

	if (this->creaturesList[0]->getCount() + this->creaturesList[1]->getCount() + this->creaturesList[2]->getCount() + this->creaturesList[3]->getCount() + this->creaturesList[4]->getCount() > 0)
		cout << "." << endl;
}

void Hero::endTurn() {

}

Creature& Hero::getCreatureByName(string creatureName) {

	if (creatureName == "Zombie")
	{
		return *(creaturesList[0]);
	}
	else if (creatureName == "Vampire")
	{
		return *(creaturesList[1]);
	}
	else if (creatureName == "Archer")
	{
		return *(creaturesList[2]);
	}
	else if (creatureName == "Wizard")
	{
		return *(creaturesList[3]);
	}
	else if (creatureName == "Black_Dragon")
	{
		return *(creaturesList[4]);
	}
	else
		return *creaturesList[0];

}

string Hero::getName(){

	return this->name;

}
string Hero::getType(){

	return this->type;

}

int Hero::getTotalCreatures() {

	return (this->creaturesList[0]->getCount() + this->creaturesList[1]->getCount() + this->creaturesList[2]->getCount() + this->creaturesList[3]->getCount() + this->creaturesList[4]->getCount());

}
Creature* Hero::getCreaturesList(int c){
	if (c>=0 && c<5)
		return this->creaturesList[c];
	return NULL;
}

double Hero::getGoldAmount() {
	return this->goldAmount;
}

void Hero::setGoldAmount(double c) {
	if (c>=0)
		this->goldAmount = c;
}
